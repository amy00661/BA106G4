package iii.car.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import iii.car.model.CarService;
import iii.car.model.CarVO;

/**
 * Servlet implementation class CarServlet
 */

public class CarServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		if("getOne_For_Update".equals(action)){
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String db_id = req.getParameter("db_id");
//				String db_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (db_id == null || db_id.trim().length() == 0) {
//					errorMsgs.put("db_id","員工姓名: 請勿空白");
//				}
//				String emp_id = req.getParameter("emp_id");
//				String emp_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (emp_id == null || emp_id.trim().length() == 0) {
//					errorMsgs.put("emp_id","員工姓名: 請勿空白");
//				}
//				String car_plate = req.getParameter("car_plate");
//				String car_plateReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (car_plate == null || car_plate.trim().length() == 0) {
//					errorMsgs.put("car_plate","員工姓名: 請勿空白");
//				}
//				String car_brand = req.getParameter("car_brand");
//				String car_brandReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (car_brand == null || car_brand.trim().length() == 0) {
//					errorMsgs.put("car_brand","員工姓名: 請勿空白");
//				}
//				String car_driver = req.getParameter("car_driver");
//				String car_driverReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (car_driver == null || car_driver.trim().length() == 0) {
//					errorMsgs.put("car_driver","員工姓名: 請勿空白");
//				}
//				String car_status = req.getParameter("car_status");
//				String car_statusReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (car_status == null || car_status.trim().length() == 0) {
//					errorMsgs.put("car_status","員工姓名: 請勿空白");
//				}
//				String car_color = req.getParameter("car_color");
//				String car_colorReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (car_color == null || car_color.trim().length() == 0) {
//					errorMsgs.put("car_color","員工姓名: 請勿空白");
//				}
//				Double car_pdv = null;
//				try {
//					car_pdv = new Double(req.getParameter("car_pdv").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("car_pdv","薪水請填數字");
//				}
//				Double car_load = null;
//				try {
//					car_load = new Double(req.getParameter("car_load").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("car_load","薪水請填數字");
//				}
//				String car_note = req.getParameter("car_note");
//				String car_noteReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (car_note == null || car_note.trim().length() == 0) {
//					errorMsgs.put("car_note","員工姓名: 請勿空白");
//				}
//
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/backend/car/car.jsp");
//					failureView.forward(req, res);
//					System.out.println(95);
//					return;
//				}
//				/***************************2.開始新增資料***************************************/
//				CarService carSvc = new CarService();
//				carSvc.addCar(db_id, emp_id, car_plate, car_driver, car_status, car_brand, car_color, car_pdv, car_load, car_note);
//				
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "backend/car/car.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);		
//				
//				/***************************其他可能的錯誤處理**********************************/
//			
//			} catch (Exception e) {
//				errorMsgs.put("Exception",e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("backend/car/car.jsp");
//				failureView.forward(req, res);
//				e.printStackTrace();
//			}		
//		}
		
