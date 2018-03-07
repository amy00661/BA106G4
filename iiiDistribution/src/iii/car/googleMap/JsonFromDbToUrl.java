package iii.car.googleMap;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonFromDbToUrl extends HttpServlet {
	
	private static final long serialVersionUID = -6416523496068444037L;
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestJSON");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final String SQL = "SELECT JSON_NAME FROM JSON WHERE JSON_ID = ?";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		HttpSession session = req.getSession();
	   
	    Integer count = (Integer)session.getAttribute("json_id");
	    if (count == null){
	      count = new Integer(1);
	    }
	    else
	      count = new Integer(count.intValue() + 1);
	    session.setAttribute("json_id", count);
		if(count>2100) session.setAttribute("json_id", 200);
		try {
			PrintWriter out = res.getWriter();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, count);
			ResultSet rs1 = pstmt.executeQuery();
			rs1.next();
			Clob clob = rs1.getClob(1);
			String sb = readString(clob);

//			System.out.println(sb);
			System.out.println(count);
			/******************** JSON ********************/

			JSONObject jObj = new JSONObject(sb.toString());
			JSONObject jObj2 = jObj.getJSONObject("result");
			JSONArray jArray = jObj2.getJSONArray("records");
//			for (int i = 0; i < jArray.length(); i++) {
//				Double speed = 0.0;
//				JSONObject data = jArray.getJSONObject(i);
//			}
//				String spe = data.getString("Speed");
//				if(!spe.equals(""))
//					speed = Double.valueOf(spe);
//				if(speed>0){
//				String busID = data.getString("BusID");
//				String routeId = data.getString("RouteID");
//				
//				
//				String latitude = data.getString("Latitude");
//				Double lat = 0.0; 
//				if(!latitude.equals(""))
//					lat = Double.valueOf(latitude);
//
//				String longitude = data.getString("Longitude");
//				Double lng = 0.0;
//				if(!longitude.equals(""))
//					lng = Double.valueOf(longitude);
//					System.out.println("BusID = " + busID);
//					System.out.println("Speed = " + speed);
//					System.out.println("Latitude = " + lat);
//					System.out.println(lat.getClass());
//					System.out.println("Longitude = " + lng);
//					System.out.println(lng.getClass());
//					System.out.println("RouteID = " + routeId);
//					System.out.println("====================================");
				out.print(jArray);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	public static String readString(Clob clob) throws IOException, SQLException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(clob.getCharacterStream());
		String str;
		while((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		return sb.toString();
	}
}
