package Main.service;

import java.util.List;

import Main.entity.Product;

public interface ProductService {

	List<Product> findAll();

	Product findById(Integer id);

	List<Product> findByCategoryId(String cid);

	Product create(Product pr);

	Product update(Product pr);

	void delete(Integer id);

	List<Product> findAllByOrderByIdDesc();

	void TruSoLuong(Long id);
	
}
