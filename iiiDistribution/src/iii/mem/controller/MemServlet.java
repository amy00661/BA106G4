package iii.mem.controller;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import iii.mem.model.*;

public class MemServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)
		throws ServletException, IOException{
		
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)
		throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = null;
		
		if("getAll".equals(action)){
			/***************************開始查詢資料 ****************************************/
			MemDAO dao = new MemDAO();
			List<MemVO> list = dao.getAll();
			
			/***************************查詢完成,準備轉交(Send the Success view)*************/			
			session =req.getSession();
			session.setAttribute("list", list);    // 資料庫取出的list物件,存入session
			
			// Send the Success view
			String url = "/mem/listAllMem_getFromSession.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���listAllMem_getFromSession.jsp
			successView.forward(req, res);
			return;
			
		}
		
		if("getOne_For_Display".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			
			
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				
				String memId = req.getParameter("member_id");
				String memIdReg="^[M][E][M][0-9]{3}$";
				if(memId == null || (memId.trim().length() == 0)){
					errorMsgs.add("請輸入員工編號");		
				}else if(!(memId.trim().matches(memIdReg))){
					errorMsgs.add("員工編號格式錯誤");
				}
	
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/mem/select_MemPage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/				
				MemService memSvc= new MemService();
				MemVO memVO = memSvc.getOneMem(memId);
				if(memVO == null){
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView=req.getRequestDispatcher("/mem/select_MemPage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO);
				String url = "/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************��L�i�઺���~�B�z*************************************/
				
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" +e.getMessage());
				
				RequestDispatcher failureView = req.getRequestDispatcher("/mem/select_page.jsp");
				failureView.forward(req, res);
				
				
			}
			
		}
		
		
		if("getOne_For_Update".equals(action)){ // 來自listAllEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***************************1.接收請求參數****************************************/
				String member_id = req.getParameter("member_id");
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(member_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/				
				req.setAttribute("memVO", memVO);
				RequestDispatcher successView = 
						req.getRequestDispatcher("/backend/mem/update_mem_input.jsp");
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("無法取得要修改資料: "+e.getMessage());
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/backend/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}		
		}

		if("update".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			session =req.getSession();
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String flag=new String("no");
				String emp_id="";
				String member_status="01";
				String member_id=req.getParameter("member_id");
				System.out.println("Servlet MEMID = " + member_id);
				String member_mail=req.getParameter("member_mail");
				
				String member_psw=req.getParameter("member_psw");
				System.out.println("psw1 = " + member_psw);
				if(member_psw==null || member_psw.trim().length()==0){
					errorMsgs.add("密碼請勿空白");
				}
				
				String member_psw2=req.getParameter("member_psw2");
				System.out.println("psw2 = " + member_psw2);
				if(member_psw==null || member_psw.trim().length()==0){
					errorMsgs.add("密碼請勿空白");
				}
				else if(!(member_psw.equals(member_psw2))){
					errorMsgs.add("與密碼不一致");
				}
				
				String member_name=req.getParameter("member_name");
				if(member_name==null || member_name.trim().length()==0){
					member_name="";
				}
				
				java.sql.Date member_birth = null;
				try{
					member_birth=java.sql.Date.valueOf(req.getParameter("member_birth"));
				}catch(IllegalArgumentException e){
					member_birth=new java.sql.Date(System.currentTimeMillis());
				}
				
				java.sql.Timestamp member_updatetime = null;
				try{
					member_updatetime=java.sql.Timestamp.valueOf(req.getParameter("member_updatetime"));
				}catch(IllegalArgumentException e){
					member_updatetime=new java.sql.Timestamp(System.currentTimeMillis());
					
				}
				
				String member_gender=req.getParameter("member_gender");
				if(member_gender==null || member_gender.trim().length()==0){
					errorMsgs.add("請選擇性別");
				}
				
				String identification = req.getParameter("member_identification");
				String idenReg="^[a-zA-z][12][0-9]{8}$";
				if(identification==null || identification.trim().length()==0){
					errorMsgs.add("身分證(統編)請勿空白");
				}
				else if(!identification.trim().matches(idenReg)){
					errorMsgs.add("身分證(統編)格式錯誤");
				}
				
				String member_cell = req.getParameter("member_cell");
				String cellReg="^09[0-9]{2}-[0-9]{3}-[0-9]{3}$";
				if(member_cell==null || member_cell.trim().length()==0){
					errorMsgs.add("手機號碼請勿空白");	
				}else if(!member_cell.trim().matches(cellReg)){
					errorMsgs.add("手機號碼格式錯誤");
				}
				
				String member_phone = req.getParameter("member_phone");
				String phoneReg="^[0-9]{2,3}-[0-9]{7,8}$";
				
				if(member_phone==null || member_phone.trim().length()==0){
					member_phone="";
				}
				else if(!member_phone.trim().matches(phoneReg)){
					errorMsgs.add("電話號碼格式錯誤");	
				}
				
				
				String member_addr1 = req.getParameter("member_addr1");
				String member_addr2 = req.getParameter("member_addr2");
				String member_addr3 = req.getParameter("member_addr3");
				String member_addr = member_addr1+member_addr2+member_addr3;
				if(member_addr==null || member_addr.trim().length()==0){
					errorMsgs.add("地址請勿空白");
				}
				
				MemVO memVO = new MemVO();
				
				memVO.setEmp_id("");
				memVO.setMember_id(member_id);
				memVO.setMember_mail(member_mail);
				memVO.setMember_psw(member_psw2);
				memVO.setMember_name(member_name);
				memVO.setMember_birth(member_birth);
				memVO.setMember_gender(member_gender);
				memVO.setMember_identification(identification);
				memVO.setMember_cell(member_cell);
				memVO.setMember_phone(member_phone);
				memVO.setMember_addr(member_addr);
				memVO.setMember_status("01");
				
				if(!errorMsgs.isEmpty()){
					req.setAttribute("memVO", memVO);
					
					RequestDispatcher failureView =
							req.getRequestDispatcher("/frontend/mem/memDataUpdate.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.update(member_id, emp_id, member_mail, member_psw2, member_name, member_birth, member_gender,identification, member_cell, member_phone, member_addr, member_status,member_updatetime);
				flag="ok";
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				session.setAttribute("memVO", memVO);
				req.setAttribute("flag", flag);
			
				String url = "/frontend/mem/memData.jsp";
				RequestDispatcher successView = 
						req.getRequestDispatcher(url);
				successView.forward(req, res);
//				res.sendRedirect(req.getContextPath()+"/frontend/mem/memData.jsp");
				
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/mem/memDataUpdate.jsp");
				failureView.forward(req, res);
			}

		}
		
		if("updateBack".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			session =req.getSession();
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String emp_id="";
				String member_status=req.getParameter("member_status");
				String member_id=req.getParameter("member_id");
				String member_mail=req.getParameter("member_mail");
				
				String member_psw=req.getParameter("member_psw");
				if(member_psw==null || member_psw.trim().length()==0){
					errorMsgs.add("密碼請勿空白");
				}
				
				String member_name=req.getParameter("member_name");
				if(member_name==null || member_name.trim().length()==0){
					member_name="";
				}
				
				java.sql.Date member_birth = null;
				try{
					member_birth=java.sql.Date.valueOf(req.getParameter("member_birth"));
				}catch(IllegalArgumentException e){
					member_birth=new java.sql.Date(System.currentTimeMillis());
					
				}
				
				java.sql.Timestamp member_updatetime = null;
				try{
					member_updatetime=java.sql.Timestamp.valueOf(req.getParameter("member_updatetime"));
				}catch(IllegalArgumentException e){
					member_updatetime=new java.sql.Timestamp(System.currentTimeMillis());
				}
				
				String member_gender=req.getParameter("member_gender");
				if(member_gender==null || member_gender.trim().length()==0){
					errorMsgs.add("請選擇性別");
				}
				
				String identification = req.getParameter("member_identification");
				String idenReg="^[a-zA-z][12][0-9]{8}$";
				if(identification==null || identification.trim().length()==0){
					errorMsgs.add("身分證(統編)請勿空白");
				}
				else if(!identification.trim().matches(idenReg)){
					errorMsgs.add("身分證(統編)格式錯誤");
				}
				
				String member_cell = req.getParameter("member_cell");
				String cellReg="^09[0-9]{2}-[0-9]{3}-[0-9]{3}$";
				if(member_cell==null || member_cell.trim().length()==0){
					errorMsgs.add("手機號碼請勿空白");	
				}else if(!member_cell.trim().matches(cellReg)){
					errorMsgs.add("手機號碼格式錯誤");
				}
				
				String member_phone = req.getParameter("member_phone");
				String phoneReg="^[0-9]{2,3}-[0-9]{7,8}$";
				
				if(member_phone==null || member_phone.trim().length()==0){
					member_phone="";
				}
				else if(!member_phone.trim().matches(phoneReg)){
					errorMsgs.add("電話號碼格式錯誤");	
				}
				String member_addr = req.getParameter("member_addr");
				if(member_addr==null || member_addr.trim().length()==0){
					errorMsgs.add("地址請勿空白");
				}
				
				MemVO memVO = new MemVO();
				
				memVO.setEmp_id("");
				memVO.setMember_id(member_id);
				memVO.setMember_mail(member_mail);
				memVO.setMember_psw(member_psw);
				memVO.setMember_name(member_name);
				memVO.setMember_birth(member_birth);
				memVO.setMember_gender(member_gender);
				memVO.setMember_identification(identification);
				memVO.setMember_cell(member_cell);
				memVO.setMember_phone(member_phone);
				memVO.setMember_addr(member_addr);
				memVO.setMember_status(member_status);
				
				
				if(!errorMsgs.isEmpty()){
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView =
							req.getRequestDispatcher("/backend/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.update(member_id, emp_id, member_mail, member_psw, member_name, member_birth, member_gender,identification, member_cell, member_phone, member_addr, member_status,member_updatetime);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				session.setAttribute("memVO", memVO);
				
				String url = "/backend/mem/listAllMem.jsp";
				RequestDispatcher successView = 
						req.getRequestDispatcher(url);
				successView.forward(req, res);
//				res.sendRedirect(req.getContextPath()+"/frontend/mem/memData.jsp");
				
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}

		}
		
		if("insert".equals(action)){
			List<String> errorMsgs=new LinkedList<String>();
			System.out.println("insertsdfdsf");
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String emp_id="";
				String member_status="01";
				
				MemService memSvc=new MemService();
			    
				
				
				String member_mail=req.getParameter("member_mail");
				
				List<MemVO> list = memSvc.getAll();
				for(MemVO mem : list){
					if(mem.getMember_mail().equals(member_mail))
						errorMsgs.add("信箱請勿重複");
				}

				
				if(member_mail==null || member_mail.trim().length()==0){
					errorMsgs.add("信箱請勿空白");
				}
				
				
				String member_psw=req.getParameter("member_psw");
				if(member_psw==null || member_psw.trim().length()==0){
					errorMsgs.add("密碼請勿空白");
				}
				
				String member_psw2=req.getParameter("member_psw2");
				if(member_psw==null || member_psw.trim().length()==0){
					errorMsgs.add("確認密碼請勿空白");
				}
				else if(!(member_psw.equals(member_psw2))){
					errorMsgs.add("確認密碼與密碼不一致");
				}
				
				
				
				String member_name=req.getParameter("member_name");
				if(member_name==null || member_name.trim().length()==0){
					member_name="";
				}
				
	
				java.sql.Date member_birth = null;

				try{
					member_birth=java.sql.Date.valueOf(req.getParameter("member_birth").trim());
				}catch(IllegalArgumentException e){
					member_birth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				String member_gender=req.getParameter("member_gender");
				if(member_gender==null || member_gender.trim().length()==0){
					errorMsgs.add("請選擇性別");
				}
				
				String identification = req.getParameter("member_identification");
				String idenReg="^[a-zA-z][12][0-9]{8}$";
				
				if(identification==null || identification.trim().length()==0){
					errorMsgs.add("身分證(統編)請勿空白");
				}
				else if(!identification.trim().matches(idenReg)){
					errorMsgs.add("身分證(統編)格式錯誤");
				}
				
				String member_cell = req.getParameter("member_cell");
				String cellReg="^09[0-9]{2}-[0-9]{3}-[0-9]{3}$";
				if(member_cell==null || member_cell.trim().length()==0){
					errorMsgs.add("手機號碼請勿空白");	
				}else if(!member_cell.trim().matches(cellReg)){
					errorMsgs.add("手機號碼格式錯誤");
				}
				
				String member_phone = req.getParameter("member_phone");
				String phoneReg="^[0-9]{2,3}-[0-9]{7,8}$";
				
				if(member_phone==null || member_phone.trim().length()==0){
					member_phone="";
				}
				else if(!member_phone.trim().matches(phoneReg)){
					errorMsgs.add("電話格式錯誤");	
				}
				
				String member_addr1 = req.getParameter("member_addr1");
				String member_addr2 = req.getParameter("member_addr2");
				String member_addr3 = req.getParameter("member_addr3");
				String member_addr=member_addr1+member_addr2+member_addr3;
				
				if(member_addr==null || member_addr.trim().length()==0){
					errorMsgs.add("地址請勿空白");
				}
				
				String agree = req.getParameter("agree");
				if(!("yes".equals(agree))){
					errorMsgs.add("請點選同意網站條款");
				}
				
				MemVO memVO = new MemVO();
				
				/***************************保持原有輸入的值***************************************/
				memVO.setEmp_id("");
				memVO.setMember_mail(member_mail);
				memVO.setMember_psw(member_psw2);
				memVO.setMember_name(member_name);
				memVO.setMember_birth(member_birth);
				memVO.setMember_gender(member_gender);
				memVO.setMember_identification(identification);
				memVO.setMember_cell(member_cell);
				memVO.setMember_phone(member_phone);
				memVO.setMember_addr(member_addr3);
				memVO.setMember_status("01");
				
				if(!errorMsgs.isEmpty()){
					req.setAttribute("memVO",memVO);
					req.setAttribute("member_addr1",member_addr1);
					req.setAttribute("member_addr2",member_addr2);
					
					String url="/frontend/mem/addMember.jsp";
					RequestDispatcher failureView = 
							req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				
				memVO = memSvc.addMem(emp_id, member_mail, member_psw2, member_name, member_birth, member_gender,identification, member_cell, member_phone, member_addr, member_status);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url="/mem/listAllMem.jsp";
//				RequestDispatcher successView = 
//					req.getRequestDispatcher("/frontend/index.jsp");
//				successView.forward(req, res);
			
				res.sendRedirect(req.getContextPath()+"/frontend/index.jsp");
				
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e){
				String url="/frontend/mem/addMember.jsp";
				
				errorMsgs.add(e.getMessage());
				
				RequestDispatcher failureView=
					req.getRequestDispatcher(url);
				failureView.forward(req, res);
	
			}
			
		}
		
		if("delete".equals(action)){// 來自listAllMem.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				/***************************1.接收請求參數***************************************/
				String member_id=req.getParameter("member_id");
				
				/***************************2.開始刪除資料***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(member_id);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url="/mem/listAllMem.jsp";
				RequestDispatcher successView = 
						req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
				
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView=
						req.getRequestDispatcher("/mem/listAllMem.jsp");
				failureView.forward(req, res);
				
			}
			
		}

	}
}
