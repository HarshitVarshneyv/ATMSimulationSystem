package com.connectme.atm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.connectme.atm.dto.LoginRequest;
import com.connectme.atm.dto.TransactionResponse;
import com.connectme.atm.entity.Transactions;
import com.connectme.atm.entity.Type;
import com.connectme.atm.entity.User;
import com.connectme.atm.exception.AccountDoesNotExistsException;
import com.connectme.atm.exception.EmailAlreadyExistsException;
import com.connectme.atm.exception.EmailDoesNotExistsException;
import com.connectme.atm.exception.InsufficientBalanceException;
import com.connectme.atm.exception.PasswordDoesNotMatchException;
import com.connectme.atm.exception.PinDoesNotMatchException;
import com.connectme.atm.repository.AtmRepository;
import com.connectme.atm.repository.TransactionsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtmService {

	private final AtmRepository atmRepository;
    private final TransactionsRepository transactionsRepository;
//------------------------------------------------------1------------------------------------------------------------------------------------------------------------------
	public User signup(User user) {
		if(atmRepository.exixtsByEmail(user.getEmail())) {
			throw new EmailAlreadyExistsException("Email Already Exists");
		}
		return atmRepository.save(user);
	}

//------------------------------------------------------2-------------------------------------------------------------------------------------------------------------------
	public User signin(LoginRequest loginRequest) {
		User user = atmRepository.findByEmail(loginRequest.getEmail())
				    .orElseThrow(() -> new EmailDoesNotExistsException("Email does not exixts"));
		
		if(!user.getPassword().equals(loginRequest.getPassword())) {
			throw new PasswordDoesNotMatchException("You have Entered the wrong password");
		}
		if(!user.getPin().equals(loginRequest.getPin())) {
			throw new PinDoesNotMatchException("You have Entered the wrong PIN");
		}
		return user;
	}

//------------------------------------------------------3-------------------------------------------------------------------------------------------------------------------
	public double checkBalance(String accountNumber) {
		// TODO Auto-generated method stub
		User user = atmRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountDoesNotExistsException("User not Fount")); 
		return user.getBalance();
	}

//------------------------------------------------------4-------------------------------------------------------------------------------------------------------------------
	public TransactionResponse withdraw(String accountNumber, double withdrawAmount) {
		// TODO Auto-generated method stub
		User user = atmRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountDoesNotExistsException("User not Found"));
		if(user.getBalance() < withdrawAmount) {
			throw new InsufficientBalanceException("Insufficient Balance");
		}
		user.setBalance(user.getBalance() - withdrawAmount);
		atmRepository.save(user);
		
		Transactions transactions = Transactions.builder()
				.user(user)
				.type(Type.WITHDRAW)
				.amount(withdrawAmount)
				.timestamp(LocalDateTime.now())
				.build();
		
		transactionsRepository.save(transactions);
		return new TransactionResponse(transactions.getType(),transactions.getAmount(),transactions.getTimestamp(),user.getBalance());
	}

//------------------------------------------------------5---------------------------------------------------------------------------------------------------------------------
	
	public TransactionResponse deposit(String accountNumber, double depositAmount) {
		// TODO Auto-generated method stub
		User user = atmRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountDoesNotExistsException("User not Found"));
		user.setBalance(user.getBalance() + depositAmount);
		atmRepository.save(user);
		
		Transactions transactions = Transactions.builder()
				.user(user)
				.type(Type.DEPOSIT)
				.amount(depositAmount)
				.timestamp(LocalDateTime.now())
				.build();
		transactionsRepository.save(transactions);
		
		return new TransactionResponse(transactions.getType(),transactions.getAmount(),transactions.getTimestamp(),user.getBalance());
	}

//------------------------------------------------------6---------------------------------------------------------------------------------------------------------------------
	
	public List<TransactionResponse> getTransactionsHistory(String accountNumber) {
		// TODO Auto-generated method stub
		User user = atmRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountDoesNotExistsException("User not Found"));
		
		return transactionsRepository.findByUser(user).stream()
				.map(tx -> new TransactionResponse(tx.getType(), tx.getAmount(), tx.getTimestamp(),user.getBalance()))
				.collect(Collectors.toList());
	}

	//------------------------------------------------------6--------------------------------------------------------------------------------------------------------------
	public List<TransactionResponse> getTransactionsWithAllwithdrawHistory(String accountNumber) {
		// TODO Auto-generated method stub
		User user = atmRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountDoesNotExistsException("User not Found"));
		
		return transactionsRepository.findByUser(user).stream()
				.filter(tx -> tx.getType() == Type.WITHDRAW)
				.map(tx -> new TransactionResponse(tx.getType(), tx.getAmount(), tx.getTimestamp(),user.getBalance()))
				.collect(Collectors.toList());
	}

	public List<TransactionResponse> getTransactionsWithAlldepositHistory(String accountNumber) {
		// TODO Auto-generated method stub
		User user = atmRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountDoesNotExistsException("User not Found"));
		
		return transactionsRepository.findByUser(user).stream()
				.filter(tx -> tx.getType() == Type.DEPOSIT)
				.map(tx -> new TransactionResponse(tx.getType(),tx.getAmount(),tx.getTimestamp(),user.getBalance()))
				.collect(Collectors.toList());
	}
}

