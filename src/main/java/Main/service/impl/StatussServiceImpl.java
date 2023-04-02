package Main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.StatussDAO;
import Main.entity.Statuss;
import Main.service.StatussService;

@Service
public class StatussServiceImpl implements StatussService {
	@Autowired
	StatussDAO tdao;

	@Override
	public List<Statuss> findAll(){
		return tdao.findAll();
	}

}
