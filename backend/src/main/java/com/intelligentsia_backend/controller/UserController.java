package com.intelligentsia_backend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelligentsia_backend.entity.PasswordResetToken;
import com.intelligentsia_backend.entity.User;
import com.intelligentsia_backend.entity.VerificationToken;
import com.intelligentsia_backend.model.PasswordModel;
import com.intelligentsia_backend.model.UserModel;
import com.intelligentsia_backend.repository.PasswordResetTokenRepository;
import com.intelligentsia_backend.repository.UserRepository;
import com.intelligentsia_backend.security_config.event.RegistrationCompleteEvent;
import com.intelligentsia_backend.services.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender emailSender;
    private SimpleMailMessage message = new SimpleMailMessage();
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserModel userModel) {
        User user1 = userRepository.findByEmail(userModel.getEmail());
        if(user1 != null){
            publisher.publishEvent(new RegistrationCompleteEvent(user1));
            return user1;
        }

        User user= userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user));
        return user;
    }
    //Normalement je dois utiliser le patter strategie
    @GetMapping("/enable")
    public String enable(@RequestParam("email") String email){
        User user=userService.findUserByEmail(email);
        if(user!=null) {
            if (user.getRole().stream().anyMatch(role -> role.getName().equals("SOCIETY"))) {
                if (user.getEnabled())
                    return "SOCIETYtrue";
                return "SOCIETYfalse";
            }else if(user.getRole().stream().anyMatch(role -> role.getName().equals("STUDENT"))){
                if (user.getEnabled())
                    return "STUDENTtrue";
                return "STUDENTfalse";
            }
        }
        return "nonExite";
    }
    @GetMapping("/verifyRegistration")
    public boolean verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")) {
            return true;
        }
        return false;
    }
    @GetMapping("/resendVerifyToken")
    public boolean resendVerificationToken(@RequestParam("id") Long id) {
        VerificationToken verificationToken=userService.generateNewVerificationToken(id);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(verificationToken);
        return true;
    }


    @GetMapping("/resendResetPasswordVerifyToken")
    public boolean resendPasswordResetToken(@RequestParam("id") Long id) {
        PasswordResetToken passwordResetToken1=userService.findById(id);
        PasswordResetToken passwordResetToken=userService.generateNewPasswordResetToken(passwordResetToken1.getToken(),passwordResetToken1.getUser().getEmail());
        resendResetPasswordVerifyToken(passwordResetToken);
        return true;
    }
    @GetMapping("/resetPassword")
    public Long resetPassword(@RequestParam("email") String email) {
        User user = userService.findUserByEmail(email);
        Long url= 0L;
        if(user!=null) {
            String token = UUID.randomUUID().toString().substring(0,6);
            userService.createPasswordResetTokenForUser(user,token);
            url = passwordResetTokenMail(user, token);
        }
        return url;
    }

    @GetMapping("/verifyResetPassword")
    public boolean verifyResetPassword(@RequestParam("token") String token) {
        String result = userService.validatePasswordResetToken(token);
        if(result.equalsIgnoreCase("valid")) {
            return true;
        }
        return false;
    }

    @PostMapping("/savePassword")
    public boolean savePassword(@RequestBody PasswordModel passwordModel) {
        String result = userService.validatePasswordResetToken(passwordResetTokenRepository.findByUser_Id(passwordModel.getIdUser()).getToken());
        if(!result.equalsIgnoreCase("valid")) {
            return false;
        }
        Optional<User> user = userService.getUserByPasswordResetToken(passwordResetTokenRepository.findByUser_Id(passwordModel.getIdUser()).getToken());
        if(user.isPresent()) {
            userService.changePassword(user.get(), passwordModel.getNewPassword());
            return true;
        } else {
            return false;
        }

    }
   /* @PostMapping("/connexion")
    public User connexion(@RequestBody PasswordModel  model){
        String email = model.getEmail();
        String password = model.getNewPassword();
        User user = userService.connexion(email,password);
        return user;
    }*/

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if(!userService.checkIfValidOldPassword(user,passwordModel.getOldPassword())) {
            return "Invalid Old Password";
        }
        //Save New Password
        userService.changePassword(user,passwordModel.getNewPassword());
        return "Password Changed Successfully";
    }

    private Long passwordResetTokenMail(User user, String token) {

        //sendVerificationEmail()
        message.setFrom("malidoniso1@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Intelligentsia");
        message.setText("Click the link to Reset your Password: " + token);
        emailSender.send(message);

        // log.info("Click the link to Reset your Password: {}",token);
        return user.getId();
    }


    private void resendResetPasswordVerifyToken(PasswordResetToken passwordResetToken) {
        String url =passwordResetToken.getToken();

        //sendVerificationEmail()

        message.setFrom("malidoniso1@gmail.com");
        message.setTo(passwordResetToken.getUser().getEmail());
        message.setSubject("Intelligentsia");
        message.setText("Click the link to verify your account: " + url);
        emailSender.send(message);

        //log.info("Click the link to verify your account: {}", url);
    }

    private void resendVerificationTokenMail(VerificationToken verificationToken) {
        String url =verificationToken.getToken();

        //sendVerificationEmail()

        message.setFrom("malidoniso1@gmail.com");
        message.setTo(verificationToken.getUser().getEmail());
        message.setSubject("Intelligentsia");
        message.setText("Click the link to verify your account: " + url);
        emailSender.send(message);

        //log.info("Click the link to verify your account: {}",url);
    }
    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String auhToken = request.getHeader("Authorization");
        if(auhToken!=null && auhToken.startsWith("Bearer")){
            try {
                String jwt=auhToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("mySecret1234");
                JWTVerifier jwtVerifier= JWT.require(algorithm).build();
                DecodedJWT decodedJWT =jwtVerifier.verify(jwt);
                String email = decodedJWT.getSubject();
                User user = userService.findUserByEmail(email);
                String jwtAccessToken= JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles",user.getRole().stream().map(ga->ga.getName()).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> idToken= new HashMap<>();
                idToken.put("access-token",jwtAccessToken);
                idToken.put("refreshToken",jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);

            }catch (Exception e){
               throw e;
            }
        }else {
            throw new RuntimeException("Referrsh token required !!!");
        }
    }
}
