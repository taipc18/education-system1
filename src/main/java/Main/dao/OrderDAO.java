package Main.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import Main.entity.Order;


public interface OrderDAO extends JpaRepository<Order, Long>{

	@Query("SELECT o FROM Order o WHERE o.account.username=?1 ORDER BY o.id DESC")
	List<Order> findByUsername(String username);
	
	public List<Order> findAllByOrderByIdDesc();
	@Modifying
	@Transactional
	@Query(value = "UPDATE o set o.Status = N'Đã huỷ đơn hàng' "
			+ "From Orders o INNER JOIN Order_Detail od "
			+ "on od.Orderid = o.id where od.Orderid=?1", nativeQuery = true)
	void huydon(Long madonhang);
}
