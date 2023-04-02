package Main.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Main.dao.StatussDAO;
import Main.entity.Statuss;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/statuss")
public class StatussRestCtrl {
	@Autowired
	StatussDAO tSV;

	@GetMapping()
	public List<Statuss> getAll() {
		return tSV.findAll();
	}
}
