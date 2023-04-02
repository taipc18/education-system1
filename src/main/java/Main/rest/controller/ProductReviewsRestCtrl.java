package Main.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Main.entity.ProductReviews;
import Main.service.ProductReviewsService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/productreviews")
public class ProductReviewsRestCtrl {

	@Autowired
	ProductReviewsService prrSV;
	
	@GetMapping("{id}")
	public List<ProductReviews> getAllByProductId(@PathVariable("id") Integer id) {
		return prrSV.findAllByProductId(id);
	}
	
	@PostMapping
	public ProductReviews create(@RequestBody ProductReviews pr) {
		return prrSV.create(pr);
	}
}
