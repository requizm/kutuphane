package com.example.kutuphane.repository;

import java.util.List;

import com.example.kutuphane.model.Yayinevi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YayineviRepository extends JpaRepository<Yayinevi, Integer> {

    public List<Yayinevi> findByAciklama(String aciklama);

}
