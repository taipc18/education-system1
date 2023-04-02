package Main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import Main.entity.Product;



public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(String cid);
	
	public List<Product> findAllByOrderByIdDesc();
	@Modifying
	@Transactional
	@Query(value = "UPDATE p set p.Quantity = p.Quantity - o.Quantity "
			+ "from Products p INNER JOIN Order_Detail "
			+ "o on p.id = o.Productid Where Orderid=?1", nativeQuery = true)
	void TruSoLuong(Long madonhang);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE p set p.Quantity = p.Quantity + o.Quantity "
			+ "from Products p INNER JOIN Order_Detail "
			+ "o on p.id = o.Productid Where Orderid=?1", nativeQuery = true)
	void CongSoLuong(Long madonhang);
}
