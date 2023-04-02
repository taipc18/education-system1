package Main.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import Main.entity.OrderDetail;
import Main.service.OrderDetailService;
@RestController
@CrossOrigin("*")
public class OrderDetailRestCtrl {
	@Autowired
	OrderDetailService orderDetailService;
	
	
	@GetMapping("/api/orders/{id}")
	public List<OrderDetail> getListOrderDetail(@PathVariable("id") Long id) {
		return orderDetailService.getOrderDetails(id);
	}
	
	
	@GetMapping("/api/orders/hientong")
	public Integer getTong() {
		return orderDetailService.getTong();
	}
}
