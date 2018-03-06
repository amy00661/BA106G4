package iii.weight.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iii.size.model.SizeService;
import iii.size.model.SizeVO;
import iii.weight.model.WeightService;
import iii.weight.model.WeightVO;

@WebServlet("/WeightServlet")
public class WeightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req,res);
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			String flagWeight=new String("no");
			String flagWeightAdd=new String("ok");
			try{
				
				String weight_id=req.getParameter("weight_id");
				
				WeightService weightSvc=new WeightService();
				WeightVO weightVO = weightSvc.getOneWeight(weight_id);
				
				req.setAttribute("weightVO", weightVO);
				req.setAttribute("flagWeight", flagWeight);
				req.setAttribute("flagWeightAdd", flagWeightAdd);
				String url="/backend/transport_fee/listAllFee.jsp";
				RequestDispatcher successView = 
						req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e){
				flagWeight="ok";
				errorMsgs.add("無法取得要修改資料: " + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
				failureView.forward(req, res);
			}	

		}
		if("update".equals(action)){
			
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				String flagWeight=new String("ok");
				String flagWeightAdd=new String("ok");
				String emp_id=req.getParameter("emp_id");
				String weight_id=req.getParameter("weight_id");
				String weight_type=req.getParameter("weight_type");
				
				if(weight_type==null || weight_type.trim().length()==0){
					errorMsgs.add("重量條件不能為空");
				}
				
				Double weight_price = null;
				try{
					weight_price=new Double(req.getParameter("weight_price").trim());
				}catch (NumberFormatException e) {
					weight_price = 0.0;
					errorMsgs.add("重量費率不能為空");
				}
				
				java.sql.Timestamp weight_updateTime = null;
				weight_updateTime=new java.sql.Timestamp(System.currentTimeMillis());
				
				WeightVO weightVO = new WeightVO();
				
				weightVO.setEmp_id(emp_id);
				weightVO.setWeight_type(weight_type);
				weightVO.setWeight_price(weight_price);
				
				if(!errorMsgs.isEmpty()){
					flagWeight="no";
					req.setAttribute("flagWeight", flagWeight); 
					req.setAttribute("flagWeightAdd", flagWeightAdd); 
					req.setAttribute("weightVO", weightVO);
					RequestDispatcher failureView =
							req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				WeightService weightSvc = new WeightService();
				weightVO = weightSvc.updateWeight(weight_id, emp_id, weight_type, weight_price, weight_updateTime);
				req.setAttribute("flagWeight", flagWeight);
				req.setAttribute("flagWeightAdd", flagWeightAdd);
				req.setAttribute("weightVO", weightVO);
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
			String flagWeight=new String("ok");
			String flagWeightAdd=new String("no");
			try{
				WeightVO weightVO =new WeightVO();
				
				java.sql.Timestamp weight_updateTime = null;
				weight_updateTime=new java.sql.Timestamp(System.currentTimeMillis());
				weightVO.setWeight_updateTime(weight_updateTime);
				req.setAttribute("weightVO", weightVO);
				req.setAttribute("flagWeight", flagWeight);
				req.setAttribute("flagWeightAdd", flagWeightAdd);
				
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
				String flagWeight=new String("ok");
				String flagWeightAdd=new String("ok");
				String emp_id="EMP001";
				String weight_type=req.getParameter("weight_type");
				
				if(weight_type==null || weight_type.trim().length()==0){
					errorMsgs.add("重量條件不能為空");
				}
				
				Double weight_price = null;
				try{
					weight_price=new Double(req.getParameter("weight_price").trim());
				}catch (NumberFormatException e) {
					weight_price = 0.0;
					errorMsgs.add("重量計價不能為空");
				}
				
				java.sql.Timestamp weight_updateTime = null;
				weight_updateTime=new java.sql.Timestamp(System.currentTimeMillis());
				
				WeightVO weightVO = new WeightVO();
				
				weightVO.setEmp_id(emp_id);
				weightVO.setWeight_type(weight_type);
				weightVO.setWeight_price(weight_price);
				
				if(!errorMsgs.isEmpty()){
					req.setAttribute("weightVO", weightVO); 
					req.setAttribute("flagWeight", flagWeight); 
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				WeightService weightSvc = new WeightService();
				weightVO = weightSvc.addWeight(emp_id, weight_type, weight_price);
				req.setAttribute("flagWeightAdd", flagWeightAdd); 
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
				String weight_id=req.getParameter("weight_id");
				
				/***************************2.開始刪除資料***************************************/
				WeightService weightSvc = new WeightService();
				weightSvc.deleteWeight(weight_id);
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
