package iii.order_main.model;

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((create_time == null) ? 0 : create_time.hashCode());
		result = prime * result + ((db_id == null) ? 0 : db_id.hashCode());
		result = prime * result + ((emp_id == null) ? 0 : emp_id.hashCode());
		result = prime * result + ((expected_time == null) ? 0 : expected_time.hashCode());
		result = prime * result + ((extrafee == null) ? 0 : extrafee.hashCode());
		result = prime * result + ((fee == null) ? 0 : fee.hashCode());
		result = prime * result + ((item_size == null) ? 0 : item_size.hashCode());
		result = prime * result + ((item_type == null) ? 0 : item_type.hashCode());
		result = prime * result + ((item_weight == null) ? 0 : item_weight.hashCode());
		result = prime * result + ((mem_id == null) ? 0 : mem_id.hashCode());
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((order_note == null) ? 0 : order_note.hashCode());
		result = prime * result + ((order_status == null) ? 0 : order_status.hashCode());
		result = prime * result + ((order_updatetime == null) ? 0 : order_updatetime.hashCode());
		result = prime * result + ((payment_type == null) ? 0 : payment_type.hashCode());
		result = prime * result + ((receiver_address == null) ? 0 : receiver_address.hashCode());
		result = prime * result + ((receiver_cell == null) ? 0 : receiver_cell.hashCode());
		result = prime * result + ((receiver_city == null) ? 0 : receiver_city.hashCode());
		result = prime * result + ((receiver_county == null) ? 0 : receiver_county.hashCode());
		result = prime * result + ((receiver_mail == null) ? 0 : receiver_mail.hashCode());
		result = prime * result + ((receiver_name == null) ? 0 : receiver_name.hashCode());
		result = prime * result + ((receiver_tel == null) ? 0 : receiver_tel.hashCode());
		result = prime * result + ((sender_address == null) ? 0 : sender_address.hashCode());
		result = prime * result + ((sender_cell == null) ? 0 : sender_cell.hashCode());
		result = prime * result + ((sender_city == null) ? 0 : sender_city.hashCode());
		result = prime * result + ((sender_county == null) ? 0 : sender_county.hashCode());
		result = prime * result + ((sender_name == null) ? 0 : sender_name.hashCode());
		result = prime * result + ((sender_tel == null) ? 0 : sender_tel.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderVO other = (OrderVO) obj;
		if (create_time == null) {
			if (other.create_time != null)
				return false;
		} else if (!create_time.equals(other.create_time))
			return false;
		if (db_id == null) {
			if (other.db_id != null)
				return false;
		} else if (!db_id.equals(other.db_id))
			return false;
		if (emp_id == null) {
			if (other.emp_id != null)
				return false;
		} else if (!emp_id.equals(other.emp_id))
			return false;
		if (expected_time == null) {
			if (other.expected_time != null)
				return false;
		} else if (!expected_time.equals(other.expected_time))
			return false;
		if (extrafee == null) {
			if (other.extrafee != null)
				return false;
		} else if (!extrafee.equals(other.extrafee))
			return false;
		if (fee == null) {
			if (other.fee != null)
				return false;
		} else if (!fee.equals(other.fee))
			return false;
		if (item_size == null) {
			if (other.item_size != null)
				return false;
		} else if (!item_size.equals(other.item_size))
			return false;
		if (item_type == null) {
			if (other.item_type != null)
				return false;
		} else if (!item_type.equals(other.item_type))
			return false;
		if (item_weight == null) {
			if (other.item_weight != null)
				return false;
		} else if (!item_weight.equals(other.item_weight))
			return false;
		if (mem_id == null) {
			if (other.mem_id != null)
				return false;
		} else if (!mem_id.equals(other.mem_id))
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (order_note == null) {
			if (other.order_note != null)
				return false;
		} else if (!order_note.equals(other.order_note))
			return false;
		if (order_status == null) {
			if (other.order_status != null)
				return false;
		} else if (!order_status.equals(other.order_status))
			return false;
		if (order_updatetime == null) {
			if (other.order_updatetime != null)
				return false;
		} else if (!order_updatetime.equals(other.order_updatetime))
			return false;
		if (payment_type == null) {
			if (other.payment_type != null)
				return false;
		} else if (!payment_type.equals(other.payment_type))
			return false;
		if (receiver_address == null) {
			if (other.receiver_address != null)
				return false;
		} else if (!receiver_address.equals(other.receiver_address))
			return false;
		if (receiver_cell == null) {
			if (other.receiver_cell != null)
				return false;
		} else if (!receiver_cell.equals(other.receiver_cell))
			return false;
		if (receiver_city == null) {
			if (other.receiver_city != null)
				return false;
		} else if (!receiver_city.equals(other.receiver_city))
			return false;
		if (receiver_county == null) {
			if (other.receiver_county != null)
				return false;
		} else if (!receiver_county.equals(other.receiver_county))
			return false;
		if (receiver_mail == null) {
			if (other.receiver_mail != null)
				return false;
		} else if (!receiver_mail.equals(other.receiver_mail))
			return false;
		if (receiver_name == null) {
			if (other.receiver_name != null)
				return false;
		} else if (!receiver_name.equals(other.receiver_name))
			return false;
		if (receiver_tel == null) {
			if (other.receiver_tel != null)
				return false;
		} else if (!receiver_tel.equals(other.receiver_tel))
			return false;
		if (sender_address == null) {
			if (other.sender_address != null)
				return false;
		} else if (!sender_address.equals(other.sender_address))
			return false;
		if (sender_cell == null) {
			if (other.sender_cell != null)
				return false;
		} else if (!sender_cell.equals(other.sender_cell))
			return false;
		if (sender_city == null) {
			if (other.sender_city != null)
				return false;
		} else if (!sender_city.equals(other.sender_city))
			return false;
		if (sender_county == null) {
			if (other.sender_county != null)
				return false;
		} else if (!sender_county.equals(other.sender_county))
			return false;
		if (sender_name == null) {
			if (other.sender_name != null)
				return false;
		} else if (!sender_name.equals(other.sender_name))
			return false;
		if (sender_tel == null) {
			if (other.sender_tel != null)
				return false;
		} else if (!sender_tel.equals(other.sender_tel))
			return false;
		return true;
	}
	
	
}
