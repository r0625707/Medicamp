package com.medicamp.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medicamp.model.Kind;
import com.medicamp.model.Tak;

public interface KindRepository extends JpaRepository<Kind, Integer>{
	
	 @Query(value = "select k from Kind k where k.user.login = :login ")
     public List<Kind> findAllKinderenForUser(@Param("login") String login);

	 @Query(value = "select k.id from Tak t join t.kinderen k where t.idtak = :idtak")
		List<Integer> getIdKindForTak(@Param("idtak") int idtak);
}
