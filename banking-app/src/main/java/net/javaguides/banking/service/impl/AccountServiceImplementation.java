package net.javaguides.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.javaguides.banking.Dto.AccountDTO;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;


@Service
public class AccountServiceImplementation implements AccountService{


	private AccountRepository accountrepo;
	
	public AccountServiceImplementation(AccountRepository accountrepo) {
		this.accountrepo = accountrepo;
	}
	
	@Override
	public AccountDTO createAccount(AccountDTO accountDto) {
		
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount =	accountrepo.save(account);
		
		return AccountMapper.mapToAccountDTO(savedAccount);
	}

	@Override
	public AccountDTO getAccountByID(Long id) {
		Account account = accountrepo.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't exist"));
		AccountDTO retrievedDTO = AccountMapper.mapToAccountDTO(account);
		return retrievedDTO;
	}

	@Override
	public AccountDTO deposit(Long id, double amount) {
		Account account = accountrepo.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't exist"));
					double newAmount =	account.getBalance() + amount;
					account.setBalance(newAmount);
					Account savedAccount=	accountrepo.save(account);
				
			return	AccountMapper.mapToAccountDTO(savedAccount);
					
	}

	@Override
	public AccountDTO withdraw(Long id, double amount) {
		Account account = accountrepo.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't exist"));
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient balance");
		}
		
		double remainingAmount = account.getBalance()-amount;
				account.setBalance(remainingAmount);
				Account savedAccount=	accountrepo.save(account);
				
		return AccountMapper.mapToAccountDTO(savedAccount);
	}

	@Override
	public List<AccountDTO> getAllAccounts() {
		List<Account> accounts = accountrepo.findAll();
		List<AccountDTO> accountdto = accounts.stream().map((account)->AccountMapper.mapToAccountDTO(account)).collect(Collectors.toList());
		
		return accountdto;
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountrepo.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't exist"));
					accountrepo.deleteById(id);
		
	}

}
