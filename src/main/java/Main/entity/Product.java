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
@Entity @Table(name = "Products")
public class Product implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	String image;
	String image1;
	String image2;
	String image3;
	Double price;
	Integer quantity;
	String describe1;
	String describe2;
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate = new Date();
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<ProductReviews> productReviews;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Favorite> favorites;	

	public Product(Integer id, String name, String image, String image1, String image2, String image3, Double price,
			Integer quantity, String describe1, String describe2, Date createDate, Category category,
			List<OrderDetail> orderDetails, List<ProductReviews> productReviews, List<Favorite> favorites) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.price = price;
		this.quantity = quantity;
		this.describe1 = describe1;
		this.describe2 = describe2;
		this.createDate = createDate;
		this.category = category;
		this.orderDetails = orderDetails;
		this.productReviews = productReviews;
		this.favorites = favorites;
	}

	public Product() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public List<ProductReviews> getProductReviews() {
		return productReviews;
	}

	public void setProductReviews(List<ProductReviews> productReviews) {
		this.productReviews = productReviews;
	}

	public String getDescribe1() {
		return describe1;
	}

	public void setDescribe1(String describe1) {
		this.describe1 = describe1;
	}

	public String getDescribe2() {
		return describe2;
	}

	public void setDescribe2(String describe2) {
		this.describe2 = describe2;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}	
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	
	
}
