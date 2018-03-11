package iii.pro.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProDAO implements ProDAO_interface{
	
	private static DataSource ds=null;
	
	static {
		try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
				} catch (NamingException e) {
					e.printStackTrace();
				}
		}
	
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
	+ "promotion_discount,to_char(promotion_updatetime,'yyyy-mm-dd hh:mi:ss')promotion_updatetime FROM PROMOTION order by promotion_id";
	
	private static final String DELETE =
	"DELETE FROM PROMOTION where promotion_id=?";
	
	private static final String UPDATE = 
	"UPDATE PROMOTION set emp_id=?,promotion_date=?,promotion_title=?,promotion_context=?,promotion_note=?,"
	+ "promotion_start=?,promotion_end=?,promotion_picture=?,promotion_discount=?,promotion_updatetime=? where promotion_id=?";

	@Override
	public void insert(ProVO proVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = ds.getConnection();
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
	public void update(ProVO proVO) {
		
		Connection con = null;
		PreparedStatement pstmt= null;
		
		
		try{
			con = ds.getConnection();
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
	public void delete(String promotion_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, promotion_id);
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
	public ProVO findByPrimaryKey(String promotion_id) {
		
		ProVO proVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			
			
				rs.last();
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
			
			con = ds.getConnection();
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
}
