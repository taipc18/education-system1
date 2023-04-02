package Main.service;

import java.util.List;

import Main.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	List<Object> DoanhThuTheoLoai();

	List<Object> SoLuongTheoLoai();

}
