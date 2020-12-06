package com.example.kutuphane.repository;

import java.util.List;

import com.example.kutuphane.model.Kitap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitapRepository extends JpaRepository<Kitap, Integer> {

    List<Kitap> findByIsbnNumarasi(String isbnNumarasi);

    List<Kitap> findBySeriAdi(String seriAdi); 

    List<Kitap> findByAd(String ad);
}
