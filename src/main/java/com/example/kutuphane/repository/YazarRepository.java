package com.example.kutuphane.repository;

import com.example.kutuphane.model.Yazar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YazarRepository extends JpaRepository<Yazar, Integer> {

}
