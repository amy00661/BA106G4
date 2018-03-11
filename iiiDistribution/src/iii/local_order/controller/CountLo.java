package iii.local_order.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import iii.car.model.CarVO;

public class CountLo {
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	static String userid = "GROUP4";
	static String passwd = "GROUP4";
	
	private static final String COUNTLO=
			"SELECT COUNT(?) FROM LOCAL_ORDER WHERE LOCAL_SCHEDULE_ID = ?";
	
	public static int count(String LO, String LS){
		
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(COUNTLO);
			
			pstmt.setString(1, LO);
			pstmt.setString(2, LS);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
		        System.out.println("numberOfRows= " + count);
			} else {
				System.out.println("error: could not get the record counts");
		    }
			
			
			if (rs.next()) {
		        int numberOfRows = rs.getInt(1);
		        System.out.println("numberOfRows= " + numberOfRows);
		      } else {
		        System.out.println("error: could not get the record counts");
		   }
			
		} catch (ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(pstmt != null){
				try{
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return count;
	}

}
