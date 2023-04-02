package Main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewCtrl {
	
	@RequestMapping("/danhgia/index")
	public String home() {
		return "danhgia/index";
	}
}
