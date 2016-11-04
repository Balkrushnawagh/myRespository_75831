package com.capgemini.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.capgemini.exception.InsufficientInitialBalance;
import com.capgemini.exception.InvalidAccountNumber;
import com.capgemini.exception.InvalidAmountException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import com.capgemini.repository.AccountRepository;
import com.capgemini.repository.AccountRepositoryImpl;
import com.capgemini.service.AccountServiceImpl;
import static org.junit.Assert.assertEquals;

public class AccountServiceTest {
	
	@Mock
	AccountRepository accountRepository;
	
	
	AccountServiceImpl accountService ;
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		accountService	=  new AccountServiceImpl(new AccountRepositoryImpl());
		
	}
	
	/* CreateAccount Test cases
	 * 1. When Valid information in passed account should be created successfully
	 * 2. When the amount is less than five thousand should throw InsufficientInitialBalanceException
	 * 3. When the account is already exist system should throw InvalidAccountNumberException
	 * 
	 */
	
	
	
	
	@Test
	public void whenValidInformationInPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialBalance, InvalidAccountNumber {
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		
		Account actualAccount = accountService.createAccount(123, 6000, customer);
		
		assertEquals(account, actualAccount);
		
	}
	
	
	@Test(expected = com.capgemini.exception.InsufficientInitialBalance.class)
	public void whenTheAmountIsLessThanFiveThousandShouldThrowInsufficientInitialBalanceException() throws InsufficientInitialBalance, InvalidAccountNumber {
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
		accountService.createAccount(123, 400, customer);
		
	}
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumber.class)
	public void whenAccountIsAlreadyExistShouldThrowInvalidAccountNumberException() throws InsufficientInitialBalance, InvalidAccountNumber {
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
		accountService.createAccount(123, 500, customer);
		
	}
	
	
	/*DepositAmount test cases
	 * 1. When valid account number and amount is passed amount should get deposited
	 * 2. When invalid account number is passed should throw InvalidAccountNumberException
	 * 3. When negative amount value is passed should get InvalidAmountException
	 * 
	 */
	
	
	@Test
	public void whenValidAccountNumberAndAmountIsPassedAmountShouldGetDeposited() throws InsufficientInitialBalance, InvalidAccountNumber, InvalidAmountException {
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
		Mockito.when(accountRepository.updateAccount(account)).thenReturn(account);
		Account accountObj = accountService.depositAmount(123, 1000);
		account.setAccontBalance(account.getAccontBalance()+1000);
		assertEquals(account, accountObj);
		
	}
	
	
	@Test (expected = com.capgemini.exception.InvalidAccountNumber.class)
	public void whenInvalidAccountNumberIsPassedShouldThrowInvalidAccountNumberException() throws InsufficientInitialBalance, InvalidAccountNumber, InvalidAmountException {
		
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
//		Mockito.when(accountRepository.updateAccount(account)).thenReturn(account);
		accountService.depositAmount(1234, 1000);
	}
	
	@Test (expected=com.capgemini.exception.InvalidAmountException.class)
	public void whenNegativeAmountValueIsPassedShouldGetInvalidAmountException() throws InvalidAccountNumber, InvalidAmountException {
		accountService.depositAmount(1234, -1000);
	}
	
	
	/* WithdrawAmout test cases
	 * 1. When valid account number and amount is passed amount should get withdraw
	 * 2. When invalid account number is passed should throw InvalidAccountNumberException
	 * 3. When negative amount value is passed should get InvalidAmountException
	 * 
	 */
	
	@Test
	public void whenValidAccountNumberAndAmountIsPassedAmountShouldGetWithraw() throws InsufficientInitialBalance, InvalidAccountNumber, InvalidAmountException {
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
		Mockito.when(accountRepository.updateAccount(account)).thenReturn(account);
		Account accountObj = accountService.withDrawAmount(123, 1000);
		account.setAccontBalance(account.getAccontBalance()-1000);
		assertEquals(account, accountObj);
		
	}
	
	
	@Test (expected = com.capgemini.exception.InvalidAccountNumber.class)
	public void whenInvalidAccountNumberIsPassedShouldThrowInvalidAccountNumberExceptionInWithdraw() throws InsufficientInitialBalance, InvalidAccountNumber, InvalidAmountException {
		
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
//		Mockito.when(accountRepository.updateAccount(account)).thenReturn(account);
		accountService.withDrawAmount(1234, 1000);
	}
	
	@Test (expected=com.capgemini.exception.InvalidAmountException.class)
	public void whenNegativeAmountValueIsPassedShouldGetInvalidAmountExceptionInWithdraw() throws InvalidAccountNumber, InvalidAmountException {
		accountService.withDrawAmount(1234, -1000);
	}
	
	

}
