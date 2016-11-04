package com.capgemini.service;

import com.capgemini.exception.InsufficientInitialBalance;
import com.capgemini.exception.InvalidAccountNumber;
import com.capgemini.exception.InvalidAmountException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import com.capgemini.repository.AccountRepository;
import com.capgemini.repository.AccountRepositoryImpl;

public class AccountServiceImpl implements AccountService {
	
	AccountRepository accountRepository;
	
	 public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Account createAccount(int accountNumber, int amount, Customer customer) throws InsufficientInitialBalance, InvalidAccountNumber {
	
		if(amount<500) {
			throw new InsufficientInitialBalance();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAccontBalance(amount);
		account.setCustomer(customer);
		if(!accountRepository.saveAccount(account)) {
			throw new InvalidAccountNumber();
		}
		return account;
		
	}

	@Override
	public Account depositAmount(int accountNumber, int amount) throws InvalidAccountNumber, InvalidAmountException {
	
			if (amount<0) {
				throw new InvalidAmountException();
			}
			
			Account account = accountRepository.searchAccount(accountNumber);
			
			if (null==account) {
				 throw new InvalidAccountNumber();
			}
			
			account.setAccontBalance(account.getAccontBalance() + amount);
			
			account = accountRepository.updateAccount(account);
		
			return account;
	}

	@Override
	public Account withDrawAmount(int accountNumber, int amount) throws InvalidAmountException, InvalidAccountNumber {
		if (amount<0) {
			throw new InvalidAmountException();
		}
		
		Account account = accountRepository.searchAccount(accountNumber);
		
		if (null==account) {
			 throw new InvalidAccountNumber();
		}
		
		account.setAccontBalance(account.getAccontBalance() - amount);
		
		account = accountRepository.updateAccount(account);
	
		return account;
	}
	
	
	

}
