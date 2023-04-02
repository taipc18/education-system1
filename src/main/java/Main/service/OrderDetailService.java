package Main.service;

import java.util.List;
import Main.entity.OrderDetail;

public interface OrderDetailService {
	
	List<OrderDetail> getOrderDetails(Long id);
	
	Integer getTong();
	
	OrderDetail create(OrderDetail orderDetail);
}
