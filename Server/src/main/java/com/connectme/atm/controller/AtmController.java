package com.connectme.atm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connectme.atm.dto.DepositResponse;
import com.connectme.atm.dto.LoginRequest;
import com.connectme.atm.dto.TransactionResponse;
import com.connectme.atm.dto.WithdrawRequest;
import com.connectme.atm.entity.User;
import com.connectme.atm.service.AtmService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@RequiredArgsConstructor
public class AtmController {
	
	private final AtmService atmService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user){
		atmService.signup(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("User created!");
	}
	
	@PostMapping("/signin")
	public ResponseEntity<String> signin(@RequestBody LoginRequest loginRequest){
		atmService.signin(loginRequest);
		return ResponseEntity.ok("User Signup SuccessFully");
	}
	
	@GetMapping("/balance/{accountNumber}")
	public ResponseEntity<Double> checkBalance(@PathVariable String accountNumber){
		return ResponseEntity.ok(atmService.checkBalance(accountNumber));
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<TransactionResponse> withdraw(@RequestBody WithdrawRequest request){
		return ResponseEntity.ok(atmService.withdraw(request.getAccountNumber(),request.getWithdrawAmount()));
	}
	
	@PostMapping("/deposit")
	public ResponseEntity<TransactionResponse> deposit(@RequestBody DepositResponse request){
		return ResponseEntity.ok(atmService.deposit(request.getAccountNumber(),request.getDepositAmount()));
	}
	
	@GetMapping("/transaction/{accountNumber}")
	public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable String accountNumber) {
			return ResponseEntity.ok(atmService.getTransactionsHistory(accountNumber));
	}
	
	@GetMapping("/transaction/withdraw/{accountNumber}")
	public ResponseEntity<List<TransactionResponse>> getTransactionsWithAllwithdraw(@PathVariable String accountNumber) {
			return ResponseEntity.ok(atmService.getTransactionsWithAllwithdrawHistory(accountNumber));
	}
	
	@GetMapping("/transaction/deposit/{accountNumber}")
	public ResponseEntity<List<TransactionResponse>> getTransactionsWithAlldeposit(@PathVariable String accountNumber) {
			return ResponseEntity.ok(atmService.getTransactionsWithAlldepositHistory(accountNumber));
	}
}







