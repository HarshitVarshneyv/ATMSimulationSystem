package com.connectme.atm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connectme.atm.entity.User;

public interface AtmRepository extends JpaRepository<User,Long> {

	boolean exixtsByEmail(String email);
    Optional<User> findByEmail(String email);
	Optional<User> findByAccountNumber(String accountNumber);
}
