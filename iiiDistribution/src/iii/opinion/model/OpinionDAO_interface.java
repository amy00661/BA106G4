package iii.opinion.model;

import java.util.List;

import iii.emp.model.EmpVO;

public interface OpinionDAO_interface {
	public int insert(OpinionVO opinionVo);
	
	public int update(OpinionVO opinionVO);
	
	public OpinionVO findByPrimaryKey(String opinion_id);
	
	public List<OpinionVO> getAll();
	
}
