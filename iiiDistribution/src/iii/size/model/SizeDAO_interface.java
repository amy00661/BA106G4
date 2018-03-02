package iii.size.model;

import java.sql.ResultSet;
import java.util.List;

import iii.car.model.CarVO;

public interface SizeDAO_interface {
	public Integer insert(SizeVO sizeVO);
	public Integer update(SizeVO sizeVO);
	public ResultSet delete(String size_id);
	public SizeVO findByPrimaryKey(String size_id);
	public List<SizeVO> getAll();
}
