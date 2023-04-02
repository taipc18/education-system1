package Main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminCtrl {
	@RequestMapping("/admin")
	public String home() {
		return "quanly/index";
	}
}
