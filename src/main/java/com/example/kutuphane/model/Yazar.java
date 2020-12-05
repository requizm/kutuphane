package com.example.kutuphane.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yazar")
public class Yazar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ad", nullable = false, length = 25)
    private String ad;

    @Column(name = "aciklama", nullable = false, length = 45)
    private String aciklama;

    public Yazar(Integer id, String ad, String aciklama) {
        this.id = id;
        this.ad = ad;
        this.aciklama = aciklama;
    }

    public Yazar() {
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

    public YazarDTO toYazarDTO() {
        return new YazarDTO(getId(), getAd(), getAciklama());
    }
}
