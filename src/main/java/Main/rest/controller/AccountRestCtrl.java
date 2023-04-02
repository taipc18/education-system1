package Main.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Main.entity.Account;
import Main.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestCtrl {
	
	@Autowired
	AccountService aSV;
	
	@GetMapping
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return aSV.getAdministrators();
		}
		return aSV.findAll();
	}
	
	@GetMapping("{id}")
	public Account getOne(@PathVariable("id") String id) {
		return aSV.findById(id);
	}
		
	
	@PutMapping("{username}")
	public Account update(@PathVariable("username") String username, @RequestBody Account acc) {
		return aSV.updateStaff(acc);
	}
}
