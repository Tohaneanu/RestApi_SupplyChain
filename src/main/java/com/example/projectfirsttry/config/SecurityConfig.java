package com.example.projectfirsttry.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//nu pastreaza autentificarea de pe un request pe altul
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET,"/address").hasRole("CLIENT")
                .mvcMatchers(HttpMethod.GET,"/orders/{client}").hasRole("CLIENT")
                .mvcMatchers(HttpMethod.POST,"/command/{clientName}").hasRole("CLIENT")
                .mvcMatchers(HttpMethod.POST,"/user/{userId}").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.GET,"/admin/users").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/admin/orders").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/admin/orders/c/{client}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/admin/orders/m/{manufacturer}").hasRole("ADMIN")
                .anyRequest().authenticated();//authenticated();  //permitAll()

    }
}
