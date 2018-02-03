package iii.db.model;

import java.util.List;

public class DbService {
	private DbDAO_interface dao;

	public DbService() {
		dao = new DbDAO();
	}
	
	public List<DbVO> getAllDB(){
		return dao.getAll();
	}
	
}
