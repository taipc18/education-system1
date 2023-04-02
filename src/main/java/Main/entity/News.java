package Main.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity @Table(name = "News")
public class News implements Serializable{
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	String image;
	String description;
	String opening;
	String body1;
	String body2;
	String ending;
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate = new Date();
	String writer;
	
	public News(Integer id, String name, String image, String description, String opening, String body1, String body2,
			String ending, Date createDate, String writer) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.opening = opening;
		this.body1 = body1;
		this.body2 = body2;
		this.ending = ending;
		this.createDate = createDate;
		this.writer = writer;
	}
	
	public News() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOpening() {
		return opening;
	}

	public void setOpening(String opening) {
		this.opening = opening;
	}

	public String getBody1() {
		return body1;
	}

	public void setBody1(String body1) {
		this.body1 = body1;
	}

	public String getBody2() {
		return body2;
	}

	public void setBody2(String body2) {
		this.body2 = body2;
	}

	public String getEnding() {
		return ending;
	}

	public void setEnding(String ending) {
		this.ending = ending;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}	
}
