package iii.authority.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AuthorityDAO implements AuthorityDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO AUTHORITY (EMP_ID,MENU_ID) VALUES (?,?)";
	private static final String GET_Authority_ByEmpID_STMT = "SELECT * FROM AUTHORITY WHERE EMP_ID=? ";
	private static final String DELETE_AUTHORITY = "DELETE FROM AUTHORITY WHERE EMP_ID = ?";
	 
	
	@Override
	public int insert(String emp_id,Set<String> menuList) {
		int insertCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, emp_id);
			for(String menu_id:menuList){
				pstmt.setString(2, menu_id);
				pstmt.executeUpdate();
				insertCount++;
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
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
		return insertCount;
	}
	
	@Override
	public int delete(String empid) {
		int updateCount_AUTHs = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_AUTHORITY);
			pstmt.setString(1, empid);
			updateCount_AUTHs = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateCount_AUTHs;
	}

	@Override
	public Set<String> getAuthsByEmpid(String empid) {
		//Set<AuthorityVO> set = new LinkedHashSet<AuthorityVO>();
		Set<String> menuList = new LinkedHashSet<String>();
		//AuthorityVO authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Authority_ByEmpID_STMT);
			pstmt.setString(1, empid);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				menuList.add(rs.getString("MENU_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	
}
