package Main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.ReviewDAO;
import Main.entity.Review;
import Main.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewDAO rdao;

	@Override
	public List<Review> findAll() {
		return rdao.findAll();
	}

	@Override
	public Review create(Review pr) {
		return rdao.save(pr);
	}

}
