package com.myproject.config;

import com.myproject.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//безопасность на уровне метода
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**","/webjars/**","/static/**","/css/**","/js/**","/fonts/**","/images/**","/favicon.ico","/swagger-resources/**","/bucket/**","**/style.css");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/registration","/login","/h2/**").permitAll()
                .anyRequest().
                    authenticated()
                .and()
                    .formLogin()
                    //.successForwardUrl("/main")
                    .loginPage("/login")
                .defaultSuccessUrl("/", true)
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();



        //конфиг под логин
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//
//
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("pass")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Override
    //@Autowired
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

//
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);


    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        // @formatter:off
//        auth.inMemoryAuthentication()
//                .withUser("user1").password("{noop}user1").roles("USER")
//                .and()
//                .withUser("admin1").password("{noop}admin1Pass").roles("ADMIN");
//        // @formatter:on
//
//
//
//    }



}
