package iii.bul.model;

import java.util.*;
import java.sql.*;

import java.sql.*;


public class BulJDBCDAO implements BulDAO_interface {

	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GROUP4";
	String passwd = "GROUP4";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,bulVO.getEmp_id());
			pstmt.setTimestamp(2, bulVO.getBulletin_date());
			pstmt.setString(3, bulVO.getBulletin_title());
			pstmt.setString(4, bulVO.getBulletin_context());
			pstmt.setString(5, bulVO.getBulletin_note());
			pstmt.executeUpdate();
			
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, bulVO.getEmp_id());
			pstmt.setTimestamp(2,bulVO.getBulletin_date());
			pstmt.setString(3, bulVO.getBulletin_title());
			pstmt.setString(4,bulVO.getBulletin_context());
			pstmt.setString(5, bulVO.getBulletin_note());
			pstmt.setTimestamp(6, bulVO.getBulletin_updatetime());
			pstmt.setString(7, bulVO.getBulletin_id());
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
		
			pstmt.setString(1, bulletin_id);
			pstmt.executeUpdate();
			
		
		
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	
	public static void main(String[] args) {
		
		BulJDBCDAO dao = new BulJDBCDAO();
		
		BulVO bulVO = new BulVO();
		bulVO.setEmp_id("EMP003");
		bulVO.setBulletin_date(java.sql.Timestamp.valueOf("2004-06-08 05:33:99"));
		bulVO.setBulletin_title("內部稽核座談情形");
		bulVO.setBulletin_context("檢查局於106年10月24日邀請23家證券商及2家證券金融公司之內部稽核主管與防制洗錢及打擊資恐專責主管舉辦座談，向業者宣導防制洗錢及金融消費者保護等議題。座談會中就本會近期修正之金融機構防制洗錢辦法及相關規範、證券商防制洗錢作業管理實務與本局檢查證券商及證券金融公司執行防制洗錢及金融消費者保護作業之主要檢查意見作說明，協助業者瞭解法規重點及實務作法。");
		bulVO.setBulletin_note("無須理會");
		
		dao.insert(bulVO);

//		BulVO bulVO = new BulVO();
//		bulVO.setEmp_id("EMP004");
//		bulVO.setBulletin_date(java.sql.Timestamp.valueOf("2012-06-08 05:33:22"));
//		bulVO.setBulletin_title("Hi抱怨文");
//		bulVO.setBulletin_context("兩堆廢話");
//		bulVO.setBulletin_note("要理會");
//		bulVO.setBulletin_updatetime(java.sql.Timestamp.valueOf("2018-01-28 05:15:22"));
//		bulVO.setBulletin_id("BUL006");
//		
//		dao.update(bulVO);
	
//		dao.delete("BUL006");
		
//		BulVO bulVO = dao.findByPrimaryKey("BUL006");
//		System.out.println(bulVO.getBulletin_id());
//		System.out.println(bulVO.getEmp_id());
//		System.out.println(bulVO.getBulletin_date());
//		System.out.println(bulVO.getBulletin_title());
//		System.out.println(bulVO.getBulletin_context());
//		System.out.println(bulVO.getBulletin_note());
//		System.out.println(bulVO.getBulletin_updatetime());
		
//		
//		List<BulVO> list = dao.getALL();
//		for(BulVO bul : list){
//			
//			System.out.print(bul.getBulletin_id()+" ,");
//			System.out.print(bul.getEmp_id()+" ,");
//			System.out.print(bul.getBulletin_date()+" ,");
//			System.out.print(bul.getBulletin_title()+" ,");
//			System.out.print(bul.getBulletin_context()+" ,");
//			System.out.print(bul.getBulletin_note()+" ,");
//			System.out.println(bul.getBulletin_updatetime());
//			System.out.println("=============================");
//
//		}

	}	
}
