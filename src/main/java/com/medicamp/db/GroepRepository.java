package com.medicamp.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicamp.model.Groep;

public interface GroepRepository extends JpaRepository<Groep, String> {

}
