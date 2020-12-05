package com.example.kutuphane.model;

public class KitapDTO {
    private Integer id;
    private String ad;
    private String altAdi;
    private String seriAdi;
    private Yazar yazar;
    private Yayinevi yayinevi;
    private String isbnNumarasi;
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
