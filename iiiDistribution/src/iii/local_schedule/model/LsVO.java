package iii.local_schedule.model;
import java.sql.Timestamp;

public class LsVO implements java.io.Serializable{
	private String local_schedule_id;
	private String car_id;
	private String car_type;
	private String ls_time;
	private String emp_id;
	private Timestamp ls_updatetime;
	public String getLocal_schedule_id() {
		return local_schedule_id;
	}
	public void setLocal_schedule_id(String local_schedule_id) {
		this.local_schedule_id = local_schedule_id;
	}
	public String getCar_id() {
		return car_id;
	}
	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	public String getLs_time() {
		return ls_time;
	}
	public void setLs_time(String ls_time) {
		this.ls_time = ls_time;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public Timestamp getLs_updatetime() {
		return ls_updatetime;
	}
	public void setLs_updatetime(Timestamp ls_updatetime) {
		this.ls_updatetime = ls_updatetime;
	}
	
	
	
}
