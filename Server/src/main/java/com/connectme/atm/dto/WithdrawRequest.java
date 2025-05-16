package com.connectme.atm.dto;

import lombok.Data;

@Data
public class WithdrawRequest {

	private String accountNumber;
	private double withdrawAmount;
}
