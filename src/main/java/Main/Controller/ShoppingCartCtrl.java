package Main.Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Main.Config.Config;
import Main.entity.Account;
import Main.entity.Order;
import Main.entity.OrderDetail;
import Main.entity.Product;
import Main.service.AccountService;
import Main.service.OrderDetailService;
import Main.service.OrderService;
import Main.service.ProductService;

@Controller
public class ShoppingCartCtrl {
	
	@Autowired
	AccountService accountService;
	@Autowired
	OrderService orderService;
	@Autowired
	ProductService productService;
	@Autowired
	OrderDetailService orderDetailService;
	@RequestMapping("/giohang/view")
	public String view() {
		return "giohang/view";
	}
	
	@RequestMapping("/test/checkout")
	public String d(HttpServletRequest req, HttpServletResponse resp, Authentication authentication,
			@RequestParam("currentDate") String currentDate, @RequestParam("Address") String Address,
			@RequestParam("productName") String[] productName, @RequestParam("productPrice") String[] productPrice,
			@RequestParam("productQuantity") String[] productQuantity, HttpSession httpSession) throws IOException {

			Order order = new Order();
			String username = authentication.getName();
			Account account = accountService.findById(username);
			order.setAccount(account);
			order.setAddress(Address);
			order.setCreateDate(new Date());
			order.setPaymentstatus("Chưa thanh toán");
			order.setStatus("Chờ xác nhận");
			Order createOrder = orderService.create(order);
			for(int i = 0; i < productName.length; i++) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setPrice(Double.parseDouble(productPrice[i]));
				orderDetail.setQuantity(Integer.parseInt(productQuantity[i]));
				Integer productId = Integer.parseInt(productName[i]);
				Product product = productService.findById(productId);
				orderDetail.setProduct(product);
				orderDetail.setOrder(createOrder);
				orderDetailService.create(orderDetail);
			}
			httpSession.setAttribute("orderId", createOrder.getId());
	        String vnp_Version = "2.1.0";
	        String vnp_Command = "pay";
	        String vnp_OrderInfo = "Test thanh toan online";
	        String vnp_TxnRef = Config.getRandomNumber(8);
	        String vnp_IpAddr = Config.getIpAddress(req);
	        String vnp_TmnCode = Config.vnp_TmnCode;
	        double price = 0.0;
	        List<OrderDetail> orderDetails = orderDetailService.getOrderDetails(createOrder.getId());
	        for(OrderDetail orderDetail : orderDetails) {
	        	price += orderDetail.getPrice() * orderDetail.getQuantity();
	        }
	        int amount = (int) price;
	        String orderType = "";
	        Map vnp_Params = new HashMap<>();
	        vnp_Params.put("vnp_Version", vnp_Version);
	        vnp_Params.put("vnp_Command", vnp_Command);
	        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
	        vnp_Params.put("vnp_Amount", String.valueOf(amount));
	        vnp_Params.put("vnp_CurrCode", "VND");
	        String bank_code = req.getParameter("bankcode");
	        if (bank_code != null && !bank_code.isEmpty()) {
	            vnp_Params.put("vnp_BankCode", bank_code);
	        }
	        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
	        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
	        vnp_Params.put("vnp_OrderType", orderType);

	        String locate = req.getParameter("language");
	        if (locate != null && !locate.isEmpty()) {
	            vnp_Params.put("vnp_Locale", locate);
	        } else {
	            vnp_Params.put("vnp_Locale", "vn");
	        }
	        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
	        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
	        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	        String vnp_CreateDate = formatter.format(cld.getTime());

	        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
	        cld.add(Calendar.MINUTE, 15);
//	        String vnp_ExpireDate = formatter.format(cld.getTime());
	        //Add Params of 2.1.0 Version
//	        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
	        //Billing
//	        vnp_Params.put("vnp_Bill_Mobile", req.getParameter("txt_billing_mobile"));
//	        vnp_Params.put("vnp_Bill_Email", req.getParameter("txt_billing_email"));
//	        String fullName = (req.getParameter("txt_billing_fullname")).trim();
//	        if (fullName != null && !fullName.isEmpty()) {
//	            int idx = fullName.indexOf(' ');
//	            String firstName = fullName.substring(0, idx);
//	            String lastName = fullName.substring(fullName.lastIndexOf(' ') + 1);
//	            vnp_Params.put("vnp_Bill_FirstName", firstName);
//	            vnp_Params.put("vnp_Bill_LastName", lastName);
//
//	        }
//	        vnp_Params.put("vnp_Bill_Address", req.getParameter("txt_inv_addr1"));
//	        vnp_Params.put("vnp_Bill_City", req.getParameter("txt_bill_city"));
//	        vnp_Params.put("vnp_Bill_Country", req.getParameter("txt_bill_country"));
//	        if (req.getParameter("txt_bill_state") != null && !req.getParameter("txt_bill_state").isEmpty()) {
//	            vnp_Params.put("vnp_Bill_State", req.getParameter("txt_bill_state"));
//	        }
//	        // Invoice
//	        vnp_Params.put("vnp_Inv_Phone", req.getParameter("txt_inv_mobile"));
//	        vnp_Params.put("vnp_Inv_Email", req.getParameter("txt_inv_email"));
//	        vnp_Params.put("vnp_Inv_Customer", req.getParameter("txt_inv_customer"));
//	        vnp_Params.put("vnp_Inv_Address", req.getParameter("txt_inv_addr1"));
//	        vnp_Params.put("vnp_Inv_Company", req.getParameter("txt_inv_company"));
//	        vnp_Params.put("vnp_Inv_Taxcode", req.getParameter("txt_inv_taxcode"));
//	        vnp_Params.put("vnp_Inv_Type", req.getParameter("cbo_inv_type"));
	        //Build data to hash and querystring
	        List fieldNames = new ArrayList(vnp_Params.keySet());
	        Collections.sort(fieldNames);
	        StringBuilder hashData = new StringBuilder();
	        StringBuilder query = new StringBuilder();
	        Iterator itr = fieldNames.iterator();
	        while (itr.hasNext()) {
	            String fieldName = (String) itr.next();
	            String fieldValue = (String) vnp_Params.get(fieldName);
	            if ((fieldValue != null) && (fieldValue.length() > 0)) {
	                //Build hash data
	                hashData.append(fieldName);
	                hashData.append('=');
	                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
	                //Build query
	                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
	                query.append('=');
	                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
	                if (itr.hasNext()) {
	                    query.append('&');
	                    hashData.append('&');
	                }
	            }
	        }
	        String queryUrl = query.toString();
	        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
	        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
	        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
	        com.google.gson.JsonObject job = new JsonObject();
	        job.addProperty("code", "00");
	        job.addProperty("message", "success");
	        job.addProperty("data", paymentUrl);
	        Gson gson = new Gson();
	        resp.getWriter().write(gson.toJson(job));
	        return "redirect:" + paymentUrl;
		
		
	}
}
