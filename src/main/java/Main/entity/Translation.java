package Main.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity 
@Table(name = "Translation")
public class Translation implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Float amount;
	String BankCode;
	String CardType;
	String TranslationInfo;
	Date PayDate;
	String PayTime;
	String PayStatus;
	Long TranslationNo;
	@OneToOne
	@JoinColumn(name = "OrderId")
	Order order;
	String BankTranNo;
	
	
	
	
	public Translation(Long id, Float amount, String bankCode, String cardType, String translationInfo, Date payDate,
			String payTime, String payStatus, Long translationNo, Order order, String bankTranNo) {
		super();
		this.id = id;
		this.amount = amount;
		BankCode = bankCode;
		CardType = cardType;
		TranslationInfo = translationInfo;
		PayDate = payDate;
		PayTime = payTime;
		PayStatus = payStatus;
		TranslationNo = translationNo;
		this.order = order;
		BankTranNo = bankTranNo;
	}

	public Translation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getBankCode() {
		return BankCode;
	}

	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}

	public String getCardType() {
		return CardType;
	}

	public void setCardType(String cardType) {
		CardType = cardType;
	}

	public String getTranslationInfo() {
		return TranslationInfo;
	}

	public void setTranslationInfo(String translationInfo) {
		TranslationInfo = translationInfo;
	}

	public Date getPayDate() {
		return PayDate;
	}

	public void setPayDate(Date payDate) {
		PayDate = payDate;
	}

	public String getPayTime() {
		return PayTime;
	}

	public void setPayTime(String payTime) {
		PayTime = payTime;
	}

	public String getPayStatus() {
		return PayStatus;
	}

	public void setPayStatus(String payStatus) {
		PayStatus = payStatus;
	}

	public Long getTranslationNo() {
		return TranslationNo;
	}

	public void setTranslationNo(Long translationNo) {
		TranslationNo = translationNo;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getBankTranNo() {
		return BankTranNo;
	}

	public void setBankTranNo(String bankTranNo) {
		BankTranNo = bankTranNo;
	}

	
	
	
	
}
	
	
	