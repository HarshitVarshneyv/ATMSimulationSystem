package com.connectme.atm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connectme.atm.entity.Transactions;
import com.connectme.atm.entity.User;

public interface TransactionsRepository extends JpaRepository<Transactions,Long>{

	Optional<Transactions> findByUser(User user);

}
