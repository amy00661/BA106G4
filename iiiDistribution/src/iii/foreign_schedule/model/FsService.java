package iii.foreign_schedule.model;

import java.util.List;
import java.util.Set;

import iii.local_schedule.model.LsVO;


public class FsService {
	
	private FsDAO_interface dao;
	
	public FsService(){
		dao = new FsDAO();
	}
	
	public FsVO addFs(String car_id, String car_type, String star_db, String end_db, 
			String fs_time) {
		
		FsVO fsVO = new FsVO();
		
		fsVO.setCar_ID(car_id);
		fsVO.setCar_TYPE(car_type);
		fsVO.setStar_DB(star_db);
		fsVO.setEnd_DB(end_db);
		fsVO.setFs_TIME(fs_time);

		dao.insert(fsVO);
		
		return fsVO;
	}
	
	public FsVO updateFs(String foreign_schedule_id,
			String car_id,String car_type, String star_db, String end_db, 
			String fs_time) {
		
		FsVO fsVO = new FsVO();
		
		fsVO.setForeign_schedule_ID(foreign_schedule_id);
		fsVO.setCar_ID(car_id);
		fsVO.setCar_TYPE(car_type);
		fsVO.setStar_DB(star_db);
		fsVO.setEnd_DB(end_db);
		fsVO.setFs_TIME(fs_time);
		
		dao.update(fsVO);
		return fsVO;
	}
	
	public void deleteFs(String foreign_schedule_id) {
		dao.delete(foreign_schedule_id);
	}
		
	public FsVO getOneFs(String foreign_schedule_id) {
		return dao.findByPrimaryKey(foreign_schedule_id);
	}
	
	public List<FsVO> getAll() {
		return dao.getAll();
	}
	
	public Set<String> getCarTypeList(){
		return dao.getCarTypeList();
	}
	
	public Set<FsVO> getFSbyCarType(String car_type){
		return dao.findByCarType(car_type);
	}
}
