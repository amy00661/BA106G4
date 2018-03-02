package iii.local_schedule.model;

import java.util.List;
import java.util.Set;

public class LsService {
	
	private LsDAO_interface dao;
	
	public LsService(){
		dao = new LsDAO();
	}
	
	public LsVO addLs(String car_id, String car_type, String ls_time) {
		
		LsVO lsVO = new LsVO();
		
		lsVO.setCar_id(car_id);
		lsVO.setCar_type(car_type);
		lsVO.setLs_time(ls_time);
		dao.insert(lsVO);
		return lsVO;
	}
	
	public LsVO updateLs(String local_schedule_id, String car_id, String car_type, String ls_time) {
		
		LsVO lsVO = new LsVO();
		
		lsVO.setLocal_schedule_id(local_schedule_id);
		lsVO.setCar_id(car_id);
		lsVO.setCar_type(car_type);
		lsVO.setLs_time(ls_time);
		dao.update(lsVO);
		return lsVO;
	}
	
	public void deleteLs(String local_schedule_id) {
		dao.delete(local_schedule_id);
	}
	
	public LsVO getOneLs(String local_schedule_id) {
		return dao.findByPrimaryKey(local_schedule_id);
	}
	
	public List<LsVO> getAll() {
		return dao.getAll();
	}
	
	public Set<LsVO> getLSbyCarType(String car_type){
		return dao.findByCarType(car_type);
	}
}
