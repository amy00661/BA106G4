package iii.db.model;

import java.util.List;

public interface DBDAO_interface {
	public Integer insert(DBVO dbVO);
	public Integer update(DBVO dbVO);
	public void delete(String db_id);
	public DBVO findByPrimaryKey(String db_id);
	public List <DBVO> getAll(); 
}
