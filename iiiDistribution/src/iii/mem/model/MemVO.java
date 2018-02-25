package iii.mem.model;
import java.sql.Date;
import java.sql.Timestamp;

public class MemVO implements java.io.Serializable {
	private String member_id;
	private String emp_id;
	private String member_mail;
	private String member_psw;
	private String member_name;
	private Date member_birth;
	private String member_gender;
	private String member_identification;
	private String member_cell;
	private String member_phone;
	private String member_addr;
	private String member_status;
	private Timestamp member_updatetime;
	
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
	public String getMember_mail() {
		return member_mail;
	}
	public void setMember_mail(String member_mail) {
		this.member_mail = member_mail;
	}
	public String getMember_psw() {
		return member_psw;
	}
	public void setMember_psw(String member_psw) {
		this.member_psw = member_psw;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public Date getMember_birth() {
		return member_birth;
	}
	public void setMember_birth(Date member_birth) {
		this.member_birth = member_birth;
	}
	public String getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}
	public String getMember_identification() {
		return member_identification;
	}
	public void setMember_identification(String member_identification) {
		this.member_identification = member_identification;
	}
	public String getMember_cell() {
		return member_cell;
	}
	public void setMember_cell(String member_cell) {
		this.member_cell = member_cell;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_addr() {
		return member_addr;
	}
	public void setMember_addr(String member_addr) {
		this.member_addr = member_addr;
	}
	public String getMember_status() {
		return member_status;
	}
	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}
	public Timestamp getMember_updatetime() {
		return member_updatetime;
	}
	public void setMember_updatetime(Timestamp member_updatetime) {
		this.member_updatetime = member_updatetime;
	}
		
	
}
