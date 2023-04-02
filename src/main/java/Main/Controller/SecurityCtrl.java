package Main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import Main.dao.AccountDAO;
import Main.entity.Account;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Controller
public class SecurityCtrl {
	/*
	 * @Autowired private UserServices userServices;
	 */
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	AccountDAO dao;


	@RequestMapping("/baomat/login/form")
	public String loginform(Model md) {
		md.addAttribute("ms", "Please Login");
		return "baomat/login";
	}

	@RequestMapping("/baomat/login/success")
	public String loginSuccess(Model md) {
		md.addAttribute("ms", "Login Successful");
		return "redirect:/sanpham/trangchu";
	}

	@RequestMapping("/baomat/login/error")
	public String loginError(Model md) {
		md.addAttribute("ms", "Login error");
		return "baomat/login";
	}

	@RequestMapping("/baomat/unauthoried")
	public String unauthoried(Model md) {
		md.addAttribute("ms", "Get out");
		return "baomat/login";
	}

	@RequestMapping("/baomat/logoff/success")
	public String logoutSuccess(Model md) {
		md.addAttribute("ms", "Logout Successful");
		return "baomat/login";
	}

	public void sendEmail(String recipientEmail) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("quangdodps15144@fpt.edu.vn");
		helper.setTo(recipientEmail);
		Account account = dao.findByEmail(recipientEmail);
		String subject = "Mật khẩu của bạn";

		String content = "<p>Xin chào,</p>" + "<p>Bạn đã yêu cầu gửi mật khẩu qua email</p>"
				+ "\">Mật khẩu của bạn là</a></h3>" + account.getPassword()
				+ "Cảm ơn bạn đã mua hàng tại Website của chúng tôi<br>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);

	}
}
