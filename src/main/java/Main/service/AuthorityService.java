package Main.service;

import java.util.List;

import Main.entity.Account;
import Main.entity.Authority;

public interface AuthorityService {

	List<Authority> findAll();

	List<Authority> findAuthoritiesOfAdministrators();

	Authority create(Authority auth);
	
	void delete(Integer id);
	
	void authNewUser(Account account);
}
