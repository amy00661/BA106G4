package iii.tra.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import iii.size.model.SizeVO;
import iii.tra.model.*;
import iii.weight.model.WeightService;

@WebServlet("/TraServlet")
public class TraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)){
			
			
			List<String> errorMsgs = new LinkedList<>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				
				String transition_id = req.getParameter("transition_id");
				String traIdReg="^[T][R][A][0-9]{3}$";
				if(transition_id == null || (transition_id.trim().length() == 0)){
					errorMsgs.add("編號不為空");		
				}else if(!(transition_id.trim().matches(traIdReg))){
					errorMsgs.add("編號格式不正確");
				}
				
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView =
							req.getRequestDispatcher("/tra/select_TraPage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				TraService traSvc = new TraService();
				TraVO traVO =traSvc.getOneTra(transition_id);
				
				if(traVO == null){
					errorMsgs.add("�d�L���");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView =
							req.getRequestDispatcher("/tra/select_TraPage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("traVO", traVO);
				RequestDispatcher successView =
						req.getRequestDispatcher("/tra/listOneTra.jsp");
				successView.forward(req, res);
				
			}
			catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/tra/select_TraPage.jsp");
				failureView.forward(req, res);
			}
			
			}
			
		
		if("getOne_For_Update".equals(action)){
			
			List<String> errorMsgs = new LinkedList<>();
		
			req.setAttribute("errorMsgs", errorMsgs);
			String flagTra=new String("no");
			String flagTraAdd=new String("ok");
			try{
				String transition_id=req.getParameter("transition_id");
				
				TraService TraSvc = new TraService();
				TraVO traVO = TraSvc.getOneTra(transition_id);
				
				req.setAttribute("traVO", traVO);
				req.setAttribute("flagTra", flagTra);
				req.setAttribute("flagTraAdd", flagTraAdd);
				RequestDispatcher successView = 
						req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
			
				successView.forward(req, res);
				
				
			}catch(Exception e){
				flagTra="ok";
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
				String flagTra=new String("ok");
				String flagTraAdd=new String("ok");
				String emp_id=req.getParameter("emp_id");;
				String transition_id=req.getParameter("transition_id");
				String transition_type=req.getParameter("transition_type");
				if(transition_type==null || transition_type.trim().length()==0){
					errorMsgs.add("配送條件不能為空");
				}
				
				Double transition_price = null;
				try{
				 transition_price=new Double(req.getParameter("transition_price").trim());
				}catch (NumberFormatException e) {
					transition_price = 0.0;
					errorMsgs.add("配送費率不能為空");
				}
				java.sql.Timestamp transition_updatetime = null;
				transition_updatetime=new java.sql.Timestamp(System.currentTimeMillis());
				TraVO traVO = new TraVO();
				
				traVO.setEmp_id(emp_id);
				traVO.setTransition_type(transition_type);
				traVO.setTransition_price(transition_price);
				
				
				
				if(!errorMsgs.isEmpty()){
					
					flagTra="no";
					req.setAttribute("flagTra", flagTra);
					req.setAttribute("flagTraAdd", flagTraAdd);
					req.setAttribute("traVO", traVO);
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				TraService traSvc = new TraService();
				traVO = traSvc.update(transition_id, emp_id, transition_type, transition_price, transition_updatetime);
				req.setAttribute("flagTra", flagTra);
				req.setAttribute("flagTraAdd", flagTraAdd);
				req.setAttribute("traVO", traVO);
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
			
			String flagTra=new String("ok");
			String flagTraAdd=new String("no");
			try{
				TraVO traVO =new TraVO();
				
				java.sql.Timestamp transition_updatetime = null;
				transition_updatetime=new java.sql.Timestamp(System.currentTimeMillis());
				traVO.setTransition_updatetime(transition_updatetime);
				
				req.setAttribute("traVO", traVO);
				req.setAttribute("flagTra", flagTra);
				req.setAttribute("flagTraAdd", flagTraAdd);
				
				RequestDispatcher successView = 
						req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
				successView.forward(req, res);
				
				
			}catch(Exception e){
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)){
			
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				String flagTra=new String("ok");
				String flagTraAdd=new String("ok");
				String emp_id="";
				String transition_type=req.getParameter("transition_type");
				
				if(transition_type==null || transition_type.trim().length()==0){
					errorMsgs.add("配送條件不能為空");
				}
				
				Double transition_price = null;
				try{
				 transition_price=new Double(req.getParameter("transition_price").trim());
				}catch (NumberFormatException e) {
					transition_price = 0.0;
					errorMsgs.add("配送費率不能為空");
				}
			
				
				java.sql.Timestamp transition_updatetime = null;
				transition_updatetime=new java.sql.Timestamp(System.currentTimeMillis());
				
				TraVO traVO = new TraVO();
				
				traVO.setEmp_id(emp_id);
				traVO.setTransition_type(transition_type);
				traVO.setTransition_price(transition_price);
				
				if(!errorMsgs.isEmpty()){
					req.setAttribute("traVO", traVO); 
					req.setAttribute("flagTra", flagTra); 
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/backend/transport_fee/listAllFee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				TraService traSvc = new TraService();
				traVO = traSvc.addTra(emp_id, transition_type, transition_price);
				req.setAttribute("flagTraAdd", flagTraAdd);
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
				String transition_id=req.getParameter("transition_id");
				
				/***************************2.開始刪除資料***************************************/
				TraService traSvc = new TraService();
				traSvc.deleteTra(transition_id);
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
