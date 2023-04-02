package Main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Main.entity.ProductReviews;


public interface ProductReviewsDAO extends JpaRepository<ProductReviews, Integer>{

	@Query("SELECT r FROM ProductReviews r WHERE r.product.id=?1")
	List<ProductReviews> findAllByProductId(Integer rid);
}
