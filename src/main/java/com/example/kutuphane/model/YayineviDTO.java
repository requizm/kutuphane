package com.example.kutuphane.model;

public class YayineviDTO {
    private Integer id;
    private String ad;
    private String aciklama;

    public YayineviDTO(Integer id, String ad, String aciklama) {
        this.id = id;
        this.ad = ad;
        this.aciklama = aciklama;
    }

    public YayineviDTO() {
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

    public Yayinevi toYayinevi() {
        return new Yayinevi(getId(), getAd(), getAciklama());
    }
}
