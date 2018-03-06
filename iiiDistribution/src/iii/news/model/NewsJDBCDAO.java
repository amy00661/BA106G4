package iii.news.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class NewsJDBCDAO implements NewsDAO_interface{
	
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GROUP4";
	String passwd = "GROUP4";
	
	private static final String INSERT_STMT="INSERT INTO NEWS(news_id,emp_id,news_date,news_title,news_context,news_note,"
			+ "news_picture) VALUES ('NEWS'||LPAD(TO_CHAR(news_seq.NEXTVAL), 3, '0'),?,?,?,?,?,?)";                                                                                                                        
	private static final String GET_ALL_STMT="SELECT news_id,emp_id,to_char(news_date,'yyyy-mm-dd')news_date,news_title,"
			+ "news_context,news_note,news_picture,to_char(news_updatetime,'yyyy-mm-dd hh:mi:ss')news_updatetime FROM NEWS order by news_id";
	private static final String GET_ONE_STMT = "SELECT news_id,emp_id,to_char(news_date,'yyyy-mm-dd')news_date,news_title,"
			+ "news_context,news_note,news_picture,to_char(news_updatetime,'yyyy-mm-dd hh:mi:ss')news_updatetime FROM NEWS where news_id=?"; 
	private static final String DELETE =
			"DELETE FROM NEWS where news_id=?";
	private static final String UPDATE ="UPDATE NEWS set emp_id=?, news_date=?, news_title=?, news_context=?, news_note=?, news_picture=?, news_updatetime=? where news_id=?";
	
	@Override
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,newsVO.getEmp_id());
			pstmt.setDate(2,newsVO.getNews_date());
			pstmt.setString(3,newsVO.getNews_title());
			pstmt.setString(4,newsVO.getNews_context());
			pstmt.setString(5,newsVO.getNews_note());
			pstmt.setBytes(6, newsVO.getNews_picture());
				
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
	public void update(NewsVO newsVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,newsVO.getEmp_id());
			pstmt.setDate(2,newsVO.getNews_date());
			pstmt.setString(3,newsVO.getNews_title());
			pstmt.setString(4,newsVO.getNews_context());
			pstmt.setString(5,newsVO.getNews_note());
			pstmt.setBytes(6,newsVO.getNews_picture());
			pstmt.setTimestamp(7,newsVO.getNews_updatetime());
			pstmt.setString(8,newsVO.getNews_id());
			
			
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
	public void delete(String news_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1,news_id);
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
	public NewsVO findByPrimaryKey(String news_id) {
		
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1,news_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				newsVO = new NewsVO();
				newsVO.setNews_id(rs.getString("news_id"));
				newsVO.setEmp_id(rs.getString("emp_id"));
				newsVO.setNews_date(rs.getDate("news_date"));
				newsVO.setNews_title(rs.getString("news_title"));
				newsVO.setNews_context(rs.getString("news_context"));
				newsVO.setNews_note(rs.getString("news_note"));
				newsVO.setNews_picture(rs.getBytes("news_picture"));
				newsVO.setNews_updatetime(rs.getTimestamp("news_updatetime"));
				
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
		return newsVO;

	}
	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				newsVO = new NewsVO();
				newsVO.setNews_id(rs.getString("news_id"));
				newsVO.setEmp_id(rs.getString("emp_id"));
				newsVO.setNews_date(rs.getDate("news_date"));
				newsVO.setNews_title(rs.getString("news_title"));
				newsVO.setNews_context(rs.getString("news_context"));
				newsVO.setNews_note(rs.getString("news_note"));
				newsVO.setNews_picture(rs.getBytes("news_picture"));
				newsVO.setNews_updatetime(rs.getTimestamp("news_updatetime"));
				
				list.add(newsVO);
			
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
		
		NewsJDBCDAO dao = new NewsJDBCDAO();
		
		
		java.io.FileInputStream in = new java.io.FileInputStream("C:\\Users\\CXX272\\Desktop\\girlbase64.gif");
		byte[] data = new byte[in.available()];
		in.read(data);
		in.close();
		
//		NewsVO newsVO = new NewsVO();	
//		newsVO.setEmp_id("EMP005");
//		newsVO.setNews_date(java.sql.Timestamp.valueOf("2004-06-08 05:33:99"));
//		newsVO.setNews_title("1111���Ҹ`");
//		newsVO.setNews_context("���n��");
//		newsVO.setNews_note("�t��I��ı�o�N");
//		newsVO.setNews_picture(data);
//		dao.insert(newsVO);
	
		
		NewsVO newsVO = new NewsVO();
		
		newsVO.setEmp_id("EMP002");
		newsVO.setNews_date(java.sql.Date.valueOf("2012-06-08"));
		newsVO.setNews_title("1234���Ҹ`");
		newsVO.setNews_context("���n��123");
		newsVO.setNews_note("�t��I��ı�o�N123");
		newsVO.setNews_picture(data);
		java.sql.Timestamp datetime=new java.sql.Timestamp(System.currentTimeMillis());
		newsVO.setNews_updatetime(datetime);
		
		newsVO.setNews_id("NEWS006");
	
		dao.update(newsVO);
		
		
//		dao.delete("NEWS006");  
		
		
//		NewsVO newsVO = dao.findByPrimaryKey("NEWS006");
//		System.out.println(newsVO.getNews_id());
//		System.out.println(newsVO.getEmp_id());
//		System.out.println(newsVO.getNews_date());
//		System.out.println(newsVO.getNews_title());
//		System.out.println(newsVO.getNews_context());
//		System.out.println(newsVO.getNews_note());
//		System.out.println(newsVO.getNews_picture());
//		System.out.println(newsVO.getNews_updatetime());
		
		
//		List<NewsVO> list = dao.getAll();
//		for(NewsVO News : list){
//		
//			
//			System.out.print(News.getNews_id());
//			System.out.print(News.getEmp_id());
//			System.out.print(News.getNews_date());
//			System.out.print(News.getNews_title());
//			System.out.print(News.getNews_context());
//			System.out.print(News.getNews_note());
//			System.out.print(News.getNews_picture());
//			System.out.println(News.getNews_updatetime());
//			System.out.println("============================");
			
//		}
		
	}
	@Override
	public void selectPic(String news_id) {
		// TODO Auto-generated method stub
		
	}

}
