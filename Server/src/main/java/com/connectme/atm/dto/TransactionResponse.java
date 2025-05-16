package com.connectme.atm.dto;

import java.time.LocalDateTime;

import com.connectme.atm.entity.Type;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {

	private Type type;
	private double amount;
	private LocalDateTime timeStamp;
	private double currentBalance;
}
