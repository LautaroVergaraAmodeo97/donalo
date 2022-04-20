//package com.DONALO.proyecto;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import com.DONALO.proyecto.Seguridad.UserDetailsServiceImpl;
//
//
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//   
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        
//        System.out.println("=======================================================");
//   		http.headers().frameOptions().sameOrigin().and()
//   			.authorizeRequests()
//   				.antMatchers("/css/*", "/js/*", "/login", "/registro")
//   				.permitAll()
//   			.and().formLogin()
//   				.loginPage("/login")
//   					.usernameParameter("username")
//   					.passwordParameter("clave")
//   					.defaultSuccessUrl("/")
//   					.permitAll()
//   				.and().logout()
//   					.logoutUrl("/logout")
//   					.logoutSuccessUrl("/login?logout")
//   					.permitAll().and().csrf().disable();
//    }
//
//    @Bean
//    public AuthenticationManager customAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//    }
//}