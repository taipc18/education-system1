package Main.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Main.dao.FavoriteDAO;
import Main.entity.Product;
import Main.entity.ProductReviews;
import Main.service.NewsService;
import Main.service.ProductReviewsService;
import Main.service.ProductService;

@Controller
public class ProductCtrl {
	@Autowired
	ProductService prSV;
	@Autowired
	NewsService nSV;
	@Autowired
	ProductReviewsService prreSV;
	@Autowired
	FavoriteDAO fdao;
	
	@RequestMapping("/sanpham/trangchu")
	public String list(Model md) {
		return "sanpham/trangchu";
	}
	
	@RequestMapping("/sanpham/trangchu/loai")
	public String list2(Model md, @RequestParam("cid") Optional<String> cid) {
		if(cid.isPresent()) {
			List<Product> list = prSV.findByCategoryId(cid.get());
			md.addAttribute("items", list);
		}
		return "sanpham/theoloai";
	}
	
	@RequestMapping("/sanpham/chitiet/{id}")
	public String detail(Model md, @PathVariable("id") Integer id, HttpServletRequest rq) {
		Product item = prSV.findById(id);
		md.addAttribute("item", item);
		
		Integer malaydc = id;
		String username = rq.getRemoteUser();
		md.addAttribute("masp", malaydc);
		List<ProductReviews> pdreviews = prreSV.findAllByProductId(malaydc);
		md.addAttribute("pdreviews", pdreviews);
		String coko = fdao.coko(username, id);
		md.addAttribute("coko", coko);
		return "sanpham/chitiet";
	}
	
	@RequestMapping("/sanpham/yeuthich")
	public String favourite() {
		return "sanpham/yeuthich";
	}
	
	@RequestMapping("/sanpham/bothich/{id}")
	public String unfavourite(Model md, @PathVariable("id") Integer id, HttpServletRequest rq) {
		Integer malaydc = id;
		md.addAttribute("masp", malaydc);
		String username = rq.getRemoteUser();
		fdao.delete(username, malaydc);
		Product item = prSV.findById(id);
		md.addAttribute("item", item);
		List<ProductReviews> pdreviews = prreSV.findAllByProductId(malaydc);
		md.addAttribute("pdreviews", pdreviews);
		return "sanpham/chitiet";
	}
	
	@RequestMapping("/layout/index")
	public String layout() {
		return "layout/index";
	}
}
