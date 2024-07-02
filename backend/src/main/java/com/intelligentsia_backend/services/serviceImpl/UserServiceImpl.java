package com.intelligentsia_backend.services.serviceImpl;

import com.intelligentsia_backend.entity.*;
import com.intelligentsia_backend.model.UserModel;
import com.intelligentsia_backend.repository.PasswordResetTokenRepository;
import com.intelligentsia_backend.repository.RoleRepository;
import com.intelligentsia_backend.repository.UserRepository;
import com.intelligentsia_backend.repository.VerificationTokenInstructorOrStaffRepository;
import com.intelligentsia_backend.repository.VerificationTokenRepository;
import com.intelligentsia_backend.services.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private VerificationTokenInstructorOrStaffRepository verificationTokenInstructorOrStaffRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        //user.setRole("User");
        Role role= new Role();
        role.setName(userModel.getRole());
        roleRepository.save(role);
        user.getRole().add(role);
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);
        return user;
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user=userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRole().add(role);

    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
            VerificationToken verificationToken=verificationTokenRepository.findByUser_Id(user.getId());
            if(verificationToken!=null) {
                verificationToken.setToken(token);
                verificationTokenRepository.save(verificationToken);
            }
            else{
                VerificationToken verificationToken1 = new VerificationToken(user,token);
                verificationTokenRepository.save(verificationToken1);
            }
    }
    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken= passwordResetTokenRepository.findByUser_Id(user.getId());
        if(passwordResetToken!=null){
            passwordResetToken.setToken(token);
            passwordResetTokenRepository.save(passwordResetToken);
        }else{
        PasswordResetToken passwordResetToken1 = new PasswordResetToken(user,token);
        passwordResetTokenRepository.save(passwordResetToken1);
        }
    }
    @Override
    public void saveVerificationTokenForSuper(String token, String email) {
        VerificationTokenInstructorOrStaff verificationTokenInstructorOrStaff=new VerificationTokenInstructorOrStaff(token,email);
        verificationTokenInstructorOrStaffRepository.save(verificationTokenInstructorOrStaff);

    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken
                = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "invalid";
        }
        User user =verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpirationTime().getTime()
                - cal.getTime().getTime()) <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }
    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken
                = passwordResetTokenRepository.findByToken(token);

        if (passwordResetToken == null) {
            return "invalid";
        }

        User user = passwordResetToken.getUser();
        Calendar cal = Calendar.getInstance();

        if ((passwordResetToken.getExpirationTime().getTime()
                - cal.getTime().getTime()) <= 0) {
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";
        }

        return "valid";
    }

    @Override
    public String validateVerificationTokenInstructorOrStaff(String token) {
        VerificationTokenInstructorOrStaff verificationTokenInstructorOrStaff= verificationTokenInstructorOrStaffRepository.findByToken(token);
        if (verificationTokenInstructorOrStaff == null) {
            return "invalid";
        }
        Calendar cal = Calendar.getInstance();
        if ((verificationTokenInstructorOrStaff.getExpirationTime().getTime()
                - cal.getTime().getTime()) <= 0) {
            verificationTokenInstructorOrStaffRepository.delete(verificationTokenInstructorOrStaff);
            return "expired";
        }
        return "valid";
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public VerificationToken generateNewVerificationToken(Long id) {
        VerificationToken verificationToken= verificationTokenRepository.findByUser_Id(id);
        if(verificationToken==null){
            String token=UUID.randomUUID().toString().substring(0,6);
            Optional<User> user= userRepository.findById(id);
            User User1 = user.get();
            VerificationToken verificationToken1= new VerificationToken(User1,token);
            verificationTokenRepository.save(verificationToken1);
            return verificationToken1;

        }
        verificationToken.setToken(UUID.randomUUID().toString().substring(0,6));
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }
    @Override
    public PasswordResetToken generateNewPasswordResetToken(String token, String email) {

        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if(passwordResetToken==null){
            String token1=UUID.randomUUID().toString().substring(0,6);
            User user= userRepository.findByEmail(email);
            PasswordResetToken passwordResetToken1=new PasswordResetToken(user,token);
            passwordResetTokenRepository.save(passwordResetToken1);
            return passwordResetToken1;
        }
        passwordResetToken.setToken(UUID.randomUUID().toString().substring(0,6));
        passwordResetTokenRepository.save(passwordResetToken);
        return passwordResetToken;
    }

   /* @Override
    public User connexion(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return null;
       boolean t = passwordEncoder.matches(password, user.getPassword());

       if(t==true)
           return user;
       else
           return null;
    }*/

    @Override
    public PasswordResetToken findById(Long id) {
        return passwordResetTokenRepository.findByUser_Id(id);
    }


    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }



}
