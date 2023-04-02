package Main.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "Favorites", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"Username", "Productid"})
})
public class Favorite implements Serializable{

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne @JoinColumn(name = "Username")
	private Account account;
	@ManyToOne  @JoinColumn(name = "Productid")
	private Product product;
	String have;
	
	
	
	
	
	
	public Favorite(Integer id, Account account, Product product, String have) {
		super();
		this.id = id;
		this.account = account;
		this.product = product;
		this.have = have;
	}



	public Favorite() {
		super();
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Account getAccount() {
		return account;
	}



	public void setAccount(Account account) {
		this.account = account;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public String getHave() {
		return have;
	}



	public void setHave(String have) {
		this.have = have;
	}
	
	
	
	
}
