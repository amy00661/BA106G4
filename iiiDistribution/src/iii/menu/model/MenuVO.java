package iii.menu.model;

public class MenuVO {

	private String menu_id;	
	private String menu_parent_id;
	private String menu_title;
	private Integer menu_priorty;
	private String menu_url;
	private String emp_id;
	private String menu_updatetime;
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_parent_id() {
		return menu_parent_id;
	}
	public void setMenu_parent_id(String menu_parent_id) {
		this.menu_parent_id = menu_parent_id;
	}
	public String getMenu_title() {
		return menu_title;
	}
	public void setMenu_title(String menu_title) {
		this.menu_title = menu_title;
	}
	public Integer getMenu_priorty() {
		return menu_priorty;
	}
	public void setMenu_priorty(Integer menu_priorty) {
		this.menu_priorty = menu_priorty;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getMenu_updatetime() {
		return menu_updatetime;
	}
	public void setMenu_updatetime(String menu_updatetime) {
		this.menu_updatetime = menu_updatetime;
	}
	
}
