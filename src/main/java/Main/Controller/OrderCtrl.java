package Main.Controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Main.dao.OrderDAO;
import Main.dao.ProductDAO;
import Main.entity.Order;
import Main.entity.Translation;
import Main.service.OrderService;
import Main.service.TransactionService;

@Controller
public class OrderCtrl {
	
	@Autowired
	OrderService oSV;
	@Autowired
	OrderDAO o;
	@Autowired
	ProductDAO pd;
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping("/dathang/checkout")
	public String checkout() {
		return "dathang/checkout";
	}
	
	@RequestMapping("/dathang/danhsach")
	public String list(Model md, HttpServletRequest rq) {
		String username = rq.getRemoteUser();
		md.addAttribute("listOrders", oSV.findByUsername(username));
		return "dathang/danhsach";
	}
	
	@RequestMapping("/dathang/chitiet/{id}")
	public String detail(@PathVariable("id") Long id, Model md) {
		md.addAttribute("order", oSV.findById(id));
		String trangthai = oSV.findById(id).getStatus();
		md.addAttribute("trangthai", trangthai);
		if(trangthai.equalsIgnoreCase("Chờ xác nhận")) {
			Boolean coko = true;
			md.addAttribute("coko", coko);
		}else {
			Boolean coko = false;
			md.addAttribute("coko", coko);
		}
		return "dathang/chitiet";
	}
	
	@RequestMapping("/dathang/huydon/{id}")
	public String notbuy(Model md, @PathVariable("id") Long id, HttpServletRequest rq) {
		Long malaydc = id;
		o.huydon(malaydc);
		pd.CongSoLuong(malaydc);
		md.addAttribute("order", oSV.findById(id));
		String trangthai = oSV.findById(id).getStatus();
		md.addAttribute("trangthai", trangthai);
		if(trangthai.equalsIgnoreCase("Chờ xác nhận")) {
			Boolean coko = true;
			md.addAttribute("coko", coko);
		}else {
			Boolean coko = false;
			md.addAttribute("coko", coko);
		}
		return "dathang/chitiet";
	}
	
	@RequestMapping("/dathang/chitiet/admin/{id}")
	public String detailAdmin(@PathVariable("id") Long id, Model md) {
		md.addAttribute("order", oSV.findById(id));
		return "dathang/adminChitiet";
	}
	
	@RequestMapping("/saukhithanhtoan")
	public String saukhithanhtoan(@RequestParam("vnp_Amount") String vnp_Amount, @RequestParam("vnp_BankCode") String vnp_BankCode,
			@RequestParam("vnp_BankTranNo") String vnp_BankTranNo, @RequestParam("vnp_CardType") String vnp_CardType, 
			@RequestParam("vnp_OrderInfo") String vnp_OrderInfo, @RequestParam("vnp_PayDate") String vnp_PayDate, 
			@RequestParam("vnp_ResponseCode") String vnp_ResponseCode, @RequestParam("vnp_TmnCode") String vnp_TmnCode,
			@RequestParam("vnp_TransactionNo") String vnp_TransactionNo, @RequestParam("vnp_TransactionStatus") String vnp_TransactionStatus,
			@RequestParam("vnp_TxnRef") String vnp_TxnRef,
			HttpSession httpSession, Model md) {
		
		Translation tran = new Translation();
		tran.setAmount(Float.parseFloat(vnp_Amount));
		tran.setBankCode(vnp_BankCode);
		tran.setCardType(vnp_CardType);
		tran.setTranslationInfo(vnp_OrderInfo);
		Date date = new Date();
		tran.setPayDate(date);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		String payTime = simpleDateFormat.format(date);
		tran.setPayTime(payTime);
		tran.setPayStatus(vnp_TransactionStatus);
		tran.setTranslationNo(Long.parseLong(vnp_TransactionNo));
		Long orderId = (Long) httpSession.getAttribute("orderId");
		Order returnOrder = o.findById(orderId).get();
		tran.setOrder(returnOrder);
		tran.setBankTranNo(vnp_BankTranNo);
		Translation returnTranslation = transactionService.create(tran);
		// design
		md.addAttribute("transaction", returnTranslation);
		
		
		return "dathang/saukhithanhtoan";
	}
	
	
}
