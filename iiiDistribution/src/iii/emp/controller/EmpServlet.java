package iii.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;

import iii.emp.model.EmpVO;
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
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
//		out.print("Hello World!");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) { // 來自emp_add.jsp的請求 
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
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
				String[] menuArray = req.getParameterValues("menuArray");
				if(menuArray==null){
					errorMsgs.put("menuArray", "請設定員工權限!");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/employee/emp_add.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2-1.開始新增員工資料***************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.addEmp(db_id, "abc12345", emp_status, emp_name, emp_title, emp_email, emp_hireDate, emp_leaveDate);
				
				/***************************2-2.開始新增權限資料***************************************/
				Set<String> menuList = new HashSet<String>(Arrays.asList(menuArray));//取得有勾選的menu清單
				req.setAttribute("emp_id",empVO.getEmp_id());
				req.setAttribute("menuList",menuList);
				String authSvcURL = "/employee/AuthorityServlet.do";
				RequestDispatcher successView = req.getRequestDispatcher(authSvcURL);
				successView.include(req, res);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/employee/emp_query.jsp";
				successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);		
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/employee/emp_add.jsp");
				failureView.forward(req, res);
			}
			
		} if("getOne_For_Update".equals(action)){	// 來自emp_query.jsp的請求
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***************************1.接收請求參數****************************************/
				String emp_id = req.getParameter("empid");
				
				/***************************2.開始查詢資料****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
//				System.out.println(empVO);
				
				String url = "/backend/employee/emp_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception","無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/employee/emp_query.jsp");
				failureView.forward(req, res);
			}
			
		} if ("update".equals(action)) { // 來自emp_update.jsp的請求
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String emp_id = req.getParameter("empid");
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
					errorMsgs.put("leaveDate", "離職員工請輸入離職日期");
				} else if(!leaveDate.isEmpty()){
					try{
						emp_leaveDate= java.sql.Date.valueOf(leaveDate);
					}catch (IllegalArgumentException e) {
						errorMsgs.put("hireDate","日期格式輸入錯誤");
					}
				}
				String[] menuArray = req.getParameterValues("menuArray");
				if(menuArray==null){
					errorMsgs.put("menuArray", "請設定員工權限!");
				}
				// Send the use back to the form, if there were errors
				EmpVO empVO = new EmpVO();
				if (!errorMsgs.isEmpty()) {
					empVO.setEmp_id(emp_id);
					empVO.setDb_id(db_id);
					empVO.setEmp_status(emp_status);
					empVO.setEmp_name(emp_name);
					empVO.setEmp_title(emp_title);
					empVO.setEmp_email(emp_email);
					empVO.setEmp_hireDate(emp_hireDate);
					empVO.setEmp_leaveDate(emp_leaveDate);
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/employee/emp_update.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2-1.開始修改員工資料*****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(emp_id, db_id, "abc12345", emp_status, emp_name, emp_title, emp_email, emp_hireDate, emp_leaveDate);
				/***************************2-2.更新員工原來的權限資料***************************************/
				Set<String> menuList = new HashSet<String>(Arrays.asList(menuArray));//取得有勾選的menu清單
				req.setAttribute("emp_id",empVO.getEmp_id());
				req.setAttribute("menuList",menuList);
				String authSvcURL = "/employee/AuthorityServlet.do";
				RequestDispatcher successView = req.getRequestDispatcher(authSvcURL);
				successView.include(req, res);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/employee/emp_query.jsp";
				successView = req.getRequestDispatcher(url); // 修改成功後,轉交emp_query.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/employee/emp_update.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getAll".equals(action)) { // 來自emp_query.jsp的請求
//			System.out.println("取到資料了!");
			Gson gson = new Gson();
			/***************************開始查詢資料 ****************************************/
			EmpService empSvc = new EmpService();
			List<EmpVO> empList = empSvc.getAllEmps();
			// 4. Set response type to JSON
	        res.setContentType("application/json");
	        res.setCharacterEncoding("UTF-8");
	        res.setHeader("Cache-Control", "no-cache"); 
	        //get the PrintWriter object to write the html page
			String empJson = gson.toJson(empList);
//			System.out.println(empJson);
			out.print(empJson);
		} if("login".equals(action)){
			// 【取得使用者 帳號(account) 密碼(password)】
		    String account = req.getParameter("empid");
		    String password = req.getParameter("password");

		    // 【檢查該帳號 , 密碼是否有效】
		    HashMap<String, Object> loginResult = (HashMap<String, Object>) allowUser(account,password);
		    if ( loginResult.containsKey("error") ) {          //【帳號 , 密碼無效時】
		      out.println("<HTML><BODY>");
		      out.println("<script>");
		      out.println("alert(' "+loginResult+"請重新登入!' );");
		      out.println("window.location.href = ' "+req.getContextPath()+"/backend/news/listAllNews.jsp';");
		      out.println("</script>");
		      out.println("</BODY></HTML>");
		    } else {                                       //【帳號 , 密碼有效時, 才做以下工作】
		    	EmpVO empVO = (EmpVO) loginResult.get("OK");
		    	HttpSession session = req.getSession();
		    	session.setAttribute("account", empVO);   //*工作1: 才在session內做已經登入過的標識
		    	/***************************取得員工權限清單***************************************/
		    	String authSvcURL = "/employee/AuthorityServlet.do";
				RequestDispatcher successView = req.getRequestDispatcher(authSvcURL);
				successView.include(req, res);
				/***************************取得員工擁有權限的Menu Tree***************************************/
				String menuSvcURL = "/menu/MenuServlet.do";
				successView = req.getRequestDispatcher(menuSvcURL);
				successView.include(req, res);
		      
			       try {                                                        
			         String location = (String) session.getAttribute("location");
			         if (location != null) {
			           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
			           res.sendRedirect(location);            
			           return;
			         }
			       }catch (Exception ignored) { }
		
			      res.sendRedirect(req.getContextPath()+"/backend/news/listAllNews.jsp");  //*工作3: (-->如無來源網頁:則重導至index.jsp)
		    }
		} if("logout".equals(action)){
			HttpSession session = req.getSession();
		    session.removeAttribute("account");
		    res.sendRedirect(req.getContextPath()+"/backend/login.jsp");
		}
	}
	
	   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	   //【實際上應至資料庫搜尋比對】
	  protected Map<String,Object> allowUser(String account, String password) {
	    /*if ("tomcat".equals(account) && "tomcat".equals(password))
	      return true;
	    else
	      return false;*/
		  EmpService empSvc = new EmpService();
		  EmpVO empVO = empSvc.getOneEmp(account);
		  Map<String,Object> map = new HashMap<String, Object>();
		  if(empVO.getEmp_id() == null){
			  map.put("error", "無此帳號!");
		  }else if(!password.equals(empVO.getEmp_pwd())){
			  map.put("error", "密碼錯誤!");
		  }else{
			  map.put("OK", empVO);
		  }
		return map;
	  }

}
