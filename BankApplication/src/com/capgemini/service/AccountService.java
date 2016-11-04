package com.capgemini.service;

import com.capgemini.exception.InsufficientInitialBalance;
import com.capgemini.exception.InvalidAccountNumber;
import com.capgemini.exception.InvalidAmountException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;

public interface AccountService {

	Account createAccount(int accountNumber, int amount, Customer customer) throws InsufficientInitialBalance, InvalidAccountNumber;
	Account depositAmount(int accountNumber,int amount) throws InvalidAccountNumber, InvalidAmountException;
	Account withDrawAmount (int accountNumber,int amount) throws InvalidAmountException, InvalidAccountNumber;

}
