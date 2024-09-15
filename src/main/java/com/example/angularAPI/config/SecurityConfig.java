package com.example.angularAPI.config;

import com.example.angularAPI.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public PasswordEncoder getPass(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService uds(){
        UserDetails u1 = User.withUsername("rama").password(getPass().encode("rama")).roles("ADMIN").build();
        UserDetails u2 = User.withUsername("sham").password(getPass().encode("sham")).roles("USER").build();
        InMemoryUserDetailsManager im = new InMemoryUserDetailsManager(u1,u2);
        return im;
    }

    @Bean
    public SecurityFilterChain sfc(HttpSecurity http) throws Exception{
        http.cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(req -> req.requestMatchers("/user/getAll").hasRole("ADMIN"))
                .authorizeHttpRequests(req -> req.requestMatchers("/user/getAll").permitAll())
                .authorizeHttpRequests(req -> req.requestMatchers(HttpMethod.POST,"/user/getToken").permitAll())
                .authorizeHttpRequests(req -> req.requestMatchers(HttpMethod.POST,"/user/logout").permitAll())
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Bean
    public AuthenticationManager getAuthManager(AuthenticationConfiguration conf) throws Exception{
        return conf.getAuthenticationManager();
    }





}
