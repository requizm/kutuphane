package com.example.kutuphane.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Validated
public class YazarDTO {
    private Integer id;

    @NotBlank(message = "Lütfen boş bırakmayınız")
    @Length(max = 25, message = "25 karakterden az olmalıdır")
    private String ad;

    @NotBlank(message = "Lütfen boş bırakmayınız")
    @Length(max = 45, message = "45 karakterden az olmalıdır")
    private String aciklama;

    public YazarDTO(Integer id, String ad, String aciklama) {
        this.id = id;
        this.ad = ad;
        this.aciklama = aciklama;
    }

    public YazarDTO() {
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

    public Yazar toYazar() {
        return new Yazar(getId(), getAd(), getAciklama());
    }
}
