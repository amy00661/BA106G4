package iii.opinion.model;

import java.sql.Timestamp;

public class OpinionVO {
	private String opinion_id;
	private String emp_id;
	private String opinion_type;
	private String opinion_name;
	private String opinion_phone;
	private String opinion_cell;
	private String opinion_email;
	private String opinion_content;
	private String opinion_status;
	private Timestamp opinion_createTime;
	private Timestamp opinion_updateTime;
	
	@Override
	public String toString() {
		return "意見編號:"+opinion_id+"\t處理人員:"+emp_id+"\t意見類型:"+opinion_type+"\t顧客姓名:"+opinion_name
				+"\t聯絡電話:"+opinion_phone+"\t客戶手機:"+opinion_cell+"\t顧客信箱:"+opinion_email+"\t意見內容:"+opinion_content
				+"\t處理狀態:"+opinion_status+"\t提出時間:"+opinion_createTime+"\t更新時間:"+opinion_updateTime;
	}
	public String getOpinion_id() {
		return opinion_id;
	}
	public void setOpinion_id(String opinion_id) {
		this.opinion_id = opinion_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getOpinion_type() {
		return opinion_type;
	}
	public void setOpinion_type(String opinion_type) {
		this.opinion_type = opinion_type;
	}
	public String getOpinion_name() {
		return opinion_name;
	}
	public void setOpinion_name(String opinion_name) {
		this.opinion_name = opinion_name;
	}
	public String getOpinion_phone() {
		return opinion_phone;
	}
	public void setOpinion_phone(String opinion_phone) {
		this.opinion_phone = opinion_phone;
	}
	public String getOpinion_cell() {
		return opinion_cell;
	}
	public void setOpinion_cell(String opinion_cell) {
		this.opinion_cell = opinion_cell;
	}
	
	public String getOpinion_email() {
		return opinion_email;
	}
	public void setOpinion_email(String opinion_email) {
		this.opinion_email = opinion_email;
	}
	public String getOpinion_content() {
		return opinion_content;
	}
	public void setOpinion_content(String opinion_content) {
		this.opinion_content = opinion_content;
	}
	public String getOpinion_status() {
		return opinion_status;
	}
	public void setOpinion_status(String opinion_status) {
		this.opinion_status = opinion_status;
	}
	public Timestamp getOpinion_createTime() {
		return opinion_createTime;
	}
	public void setOpinion_createTime(Timestamp opinion_createTime) {
		this.opinion_createTime = opinion_createTime;
	}
	public Timestamp getOpinion_updateTime() {
		return opinion_updateTime;
	}
	public void setOpinion_updateTime(Timestamp opinion_updateTime) {
		this.opinion_updateTime = opinion_updateTime;
	}
	
}
