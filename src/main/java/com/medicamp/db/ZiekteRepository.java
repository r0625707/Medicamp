package com.medicamp.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicamp.model.Ziekte;

public interface ZiekteRepository extends JpaRepository<Ziekte, Integer>{

}
