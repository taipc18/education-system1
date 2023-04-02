package Main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.ProductReviewsDAO;
import Main.entity.ProductReviews;
import Main.service.ProductReviewsService;


@Service
public class ProductReviewsServiceImpl implements ProductReviewsService{
	
	@Autowired
	ProductReviewsDAO rrdao;

	@Override
	public ProductReviews create(ProductReviews pr) {
		return rrdao.save(pr);
	}

	@Override
	public List<ProductReviews> findAllByProductId(Integer pid) {
		return rrdao.findAllByProductId(pid);
	}
	


}
