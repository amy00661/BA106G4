package iii.weight.model;

import java.sql.Timestamp;
import java.util.List;

public class WeightService {
	
	private WeightDAO_interface dao;
	
	public WeightService(){
		dao = new WeightDAO();
	}
	
	
	public WeightVO addWeight(String emp_id, String weight_type, Double weight_price){
		
		WeightVO weightVO = new WeightVO();
		
		weightVO.setEmp_id(emp_id);
		weightVO.setWeight_type(weight_type);
		weightVO.setWeight_price(weight_price);
		
		dao.insert(weightVO);
		
		return weightVO;
	}
	
	public WeightVO updateWeight(String weight_id, String emp_id, String weight_type, Double weight_price, Timestamp weight_updateTime){
		
		WeightVO weightVO = new WeightVO();
		
		weightVO.setWeight_id(weight_id);
		weightVO.setEmp_id(emp_id);
		weightVO.setWeight_type(weight_type);
		weightVO.setWeight_price(weight_price);
		weightVO.setWeight_updateTime(weight_updateTime);
		dao.update(weightVO);
		return weightVO;
	}
	
	public void deleteWeight(String weight_id){
		dao.delete(weight_id);
	}
	
	public WeightVO getOneWeight(String weight_id){
		return dao.findByPrimaryKey(weight_id);
	}
	
	public List<WeightVO> getAll(){
		return dao.getAll();
	}

}
