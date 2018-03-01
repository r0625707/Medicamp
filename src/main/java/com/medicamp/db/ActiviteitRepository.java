package com.medicamp.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicamp.model.Activiteit;

public interface ActiviteitRepository extends JpaRepository<Activiteit, Integer>{

}
