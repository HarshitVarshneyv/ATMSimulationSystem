package com.connectme.atm.dto;

import lombok.Data;

@Data
public class DepositResponse {

	private String accountNumber;
	private double depositAmount;
}
