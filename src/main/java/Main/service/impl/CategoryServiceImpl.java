package Main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.CategoryDAO;
import Main.entity.Category;
import Main.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDAO cdao;

	@Override
	public List<Category> findAll(){
		return cdao.findAll();
	}

	@Override
	public List<Object> DoanhThuTheoLoai() {
		return cdao.DoanhThuTheoLoai();
	}

	@Override
	public List<Object> SoLuongTheoLoai() {
		return cdao.SoLuongTheoLoai();
	}
	
}
