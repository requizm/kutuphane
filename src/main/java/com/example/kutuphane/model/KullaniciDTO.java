package com.example.kutuphane.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Validated
public class KullaniciDTO {
    private Integer id;

    @NotBlank(message = "Lütfen boş bırakmayınız")
    @Email(message = "Lütfen doğru formatta giriniz")
    private String email;

    @NotBlank(message = "Lütfen boş bırakmayınız")
    @Length(max = 16, message = "16 karakterden az olmalıdır")
    private String sifre;

    private Roller rol;

    private HesapTurleri hesap;

    public KullaniciDTO() {
    }

    public KullaniciDTO(Integer id, String email, String sifre, Roller rol, HesapTurleri hesap) {
        this.id = id;
        this.email = email;
        this.sifre = sifre;
        this.rol = rol;
        this.hesap = hesap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public Roller getRol() {
        return rol;
    }

    public void setRol(Roller rol) {
        this.rol = rol;
    }

    public HesapTurleri getHesap() {
        return hesap;
    }

    public void setHesap(HesapTurleri hesap) {
        this.hesap = hesap;
    }

    public Kullanici toKullanici() {
        return new Kullanici(getId(), getEmail(), getSifre(), getRol(), getHesap());
    }

}
