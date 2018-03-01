package com.medicamp.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medicamp.model.Kind;
import com.medicamp.model.Tak;

public interface TakRepository extends JpaRepository<Tak, Integer>{



}
