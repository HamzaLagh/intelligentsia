package com.intelligentsia_backend.security_config.config;

import com.intelligentsia_backend.entity.User;
import com.intelligentsia_backend.repository.UserRepository;
import com.intelligentsia_backend.security_config.filters.JwtAuthenticationFilter;
import com.intelligentsia_backend.security_config.filters.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserRepository userService;
    @Autowired
    AuthenticationConfiguration configr;
    private static final String[] WHITE_LIST_URLS_Nofilter={
               "/register",
                "/verifyRegistration*",
                "/resendVerifyToken*",
                "/resetPassword",
                "/registerStudent",
                "/resendResetPasswordVerifyToken",
                "/aRegistrationStaff*",
                "/registrationStaff*",
                "/registerStudent",
                "/registerSociety",
                "/verifyResetPassword",
                "/savePassword",
                "/enable*",
                "/AllJobOfferNew",
                "/societyOffer*",
                "/getOffer*",


    };

    private static final String[] WHITE_LIST_URLS= {
            "/changePassword",
            "/getAllStudent",
            "/certification*",
            "/registerOffer",
            "/registerPostuler",
            "/recupPostuler*",
            "/recupSociety*",
            "/recupAllPostulerSociety*",
            "/getStudent*",

};
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
    @Bean
    public UserDetailsService configure(AuthenticationManagerBuilder auth) throws Exception {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                User user = userService.findByEmail(email);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                user.getRole().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                });
                return new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        user.getEnabled(),
                        true,
                        true,
                        true,
                        authorities);
            }
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(WHITE_LIST_URLS_Nofilter).permitAll()
                .requestMatchers(WHITE_LIST_URLS).authenticated();
         //http.formLogin();
         http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean(configr)));
         http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
   @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        //configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200/"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

  /*  @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");


    }*/
}
