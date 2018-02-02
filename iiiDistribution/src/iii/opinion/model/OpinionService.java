package iii.opinion.model;

import java.util.List;

public class OpinionService {
		
	private OpinionDAO_interface dao;
	
	public OpinionService(){
		dao = new OpinionDAO();
	}
	
	public int addOpinion(String opinion_id,String opinion_type,String opinion_name,String opinion_phone,String opinion_cell,String opinion_email,String opinion_content){
		OpinionVO opinionVO = new OpinionVO();
		opinionVO.setOpinion_type(opinion_type);
		opinionVO.setOpinion_name(opinion_name);
		opinionVO.setOpinion_phone(opinion_phone);
		opinionVO.setOpinion_cell(opinion_cell);
		opinionVO.setOpinion_email(opinion_email);
		opinionVO.setOpinion_content(opinion_content);
		int insertRow = dao.insert(opinionVO);
		return insertRow;
	}

	public int updateOpinion(String opinion_id,String emp_id,String opinion_type,String opinion_phone,String opinion_cell,String opinion_email,String opinion_content,String opinion_status){
		OpinionVO opinionVO = new OpinionVO();
		opinionVO.setOpinion_id(opinion_id);
		opinionVO.setEmp_id(emp_id);
		opinionVO.setOpinion_type(opinion_type);
		opinionVO.setOpinion_phone(opinion_phone);
		opinionVO.setOpinion_cell(opinion_cell);
		opinionVO.setOpinion_email(opinion_email);
		opinionVO.setOpinion_content(opinion_content);
		opinionVO.setOpinion_status(opinion_status);
		
		int updateRow = dao.update(opinionVO);
		return updateRow;
		
	}
	
	public OpinionVO getOneOpinion(String opinion_id){
		return dao.findByPrimaryKey(opinion_id);
	}
	
	public List<OpinionVO> getAllOpinion(){
		return dao.getAll();
	}


}
