package Main.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Main.dao.OrderDAO;
import Main.dao.OrderDetailDAO;
import Main.entity.Order;
import Main.entity.OrderDetail;
import Main.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO odao;
	
	@Autowired
	OrderDetailDAO ddao;

	@Override
	public Order create(JsonNode orderData) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Order order = mapper.convertValue(orderData, Order.class);
			odao.save(order);
			TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
			List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
					.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
			ddao.saveAll(details);
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Order update(Order od) {
		return odao.save(od);
	}
	
	@Override
	public Order findById(Long id) {
		return odao.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		return odao.findByUsername(username);
	}

	@Override
	public List<Order> findAllByOrderByIdDesc() {
		return odao.findAllByOrderByIdDesc();
	}

	@Override
	public Order create(Order order) {
		return odao.save(order);
	}

}
