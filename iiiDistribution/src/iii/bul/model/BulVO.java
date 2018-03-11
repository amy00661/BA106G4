package iii.bul.model;
import java.sql.Date;
import java.sql.Timestamp;

public class BulVO implements java.io.Serializable{
	
	private String bulletin_id;
	private String emp_id;
	private Timestamp bulletin_date;
	private String bulletin_title;
	private String bulletin_context;
	private String bulletin_note;
	private Timestamp bulletin_updatetime;
	
	
	
	public String getBulletin_id() {
		return bulletin_id;
	}
	public void setBulletin_id(String bulletin_id) {
		this.bulletin_id = bulletin_id;
	}
	public String getBulletin_title() {
		return bulletin_title;
	}
	public void setBulletin_title(String bulletin_title) {
		this.bulletin_title = bulletin_title;
	}
	public Timestamp getBulletin_date() {
		return bulletin_date;
	}
	public void setBulletin_date(Timestamp bulletin_date) {
		this.bulletin_date = bulletin_date;
	}
	public String getBulletin_context() {
		return bulletin_context;
	}
	public void setBulletin_context(String bulletin_context) {
		this.bulletin_context = bulletin_context;
	}
	public String getBulletin_note() {
		return bulletin_note;
	}
	public void setBulletin_note(String bulletin_note) {
		this.bulletin_note = bulletin_note;
	}
	public Timestamp getBulletin_updatetime() {
		return bulletin_updatetime;
	}
	public void setBulletin_updatetime(Timestamp bulletin_updatetime) {
		this.bulletin_updatetime = bulletin_updatetime;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
	
	
	
}
