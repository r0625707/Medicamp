package com.medicamp.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medicamp.model.Kind;

public interface KindRepository extends JpaRepository<Kind, Integer>{
	
	 @Query(value = "select k from Kind k where k.user.login = :login ")
     public List<Kind> findAllKinderenForUser(@Param("login") String login);

}
