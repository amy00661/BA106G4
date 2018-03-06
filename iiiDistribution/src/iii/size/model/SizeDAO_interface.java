package iii.size.model;

import java.sql.ResultSet;
import java.util.List;



public interface SizeDAO_interface {
	public void insert(SizeVO sizeVO);
	public void update(SizeVO sizeVO);
	public void delete(String size_id);
	public SizeVO findByPrimaryKey(String size_id);
	public List<SizeVO> getAll();
}
