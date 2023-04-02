package Main.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@SuppressWarnings("serial")
@Entity 
@Table(name = "Orders")
public class Order  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String address;
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate = new Date();
	@ManyToOne
	@JoinColumn(name = "Username")
	Account account;
	String status;
	String Paymentstatus;
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails;

	
	
	public Order(Long id, String address, Date createDate, Account account, String status, String paymentstatus,
			List<OrderDetail> orderDetails) {
		super();
		this.id = id;
		this.address = address;
		this.createDate = createDate;
		this.account = account;
		this.status = status;
		Paymentstatus = paymentstatus;
		this.orderDetails = orderDetails;
	}

	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getPaymentstatus() {
		return Paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		Paymentstatus = paymentstatus;
	}
	
	
}