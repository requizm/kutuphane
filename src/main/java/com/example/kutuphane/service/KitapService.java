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

    public Kitap get(Integer id) {
        if (kitapMevcutMu(id)) {
            return kitapRepository.findById(id).get();
        }
        return null;
    }

    public boolean kitapMevcutMu(Integer id) {
        Optional<Kitap> kitap = kitapRepository.findById(id);
        return kitap.isPresent();
    }

    public void add(Kitap yayinEvi) {
        kitapRepository.save(yayinEvi);
    }

    public void update(Kitap kitap) {
        kitapRepository.save(kitap);
    }

    public List<Kitap> findByIsbn(String isbn) {
        List<Kitap> kitaplar = kitapRepository.findAll();
        List<Kitap> sonucKitaplar = new ArrayList<>();
        for (int i = 0; i < kitaplar.size(); i++) {
            if (simpleTextSearch(isbn.toCharArray(), kitaplar.get(i).getIsbnNumarasi().toCharArray())) {
                sonucKitaplar.add(kitaplar.get(i));
            }
        }
        return sonucKitaplar;
    }

    public List<Kitap> findByAd(String ad) {
        List<Kitap> kitaplar = kitapRepository.findAll();
        List<Kitap> sonucKitaplar = new ArrayList<>();
        for (int i = 0; i < kitaplar.size(); i++) {
            if (simpleTextSearch(ad.toCharArray(), kitaplar.get(i).getAd().toCharArray())) {
                sonucKitaplar.add(kitaplar.get(i));
            }
        }
        return sonucKitaplar;
    }

    public List<Kitap> findBySeriAdi(String seriAdi) {
        List<Kitap> kitaplar = kitapRepository.findAll();
        List<Kitap> sonucKitaplar = new ArrayList<>();
        for (int i = 0; i < kitaplar.size(); i++) {
            if (simpleTextSearch(seriAdi.toCharArray(), kitaplar.get(i).getSeriAdi().toCharArray())) {
                sonucKitaplar.add(kitaplar.get(i));
            }
        }
        return sonucKitaplar;
    }

    public List<Kitap> findByYazarAdi(String yazarAdi) {
        List<Kitap> kitaplar = kitapRepository.findAll();
        List<Kitap> sonucKitaplar = new ArrayList<>();
        for (int i = 0; i < kitaplar.size(); i++) {
            if (simpleTextSearch(yazarAdi.toCharArray(), kitaplar.get(i).getYazar().getAd().toCharArray())) {
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

    private boolean simpleTextSearch(char[] pattern, char[] text) {
        int patternSize = pattern.length;
        int textSize = text.length;

        int i = 0;

        while ((i + patternSize) <= textSize) {
            int j = 0;
            while (text[i + j] == pattern[j]) {
                j += 1;
                if (j >= patternSize)
                    return true;
            }
            i += 1;
        }
        return false;
    }
}
