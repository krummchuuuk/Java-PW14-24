package com.example.PW14.java.configs_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(getPasswordEncoder());
    }
	@Bean 
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/css/**", "/login", "/", "/registration").permitAll()
			.anyRequest().authenticated();
		http.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/home")
			.failureForwardUrl("/login")
			.usernameParameter("username")
			.passwordParameter("password")
            .permitAll();
		http.logout()
			.permitAll()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
			.invalidateHttpSession(true);
	}
}