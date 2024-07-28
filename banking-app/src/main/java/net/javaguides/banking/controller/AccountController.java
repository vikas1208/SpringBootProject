package net.javaguides.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.banking.Dto.AccountDTO;
import net.javaguides.banking.service.AccountService;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private AccountService accountservice;

	
	public AccountController(AccountService accountservice) {
		this.accountservice = accountservice;
	} 
	
	// Add account REST API
	@PostMapping()
	public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountdto){
		
		return new ResponseEntity<AccountDTO>(accountservice.createAccount(accountdto), HttpStatus.CREATED);
	}
	
	//Get account REST API
	@GetMapping("/{id}")
	public ResponseEntity<AccountDTO> fetchAccount(@PathVariable Long id){
			AccountDTO accountdto =		accountservice.getAccountByID(id);
		return ResponseEntity.ok(accountdto);
		
	}
	
	//Deposit amount REST API
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDTO> deposit(@PathVariable Long id, @RequestBody Map<String,Double> request){
				AccountDTO accountdto =	accountservice.deposit(id, request.get("amount"));
				
		return ResponseEntity.ok(accountdto);
		
	}
	
	//Withdraw amount REST API
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDTO> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request){
				AccountDTO accountdto =	accountservice.withdraw(id, request.get("amount"));
				
		return ResponseEntity.ok(accountdto);
		
	}
	
	//GET All Account REST API
	@GetMapping("/allAccounts")
	public ResponseEntity<List<AccountDTO>> getAllAccounts(){
		List<AccountDTO> allAccountDTO = accountservice.getAllAccounts();
		return ResponseEntity.ok(allAccountDTO);
	}
	
	//DELETE Account REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
					accountservice.deleteAccount(id);
		return ResponseEntity.ok("Account is deleted");			
	}
	
}
