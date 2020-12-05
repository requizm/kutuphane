package com.example.kutuphane.service;

import java.util.List;
import java.util.Optional;

import com.example.kutuphane.model.Yayinevi;
import com.example.kutuphane.model.Kitap;
import com.example.kutuphane.repository.KitapRepository;
import com.example.kutuphane.repository.YayineviRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YayineviService {
    @Autowired
    private YayineviRepository yayineviRepository;

    @Autowired
    private KitapRepository kitapRepository;

    public List<Yayinevi> getAll() {
        return yayineviRepository.findAll();
    }

    public Optional<Yayinevi> get(Integer id) {
        return yayineviRepository.findById(id);
    }

    public void add(Yayinevi yayinEvi) {
        yayineviRepository.save(yayinEvi);
    }

    public boolean update(Yayinevi yazar) {
        yayineviRepository.save(yazar);
        return true;
    }

    public void delete(Integer id) {

        if (yayineviRepository.findById(id).isPresent()) {
            Yayinevi yayinevi = yayineviRepository.findById(id).get();
            List<Kitap> kitaplar = kitapRepository.findAll();
            for (int i = 0; i < kitaplar.size(); i++) {
                if (kitaplar.get(i).getYayinevi().getId() == yayinevi.getId()) {
                    kitapRepository.deleteById(kitaplar.get(i).getId());
                }
            }
            yayineviRepository.deleteById(id);
        }
    }
}
