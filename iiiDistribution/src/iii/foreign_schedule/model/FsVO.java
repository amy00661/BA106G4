package iii.foreign_schedule.model;
import java.sql.Date;
import java.sql.Timestamp;

public class FsVO implements java.io.Serializable{
	private String foreign_schedule_ID;
	private String car_ID;
	private String car_TYPE;
	private String star_DB;
	private String end_DB;
	private String fs_TIME;
	private String emp_ID;
	private Timestamp fs_updatetime;
	public String getForeign_schedule_ID() {
		return foreign_schedule_ID;
	}
	public void setForeign_schedule_ID(String foreign_schedule_ID) {
		this.foreign_schedule_ID = foreign_schedule_ID;
	}
	public String getCar_ID() {
		return car_ID;
	}
	public void setCar_ID(String car_ID) {
		this.car_ID = car_ID;
	}
	public String getCar_TYPE() {
		return car_TYPE;
	}
	public void setCar_TYPE(String car_TYPE) {
		this.car_TYPE = car_TYPE;
	}
	public String getStar_DB() {
		return star_DB;
	}
	public void setStar_DB(String star_DB) {
		this.star_DB = star_DB;
	}
	public String getEnd_DB() {
		return end_DB;
	}
	public void setEnd_DB(String end_DB) {
		this.end_DB = end_DB;
	}
	public String getFs_TIME() {
		return fs_TIME;
	}
	public void setFs_TIME(String fs_TIME) {
		this.fs_TIME = fs_TIME;
	}
	public String getEmp_ID() {
		return emp_ID;
	}
	public void setEmp_ID(String emp_ID) {
		this.emp_ID = emp_ID;
	}
	public Timestamp getFs_updatetime() {
		return fs_updatetime;
	}
	public void setFs_updatetime(Timestamp fs_updatetime) {
		this.fs_updatetime = fs_updatetime;
	}
	
	
	
	
	
}
