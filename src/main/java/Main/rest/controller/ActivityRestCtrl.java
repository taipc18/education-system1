package Main.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Main.entity.Activity;
import Main.service.ActivityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/activities")
public class ActivityRestCtrl {
	
	@Autowired
	ActivityService acti;
	
	
	@GetMapping()
	public List<Activity> getAll() {
		return acti.findAllByOrderByIdDesc();
	}
	
	@PostMapping
	public Activity create(@RequestBody Activity pr) {
		return acti.create(pr);
	}

}
