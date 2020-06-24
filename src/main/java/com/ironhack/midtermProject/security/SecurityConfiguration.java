package com.ironhack.midtermProject.security;

import com.ironhack.midtermProject.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth.inMemoryAuthentication().withUser("arnoldo").password(passwordEncoder.encode("arnoldo")).roles("ADMIN");
        //auth.inMemoryAuthentication().withUser("irene").password(passwordEncoder.encode("irene")).roles("SALES");
        //auth.inMemoryAuthentication().withUser("gonzalo").password(passwordEncoder.encode("gonzalo")).roles("THIRD_PARTY");
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println("SecurityConfiguration - configure");
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/admin").hasRole("ADMIN")
                .mvcMatchers("/").hasAnyRole("ADMIN", "SALES")
                .anyRequest().authenticated()
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic();

    }
}
