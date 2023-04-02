package Main.service;

import java.util.List;

import Main.entity.Account;

public interface AccountService {
	
	Account updateStaff(Account acc);
	
	Account findById(String username);

	List<Account> getAdministrators();

	List<Account> findAll();
	
	Account findByEmail(String email);
	
	void createAccount(Account account);
	
	Boolean isAccountExist(String username);
}
