package Main.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Main.entity.Review;
import Main.service.ReviewService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/reviews")
public class ReviewRestCtrl {

	@Autowired
	ReviewService rSV;
	
	@GetMapping()
	public List<Review> getAll() {
		return rSV.findAll();
	}
	
	@PostMapping
	public Review create(@RequestBody Review pr) {
		return rSV.create(pr);
	}
}
