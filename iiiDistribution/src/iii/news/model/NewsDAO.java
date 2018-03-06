package iii.news.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class NewsDAO implements NewsDAO_interface{
	
	
	private static DataSource ds=null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT="INSERT INTO NEWS(news_id,emp_id,news_date,news_title,news_context,news_note,"
			+ "news_picture) VALUES ('NEWS'||LPAD(TO_CHAR(news_seq.NEXTVAL), 3, '0'),?,?,?,?,?,?)";                                                                                                                        
	private static final String GET_ALL_STMT="SELECT news_id,emp_id,to_char(news_date,'yyyy-mm-dd')news_date,news_title,"
			+ "news_context,news_note,news_picture,to_char(news_updatetime,'yyyy-mm-dd hh:mi:ss')news_updatetime FROM NEWS order by news_id";
	private static final String GET_ONE_STMT = "SELECT news_id,emp_id,to_char(news_date,'yyyy-mm-dd')news_date,news_title,"
			+ "news_context,news_note,news_picture,to_char(news_updatetime,'yyyy-mm-dd hh:mi:ss')news_updatetime FROM NEWS where news_id=?"; 
	private static final String DELETE =
			"DELETE FROM NEWS where news_id=?";
	private static final String UPDATE ="UPDATE NEWS set emp_id=?, news_date=?, news_title=?, news_context=?, news_note=?, news_picture=?, news_updatetime=? where news_id=?";
	private static final String SELECTPIC ="SELECT NEWS_PICTURE FROM NEWS WHERE NEWS_ID=?";
	
	@Override
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,newsVO.getEmp_id());
			pstmt.setDate(2,newsVO.getNews_date());
			pstmt.setString(3,newsVO.getNews_title());
			pstmt.setString(4,newsVO.getNews_context());
			pstmt.setString(5,newsVO.getNews_note());
			pstmt.setBytes(6,newsVO.getNews_picture());
				
			pstmt.executeUpdate();
		}catch (SQLException se){
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
			con = ds.getConnection();
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

		}catch (SQLException se){
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
			
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1,news_id);
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
	public NewsVO findByPrimaryKey(String news_id) {
		
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
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
			
			con = ds.getConnection();
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

		}catch (SQLException se) {
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
	@Override
	
	public void selectPic(String news_id) {
		// TODO Auto-generated method stub
		
	}

}
