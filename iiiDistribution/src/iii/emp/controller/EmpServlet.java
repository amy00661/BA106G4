package iii.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iii.emp.model.EmpService;

public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public EmpServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
//		out.print("Hello World!");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) { // 來自emp_add.jsp的請求 
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String contextPath = req.getContextPath();
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String emp_name = req.getParameter("empname");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.put("empname","員工姓名: 請勿空白");
				} else if(!emp_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("empname","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String emp_email = req.getParameter("empmail");
				String emailReg = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4})$";
				if (emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.put("empmail","信箱：請勿空白");
				} else if(!emp_email.trim().matches(emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("empmail","請輸入正確的信箱格式");
	            }
				String emp_title = req.getParameter("emp_title");
				String emp_status = req.getParameter("empstatus");
				String db_id = req.getParameter("db_id");
				java.sql.Date emp_hireDate = null;
				try {
					String hireDate = req.getParameter("hireDate").trim();
					if(!hireDate.isEmpty())
						emp_hireDate= java.sql.Date.valueOf(hireDate);
					else
						errorMsgs.put("hireDate","請輸入日期");
				} catch (IllegalArgumentException e) {
					errorMsgs.put("hireDate","日期格式輸入錯誤");
				}
				java.sql.Date emp_leaveDate = null;
				String leaveDate = req.getParameter("leaveDate").trim();		
				if(leaveDate.isEmpty() && "00".equals(emp_status)){	
					errorMsgs.put("leaveDate", "請輸入日期");
				} else if(!leaveDate.isEmpty()){
					try{
						emp_leaveDate= java.sql.Date.valueOf(leaveDate);
					}catch (IllegalArgumentException e) {
						errorMsgs.put("hireDate","日期格式輸入錯誤");
					}
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/emp_add.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EmpService empSvc = new EmpService();
				empSvc.addEmp(db_id, "abc12345", emp_status, emp_name, emp_title, emp_email, emp_hireDate, emp_leaveDate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/employee/empQuery.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);		
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/emp_add.jsp");
				failureView.forward(req, res);
			}
			
		} if("getAll".equals(action)){
			out.print("success");
		}
	}

}
