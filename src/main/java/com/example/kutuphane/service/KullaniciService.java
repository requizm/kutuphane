package com.example.kutuphane.service;

import java.util.List;

import javax.annotation.PostConstruct;

import com.example.kutuphane.model.HesapTurleri;
import com.example.kutuphane.model.Kullanici;
import com.example.kutuphane.model.Roller;
import com.example.kutuphane.repository.KullaniciRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class KullaniciService {

    @Autowired
    KullaniciRepository kullaniciRepository;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    private void addRoot(){
        if(!kullaniciMevcutMu("root@root.com")){
            Kullanici kullanici = new Kullanici();
            kullanici.setEmail("root@root.com");
            kullanici.setRol(Roller.ROLE_ADMIN);
            kullanici.setHesap(HesapTurleri.LOCAL);
            kullanici.setSifre(bCryptPasswordEncoder().encode("root"));
            kullaniciRepository.save(kullanici);
        }
    }

    public boolean kullaniciMevcutMu(Kullanici kullanici) {
        List<Kullanici> kullanicilar = kullaniciRepository.findAll();
        for (int i = 0; i < kullanicilar.size(); i++) {
            if (kullanicilar.get(i).getEmail().equals(kullanici.getEmail())
                    && kullanicilar.get(i).getSifre().equals(kullanici.getSifre())
                    && kullanicilar.get(i).getHesap() == HesapTurleri.LOCAL) {
                return true;
            }
        }
        return false;
    }

    public boolean kullaniciMevcutMu(String email) {
        List<Kullanici> kullanicilar = kullaniciRepository.findAll();
        for (int i = 0; i < kullanicilar.size(); i++) {
            if (kullanicilar.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Kullanici kullaniciyiGetir(String email) {
        List<Kullanici> kullanicilar = kullaniciRepository.findAll();
        for (int i = 0; i < kullanicilar.size(); i++) {
            if (kullanicilar.get(i).getEmail().equals(email)) {
                return kullanicilar.get(i);
            }
        }
        return new Kullanici();
    }

    public void add(Kullanici kullanici) {
        kullanici.setSifre(bCryptPasswordEncoder().encode(kullanici.getSifre()));
        kullanici.setRol(Roller.ROLE_ADMIN);
        kullanici.setHesap(HesapTurleri.LOCAL);
        kullaniciRepository.save(kullanici);
    }

}
