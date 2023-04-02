package Main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.ProductDAO;
import Main.entity.Product;
import Main.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDAO pdao;

	@Override
	public List<Product> findAll() {
		return pdao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return pdao.findById(id).get();
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		return pdao.findByCategoryId(cid);
	}

	@Override
	public Product create(Product pr) {
		return pdao.save(pr);
	}

	@Override
	public Product update(Product pr) {
		return pdao.save(pr);
	}

	@Override
	public void delete(Integer id) {
		pdao.deleteById(id);
	}

	@Override
	public List<Product> findAllByOrderByIdDesc() {
		return pdao.findAllByOrderByIdDesc();
	}

	@Override
	public void TruSoLuong(Long id) {
		pdao.TruSoLuong(id);
	}
}
