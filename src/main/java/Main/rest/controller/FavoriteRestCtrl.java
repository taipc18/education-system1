package Main.rest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Main.entity.Favorite;
import Main.service.FavoriteService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorites")
public class FavoriteRestCtrl {

	@Autowired
	FavoriteService fsv;
	
	@GetMapping("/layma/{username}")
	public List<Integer> getID(@PathVariable("username") String username) {
		return fsv.findProductIdByUsername(username);
	}
	
	@GetMapping("/layhet/{username}")
	public List<Favorite> getAll(@PathVariable("username") String username) {
		return fsv.findByUsername(username);
	}
	
	@PostMapping
	public Favorite create(@RequestBody Favorite f) {
		return fsv.create(f);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		fsv.delete(id);
	}
}
