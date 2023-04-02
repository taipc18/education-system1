package Main.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import Main.Util.PasswordUtil;
import Main.entity.Account;
import Main.service.AccountService;
import Main.service.MailService;

@Controller
public class AccountCtrl {
	@Autowired
	AccountService aSV;
	@Autowired
	PasswordUtil passwordUtil;
	@Autowired
	MailService mailService;
	@Autowired
	HttpSession httpSession;
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping("/taikhoan/caidat/{id}")
	public String doithongtin(Model md, @PathVariable("id") String id) {
		return "taikhoan/caidat";
	}
	
	@RequestMapping("/taikhoan/doimatkhau/{id}")
	public String doimatkhau(Model md, @PathVariable("id") String id) {
		return "taikhoan/xacthucmatkhau";
	}
	
	@RequestMapping("/taikhoan/doi/{id}")
	public String doimatkhau2(Model md, @PathVariable("id") String id) {
		return "taikhoan/doimatkhau";
	}
	
	private boolean checkStringLength(String string,int min, int max) {
		if((string.length() < min) || string.length() > max) {
			return false;
		}
		return true;
	}
	
	@RequestMapping("/baomat/quenmatkhau/form")
	public String loadforgotPassword() {
		return "baomat/quenmatkhau";
	}
	
	@RequestMapping("/baomat/xx/form")
	public String kkk() {
		return "baomat/datlaimatkhau";
	}
	
	@RequestMapping("/baomat/laylaimatkhau")
	public String restorePassword(@RequestParam("username") String username,
			Model model) {
		String url = "http://localhost:8080/rest/accounts/" + username;
		try {
			Account account = restTemplate.getForObject(url, Account.class);
			String subject = "Quên mật khẩu";
			String to = account.getEmail();
			String code = String.valueOf(passwordUtil.generatePassword(6));
			String body = "Mã xác nhận của bạn là " + code;
			mailService.send(to, subject, body);
			httpSession.setAttribute("code", code);
			httpSession.setAttribute("currentForgotpasswordUser", account.getUsername());
			return "baomat/datlaimatkhau";
		} catch (Exception e) {
			model.addAttribute("message","Tài khoản không hợp lệ");
			return "baomat/quenmatkhau";
		}
	}
	
	@RequestMapping("/baomat/xacnhanmatkhau")
	public String submitNewpassword(@RequestParam("new-password") String password,
									@RequestParam("cf-new-password") String newPassword,
									@RequestParam("verify-code") String verifyCode,
									Model model) {
		String currentUser = (String) httpSession.getAttribute("currentForgotpasswordUser");
		String code = (String) httpSession.getAttribute("code");
		if(password.trim().length() == 0 || newPassword.trim().length() == 0 || verifyCode.trim().length() == 0) {
			model.addAttribute("message", "Vui lòng nhập đầy đủ thông tin");
			return "baomat/datlaimatkhau";
		}
		if((!checkStringLength(password, 5, 30)) || (!checkStringLength(newPassword, 5, 30))) {
			model.addAttribute("message", "Mật khẩu phải có tối thiểu 5 kí tự");
			return "baomat/datlaimatkhau";
		}
		if(!password.equals(newPassword)) {
			model.addAttribute("message", "Xác nhận mật khẩu không khớp");
			return "baomat/datlaimatkhau";
		}
		if(!code.equals(verifyCode)) {
			model.addAttribute("message", "Mã xác nhận không hợp lệ");
			return "baomat/datlaimatkhau";
		}
		String url = "http://localhost:8080/rest/accounts/" + currentUser;
		Account account = restTemplate.getForObject(url, Account.class);
		account.setPassword(newPassword);
		HttpEntity<Account> entity = new HttpEntity<Account>(account);
		restTemplate.put(url, entity);
		model.addAttribute("message","Đặt lại mật khẩu thành công");
		return "baomat/datlaimatkhau";
	}
}
