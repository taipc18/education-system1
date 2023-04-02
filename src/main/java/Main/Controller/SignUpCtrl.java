package Main.Controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import Main.Util.PasswordUtil;
import Main.entity.Account;
import Main.service.AccountService;
import Main.service.AuthorityService;
import Main.service.MailService;

@Controller
public class SignUpCtrl {
	@Autowired
	AccountService aSV;
	@Autowired
	AuthorityService authSV;
	@Autowired
	PasswordUtil passwordUtil;
	@Autowired
	MailService mailService;
	@Autowired
	HttpSession httpSession;
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping("/baomat/signup/form")
	public String signup(Model model) {
		Account account = new Account();
		model.addAttribute("account", account);
		return "baomat/signup";
	}
	@RequestMapping("/xacnhan")
	public String assertAccount() {
		return "baomat/xacnhantaikhoan";
	}
	@PostMapping("/baomat/dangki")
	public String signup(Model model,@ModelAttribute("account") Account account
			,Errors errors) {
		Account accountName = aSV.findById(account.getUsername());
		if(accountName!=null) {
			Account acc = aSV.findById(account.getUsername());
			String mailacc = acc.getEmail();
			boolean activeacc= acc.isActive();
			if(activeacc) {
				model.addAttribute("message", "Tài khoản đã tồn tại!");
				return "baomat/signup";
			} else {
				account.setActive(false);
				/* aSV.createAccount(account); */
				authSV.authNewUser(account);
				// gửi mail chứa mã xác nhận về tài khoản
				String codedk = String.valueOf(passwordUtil.generatePassword(6));
				String subject = "Xác nhận tài khoản ";
				String bodyMail = "Mã xác nhận đăng kí tài khoản: " + account.getUsername() + " là " + codedk +"\n";
				String to = mailacc;
				try {
					mailService.send(to, subject, bodyMail);
					model.addAttribute("message", "Tài khoản đã tồn tại nhưng chưa được kích hoạt. Mã kích hoạt đã được gửi về email: "+ acc.getEmail());
					httpSession.setAttribute("codedk", codedk);
					httpSession.setAttribute("userdk", account.getUsername());
					return "baomat/xacnhantaikhoan";
				} catch (MessagingException e) {
					e.printStackTrace();
					model.addAttribute("message", "lỗi");
					return "baomat/signup";
				}
			}
		} else {
			account.setActive(false);
			aSV.createAccount(account);
			authSV.authNewUser(account);
			String codedk = String.valueOf(passwordUtil.generatePassword(6));
			String subject = "Xác nhận tài khoản ";
			String bodyMail = "Mã xác nhận đăng kí tài khoản: " + account.getUsername() + " là " + codedk +"\n";
			String to = account.getEmail();
			try {
				mailService.send(to, subject, bodyMail);
				model.addAttribute("message", "Mã xác nhận đã được gửi! Vui lòng truy cập email để xác nhận tài khoản");
				httpSession.setAttribute("codedk", codedk);
				httpSession.setAttribute("userdk", account.getUsername());
				return "baomat/xacnhantaikhoan";
			} catch (MessagingException e) {
				e.printStackTrace();
				model.addAttribute("message", "lỗi");
				return "baomat/signup";
			}
		}
	}
	
	@RequestMapping("/baomat/xacnhantaikhoan")
	public String submitNewpassword(@RequestParam("cf-signup") String code,
									Model model) {
		String codecf = (String) httpSession.getAttribute("codedk");
		String userdk = (String) httpSession.getAttribute("userdk");
		if(!code.equals(codecf)) {
			model.addAttribute("message", "Mã xác nhận không hợp lệ");
			return "baomat/xacnhantaikhoan";
		}
		String url = "http://localhost:8080/rest/accounts/" + userdk;
		Account account = restTemplate.getForObject(url, Account.class);
		account.setActive(true);
		HttpEntity<Account> entity = new HttpEntity<Account>(account);
		restTemplate.put(url, entity);
		model.addAttribute("ms","SignUp Success");
		return "baomat/login";
	}
}
