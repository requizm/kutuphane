package com.example.kutuphane.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kitap")
public class Kitap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ad", nullable = false, length = 25)
    private String ad;

    @Column(name = "altAdi", nullable = false, length = 25)
    private String altAdi;

    @Column(name = "seriAdi", nullable = false, length = 25)
    private String seriAdi;

    @OneToOne
    @JoinColumn(name = "yazarId", nullable = false, referencedColumnName = "id")
    private Yazar yazar;

    @OneToOne
    @JoinColumn(name = "yayinEviId", nullable = false, referencedColumnName = "id")
    private Yayinevi yayinevi;

    @Column(name = "isbnNumarasi", nullable = false, length = 13)
    private String isbnNumarasi;

    @Column(name = "aciklama", nullable = false, length = 45)
    private String aciklama;

    public Kitap() {
    }

    public Kitap(Integer id, String ad, String altAdi, String seriAdi, Yazar yazar, Yayinevi yayinevi,
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

    public KitapDTO toKitapDTO() {
        return new KitapDTO(getId(), getAd(), getAltAdi(), getSeriAdi(), getYazar(), getYayinevi(), getIsbnNumarasi(),
                getAciklama());
    }
}
