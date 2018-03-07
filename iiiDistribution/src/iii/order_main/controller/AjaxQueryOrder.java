package iii.order_main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

//import net.sf.json.JSONArray;

import org.json.*;

public class AjaxQueryOrder extends HttpServlet {
	
	private static final String TEST=
			"SELECT ORDER_ID, RECEIVER_CITY, RECEIVER_COUNTY, RECEIVER_ADDRESS FROM ORDER_MAIN WHERE ORDER_ID IN (SELECT ORDER_ID FROM LOCAL_ORDER) AND ORDER_ID IN ( SELECT ORDER_ID FROM LOCAL_ORDER WHERE LOCAL_SCHEDULE_ID IN ( SELECT LOCAL_SCHEDULE_ID FROM LOCAL_ORDER WHERE LOCAL_SCHEDULE_ID IN (SELECT LOCAL_SCHEDULE_ID FROM LOCAL_SCHEDULE WHERE LOCAL_SCHEDULE_ID = ?)))";
	
	private static final long serialVersionUID = 1L;
    public AjaxQueryOrder() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out= res.getWriter();
		String queryOrder=req.getParameter("queryOrder");
		List<String> list=new ArrayList<String>();
//		JSONArray array = new JSONArray();
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "GROUP4";
		String passwd = "GROUP4";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			StringBuilder sb =new StringBuilder();
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(TEST);
			
			pstmt.setString(1, queryOrder);
			rs = pstmt.executeQuery();
			sb.append("[");
			while(rs.next())
			{	
				sb.append("{\"ORDER_ID\":"+"\""+rs.getString("ORDER_ID")+"\", "
						+ "\"RECEIVER_CITY\":"+"\""+rs.getString("RECEIVER_CITY")+"\", "
						+ "\"RECEIVER_COUNTY\":"+"\""+rs.getString("RECEIVER_COUNTY")+"\", "
						+ "\"RECEIVER_CITY\":"+"\""+rs.getString("RECEIVER_ADDRESS")+"\"},");
			}
			sb.setLength(sb.length()-1);
			sb.append("]");	
			String str = sb.toString();
			out.print(str);
			out.close();
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn's load database"+e.getMessage());
		}catch(SQLException se){
			se.getMessage();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

	}
}