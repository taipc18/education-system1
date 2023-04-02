package Main.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Main.entity.Product;
import Main.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestCtrl {
	
	@Autowired
	ProductService prSV;
	
	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return prSV.findById(id);
	}
// khi chọn chức năng nào đó sẽ thực thi chức năng đó dẩn đến implements 
	
	@GetMapping()
	public List<Product> getAll() {
		return prSV.findAll();
	}
	
	@GetMapping("/findAllByOrderByIdDesc")
	public List<Product> findAllByOrderByIdDesc() {
		return prSV.findAllByOrderByIdDesc();
	}
	
	@PostMapping
	public Product create(@RequestBody Product pr) {
		return prSV.create(pr);
	}
	
	@PutMapping("/trusoluong/{id}")
	public void trusoluong(@PathVariable("id") Long id) {
		prSV.TruSoLuong(id);
	}
	
	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product pr) {
		return prSV.update(pr);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		prSV.delete(id);
	}
}
