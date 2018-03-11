package android.order_main.model;

import java.sql.Timestamp;

public class OrderVO {
	private String order_id;
	private String emp_id;
	private String mem_id;
	private String db_id;
	private String order_status;
	private String payment_type;
	private Double fee;
	private Double extrafee;
	private Double item_size;
	private Double item_weight;
	private String item_type;
	private Timestamp create_time;
	private String receiver_name;
	private String receiver_tel;
	private String receiver_cell;
	private String receiver_city;
	private String receiver_county;
	private String receiver_address;
	private String receiver_mail;
	private String sender_name;
	private String sender_tel;
	private String sender_cell;
	private String sender_city;
	private String sender_county;
	private String sender_address;
	private Timestamp expected_time;
	private String order_note;
	private Timestamp order_updatetime;
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getDb_id() {
		return db_id;
	}
	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Double getExtrafee() {
		return extrafee;
	}
	public void setExtrafee(Double extrafee) {
		this.extrafee = extrafee;
	}
	public Double getItem_size() {
		return item_size;
	}
	public void setItem_size(Double item_size) {
		this.item_size = item_size;
	}
	public Double getItem_weight() {
		return item_weight;
	}
	public void setItem_weight(Double item_weight) {
		this.item_weight = item_weight;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_tel() {
		return receiver_tel;
	}
	public void setReceiver_tel(String receiver_tel) {
		this.receiver_tel = receiver_tel;
	}
	public String getReceiver_city() {
		return receiver_city;
	}
	public void setReceiver_city(String receiver_city) {
		this.receiver_city = receiver_city;
	}
	public String getReceiver_county() {
		return receiver_county;
	}
	public void setReceiver_county(String receiver_county) {
		this.receiver_county = receiver_county;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public String getReceiver_mail() {
		return receiver_mail;
	}
	public void setReceiver_mail(String receiver_mail) {
		this.receiver_mail = receiver_mail;
	}
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	public String getSender_tel() {
		return sender_tel;
	}
	public void setSender_tel(String sender_tel) {
		this.sender_tel = sender_tel;
	}
	public Timestamp getExpected_time() {
		return expected_time;
	}
	public void setExpected_time(Timestamp expected_time) {
		this.expected_time = expected_time;
	}
	public String getOrder_note() {
		return order_note;
	}
	public void setOrder_note(String order_note) {
		this.order_note = order_note;
	}
	public Timestamp getOrder_updatetime() {
		return order_updatetime;
	}
	public void setOrder_updatetime(Timestamp order_updatetime) {
		this.order_updatetime = order_updatetime;
	}
	public String getReceiver_cell() {
		return receiver_cell;
	}
	public void setReceiver_cell(String receiver_cell) {
		this.receiver_cell = receiver_cell;
	}
	public String getSender_cell() {
		return sender_cell;
	}
	public void setSender_cell(String sender_cell) {
		this.sender_cell = sender_cell;
	}
	public String getSender_city() {
		return sender_city;
	}
	public void setSender_city(String sender_city) {
		this.sender_city = sender_city;
	}
	public String getSender_county() {
		return sender_county;
	}
	public void setSender_county(String sender_county) {
		this.sender_county = sender_county;
	}
	public String getSender_address() {
		return sender_address;
	}
	public void setSender_address(String sender_address) {
		this.sender_address = sender_address;
	}
	
}
