package Main.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import Main.dao.FavoriteDAO;
import Main.entity.Favorite;


@Controller
public class HomeCtrl {
	
	@Autowired
	FavoriteDAO fdao;
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/sanpham/trangchu";
	}
	
	@RequestMapping("/test")
	public String test(Model md) {
		String ten = "ADMIN";
		List<Favorite> list = fdao.findByUsername(ten);
		md.addAttribute("list", list);
		return "taikhoan/test";
	}
	
	
}
