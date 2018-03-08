package com.medicamp.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.medicamp.model.Kind;
import com.medicamp.model.Tak;

public interface TakRepository extends JpaRepository<Tak, Integer>{

	@Query(value = "select t.idtak from Groep g join g.takken t where g.idgroep = :idgroep")
	List<Integer> getTakkenIdForGroep(@Param("idgroep") int idgroep);
	
	@Query(value = "select t from User u join u.takken t where u.login = :login")
	List<Tak> findAllForUser(@Param("login")String login);
    
	
    
	/*@Query(value = "select t. from User u join u.takken t where u.login = :login")
	List<Tak> findAllTakkenForUser(@Param("login")String login);*/



	
	
}
