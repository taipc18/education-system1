package Main.rest.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Main.entity.Role;
import Main.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleRestCtrl {
	
	@Autowired
	RoleService rSV;
	
	@GetMapping
	public List<Role> getAll(){
		return rSV.findAll();
	}

}
