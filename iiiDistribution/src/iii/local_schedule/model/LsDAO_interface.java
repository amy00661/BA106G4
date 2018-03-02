package iii.local_schedule.model;

import java.util.List;
import java.util.Set;

public interface LsDAO_interface {
	public void insert(LsVO lsVO);
	public void update(LsVO lsVO);
	public void delete(String lsVO);
	public LsVO findByPrimaryKey(String local_schedule_id);
	public List<LsVO> getAll();
	public Set<LsVO> findByCarType(String car_type);
}
