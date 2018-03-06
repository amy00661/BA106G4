package iii.pro.model;

import java.util.List;

import iii.news.model.NewsVO;

public interface ProDAO_interface {
	public void insert(ProVO proVO);
	public void update(ProVO proVO);
	public void delete(String promotion_id);
	public ProVO findByPrimaryKey(String promotion_id);
	public List<ProVO> getAll();
	
	
}
