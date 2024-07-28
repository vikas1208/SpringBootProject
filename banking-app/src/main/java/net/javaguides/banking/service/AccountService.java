package net.javaguides.banking.service;

import java.util.List;

import net.javaguides.banking.Dto.AccountDTO;


public interface AccountService {
	
	AccountDTO createAccount(AccountDTO accountDto); 
	AccountDTO getAccountByID(Long id);
	AccountDTO deposit(Long id, double amount);
	AccountDTO withdraw(Long id, double amount);
	List<AccountDTO> getAllAccounts();
	void deleteAccount(Long id);

}
