package Main.service;

import java.util.List;

import Main.entity.ProductReviews;


public interface ProductReviewsService {
	
	List<ProductReviews> findAllByProductId(Integer pid);
	
	ProductReviews create(ProductReviews pr);
}
