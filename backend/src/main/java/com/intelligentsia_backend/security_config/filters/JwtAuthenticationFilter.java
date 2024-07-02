package com.intelligentsia_backend.security_config.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelligentsia_backend.model.PasswordModel;
import com.intelligentsia_backend.repository.UserRepository;
import com.intelligentsia_backend.services.service.UserService;
import com.intelligentsia_backend.services.serviceImpl.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("attemptAuthentication");
        //String email=request.getParameter("email");
        //String password= request.getParameter("password");
        InputStream inputStream = request.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        PasswordModel passwordModel=objectMapper.readValue(inputStream, PasswordModel.class);
        String email= passwordModel.getEmail();
        String password=passwordModel.getOldPassword();
        System.out.println(email);
        System.out.println(password);
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(email,password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication");
        User user =(User) authResult.getPrincipal();
        Algorithm algorithm1 = Algorithm.HMAC256("mySecret1234");
        String jwtAccessToken= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000))
                .withIssuer(request.getRequestURI().toString())
                .withClaim("roles",user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
                .withClaim("enabled",user.isEnabled())
                .sign(algorithm1);
        String jwtRefreshToken= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+15*60*1000))
                .withIssuer(request.getRequestURI().toString())
                .sign(algorithm1);
        Map<String,String> idToken= new HashMap<>();
        idToken.put("accessToken",jwtAccessToken);
        idToken.put("refreshToken",jwtRefreshToken);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(),idToken);
    }
}
