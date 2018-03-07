package iii.authority.model;

import java.sql.Timestamp;

public class AuthorityVO {
	private String emp_id;
	private String menu_id;
	private Timestamp emp_updateTime;
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public Timestamp getEmp_updateTime() {
		return emp_updateTime;
	}
	public void setEmp_updateTime(Timestamp emp_updateTime) {
		this.emp_updateTime = emp_updateTime;
	}
	@Override
	public String toString() {
		return "員工:"+emp_id+"\t選單:"+menu_id;
	}
	
}
