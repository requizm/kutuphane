package com.example.kutuphane.service;

import java.util.List;
import java.util.Optional;

import com.example.kutuphane.model.Kitap;
import com.example.kutuphane.model.Yazar;
import com.example.kutuphane.repository.KitapRepository;
import com.example.kutuphane.repository.YazarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YazarService {

    @Autowired
    private YazarRepository yazarRepository;

    @Autowired
    private KitapRepository kitapRepository;

    public List<Yazar> getAll() {
        return yazarRepository.findAll();
    }

    public Yazar get(Integer id) {
        if (yazarMevcutMu(id)) {
            return yazarRepository.findById(id).get();
        }
        return null;
    }

    public boolean yazarMevcutMu(Integer id) {
        Optional<Yazar> yazar = yazarRepository.findById(id);
        return yazar.isPresent();
    }

    public void add(Yazar yazar) {
        yazarRepository.save(yazar);
    }

    public void update(Yazar yazar) {
        yazarRepository.save(yazar);
    }

    public void delete(Integer id) {

        if (yazarRepository.findById(id).isPresent()) {
            Yazar yazar = yazarRepository.findById(id).get();
            List<Kitap> kitaplar = kitapRepository.findAll();
            for (int i = 0; i < kitaplar.size(); i++) {
                if (kitaplar.get(i).getYazar().getId() == yazar.getId()) {
                    kitapRepository.deleteById(kitaplar.get(i).getId());
                }
            }
            yazarRepository.deleteById(id);
        }
    }
}
