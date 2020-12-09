package com.example.kutuphane.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.kutuphane.model.HesapTurleri;
import com.example.kutuphane.model.Kullanici;
import com.example.kutuphane.model.Roller;
import com.example.kutuphane.service.KullaniciService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private KullaniciService kullaniciService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oauth2User.getEmail();

        if (!kullaniciService.kullaniciMevcutMu(email)) {
            Kullanici kullanici = new Kullanici();
            kullanici.setEmail(email);
            kullanici.setSifre("github");
            kullanici.setRol(Roller.ROLE_USER);
            kullanici.setHesap(HesapTurleri.GITHUB);
            
            
            kullaniciService.add(kullanici);
        } else {
            
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
