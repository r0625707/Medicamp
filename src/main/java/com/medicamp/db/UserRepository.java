package com.medicamp.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicamp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
