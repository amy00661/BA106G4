package iii.menu.model;

import java.util.LinkedHashSet;

public interface MenuDAO_interface {

	public MenuVO findByPrimaryKey(String menu_id);
	
	public LinkedHashSet<MenuNodeVO> getMenuBar_ByEmp(String emp_id,int menuCount);
	
	
	
}
