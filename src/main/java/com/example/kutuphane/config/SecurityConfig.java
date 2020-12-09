package com.example.kutuphane.config;

import com.example.kutuphane.security.CustomOauth2UserService;
import com.example.kutuphane.security.MyUserDetailsService;
import com.example.kutuphane.security.Oauth2LoginFailureHandler;
import com.example.kutuphane.security.Oauth2LoginSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomOauth2UserService customOauth2UserService;

    @Autowired
    private Oauth2LoginSuccessHandler oauth2LoginSuccessHandler;

    @Autowired
    private Oauth2LoginFailureHandler oauth2LoginFailureHandler;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/resources/**", "/static/**","/webjars/**");
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/", "/oauth2/**", "/register").permitAll()
        .antMatchers("/yazar/**", "/yayinevi/**").hasAnyRole("USER", "ADMIN")
        .antMatchers("/kitap/**", "/ara/**").hasRole("ADMIN").anyRequest().authenticated()
        .and()
            .formLogin().loginPage("/login")
            .usernameParameter("email").passwordParameter("sifre").permitAll()
            .defaultSuccessUrl("/")
        .and()
        .oauth2Login().loginPage("/login").userInfoEndpoint().userService(customOauth2UserService)
        .and().successHandler(oauth2LoginSuccessHandler).failureHandler(oauth2LoginFailureHandler)
        .and()
        .logout().logoutSuccessUrl("/").logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .invalidateHttpSession(true).deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth
        .userDetailsService(myUserDetailsService)
        .passwordEncoder(bCryptPasswordEncoder());
    }
}
