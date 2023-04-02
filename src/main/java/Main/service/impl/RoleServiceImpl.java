package Main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.RoleDAO;
import Main.entity.Role;
import Main.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDAO dao;

	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}
	
	
}
