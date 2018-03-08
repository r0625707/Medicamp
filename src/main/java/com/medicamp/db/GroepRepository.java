package com.medicamp.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medicamp.model.Groep;
import com.medicamp.model.Kind;

public interface GroepRepository extends JpaRepository<Groep, Integer> {
	
	 @Query(value = "select g from User u join u.groepen g where u.login = :login")
     public List<Groep> findAllGroepenForUser(@Param("login") String login);

}
