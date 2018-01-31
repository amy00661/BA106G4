package iii.menu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MenuDAO implements MenuDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_STMT = "SELECT * FROM MENU WHERE MENU_ID=?";
	
	
	@Override
	public MenuVO findByPrimaryKey(String menu_id) {
		MenuVO menuVO = new MenuVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, menu_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				menuVO.setMenu_id(rs.getString("MENU_ID"));
				menuVO.setMenu_parent_id(rs.getString("MENU_PARENT_ID"));
				menuVO.setMenu_title(rs.getString("MENU_TITLE"));
				menuVO.setMenu_priorty(rs.getInt("MENU_PRIORITY"));
				menuVO.setMenu_url(rs.getString("MENU_URL"));
				menuVO.setEmp_id(rs.getString("EMP_ID"));
				menuVO.setMenu_updatetime(rs.getString("MENU_UPDATETIME"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
}
