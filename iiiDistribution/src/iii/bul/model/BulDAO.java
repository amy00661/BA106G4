package iii.bul.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

import java.sql.*;


public class BulDAO implements BulDAO_interface {

	private static DataSource ds=null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INTO BULLETIN(bulletin_id,emp_id,bulletin_date,bulletin_title,bulletin_context,bulletin_note)"
			+ "VALUES ('BUL'||LPAD(TO_CHAR(bulletin_seq.NEXTVAL), 3, '0'),?,?,?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT bulletin_id, emp_id,to_char(bulletin_date,'yyyy-mm-dd hh:mi:ss')bulletin_date,bulletin_title,bulletin_context,bulletin_note,"
			+"to_char(bulletin_updatetime,'yyyy-mm-dd hh:mi:ss')bulletin_updatetime FROM BULLETIN order by bulletin_id";
	private static final String GET_ONE_STMT = 
			"SELECT bulletin_id, emp_id,to_char(bulletin_date,'yyyy-mm-dd hh:mi:ss')bulletin_date,bulletin_title,bulletin_context,bulletin_note,"
			+"to_char(bulletin_updatetime,'yyyy-mm-dd hh:mi:ss')bulletin_updatetime FROM BULLETIN where bulletin_id = ?";
	private static final String DELETE =
			"DELETE FROM BULLETIN where bulletin_id = ?";
	private static final String UPDATE = 
			"UPDATE BULLETIN set emp_id=?, bulletin_date=?,bulletin_title=?,bulletin_context=?,bulletin_note=?,bulletin_updatetime=? where bulletin_id = ?";
	
	
	@Override
	public void insert(BulVO bulVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,bulVO.getEmp_id());
			pstmt.setTimestamp(2, bulVO.getBulletin_date());
			pstmt.setString(3, bulVO.getBulletin_title());
			pstmt.setString(4, bulVO.getBulletin_context());
			pstmt.setString(5, bulVO.getBulletin_note());
			pstmt.executeUpdate();
			
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
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

	@Override
	public void update(BulVO bulVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, bulVO.getEmp_id());
			pstmt.setTimestamp(2,bulVO.getBulletin_date());
			pstmt.setString(3, bulVO.getBulletin_title());
			pstmt.setString(4,bulVO.getBulletin_context());
			pstmt.setString(5, bulVO.getBulletin_note());
			pstmt.setTimestamp(6, bulVO.getBulletin_updatetime());
			pstmt.setString(7, bulVO.getBulletin_id());
			
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public void delete(String bulletin_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
		
			pstmt.setString(1, bulletin_id);
			pstmt.executeUpdate();
			
		
		
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public BulVO findByPrimaryKey(String bulletin_id) {
		
		BulVO bulVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, bulletin_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				bulVO = new BulVO();
				bulVO.setBulletin_id(rs.getString("bulletin_id"));
				bulVO.setEmp_id(rs.getString("emp_id"));
				bulVO.setBulletin_date(rs.getTimestamp("bulletin_date"));
				bulVO.setBulletin_title(rs.getString("bulletin_title"));
				bulVO.setBulletin_context(rs.getString("bulletin_context"));
				bulVO.setBulletin_note(rs.getString("bulletin_note"));
				bulVO.setBulletin_updatetime(rs.getTimestamp("bulletin_updatetime"));
				
				
			}
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return bulVO;

	}

	@Override
	public List<BulVO> getALL() {
		List<BulVO> list = new ArrayList<BulVO>();
		BulVO bulVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				bulVO = new BulVO();
				bulVO.setBulletin_id(rs.getString("bulletin_id"));
				bulVO.setEmp_id(rs.getString("emp_id"));
				bulVO.setBulletin_date(rs.getTimestamp("bulletin_date"));
				bulVO.setBulletin_title(rs.getString("bulletin_title"));
				bulVO.setBulletin_context(rs.getString("bulletin_context"));
				bulVO.setBulletin_note(rs.getString("bulletin_note"));
				bulVO.setBulletin_updatetime(rs.getTimestamp("bulletin_updatetime"));
				
				list.add(bulVO);
				
			}
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;		
	}
}
