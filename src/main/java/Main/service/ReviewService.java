package Main.service;

import java.util.List;

import Main.entity.Review;

public interface ReviewService {

	List<Review> findAll();
	
	Review create(Review pr);
}
