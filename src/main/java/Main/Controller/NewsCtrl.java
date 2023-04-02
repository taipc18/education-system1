package Main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Main.entity.News;
import Main.service.NewsService;



@Controller
public class NewsCtrl {
	
	@Autowired
	NewsService nSV;

	@RequestMapping("/tintuc/chitiet/{id}")
	public String detail(Model md, @PathVariable("id") Integer id) {
		News itemNews = nSV.findById(id);
		md.addAttribute("itemNews", itemNews);
		return "tintuc/chitiet";
	}
}
