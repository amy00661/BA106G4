package login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import iii.emp.model.EmpService;

import javax.servlet.annotation.WebServlet;

@WebServlet("/backloginhandler")
public class backLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
   //【實際上應至資料庫搜尋比對】
  protected String allowUser(String account, String password) {
    /*if ("tomcat".equals(account) && "tomcat".equals(password))
      return true;
    else
      return false;*/
	  EmpService empSvc = new EmpService();
	  String emp_id = empSvc.getOneEmp(account).getEmp_id();
	  String emp_pwd = empSvc.getOneEmp(account).getEmp_pwd();
	  if(emp_id == null){
		  return "無此帳號!";
	  }else if(password.equals(emp_pwd)){
		  return "密碼錯誤!";
	  }
	  return "OK";
  }
  
  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    res.setContentType("text/html; charset=utf-8");
    PrintWriter out = res.getWriter();

    // 【取得使用者 帳號(account) 密碼(password)】
    String account = req.getParameter("empid");
    String password = req.getParameter("password");

    // 【檢查該帳號 , 密碼是否有效】
    String loginResult = allowUser(account,password);
    if (!("OK".equals(loginResult))) {          //【帳號 , 密碼無效時】
      out.println("<HTML><BODY>");
      out.println("<script>");
      out.println("alert(' "+loginResult+"請重新登入!' );");
      out.println("window.location.href = ' "+req.getContextPath()+"/backend/login.jsp';");
      out.println("</script>");
      out.println("</BODY></HTML>");
    }else {                                       //【帳號 , 密碼有效時, 才做以下工作】
      HttpSession session = req.getSession();
      session.setAttribute("account", account);   //*工作1: 才在session內做已經登入過的標識
      
       try {                                                        
         String location = (String) session.getAttribute("location");
         if (location != null) {
           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
           res.sendRedirect(location);            
           return;
         }
       }catch (Exception ignored) { }

      res.sendRedirect(req.getContextPath()+"/backend/login_success.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
    }
  }
}