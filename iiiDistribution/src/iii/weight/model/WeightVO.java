package iii.weight.model;

import java.sql.Timestamp;

public class WeightVO {
	
	private String weight_id;
	private String emp_id;
	private String weight_type;
	private Double weight_price;
	private Timestamp weight_updateTime;
	public String getWeight_id() {
		return weight_id;
	}
	public void setWeight_id(String weight_id) {
		this.weight_id = weight_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getWeight_type() {
		return weight_type;
	}
	public void setWeight_type(String weight_type) {
		this.weight_type = weight_type;
	}
	public Double getWeight_price() {
		return weight_price;
	}
	public void setWeight_price(Double weight_price) {
		this.weight_price = weight_price;
	}
	public Timestamp getWeight_updateTime() {
		return weight_updateTime;
	}
	public void setWeight_updateTime(Timestamp weight_updateTime) {
		this.weight_updateTime = weight_updateTime;
	}
	
}
