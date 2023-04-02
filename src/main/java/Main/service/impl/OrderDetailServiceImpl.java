package Main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.OrderDetailDAO;
import Main.entity.OrderDetail;
import Main.service.OrderDetailService;
@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	OrderDetailDAO orderDetailDAO;
	@Override
	public List<OrderDetail> getOrderDetails(Long id) {
		return orderDetailDAO.getODbyOrderId(id);
	}
	
	@Override
	public Integer getTong() {
		return orderDetailDAO.Tongtien();
	}

	@Override
	public OrderDetail create(OrderDetail orderDetail) {
		return orderDetailDAO.save(orderDetail);
	}

}
