package iii.news.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import iii.news.model.*;


@WebServlet("/NewsServlet")
@MultipartConfig
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)){
			List<String> errorMsgs = new LinkedList<>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				
				String news_id = req.getParameter("news_id");
				String newsReg = "^[N][E][W][S][0-9]{3}$";
				
				if(news_id == null || news_id.trim().length()==0){
					errorMsgs.add("�п�J�̷s�����s��");
				}else if(!(news_id.trim().matches(newsReg))){
					errorMsgs.add("�̷s�����s���榡���~");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView =
							req.getRequestDispatcher("news/select_NewsPage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(news_id);
				if(newsVO == null){
					errorMsgs.add("�d�L���");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/news/select_NewsPage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("newsVO", newsVO);
				RequestDispatcher successView = 
						req.getRequestDispatcher("/news/listOneNews.jsp");
				successView.forward(req, res);
				/***************************��L�i�઺���~�B�z*************************************/
				
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/news/select_NewsPage.jsp");
				failureView.forward(req, res);
						
			}
		}
		
		if("getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***************************1.�����ШD�Ѽ�****************************************/	
				String news_id = req.getParameter("news_id");
				/***************************2.�}�l�d�߸��****************************************/
				NewsService newsSvc = new NewsService();
				
				java.sql.Date news_date = null;
				news_date=java.sql.Date.valueOf(req.getParameter("news_date"));
				
				
				NewsVO newsVO = newsSvc.getOneNews(news_id);
				req.setAttribute("newsVO",newsVO);
				RequestDispatcher successView = 
						req.getRequestDispatcher("/backend/news/update_news_input.jsp");
				successView.forward(req, res);
				
			}catch(Exception e){
				errorMsgs.add("無法取得要修改資料: "+e.getMessage());
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/backend/news/update_news_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				String emp_id="EMP001";
				String news_id=req.getParameter("news_id");
				
				java.sql.Date news_date = null;
				try{
					news_date=java.sql.Date.valueOf(req.getParameter("news_date"));
				}catch(IllegalArgumentException e){
					news_date = new java.sql.Date(System.currentTimeMillis());
				}
				
				java.sql.Timestamp news_updatetime = null;
				news_updatetime = new java.sql.Timestamp(System.currentTimeMillis());
				
				String news_title = req.getParameter("news_title");
				if(news_title==null || news_title.trim().length()==0){
					errorMsgs.add("標題不能為空");
				}
				
				String news_context = req.getParameter("news_context");
				if(news_context==null || news_context.trim().length()==0){
					errorMsgs.add("內文不能為空");
				}
				
				String news_note = req.getParameter("news_note");
				if(news_note==null || news_note.trim().length()==0){
					errorMsgs.add("備註不能為空");
				}
				
				
				Part news_picture = req.getPart("news_picture");   
				byte[] picture=null;
				if(news_picture.getSize()!=0){
					java.io.InputStream in =news_picture.getInputStream();
					picture = new byte[in.available()];
					in.read(picture);
					in.close();
				}else{
					NewsService newsSvc = new NewsService();        //圖片不更新 取舊的圖片(雲龍提供)
					picture=newsSvc.getOneNews(news_id).getNews_picture();
				}
				
				NewsVO newsVO = new NewsVO();
				
				newsVO.setNews_id(news_id);
				newsVO.setEmp_id("");
				newsVO.setNews_date(news_date);
				newsVO.setNews_title(news_title);
				newsVO.setNews_context(news_context);
				newsVO.setNews_note(news_note);
				newsVO.setNews_picture(picture);
				
				
				
				if(!errorMsgs.isEmpty()){
					req.setAttribute("newsVO", newsVO);
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/backend/news/update_news_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.update(news_id, emp_id, news_date, news_title, news_context, news_note, picture, news_updatetime);
				
				req.setAttribute("newsVO", newsVO);
				RequestDispatcher successView=
						req.getRequestDispatcher("/backend/news/listAllNews.jsp");
				successView.forward(req, res);
				
			}catch(Exception e){
				e.getMessage();
			}
			
		}
		if("insert".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				String emp_id="EMP001";
				
				NewsService newsSvc=new NewsService();
				
				java.sql.Date news_date = null;
				news_date = new java.sql.Date(System.currentTimeMillis());
				
				String news_title = req.getParameter("news_title");
				if(news_title==null || news_title.trim().length()==0){
					errorMsgs.add("標題不能為空");
				}
				
				String news_context = req.getParameter("news_context");
				if(news_context==null || news_context.trim().length()==0){
					errorMsgs.add("內文不能為空");
				}
				
				String news_note = req.getParameter("news_note");
				if(news_note==null || news_note.trim().length()==0){
					errorMsgs.add("備註不能為空");
				}
				
				Part news_picture = req.getPart("news_picture");
					
				java.io.InputStream in =news_picture.getInputStream();
				byte[] picture = new byte[in.available()];
				in.read(picture);
				in.close();
				
				NewsVO newsVO = new NewsVO();
				
				newsVO.setEmp_id("");
				newsVO.setNews_date(news_date);
				newsVO.setNews_title(news_title);
				newsVO.setNews_context(news_context);
				newsVO.setNews_note(news_note);
				newsVO.setNews_picture(picture);
				
				if(!errorMsgs.isEmpty()){
					req.setAttribute("newsVO", newsVO);
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/backend/news/add_news_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.�}�l�s�W���***************************************/
				
				newsVO = newsSvc.addNews(emp_id, news_date, news_title, news_context, news_note,picture);
				
				RequestDispatcher successView =
						req.getRequestDispatcher("/backend/news/listAllNews.jsp");
				successView.forward(req, res);
					
				}catch(Exception e){
					
					errorMsgs.add(e.getMessage());
					
					RequestDispatcher failureView=
						req.getRequestDispatcher("/backend/news/add_news_input.jsp");
					failureView.forward(req, res);
				}
		}
	}
}
