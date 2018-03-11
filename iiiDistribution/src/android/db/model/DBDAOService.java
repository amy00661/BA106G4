package android.db.model;

import java.util.List;

public class DBDAOService {

	private DBDAO_interface dao;
	
	public DBDAOService(){
		dao = new DBDAO();
	}
	
	public DBVO addDB(String db_id, String db_name){
		DBVO dbVO = new DBVO();
		
		dbVO.setDb_id(db_id);
		dbVO.setDb_name(db_name);
		
		dao.insert(dbVO);
				
		return dbVO;
	}
	
	public DBVO updateDB(String db_id, String db_name){
		DBVO dbVO = new DBVO();
		
		dbVO.setDb_id(db_id);
		dbVO.setDb_name(db_name);
		
		dao.update(dbVO);
		
		return dbVO;
	}
	
	public void deleteDB(String db_id){
		dao.delete(db_id);
	}
	
	public DBVO getOneDB(String db_id){
		return dao.findByPrimaryKey(db_id);
	}
	
	public List<DBVO> getAll(){
		return dao.getAll();
	}
	
}
