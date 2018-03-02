package iii.fee_transition.model;

import java.sql.Timestamp;
import java.util.List;

public class TraService {

	private TraDAO_interface dao;
	
	public TraService(){
		dao = new TraDAO();
		
	}
	
	public TraVO addTra(String emp_id,String transition_type,Double transition_price,Timestamp transition_updatetime){
		
		TraVO traVO = new TraVO();
		
		traVO.setEmp_id(emp_id);
		traVO.setTransition_type(transition_type);
		traVO.setTransition_price(transition_price);
		
		dao.insert(traVO);
		return traVO;
		
	}
	
	public TraVO update(String transition_id,String emp_id,String transition_type,Double transition_price,Timestamp transition_updatetime){
		
		TraVO traVO = new TraVO();
		
		traVO.setTransition_id(transition_id);
		traVO.setEmp_id(emp_id);
		traVO.setTransition_type(transition_type);
		traVO.setTransition_price(transition_price);
		traVO.setUpdatetime(transition_updatetime);
		
		dao.update(traVO);
		return traVO;
	}
	
	public void deleteTra(String transition_id){
		dao.delete(transition_id);
	}
	
	public TraVO getOneNews(String transition_id){
		
		return dao.findByPrimaryKey(transition_id);
	}
	
	public List<TraVO> getAll(){
		
		return dao.getAll();
	}
	
}
