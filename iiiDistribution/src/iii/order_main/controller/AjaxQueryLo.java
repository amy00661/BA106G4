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

public class AjaxQueryLo extends HttpServlet {
	
	private static final String TEST=
			"SELECT LOCAL_SCHEDULE_ID FROM LOCAL_SCHEDULE WHERE CAR_ID IN (SELECT CAR_ID FROM LOCAL_SCHEDULE WHERE CAR_ID IN (SELECT CAR_ID FROM CAR WHERE CAR_ID = ?))";
	
	
	private static final long serialVersionUID = 1L;
    public AjaxQueryLo() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out= res.getWriter();
		String queryLo=req.getParameter("queryLo");
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(TEST);
			
			pstmt.setString(1, queryLo);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{		
				list.add(rs.getString("LOCAL_SCHEDULE_ID"));
			}
			
			Gson gson = new Gson();
			String json = gson.toJson(list);
			out.print(json.toString());
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