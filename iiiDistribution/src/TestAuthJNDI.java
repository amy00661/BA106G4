/** 自行取得DataSource的 servlet
 
 1.需配合 web.xml 如下:
    <resource-ref>
      <description>DB Connection</description>
      <res-ref-name>jdbc/TestDB</res-ref-name>
      <res-type>javax.sql.DataSource</res-type>
      <res-auth>Container</res-auth>
    </resource-ref>
 2.需配合 server.xml
    -參考: http://localhost:8080/index.jsp 首頁
             之 Tomcat Documentation 之 JNDI DataSource HOW-TO 的說明
    -注意: 隨 servlet container 版本寫法會不同              
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.naming.*;
import javax.sql.*;

import iii.authority.model.AuthorityDAO;
import iii.authority.model.AuthorityDAO_interface;
import iii.authority.model.AuthorityVO;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;
@WebServlet("/authority/authority.do")
public class TestAuthJNDI extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String empid = "EMP001";
		AuthorityDAO_interface authDAO = new AuthorityDAO();
		//測試以員工編號查出權限
		Set<AuthorityVO> authSet = authDAO.getAuthsByEmpid(empid);
		for(AuthorityVO authorityVO:authSet){
			out.println(authorityVO);
		}
		
		//測試刪除特定員工的權限
		int deleteCount = authDAO.delete(empid);
		System.out.println("成功刪除"+deleteCount+"筆員工權限");
		
		//測試寫入員工權限
		Set<String> menuList = new LinkedHashSet<String>();
		menuList.add("MENU001");
		menuList.add("MENU002");
		menuList.add("MENU003");
		int insertCount = authDAO.insert(empid,menuList);
		System.out.println("已新增員工:"+empid+"的"+insertCount+"筆權限");
		
	}
}