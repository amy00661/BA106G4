import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestStatusCodeUtil")
public class TestStatusCodeUtil extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public TestStatusCodeUtil() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<BODY>");
		HashMap<String,String> dbObjs = StatusCodeUtil.DB;
		Set<String> db_Keys = dbObjs.keySet();
		for(String db_id: db_Keys){
			out.print("<p>");
			out.print("貨運中心代碼："+db_id+"，貨運中心名稱："+dbObjs.get(db_id));
			out.print("</p>");
		}
		out.println("</BODY></HTML>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
