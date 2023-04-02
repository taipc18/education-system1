package Main.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity 
@Table(name = "Statuss")
public class Statuss implements Serializable{
	@Id
	String id;
	String status;
	public Statuss(String id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public Statuss() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

