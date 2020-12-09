package com.example.kutuphane.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Validated
public class KitapDTO {
    private Integer id;

    @NotBlank(message = "Lütfen boş bırakmayınız")
    @Length(max = 25, message = "25 karakterden az olmalıdır")
    private String ad;

    @NotBlank(message = "Lütfen boş bırakmayınız")
    @Length(max = 25, message = "25 karakterden az olmalıdır")
    private String altAdi;

    @NotBlank(message = "Lütfen boş bırakmayınız")
    @Length(max = 25, message = "25 karakterden az olmalıdır")
    private String seriAdi;

    @NotBlank(message = "Lütfen boş bırakmayınız")
    private Yazar yazar;
    
    @NotBlank(message = "Lütfen boş bırakmayınız")
    private Yayinevi yayinevi;

    @NotBlank(message = "Lütfen boş bırakmayınız")
    @Length(max = 13, min = 13, message = "13 karakter olmalıdır")
    private String isbnNumarasi;

    @NotBlank(message = "Lütfen boş bırakmayınız")
    @Length(max = 25, message = "25 karakterden az olmalıdır")
    private String aciklama;

    public KitapDTO() {
    }

    public KitapDTO(Integer id, String ad, String altAdi, String seriAdi, Yazar yazar, Yayinevi yayinevi,
            String isbnNumarasi, String aciklama) {
        this.id = id;
        this.ad = ad;
        this.altAdi = altAdi;
        this.seriAdi = seriAdi;
        this.yazar = yazar;
        this.yayinevi = yayinevi;
        this.isbnNumarasi = isbnNumarasi;
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

    public String getAltAdi() {
        return altAdi;
    }

    public void setAltAdi(String altAdi) {
        this.altAdi = altAdi;
    }

    public String getSeriAdi() {
        return seriAdi;
    }

    public void setSeriAdi(String seriAdi) {
        this.seriAdi = seriAdi;
    }

    public Yazar getYazar() {
        return yazar;
    }

    public void setYazar(Yazar yazar) {
        this.yazar = yazar;
    }

    public Yayinevi getYayinevi() {
        return yayinevi;
    }

    public void setYayinevi(Yayinevi yayinevi) {
        this.yayinevi = yayinevi;
    }

    public String getIsbnNumarasi() {
        return isbnNumarasi;
    }

    public void setIsbnNumarasi(String isbnNumarasi) {
        this.isbnNumarasi = isbnNumarasi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Kitap toKitap() {
        return new Kitap(getId(), getAd(), getAltAdi(), getSeriAdi(), getYazar(), getYayinevi(), getIsbnNumarasi(),
                getAciklama());
    }
}
