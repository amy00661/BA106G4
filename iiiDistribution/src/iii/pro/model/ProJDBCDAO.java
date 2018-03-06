package iii.pro.model;

import java.util.*;
import java.io.FileNotFoundException;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProJDBCDAO implements ProDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GROUP4";
	String passwd = "GROUP4";
	
	private static final String INSERT_STMT=
	"INSERT INTO PROMOTION(promotion_id,emp_id,promotion_date,promotion_title,"
	+ "promotion_context,promotion_note,promotion_start,promotion_end,promotion_picture,promotion_discount) VALUES"
	+ "('PMT'||LPAD(TO_CHAR(news_seq.NEXTVAL), 3, '0'),?,?,?,?,?,?,?,?,?)";
	
	private static final String GET_ALL_STMT=
	"SELECT promotion_id,emp_id,to_char(promotion_date,'yyyy-mm-dd')promotion_date,promotion_title,promotion_context,promotion_note,"
	+ "to_char(promotion_start,'yyyy-mm-dd')promotion_start,to_char(promotion_end,'yyyy-mm-dd')promotion_end,promotion_picture,"
	+ "promotion_discount,to_char(promotion_updatetime,'yyyy-mm-dd hh:mi:ss')promotion_updatetime FROM PROMOTION order by promotion_id";
	
	private static final String GET_ONE_STMT = 
	"SELECT promotion_id,emp_id,to_char(promotion_date,'yyyy-mm-dd')promotion_date,promotion_title,promotion_context,promotion_note,"
	+ "to_char(promotion_start,'yyyy-mm-dd')promotion_start,to_char(promotion_end,'yyyy-mm-dd')promotion_end,promotion_picture,"
	+ "promotion_discount,to_char(promotion_updatetime,'yyyy-mm-dd hh:mi:ss')promotion_updatetime FROM PROMOTION where promotion_id=?";
	
	private static final String DELETE =
	"DELETE FROM PROMOTION where promotion_id=?";
	
	private static final String UPDATE = 
	"UPDATE�@PROMOTION set emp_id=?,promotion_date=?,promotion_title=?,promotion_context=?,promotion_note=?,"
	+ "promotion_start=?,promotion_end=?,promotion_picture=?,promotion_discount=?,promotion_updatetime=? where promotion_id=?";

	@Override
	public void insert(ProVO proVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, proVO.getEmp_id());
			pstmt.setDate(2, proVO.getPromotion_date());
			pstmt.setString(3, proVO.getPromotion_title());
			pstmt.setString(4, proVO.getPromotion_context());
			pstmt.setString(5, proVO.getPromotion_note());
			pstmt.setDate(6, proVO.getPromotion_start());
			pstmt.setDate(7, proVO.getPromotion_end());
			pstmt.setBytes(8, proVO.getPromotion_picture());
			pstmt.setDouble(9, proVO.getPromotion_discount());
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se){
			throw new RuntimeException("A database error occured. "
			+ se.getMessage());
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
		}
		if(con != null){
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace(System.err);
			}
		}
		
	}

	@Override
	public void update(ProVO proVO) {
		
		Connection con = null;
		PreparedStatement pstmt= null;
		
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, proVO.getEmp_id());
			pstmt.setDate(2, proVO.getPromotion_date());
			pstmt.setString(3, proVO.getPromotion_title());
			pstmt.setString(4, proVO.getPromotion_context());
			pstmt.setString(5, proVO.getPromotion_note());
			pstmt.setDate(6, proVO.getPromotion_start());
			pstmt.setDate(7, proVO.getPromotion_end());
			pstmt.setBytes(8, proVO.getPromotion_picture());
			pstmt.setDouble(9, proVO.getPromotion_discount());
			pstmt.setTimestamp(10, proVO.getPromotion_updatetime());
			pstmt.setString(11, proVO.getPromotion_id());
			
			pstmt.executeUpdate();
	
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
	public void delete(String promotion_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, promotion_id);
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
	public ProVO findByPrimaryKey(String promotion_id) {
		
		ProVO proVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, promotion_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				proVO=new ProVO();
				proVO.setPromotion_id(rs.getString("promotion_id"));
				proVO.setEmp_id(rs.getString("emp_id"));
				proVO.setPromotion_date(rs.getDate("promotion_date"));
				proVO.setPromotion_title(rs.getString("promotion_title"));
				proVO.setPromotion_context(rs.getString("promotion_context"));
				proVO.setPromotion_note(rs.getString("promotion_note"));
				proVO.setPromotion_start(rs.getDate("promotion_start"));
				proVO.setPromotion_end(rs.getDate("promotion_end"));
				proVO.setPromotion_picture(rs.getBytes("promotion_picture"));
				proVO.setPromotion_discount(rs.getDouble("promotion_discount"));
				proVO.setPromotion_updatetime(rs.getTimestamp("promotion_updatetime"));

			}
			
			
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
		return proVO;
	}

	@Override
	public List<ProVO> getAll() {
		
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO proVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				proVO = new ProVO();
				proVO.setPromotion_id(rs.getString("promotion_id"));
				proVO.setEmp_id(rs.getString("emp_id"));
				proVO.setPromotion_date(rs.getDate("promotion_date"));
				proVO.setPromotion_title(rs.getString("promotion_title"));
				proVO.setPromotion_context(rs.getString("promotion_context"));
				proVO.setPromotion_note(rs.getString("promotion_note"));
				proVO.setPromotion_start(rs.getDate("promotion_start"));
				proVO.setPromotion_end(rs.getDate("promotion_end"));
				proVO.setPromotion_picture(rs.getBytes("promotion_picture"));
				proVO.setPromotion_discount(rs.getDouble("promotion_discount"));
				proVO.setPromotion_updatetime(rs.getTimestamp("promotion_updatetime"));
				
				list.add(proVO);
			}
			
			
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
		
	public static void main(String[] args) throws Exception {
		
		ProJDBCDAO dao = new ProJDBCDAO();
		
		java.io.FileInputStream in = new java.io.FileInputStream("C:\\Users\\CXX272\\Desktop\\girlbase64.gif");
		byte[] data = new byte[in.available()];
		in.read(data);                                      
		in.close();
		
//		�W�[
		ProVO proVO = new ProVO();
		
		proVO.setEmp_id("EMP010");
		proVO.setPromotion_date(java.sql.Date.valueOf("2017-06-08"));
		proVO.setPromotion_title("���H�`�ɴ�");
		proVO.setPromotion_context("�����o!�u�¿߾ǥͦ���ۥѤ�A�j�]�p�]�¿ߥ����]�v. ��������~�ǳƶ}���o!!!");
		proVO.setPromotion_note("�L�ܥi��");
		proVO.setPromotion_start(java.sql.Date.valueOf("2018-02-01"));
		proVO.setPromotion_end(java.sql.Date.valueOf("2018-02-28"));
		proVO.setPromotion_picture(data);
		proVO.setPromotion_discount(0.8);		
		dao.insert(proVO);
		System.out.println("");
//		�ק�
//		ProVO proVO = new ProVO();
//		
//		proVO.setEmp_id("EMP006");
//		proVO.setPromotion_date(java.sql.Timestamp.valueOf("2017-07-09 05:33:99"));
//		proVO.setPromotion_title("���˸`�ɴ�");
//		proVO.setPromotion_context("�����o!�u�տ߾ǥͦ���ۥѤ�A�j�]�p�]�տߥ����]�v. ��������~�ǳƶ}���o!!!");
//		proVO.setPromotion_note("�L���H��");
//		proVO.setPromotion_start(java.sql.Timestamp.valueOf("2018-05-01 05:33:20"));
//		proVO.setPromotion_end(java.sql.Timestamp.valueOf("2018-05-28 05:33:20"));
//		proVO.setPromotion_discount(0.85);
//		proVO.setPromotion_updatetime(java.sql.Timestamp.valueOf("2018-06-27 05:33:20"));
//		proVO.setPromotion_id("PMT008");
//		
//		dao.update(proVO);
		
//		�R��
//		dao.delete("PMT007");
		
		
		
//		ProVO proVO =dao.findByPrimaryKey("PMT008");
//		
//		System.out.println(proVO.getPromotion_id());
//		System.out.println(proVO.getEmp_id());
//		System.out.println(proVO.getPromotion_date());
//		System.out.println(proVO.getPromotion_title());
//		System.out.println(proVO.getPromotion_context());
//		System.out.println(proVO.getPromotion_note());
//		System.out.println(proVO.getPromotion_start());
//		System.out.println(proVO.getPromotion_end());
//		System.out.println(proVO.getPromotion_picture());
//		System.out.println(proVO.getPromotion_discount());
//		System.out.println(proVO.getPromotion_updatetime());
		
		
//		List<ProVO> list = dao.getAll();
//		for(ProVO pro : list){
//			
//			System.out.print(pro.getPromotion_id()+", ");
//			System.out.print(pro.getEmp_id()+", ");
//			System.out.print(pro.getPromotion_date()+", ");
//			System.out.print(pro.getPromotion_title()+", ");
//			System.out.print(pro.getPromotion_context()+", ");
//			System.out.print(pro.getPromotion_note()+", ");
//			System.out.print(pro.getPromotion_start()+", ");
//			System.out.print(pro.getPromotion_end()+", ");
//			System.out.print(pro.getPromotion_picture()+", ");
//			System.out.println(pro.getPromotion_discount());
//			System.out.println(pro.getPromotion_updatetime()+", ");
//			System.out.println("============================");
//			
//		}
		
	}
}
