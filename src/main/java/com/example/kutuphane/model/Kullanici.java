package com.example.kutuphane.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kullanici {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "sifre", nullable = false)
    private String sifre;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Roller rol;

    @Enumerated(EnumType.STRING)
    @Column(name = "hesap_turleri", nullable = false)
    private HesapTurleri hesap;

    public Kullanici() {
    }

    public Kullanici(Integer id, String email, String sifre, Roller rol, HesapTurleri hesap) {
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

    public KullaniciDTO toKullaniciDTO() {
        return new KullaniciDTO(getId(), getEmail(), getSifre(), getRol(), getHesap());
    }
}
