package com.example.kutuphane.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.kutuphane.model.Kitap;
import com.example.kutuphane.repository.KitapRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KitapService {
    @Autowired
    private KitapRepository kitapRepository;

    public List<Kitap> getAll() {
        return kitapRepository.findAll();
    }

    public Optional<Kitap> get(Integer id) {
        return kitapRepository.findById(id);
    }

    public void add(Kitap yayinEvi) {
        kitapRepository.save(yayinEvi);
    }

    public void update(Kitap kitap) {
        kitapRepository.save(kitap);
    }

    public List<Kitap> findByIsbn(String isbn) {
        return kitapRepository.findByIsbnNumarasi(isbn);
    }

    public List<Kitap> findByAd(String ad) {
        return kitapRepository.findByAd(ad);
    }

    public List<Kitap> findBySeriAdi(String seriAdi) {
        return kitapRepository.findBySeriAdi(seriAdi);
    }

    public List<Kitap> findByYazarAdi(String yazarAdi) {
        List<Kitap> kitaplar = kitapRepository.findAll();
        List<Kitap> sonucKitaplar = new ArrayList<Kitap>();
        for (int i = 0; i < kitaplar.size(); i++) {
            if (kitaplar.get(i).getYazar().getAd().equals(yazarAdi)) {
                sonucKitaplar.add(kitaplar.get(i));
            }
        }
        return sonucKitaplar;
    }

    public void delete(Integer id) {

        if (kitapRepository.findById(id).isPresent()) {
            kitapRepository.deleteById(id);
        }
    }
}
