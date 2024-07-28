package net.javaguides.banking.mapper;

import net.javaguides.banking.Dto.AccountDTO;
import net.javaguides.banking.entity.Account;

public class AccountMapper {

	
	public static Account mapToAccount(AccountDTO accountDto) {
		
		Account account = new Account(accountDto.getId(),accountDto.getAccountHolderName(),accountDto.getBalance());
		
		return account;
		
	}
	
	public static AccountDTO mapToAccountDTO(Account account) {
		AccountDTO accountDto= new AccountDTO(account.getId(),account.getAccountHolderName(),account.getBalance());
		
		return accountDto;
	}
}
