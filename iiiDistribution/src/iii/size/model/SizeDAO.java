package iii.size.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class SizeDAO implements SizeDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx  = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private final static String INSERT_STMT = 
			"INSERT INTO FEE_SIZE(SIZE_ID, EMP_ID, SIZE_TYPE, SIZE_PRICE) "
			+ "VALUES ('SIZE'||LPAD(TO_CHAR(fee_size_seq.NEXTVAL),3 ,'0'), ?, ?, ?)";
	private final static String UPDATE = 
			"UPDATE FEE_SIZE SET EMP_ID=?, SIZE_TYPE=?, SIZE_PRICE=?, SIZE_UPDATETIME=? WHERE SIZE_ID=?";
	private final static String DELETE = 
			"DELETE FROM FEE_SIZE WHERE SIZE_ID = ?";
	private final static String GET_ALL_STMT = 
			"SELECT SIZE_ID, EMP_ID, SIZE_TYPE, SIZE_PRICE, TO_CHAR(SIZE_UPDATETIME, 'yyyy-mm-dd hh:mm:ss')SIZE_UPDATETIME FROM FEE_SIZE ORDER BY SIZE_ID";
	private final static String GET_ONE_STMT =  
			"SELECT SIZE_ID, EMP_ID, SIZE_TYPE, SIZE_PRICE, TO_CHAR(SIZE_UPDATETIME, 'yyyy-mm-dd hh:mm:ss')SIZE_UPDATETIME FROM FEE_SIZE WHERE SIZE_ID = ?";
	
	@Override
	public void insert(SizeVO sizeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, sizeVO.getEmp_id());
			pstmt.setString(2, sizeVO.getSize_type());
			pstmt.setDouble(3, sizeVO.getSize_price());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			} 
			if(con != null) {
				try {
				con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(SizeVO sizeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(5, sizeVO.getSize_id());
			pstmt.setString(1, sizeVO.getEmp_id());
			pstmt.setString(2, sizeVO.getSize_type());
			pstmt.setDouble(3, sizeVO.getSize_price());
			pstmt.setTimestamp(4, sizeVO.getSize_updatetime());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try{
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}	
	}

	@Override
	public void delete(String size_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, size_id);
			
			pstmt.executeQuery();
			
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
	public SizeVO findByPrimaryKey(String size_id) {
		
		SizeVO sizeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, size_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				sizeVO = new SizeVO();
				sizeVO.setSize_id(rs.getString("SIZE_ID"));
				sizeVO.setEmp_id(rs.getString("EMP_ID"));
				sizeVO.setSize_type(rs.getString("SIZE_TYPE"));
				sizeVO.setSize_price(rs.getDouble("SIZE_PRICE"));
				sizeVO.setSize_updatetime(rs.getTimestamp("SIZE_UPDATETIME"));
			}
			
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
		return sizeVO;
	}

	@Override
	public List<SizeVO> getAll() {
		List<SizeVO> list = new ArrayList<SizeVO>();
		SizeVO sizeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				sizeVO = new SizeVO();
				sizeVO.setSize_id(rs.getString("SIZE_ID"));
				sizeVO.setEmp_id(rs.getString("EMP_ID"));
				sizeVO.setSize_type(rs.getString("SIZE_TYPE"));
				sizeVO.setSize_price(rs.getDouble("SIZE_PRICE"));
				sizeVO.setSize_updatetime(rs.getTimestamp("SIZE_UPDATETIME"));
				list.add(sizeVO);
			}
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
}
