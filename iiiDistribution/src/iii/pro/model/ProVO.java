package iii.pro.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ProVO {
	private String promotion_id;
	private String emp_id;
	private Date promotion_date;
	private String promotion_title;
	private String promotion_context;
	private String promotion_note;
	private Date promotion_start;
	private Date promotion_end;
	private byte[] promotion_picture;
	private Double promotion_discount;
	private Timestamp promotion_updatetime;
	
	public String getPromotion_id() {
		return promotion_id;
	}
	public void setPromotion_id(String promotion_id) {
		this.promotion_id = promotion_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public Date getPromotion_date() {
		return promotion_date;
	}
	public void setPromotion_date(Date promotion_date) {
		this.promotion_date = promotion_date;
	}
	public String getPromotion_title() {
		return promotion_title;
	}
	public void setPromotion_title(String promotion_title) {
		this.promotion_title = promotion_title;
	}
	public String getPromotion_context() {
		return promotion_context;
	}
	public void setPromotion_context(String promotion_context) {
		this.promotion_context = promotion_context;
	}
	public String getPromotion_note() {
		return promotion_note;
	}
	public void setPromotion_note(String promotion_note) {
		this.promotion_note = promotion_note;
	}
	public Date getPromotion_start() {
		return promotion_start;
	}
	public void setPromotion_start(Date promotion_start) {
		this.promotion_start = promotion_start;
	}
	public Date getPromotion_end() {
		return promotion_end;
	}
	public void setPromotion_end(Date promotion_end) {
		this.promotion_end = promotion_end;
	}
	public Double getPromotion_discount() {
		return promotion_discount;
	}
	public void setPromotion_discount(Double promotion_discount) {
		this.promotion_discount = promotion_discount;
	}
	
	public byte[] getPromotion_picture() {
		return promotion_picture;
	}
	public void setPromotion_picture(byte[] promotion_picture) {
		this.promotion_picture = promotion_picture;
	}

	public Timestamp getPromotion_updatetime() {
		return promotion_updatetime;
	}
	public void setPromotion_updatetime(Timestamp promotion_updatetime) {
		this.promotion_updatetime = promotion_updatetime;
	}

}
