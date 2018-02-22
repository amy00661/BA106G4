package iii.menu.model;

import java.util.LinkedHashSet;

public class MenuService {
	public MenuDAO_interface dao;
	public MenuService(){
		dao = new MenuDAO();
	}
	public MenuVO getOneMenu(String menu_id){
		return dao.findByPrimaryKey(menu_id);
	}
	
	public LinkedHashSet<MenuNodeVO> getMenuBar_ByEmp(String emp_id,int menuCount){
		return dao.getMenuBar_ByEmp(emp_id, menuCount);
	}
}
