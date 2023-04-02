package Main.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Main.entity.Category;
import Main.service.CategoryService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoriesRestCtrl {
	@Autowired
	CategoryService cSV;
	
	
	@GetMapping()
	public List<Category> getAll(){
		return cSV.findAll();
	}
	
	@GetMapping("/DoanhThuTheoLoai")
	public List<Object> DoanhThuTheoLoai(){
		return cSV.DoanhThuTheoLoai();
	}
	
	@GetMapping("/SoLuongTheoLoai")
	public List<Object> SoLuongTheoLoai(){
		return cSV.SoLuongTheoLoai();
	}
	
	
}
