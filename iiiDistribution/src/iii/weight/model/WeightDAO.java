package iii.weight.model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class WeightDAO implements WeightDAO_interface {
	
	private static DataSource ds = null;
	static{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO FEE_WEIGHT(WEIGHT_ID, EMP_ID, WEIGHT_TYPE, WEIGHT_PRICE) "
			+ "VALUES('WEI'||LPAD(TO_CHAR(fee_weight_seq.NEXTVAL),3 ,'0'),? ,?, ?)";
	private static final String UPDATE = 
			"UPDATE FEE_WEIGHT SET EMP_ID=?, WEIGHT_TYPE=?, WEIGHT_PRICE=?, WEIGHT_UPDATETIME=? WHERE WEIGHT_ID=?";
	private static final String DELETE = 
			"DELETE FROM FEE_WEIGHT WHERE WEIGHT_ID = ?";
	private static final String GET_ONE_STMT = 
			"SELECT WEIGHT_ID, EMP_ID, WEIGHT_TYPE, WEIGHT_PRICE, TO_CHAR(WEIGHT_UPDATETIME, 'yyyy-mm-dd hh:mm:ss')WEIGHT_UPDATETIME FROM FEE_WEIGHT WHERE WEIGHT_ID=?";
	private static final String GET_ALL_STMT = 
			"SELECT WEIGHT_ID, EMP_ID, WEIGHT_TYPE, WEIGHT_PRICE, TO_CHAR(WEIGHT_UPDATETIME, 'yyyy-mm-dd hh:mm:ss')WEIGHT_UPDATETIME FROM FEE_WEIGHT ORDER BY WEIGHT_ID";
		
	@Override
	public void insert(WeightVO weightVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, weightVO.getEmp_id());
			pstmt.setString(2, weightVO.getWeight_type());
			pstmt.setDouble(3, weightVO.getWeight_price());
			
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
	public void update(WeightVO weightVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(5, weightVO.getWeight_id());
			pstmt.setString(1, weightVO.getEmp_id());
			pstmt.setString(2, weightVO.getWeight_type());
			pstmt.setDouble(3, weightVO.getWeight_price());
			pstmt.setTimestamp(4, weightVO.getWeight_updateTime());
			
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
	public void delete(String weight_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, weight_id);
			
			pstmt.executeUpdate();
			
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
	public WeightVO findByPrimaryKey(String weight_id) {
		
		WeightVO weightVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, weight_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				weightVO = new WeightVO();
				weightVO.setWeight_id(rs.getString("WEIGHT_ID"));
				weightVO.setEmp_id(rs.getString("EMP_ID"));
				weightVO.setWeight_type(rs.getString("WEIGHT_TYPE"));
				weightVO.setWeight_price(rs.getDouble("WEIGHT_PRICE"));
				weightVO.setWeight_updateTime(rs.getTimestamp("WEIGHT_UPDATETIME"));
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
		return weightVO;
	}

	@Override
	public List<WeightVO> getAll() {
		List<WeightVO> list = new ArrayList<WeightVO>();
		WeightVO weightVO = null;
		
		Connection con  = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				weightVO = new WeightVO();
				
				weightVO.setWeight_id(rs.getString("weight_id"));
				weightVO.setEmp_id(rs.getString("emp_id"));
				weightVO.setWeight_type(rs.getString("weight_type"));
				weightVO.setWeight_price(rs.getDouble("weight_price"));
				weightVO.setWeight_updateTime(rs.getTimestamp("weight_updateTime"));
				list.add(weightVO);
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
