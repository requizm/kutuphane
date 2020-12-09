package com.example.kutuphane.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.kutuphane.model.Kullanici;
import com.example.kutuphane.model.Roller;
import com.example.kutuphane.service.KullaniciService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private KullaniciService kullaniciService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Kullanici kullanici = kullaniciService.get(email);
        List<GrantedAuthority> authorities = getUserAuthority(kullanici.getRol());
        return buildUserForAuthentication(kullanici, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Roller rol) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();

        roles.add(new SimpleGrantedAuthority(rol.toString()));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(Kullanici kullanici, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(kullanici.getEmail(), kullanici.getSifre(), true,
                true, true, true, authorities);
    }
}
