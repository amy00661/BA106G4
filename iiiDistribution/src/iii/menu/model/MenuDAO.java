package iii.menu.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

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
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return null;
	}


	
	
	@Override
	public LinkedHashSet<MenuNodeVO> getMenuBar_ByEmp(String emp_id, int menuCount) {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		LinkedHashSet<MenuNodeVO> rootTreeVO = new LinkedHashSet<>();
		//使用員編、及此員工的menu總筆數，傳入Procedure取得各節點的完整階層資料
			try {
				con = ds.getConnection();
				cstmt = con.prepareCall("{call GET_NODE_HIRACHY(?,?,?)}");
				cstmt.setString(1, emp_id);
				//HashMap<String,JsonTreeVO> treeMap = new HashMap<>();
				
				for(int i=1 ; i<=menuCount ; i++){
					cstmt.setInt(2, i);
					cstmt.registerOutParameter(3, OracleTypes.CURSOR);
					// execute GET_NODE_HIRACHY store procedure
					cstmt.executeUpdate();
					// get cursor and cast it to ResultSet
					rs = (ResultSet) cstmt.getObject(3);
					rootTreeVO = GenerateMenuTree(rs,rootTreeVO);
				}

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}

				if (cstmt != null) {
					try {
						cstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}

				if (con != null) {
					try {
						con.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}

			}
		return rootTreeVO;
	}

	public LinkedHashSet<MenuNodeVO> GenerateMenuTree(ResultSet rs,LinkedHashSet<MenuNodeVO> rootTreeVO){
			MenuNodeVO parentTreeVO =null;
			MenuNodeVO currentTreeVO = null;
			try {
				while (rs.next()) {
					currentTreeVO = new MenuNodeVO();
					currentTreeVO.setId(rs.getString("MENU_ID"));
					currentTreeVO.setText(rs.getString("MENU_TITLE"));
					if( parentTreeVO == null ){
						if(rootTreeVO.contains(currentTreeVO)){
							for(MenuNodeVO rootTreeObj:rootTreeVO){
								if(rootTreeObj.equals(currentTreeVO))
									parentTreeVO = rootTreeObj;
							}
						}
						else{
							rootTreeVO.add(currentTreeVO);
							parentTreeVO = currentTreeVO;
						}
					}else{
						LinkedHashSet<MenuNodeVO> childrenList = parentTreeVO.getChildren();
						if(childrenList.contains(currentTreeVO)){
							for(MenuNodeVO childrenObj:childrenList){
								if(childrenObj.equals(currentTreeVO))
									parentTreeVO = childrenObj;
							}
						}else{
							parentTreeVO.getChildren().add(currentTreeVO);
							parentTreeVO = currentTreeVO;
						}
					}
				}
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
			return rootTreeVO;
		}
}
