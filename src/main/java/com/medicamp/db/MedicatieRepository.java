package com.medicamp.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicamp.model.Medicatie;

public interface MedicatieRepository extends JpaRepository<Medicatie, Integer>{

}
