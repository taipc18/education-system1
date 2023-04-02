package Main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Main.entity.OrderDetail;


public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
	
	@Query("Select od From OrderDetail od where od.order.id=?1")
	List<OrderDetail> getODbyOrderId(Long id);
	
	@Query(value = "SELECT sum(od.price*od.quantity) from Order_Detail od inner join Orders o on od.Orderid = o.id where o.Status = 'Giao thành công'", nativeQuery = true)
	int Tongtien();
}
