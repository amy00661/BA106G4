package iii.news.model;

import java.util.*;

public interface NewsDAO_interface {
	public void insert(NewsVO newsVO);
	public void update(NewsVO newsVO);
	public void delete(String news_id);
	public void selectPic(String news_id);
	public NewsVO findByPrimaryKey(String news_id);
	public List<NewsVO> getAll();
}
