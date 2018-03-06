package iii.news.model;

import java.sql.Timestamp;
import java.util.List;

public class NewsService {

	private NewsDAO_interface dao;
	
	public NewsService(){
		dao = new NewsDAO();
	}
	
	public NewsVO addNews(String emp_id,java.sql.Date news_date,String news_title,
			String news_context,String news_note,byte[] news_picture){
		
		NewsVO newsVO = new NewsVO();
		
		newsVO.setEmp_id(emp_id);
		newsVO.setNews_date(news_date);
		newsVO.setNews_title(news_title);
		newsVO.setNews_context(news_context);
		newsVO.setNews_note(news_note);
		newsVO.setNews_picture(news_picture);
		
		
		dao.insert(newsVO);
		return newsVO;
		
	}
	
	public NewsVO update(String news_id,String emp_id,java.sql.Date news_date,String news_title,
			String news_context,String news_note,byte[] news_picture,Timestamp news_updatetime){
		
		NewsVO newsVO = new NewsVO();
		
		newsVO.setNews_id(news_id);
		newsVO.setEmp_id(emp_id);
		newsVO.setNews_date(news_date);
		newsVO.setNews_title(news_title);
		newsVO.setNews_context(news_context);
		newsVO.setNews_note(news_note);
		newsVO.setNews_picture(news_picture);
		newsVO.setNews_updatetime(news_updatetime);
		dao.update(newsVO);
		
		return newsVO;
		
	}
	
	public void deleteNews(String news_id){
		
		dao.delete(news_id);
	}
	
	public NewsVO getOneNews(String news_id){
		
		return dao.findByPrimaryKey(news_id);
	}
	
	public List<NewsVO> getAll(){
		
		return dao.getAll();
	}
}
