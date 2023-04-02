package Main.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import Main.entity.Order;

public interface OrderService {
	
	List<Order> findAllByOrderByIdDesc();
	
	Order create(JsonNode orderData);
	
	Order update(Order od);
	
	Order findById(Long id);

	List<Order> findByUsername(String username);
	
	Order create(Order order);

}
