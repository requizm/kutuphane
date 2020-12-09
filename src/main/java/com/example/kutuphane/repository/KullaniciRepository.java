package com.example.kutuphane.repository;

import java.util.List;

import com.example.kutuphane.model.Kullanici;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, Integer> {

    public List<Kullanici> findByEmail(String email);

}
