package iii.foreign_schedule.model;

import java.util.List;
import java.util.Set;

public interface FsDAO_interface {
	public void insert(FsVO fsVO);
	public void update(FsVO fsVO);
	public void delete(String fsVO);
	public FsVO findByPrimaryKey(String foreign_schedule_ID);
	public List<FsVO> getAll();
	public Set<String> getCarTypeList();
	public Set<FsVO> findByCarType(String car_type);
}
