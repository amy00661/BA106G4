package iii.pro.controller;

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
import iii.pro.model.ProService;
import iii.pro.model.ProVO;


@WebServlet("/ProServlet")
@MultipartConfig
public class ProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***************************1.�����ШD�Ѽ�****************************************/	
				String promotion_id = req.getParameter("promotion_id");
				/***************************2.�}�l�d�߸��****************************************/
				ProService proSvc = new ProService();
				java.sql.Date promotion_date = null;
				promotion_date=java.sql.Date.valueOf(req.getParameter("promotion_date"));
				
				
				ProVO proVO = proSvc.getOnePro(promotion_id);
				req.setAttribute("proVO",proVO);
				RequestDispatcher successView = 
						req.getRequestDispatcher("/backend/promotion/update_pro_input.jsp");
				successView.forward(req, res);
				
			}catch(Exception e){
				errorMsgs.add("無法取得要修改資料: "+e.getMessage());
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/backend/promotion/update_pro_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				String emp_id="EMP001";
				String promotion_id=req.getParameter("promotion_id");
				
				java.sql.Date promotion_date = null;
				try{
					promotion_date=java.sql.Date.valueOf(req.getParameter("promotion_date"));
				}catch(IllegalArgumentException e){
					promotion_date = new java.sql.Date(System.currentTimeMillis());
				}
				
				java.sql.Timestamp promotion_updatetime = null;
				promotion_updatetime = new java.sql.Timestamp(System.currentTimeMillis());
				
				
				String promotion_title = req.getParameter("promotion_title");
				if(promotion_title==null || promotion_title.trim().length()==0){
					errorMsgs.add("標題不能為空");
				}
				
				
				String promotion_context = req.getParameter("promotion_context");
				if(promotion_context==null || promotion_context.trim().length()==0){
					errorMsgs.add("內文不能為空");
				}
				
				String promotion_note = req.getParameter("promotion_note");
				if(promotion_note==null || promotion_note.trim().length()==0){
					errorMsgs.add("備註不能為空");
				}
				java.sql.Date promotion_start = null;
				try{
					promotion_start=java.sql.Date.valueOf(req.getParameter("promotion_start"));
				}catch(IllegalArgumentException e){
					promotion_start = new java.sql.Date(System.currentTimeMillis());
				}
				java.sql.Date promotion_end = null;
				try{
					promotion_end=java.sql.Date.valueOf(req.getParameter("promotion_end"));
				}catch(IllegalArgumentException e){
					promotion_end = new java.sql.Date(System.currentTimeMillis());
				}
				Double promotion_discount = null;
				try{
					promotion_discount=new Double(req.getParameter("promotion_discount").trim());
				}catch (NumberFormatException e) {
					promotion_discount = 1.0;
					errorMsgs.add("配送費率不能為空");
				}
				Part promotion_picture = req.getPart("promotion_picture");   
				byte[] picture=null;
				if(promotion_picture.getSize()!=0){
					java.io.InputStream in =promotion_picture.getInputStream();
					picture = new byte[in.available()];
					in.read(picture);
					in.close();
				}else{
					ProService proSvc = new ProService();        //圖片不更新 取舊的圖片(雲龍提供)
					picture=proSvc.getOnePro(promotion_id).getPromotion_picture();
				}
				
				ProVO proVO = new ProVO();
				
				proVO.setPromotion_id(promotion_id);
				proVO.setEmp_id(emp_id);
				proVO.setPromotion_date(promotion_date);
				proVO.setPromotion_title(promotion_title);
				proVO.setPromotion_context(promotion_context);
				proVO.setPromotion_note(promotion_note);
				proVO.setPromotion_start(promotion_start);
				proVO.setPromotion_end(promotion_end);
				proVO.setPromotion_discount(promotion_discount);
				proVO.setPromotion_picture(picture);
				
				
				if(!errorMsgs.isEmpty()){
					System.out.println("errorMsgs");
					req.setAttribute("proVO", proVO);
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/backend/promotion/update_pro_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ProService proSvc = new ProService();
				proVO = proSvc.update(promotion_id, emp_id, promotion_date, promotion_title, promotion_context, promotion_note, promotion_start, promotion_end,picture, promotion_discount, promotion_updatetime);
				req.setAttribute("proVO", proVO);
				RequestDispatcher successView=
						req.getRequestDispatcher("/backend/promotion/listAllPro.jsp");
				successView.forward(req, res);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
		}
		if("insert".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				String emp_id="EMP001";
				
				ProService proSvc=new ProService();
				
				java.sql.Date promotion_date = null;
				try{
					promotion_date=java.sql.Date.valueOf(req.getParameter("promotion_date"));
				}catch(IllegalArgumentException e){
					promotion_date = new java.sql.Date(System.currentTimeMillis());
				}
				
				java.sql.Timestamp promotion_updatetime = null;
				promotion_updatetime = new java.sql.Timestamp(System.currentTimeMillis());
				
				String promotion_title = req.getParameter("promotion_title");
				if(promotion_title==null || promotion_title.trim().length()==0){
					errorMsgs.add("標題不能為空");
				}
				
				String promotion_context = req.getParameter("promotion_context");
				if(promotion_context==null || promotion_context.trim().length()==0){
					errorMsgs.add("內文不能為空");
				}
				
				String promotion_note = req.getParameter("promotion_note");
				if(promotion_note==null || promotion_note.trim().length()==0){
					errorMsgs.add("備註不能為空");
				}
				
				java.sql.Date promotion_start = null;
				try{
					promotion_start=java.sql.Date.valueOf(req.getParameter("promotion_start"));
				}catch(IllegalArgumentException e){
					promotion_start = new java.sql.Date(System.currentTimeMillis());
				}
				
				java.sql.Date promotion_end = null;
				try{
					promotion_end=java.sql.Date.valueOf(req.getParameter("promotion_end"));
				}catch(IllegalArgumentException e){
					promotion_end = new java.sql.Date(System.currentTimeMillis());
				}
				
				Double promotion_discount = null;
				try{
					promotion_discount=new Double(req.getParameter("promotion_discount").trim());
				}catch (NumberFormatException e) {
					promotion_discount = 1.0;
					errorMsgs.add("配送費率不能為空");
				}
				
				Part promotion_picture = req.getPart("promotion_picture");   
					java.io.InputStream in =promotion_picture.getInputStream();
					byte[] picture = new byte[in.available()];
					in.read(picture);
					in.close();
				
				ProVO proVO = new ProVO();
				
				proVO.setEmp_id(emp_id);
				proVO.setPromotion_date(promotion_date);
				proVO.setPromotion_title(promotion_title);
				proVO.setPromotion_context(promotion_context);
				proVO.setPromotion_note(promotion_note);
				proVO.setPromotion_start(promotion_start);
				proVO.setPromotion_end(promotion_end);
				proVO.setPromotion_discount(promotion_discount);
				proVO.setPromotion_picture(picture);
				
				if(!errorMsgs.isEmpty()){
					req.setAttribute("proVO", proVO);
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/backend/promotion/add_pro_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.�}�l�s�W���***************************************/
				
				proVO = proSvc.addPro(emp_id, promotion_date, promotion_title, promotion_context, promotion_note, promotion_start, promotion_end, picture, promotion_discount, promotion_updatetime);
				
				RequestDispatcher successView =
						req.getRequestDispatcher("/backend/promotion/listAllPro.jsp");
				successView.forward(req, res);
					
				}catch(Exception e){
					
					errorMsgs.add(e.getMessage());
					
					RequestDispatcher failureView=
						req.getRequestDispatcher("/backend/promotion/add_pro_input.jsp");
					failureView.forward(req, res);
				}
		}
	}
}
