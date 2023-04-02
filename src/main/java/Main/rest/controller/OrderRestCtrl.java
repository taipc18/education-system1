package Main.rest.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import Main.entity.Order;
import Main.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestCtrl {
	@Autowired
	OrderService oSV;
	@Autowired
	private JavaMailSender mailSender;

	@PostMapping()
	public Order create(@RequestBody JsonNode order) {
		return oSV.create(order);
	}

	@GetMapping()
	public List<Order> getAll() {
		return oSV.findAllByOrderByIdDesc();
	}

	@PutMapping("{id}")
	public Order update(@PathVariable("id") Integer id, @RequestBody Order od) throws UnsupportedEncodingException, MessagingException {
		String em= od.getAccount().getEmail();
		if (od.getStatus().equals("Đã xác nhận")) {
			sendEmail(em,"Đơn hàng của bạn đã được xác nhận");
		} else if(od.getStatus().equals("Đã huỷ đơn hàng")){
			sendEmail(em,"Đơn hàng của bạn đã bị huỷ");
		} else {
			sendEmail(em,"Giao hàng thành công");
		}
		
		return oSV.update(od);
	}

	public void sendEmail(String recipientEmail, String contentt)
			throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("CityZen Shop<Toy-World>");
		helper.setTo(recipientEmail);
		String subject = "Trạng thái đơn hàng";
		String content = "đã xác nhận đơn hàng";

		helper.setSubject(subject);
		helper.setText(contentt, true);

		mailSender.send(message);

	}

}
