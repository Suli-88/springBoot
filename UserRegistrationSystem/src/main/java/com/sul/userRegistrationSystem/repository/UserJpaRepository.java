package com.sul.userRegistrationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sul.userRegistrationSystem.dto.UserDTO;

@Repository
public interface UserJpaRepository extends JpaRepository<UserDTO, Long> {

	UserDTO getUserById(Long id);
	
	UserDTO getUserByName(String name);
}
