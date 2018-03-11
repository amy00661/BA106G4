package iii.size.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iii.mem.model.MemService;
import iii.size.model.*;
import iii.tra.model.TraVO;

@WebServlet("/SizeServlet")
public class SizeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		System.out.println(action);
		
		if("getOne_For_Display".equals(action)){
			
			List<String> errorMsgs = new LinkedList<>();
			
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				
				String size_id=req.getParameter("size_id");
	
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/mem/select_MemPage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				SizeService sizeSvc = new SizeService();
				SizeVO sizeVO=sizeSvc.getOneSize(size_id);
				
				if(sizeVO == null){
					errorMsgs.add("�d�L���");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView=req.getRequestDispatcher("/size/select_SizePage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("sizeVO", sizeVO);
				RequestDispatcher successView =
						req.getRequestDispatcher("/size/listOneSize.jsp");
				
			}catch(Exception e){
				errorMsgs.add("無法取得要修改資料: " +e.getMessage());
				
				RequestDispatcher failureView = req.getRequestDispatcher("/mem/select_page.jsp");
				failureView.forward(req, res);
	
			}		
		}

		if("getOne_For_Update".equals(action)){
			
			List<String> errorMsgs = new LinkedList<>();
			
			req.setAttribute("errorMsgs",errorMsgs);
			String flagSize=new String("no");
			String flagSizeAdd=new String("ok");
			try{
				
				String size_id = req.getParameter("size_id");
				
				SizeService sizeSvc = new SizeService();
				SizeVO sizeVO = sizeSvc.getOneSize(size_id);
				java.sql.Timestamp size_updatetime = null;
				try{
					size_updatetime=java.sql.Timestamp.valueOf(req.getParameter("size_updatetime"));
				}catch(IllegalArgumentException e){
					size_updatetime=new java.sql.Timestamp(System.currentTimeMillis());
				}
				sizeVO.setSize_updatetime(size_updatetime);
				
				req.setAttribute("sizeVO", sizeVO);
				req.setAttribute("flagSize", flagSize);
				req.setAttribute("flagSizeAdd", flagSizeAdd);
				RequestDispatcher successView = 
						req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
				successView.forward(req, res);
				
				
			}catch(Exception e){
				flagSize="ok";
				errorMsgs.add("無法取得要修改資料: "+e.getMessage());
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
				failureView.forward(req, res);
			}
	
		}
		
		
		if("update".equals(action)){
			
			List<String> errorMsgs = new LinkedList<>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				String flagSize=new String("ok");
				String flagSizeAdd=new String("ok");
				String emp_id=req.getParameter("emp_id");
				String size_id=req.getParameter("size_id");
				String size_type=req.getParameter("size_type");
				
				
				if(size_type==null || size_type.trim().length()==0){
					errorMsgs.add("尺寸條件不能為空");
				}
				
				Double size_price = null;
				try{
					size_price=new Double(req.getParameter("size_price").trim());
				}catch (NumberFormatException e) {
					size_price = 0.0;
					errorMsgs.add("尺寸計價不能為空");
				}
				
				
				java.sql.Timestamp size_updatetime = null;
				try{
					size_updatetime=java.sql.Timestamp.valueOf(req.getParameter("size_updatetime"));
				}catch(IllegalArgumentException e){
					size_updatetime=new java.sql.Timestamp(System.currentTimeMillis());
				}
				
				
				SizeVO sizeVO = new SizeVO();
				
				sizeVO.setEmp_id(emp_id);
				sizeVO.setSize_type(size_type);
				sizeVO.setSize_price(size_price);
				sizeVO.setSize_updatetime(size_updatetime);
				
				
				if(!errorMsgs.isEmpty()){
					flagSize="no";
					req.setAttribute("flagSize", flagSize); 
					req.setAttribute("flagSizeAdd", flagSizeAdd); 
					req.setAttribute("sizeVO", sizeVO); 
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				SizeService sizeSvc = new SizeService();
				sizeVO = sizeSvc.updateSize(size_id, emp_id, size_type, size_price, size_updatetime);
				
				req.setAttribute("flagSize", flagSize);
				req.setAttribute("flagSizeAdd", flagSizeAdd);
				req.setAttribute("sizeVO", sizeVO);
				RequestDispatcher successView = 
						req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
				successView.forward(req, res);
				
				
			}catch(Exception e){
				errorMsgs.add("錯誤訊息:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
				failureView.forward(req, res);
			}	
		}
		
		if("getOne_For_Insert".equals(action)){
			
			String flagSize=new String("ok");
			String flagSizeAdd=new String("no");
			try{
				SizeVO sizeVO =new SizeVO();
				
				java.sql.Timestamp size_updatetime = null;
				size_updatetime=new java.sql.Timestamp(System.currentTimeMillis());
				sizeVO.setSize_updatetime(size_updatetime);
				
				req.setAttribute("sizeVO", sizeVO);
				req.setAttribute("flagSize", flagSize);
				req.setAttribute("flagSizeAdd", flagSizeAdd);
				
				RequestDispatcher successView = 
						req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
				successView.forward(req, res);
				
				
			}catch(Exception e){
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)){
			
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try{
				String flagSize=new String("ok");
				String flagSizeAdd=new String("ok");
				String emp_id="";
				String size_type = req.getParameter("size_type");
				
				
				if(size_type==null || size_type.trim().length()==0){
					errorMsgs.add("條件不為空");
				}
				
				Double size_price = null;
				try{
					size_price=new Double(req.getParameter("size_price").trim());
				}catch (NumberFormatException e) {
					size_price = 0.0;
					errorMsgs.add("計價不為空");
				}
				
				java.sql.Timestamp size_updatetime = null;
				size_updatetime=new java.sql.Timestamp(System.currentTimeMillis());
				
				SizeVO sizeVO = new SizeVO();
				
				sizeVO.setEmp_id(emp_id);
				sizeVO.setSize_type(size_type);
				sizeVO.setSize_price(size_price);
				
				if(!errorMsgs.isEmpty()){
					flagSize="ok";
					req.setAttribute("sizeVO", sizeVO); 
					req.setAttribute("flagSize", flagSize);
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				SizeService sizeSvc = new SizeService();
				sizeVO = sizeSvc.addSize(emp_id, size_type, size_price);
				req.setAttribute("flagSizeAdd", flagSizeAdd); 
				RequestDispatcher successView= 
						req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
				successView.forward(req, res);
				
			}catch(Exception e){
				
				errorMsgs.add(e.getMessage());
				
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
				failureView.forward(req, res);
			}
							
		}
		
		
		if("delete".equals(action)){// 來自listAllMem.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			
			try{
				
				/***************************1.接收請求參數***************************************/
				String size_id=req.getParameter("size_id");
				System.out.println(req.getParameter("size_id"));
				/***************************2.開始刪除資料***************************************/
				SizeService sizeSvc = new SizeService();
				sizeSvc.deleteSize(size_id);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url="/backend/transport_fee/listAllFee.jsp";
				RequestDispatcher successView = 
						req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
				
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView=
						req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
				failureView.forward(req, res);
				
			}
			
		}
		
	}
}