		if("delete".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
// 			req.setAttribute("errorMsgs", errorMsgs);
 			
 			String requestURL = req.getParameter("requestURL");
 			
 			try{
 				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
 				String car_id = new String(req.getParameter("car_id"));
 				
 				/***************************2.開始新增資料***************************************/
 				CarService carSrv = new CarService();
 				carSrv.deleteCar(car_id);
 				
 				/***************************3.新增完成,準備轉交(Send the Success view)***********/
 				String url = requestURL;
 				RequestDispatcher successView = req.getRequestDispatcher(url);
 				successView.forward(req, res);
 				System.out.println(142);
 				
 			} catch (Exception e) {
				errorMsgs.add("Exception"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/car/car.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}		
		}
		
		if("update".equals(action)){
		      Map<String, String> updateError = new LinkedHashMap<String, String>();
		      req.setAttribute("updateError", updateError);
		      
		      try{
		        /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		        String car_id = new String(req.getParameter("car_id").trim());
		        
		    	String db_id = req.getParameter("db_id");
		        if (db_id == null || db_id.trim().length() == 0) {
		          updateError.put("db_id","貨運公司: 請勿空白");
		        }
		        String emp_id = req.getParameter("emp_id");
		        if (emp_id == null || emp_id.trim().length() == 0) {
		          updateError.put("emp_id","員工姓名: 請勿空白");
		        }
		        String car_plate = req.getParameter("car_plate");
		        if (car_plate == null || car_plate.trim().length() == 0) {
		          updateError.put("car_plate","車輛車牌: 請勿空白");
		        }   
		        
		        String car_brand = req.getParameter("car_brand");
		        String car_driver = req.getParameter("car_driver");
		        String car_status = req.getParameter("car_status");
		        if (car_status == null || car_status.trim().length() == 0) {
		          updateError.put("car_status","車輛狀態: 請勿空白");
		        }
		        String car_color = req.getParameter("car_color");
		        
		        Double car_pdv = null;
		        try {
		          car_pdv = new Double(req.getParameter("car_pdv").trim());
		        } catch (NumberFormatException e) {
		          updateError.put("car_pdv","排氣量請填數字");
		        }
		        Double car_load = null;
		        try {
		          car_load = new Double(req.getParameter("car_load").trim());
		        } catch (NumberFormatException e) {
		          updateError.put("car_load","負重請填數字");
		        }		    
		        
		        java.sql.Timestamp car_updatetime = null;
		        try{
		        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		        java.util.Date parseDate = df.parse(req.getParameter("car_updatetime"));	 
		        car_updatetime = new java.sql.Timestamp(parseDate.getTime());
		        
		        } catch (IllegalArgumentException | ParseException e) {
		        	
		        	car_updatetime = new java.sql.Timestamp(System.currentTimeMillis());
		        	updateError.put("car_updatetime","愚蠢");
		        }
		       
		        String car_note = req.getParameter("car_note");

		        if(!updateError.isEmpty()) {
		          RequestDispatcher failureView = req.getRequestDispatcher("/backend/car/car.jsp");
		          failureView.forward(req, res);
		          System.out.println(210);
		          return;
		        }
		        /***************************2.開始新增資料***************************************/
				CarService carSvc = new CarService();
				carSvc.updateCar(car_id, db_id, emp_id, car_plate, car_driver, car_status, car_brand, car_color, car_pdv, car_load, car_updatetime, car_note);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/car/car.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				System.out.println(223);
				/***************************其他可能的錯誤處理**********************************/
		        
				} catch (Exception e) {
					updateError.put("Exception",e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/car/car.jsp");
					failureView.forward(req, res);
					e.printStackTrace();
					System.out.println(230);
				}	      
		    }	
		
		if("insert".equals(action)){
			
//			JsonObject json = new JsonObject();
			Map<String, String> addError = new LinkedHashMap<String, String>();
			req.setAttribute("addError", addError);
//			
//			
//			Gson gson = new Gson();
//			
//			String gg = gson.toJson(addError);
//			
//			json.addProperty("error", gg);
//			String xx = json.toString();
//			PrintWriter out = res.getWriter();
//			out.write(xx);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String db_id = req.getParameter("db_id");
				if (db_id == null || db_id.trim().length() == 0) {
					addError.put("db_id","貨運公司: 請勿空白");
				}
				String emp_id = req.getParameter("emp_id");
				if (emp_id == null || emp_id.trim().length() == 0) {
					addError.put("emp_id","員工姓名: 請勿空白");
				}
				String car_plate = req.getParameter("car_plate");
				if (car_plate == null || car_plate.trim().length() == 0) {
					addError.put("car_plate","車輛車牌: 請勿空白");
				} 	
				String car_brand = req.getParameter("car_brand");
				String car_driver = req.getParameter("car_driver");
				String car_status = req.getParameter("car_status");
				String car_statusReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (car_status == null || car_status.trim().length() == 0) {
					addError.put("car_status","車輛狀態: 請勿空白");
				}
				String car_color = req.getParameter("car_color");
				Double car_pdv = null;
				try {
					car_pdv = new Double(req.getParameter("car_pdv").trim());
				} catch (NumberFormatException e) {
					addError.put("car_pdv","排氣量請填數字");
				}
				Double car_load = null;
				try {
					car_load = new Double(req.getParameter("car_load").trim());
				} catch (NumberFormatException e) {
					addError.put("car_load","負重請填數字");
				}
				String car_note = req.getParameter("car_note");
				
				CarVO carVO = new CarVO();
				carVO.setDb_id(db_id);
				carVO.setEmp_id(emp_id);
				carVO.setCar_driver(car_driver);

				if(!addError.isEmpty()) {
					req.setAttribute("carVO", carVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/car/car.jsp");
					failureView.forward(req, res);
					return;					
				}
				/***************************2.開始新增資料***************************************/
				CarService carSvc = new CarService();
				carSvc.addCar(db_id, emp_id, car_plate, car_driver, car_status, car_brand, car_color, car_pdv, car_load, car_note);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/car/car.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);		
				
				/***************************其他可能的錯誤處理**********************************/
			
			} catch (Exception e) {
				addError.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/car/car.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
				System.out.println(309);
			}
		}	
	}	
}