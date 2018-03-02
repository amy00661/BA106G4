package iii.size.model;

import java.sql.Timestamp;

public class SizeVO {
	private String size_id;
	private String emp_id;
	private String size_type;
	private Double size_price;
	private Timestamp size_updateTime;
	public String getSize_id() {
		return size_id;
	}
	public void setSize_id(String size_id) {
		this.size_id = size_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getSize_type() {
		return size_type;
	}
	public void setSize_type(String size_type) {
		this.size_type = size_type;
	}
	public Double getSize_price() {
		return size_price;
	}
	public void setSize_price(Double size_price) {
		this.size_price = size_price;
	}
	public Timestamp getSize_updateTime() {
		return size_updateTime;
	}
	public void setSize_updateTime(Timestamp size_updateTime) {
		this.size_updateTime = size_updateTime;
	}
}
