package com.capgemini.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

import static junit.framework.Assert.assertEquals;

import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

	AccountService accountService;
	
	@Mock
	AccountRepository accountRepository;
	
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		accountService = new AccountServiceImpl(accountRepository);
	}

	/*
	 * test cases for create account
	 * 1. when the amount is less than 500 system should throw exception
	 * 2. when the valid info is passed account should be created successfully
	 */
	
	@Test(expected = com.capgemini.exceptions.InsufficientInitialBalanceException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialBalanceException
	{
		accountService.createAccount(101, 400);
	}
	
	
	@Test
	public void testCreateAccount() throws InsufficientInitialBalanceException {
		Account account = new Account();
		account.setAccountNumber(123);
		account.setAmount(10000);
		
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(accountService.createAccount(123, 10000), account);
	}
	

}
