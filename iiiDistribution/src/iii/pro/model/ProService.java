package iii.pro.model;

import java.sql.Timestamp;
import java.util.List;

public class ProService {
	
	private ProDAO_interface dao;
	
	public ProService(){
		dao = new ProDAO();
	}
	
	public ProVO addPro(String emp_id,java.sql.Date promotion_date,String promotion_title,String promotion_context,String promotion_note
			,java.sql.Date promotion_start,java.sql.Date promotion_end,byte[] promotion_picture,Double promotion_discount,Timestamp promotion_updatetime){
		
		ProVO proVO = new ProVO();
		
		proVO.setEmp_id(emp_id);
		proVO.setPromotion_date(promotion_date);
		proVO.setPromotion_title(promotion_title);
		proVO.setPromotion_context(promotion_context);
		proVO.setPromotion_note(promotion_note);
		proVO.setPromotion_start(promotion_start);
		proVO.setPromotion_end(promotion_end);
		proVO.setPromotion_picture(promotion_picture);
		proVO.setPromotion_discount(promotion_discount);
		
		dao.insert(proVO);
		return proVO;
	}
	
	public ProVO update(String promotion_id,String emp_id,java.sql.Date promotion_date,String promotion_title,String promotion_context,
			String promotion_note,java.sql.Date promotion_start,java.sql.Date promotion_end,byte[] promotion_picture,Double promotion_discount,Timestamp promotion_updatetime){
		
		ProVO proVO = new ProVO();
		
		proVO.setPromotion_id(promotion_id);
		proVO.setEmp_id(emp_id);
		proVO.setPromotion_date(promotion_date);
		proVO.setPromotion_title(promotion_title);
		proVO.setPromotion_context(promotion_context);
		proVO.setPromotion_note(promotion_note);
		proVO.setPromotion_start(promotion_start);
		proVO.setPromotion_end(promotion_end);
		proVO.setPromotion_picture(promotion_picture);
		proVO.setPromotion_discount(promotion_discount);
		proVO.setPromotion_updatetime(promotion_updatetime);
		
		dao.update(proVO);
		return proVO;
		
	}
	
	public void deletePro(String promotion_id){
		
		dao.delete(promotion_id);
		
	}
	
	public ProVO getOnePro(String promotion_id){
		
		return dao.findByPrimaryKey(promotion_id);
		
	}
	
	public List<ProVO> getAll(){
		
		return dao.getAll();
	}
}
