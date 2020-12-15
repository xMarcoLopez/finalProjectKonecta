package com.konecta.projectBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konecta.projectBook.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
