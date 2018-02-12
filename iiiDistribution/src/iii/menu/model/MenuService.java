package iii.menu.model;

public class MenuService {
	public MenuDAO_interface dao;
	public MenuService(){
		dao = new MenuDAO();
	}
	public MenuVO getOneMenu(String menu_id){
		return dao.findByPrimaryKey(menu_id);
	}
}
