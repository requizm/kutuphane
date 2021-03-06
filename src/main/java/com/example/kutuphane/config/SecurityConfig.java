package com.example.kutuphane.config;

import com.example.kutuphane.security.CustomOAuth2UserService;
import com.example.kutuphane.security.MyUserDetailsService;
import com.example.kutuphane.security.OAuth2LoginFailureHandler;
import com.example.kutuphane.security.OAuth2LoginSuccessHandler;

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
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Autowired
    private OAuth2LoginFailureHandler oAuth2LoginFailureHandler;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/oauth2/**", "/register").permitAll()
                .antMatchers("/yazar/**", "/yayinevi/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/kitap/**", "/ara/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
                .loginPage("/login").usernameParameter("email").passwordParameter("sifre").permitAll()
                .defaultSuccessUrl("/").and().oauth2Login().loginPage("/login").userInfoEndpoint()
                .userService(customOAuth2UserService).and().successHandler(oAuth2LoginSuccessHandler)
                .failureHandler(oAuth2LoginFailureHandler).and().logout().logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
