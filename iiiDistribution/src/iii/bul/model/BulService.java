package iii.bul.model;

import java.sql.Timestamp;
import java.util.List;


public class BulService {

	private BulDAO_interface dao;
	
	public BulService(){
		dao = new BulDAO();
	}
	
	public BulVO addBul(String emp_id,Timestamp bulletin_date,String bulletin_title,
			String bulletin_context,String bulletin_note,Timestamp bulletin_updatetime){
		
		BulVO bulVO = new BulVO();
		
		bulVO.setEmp_id(emp_id);
		bulVO.setBulletin_date(bulletin_date);
		bulVO.setBulletin_title(bulletin_title);
		bulVO.setBulletin_context(bulletin_context);
		bulVO.setBulletin_note(bulletin_note);
						
		dao.insert(bulVO);
		
		return bulVO;
		
	}
	
	
	public BulVO update(String bulletin_id,String emp_id,Timestamp bulletin_date,String bulletin_title,
			String bulletin_context,String bulletin_note,Timestamp bulletin_updatetime){
		
		BulVO bulVO = new BulVO();
		
		bulVO.setBulletin_id(bulletin_id);
		bulVO.setEmp_id(emp_id);
		bulVO.setBulletin_date(bulletin_date);
		bulVO.setBulletin_title(bulletin_title);
		bulVO.setBulletin_context(bulletin_context);
		bulVO.setBulletin_note(bulletin_note);
		bulVO.setBulletin_updatetime(bulletin_updatetime);
		
		dao.update(bulVO);
		return bulVO;
		
	}
	
	public void deleteNews(String bulletin_id){
			
			dao.delete(bulletin_id);
	}
	
	public BulVO getOneNews(String bulletin_id){
		
		return dao.findByPrimaryKey(bulletin_id);
	}
	
	public List<BulVO> getAll(){
		
		return dao.getALL();
	}

}
