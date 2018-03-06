package iii.size.model;

import java.sql.Timestamp;
import java.util.List;

public class SizeService {
	
	private SizeDAO_interface dao;
	
	public SizeService(){
		dao = new SizeDAO();
	}
	
	public SizeVO addSize(String emp_id, String size_type, Double size_price){
		SizeVO sizeVO = new SizeVO();
		
		sizeVO.setEmp_id(emp_id);
		sizeVO.setSize_type(size_type);
		sizeVO.setSize_price(size_price);
		
		dao.insert(sizeVO);
		
		return sizeVO;
	}
	
	public SizeVO updateSize(String size_id, String emp_id, String size_type, Double size_price, Timestamp size_updatetime){
		SizeVO sizeVO = new SizeVO();
		
		sizeVO.setSize_id(size_id);
		sizeVO.setEmp_id(emp_id);
		sizeVO.setSize_type(size_type);
		sizeVO.setSize_price(size_price);
		sizeVO.setSize_updatetime(size_updatetime);
		
		dao.update(sizeVO);
		
		return sizeVO;
	}
	
	public void deleteSize(String size_id){
		dao.delete(size_id);
	}
	
	public SizeVO getOneSize(String size_id){
		return dao.findByPrimaryKey(size_id);
	}
	
	public List<SizeVO> getAll(){
		return dao.getAll();
	}
	
}
