package com.example.kutuphane.service;

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

    public boolean update(Kitap kitap) {
        // if (kitapRepository.findById(id).isPresent()) {
        kitapRepository.save(kitap);
        return true;
        // }
        // return false;
    }

    public boolean delete(Integer id) {

        if (kitapRepository.findById(id).isPresent()) {
            kitapRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
