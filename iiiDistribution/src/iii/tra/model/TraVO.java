package iii.tra.model;

import java.sql.Timestamp;

public class TraVO {

	private String transition_id;
	private String transition_type;
	private Double transition_price;
	private Timestamp transition_updatetime;
	private String emp_id;
	
	public String getTransition_id() {
		return transition_id;
	}
	public void setTransition_id(String transition_id) {
		this.transition_id = transition_id;
	}
	public String getTransition_type() {
		return transition_type;
	}
	public void setTransition_type(String transition_type) {
		this.transition_type = transition_type;
	}
	public Double getTransition_price() {
		return transition_price;
	}
	public void setTransition_price(Double transition_price) {
		this.transition_price = transition_price;
	}
	public Timestamp getTransition_updatetime() {
		return transition_updatetime;
	}
	public void setTransition_updatetime(Timestamp transition_updatetime) {
		this.transition_updatetime = transition_updatetime;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
}
