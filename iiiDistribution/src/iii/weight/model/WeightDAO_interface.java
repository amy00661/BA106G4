package iii.weight.model;

import java.sql.ResultSet;
import java.util.List;

public interface WeightDAO_interface {
	public void insert(WeightVO weightVO);
	public void update(WeightVO weightVO);
	public void delete(String weight_id);
	public WeightVO findByPrimaryKey(String weight_id);
	public List<WeightVO> getAll();
}
