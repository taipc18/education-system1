package Main.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.AccountDAO;
import Main.entity.Account;
import Main.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO adao;

	@Override
	public Account findById(String username) {
		return adao.findById(username).orElse(null);
	}

	@Override
	public List<Account> getAdministrators() {
		return adao.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		return adao.findAll();
	}

	@Override
	public Account findByEmail(String email) {
		return adao.findByEmail(email);
	}

	@Override
	public void createAccount(Account account) {
		adao.save(account);
	}
	
	@Override
	public Boolean isAccountExist(String username) {
		return adao.existsById(username);
	}

	@Override
	public Account updateStaff(Account acc) {
		return adao.save(acc);
	}

}
