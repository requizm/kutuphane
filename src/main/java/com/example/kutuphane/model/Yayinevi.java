package com.example.kutuphane.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yayin_evi")
public class Yayinevi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ad", nullable = false, length = 25)
    private String ad;

    @Column(name = "aciklama", nullable = false, length = 45)
    private String aciklama;

    public Yayinevi() {
    }

    public Yayinevi(String ad, String aciklama) {
        this.ad = ad;
        this.aciklama = aciklama;
    }

    public Yayinevi(Integer id, String ad, String aciklama) {
        this.id = id;
        this.ad = ad;
        this.aciklama = aciklama;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public YayineviDTO toYayineviDTO() {
        return new YayineviDTO(getId(), getAd(), getAciklama());
    }
}
