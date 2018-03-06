package iii.news.model;
import java.sql.Date;
import java.sql.Timestamp;
public class NewsVO implements java.io.Serializable{
	private String news_id;
	private String emp_id;
	private Date news_date;
	private String news_title;
	private String news_context;
	private String news_note;
	private byte[] news_picture;
	private Timestamp news_updatetime;
	
	
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public Date getNews_date() {
		return news_date;
	}
	public void setNews_date(Date news_date) {
		this.news_date = news_date;
	}
	public String getNews_context() {
		return news_context;
	}
	public void setNews_context(String news_context) {
		this.news_context = news_context;
	}
	public String getNews_note() {
		return news_note;
	}
	public void setNews_note(String news_note) {
		this.news_note = news_note;
	}
	
	public byte[] getNews_picture() {
		return news_picture;
	}
	public void setNews_picture(byte[] news_picture) {
		this.news_picture = news_picture;
	}
	public Timestamp getNews_updatetime() {
		return news_updatetime;
	}
	public void setNews_updatetime(Timestamp news_updatetime) {
		this.news_updatetime = news_updatetime;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
	
}
