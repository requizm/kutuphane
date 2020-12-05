package com.example.kutuphane.repository;

import com.example.kutuphane.model.Kitap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitapRepository extends JpaRepository<Kitap, Integer> {

}
