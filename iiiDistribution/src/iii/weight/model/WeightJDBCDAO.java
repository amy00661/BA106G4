package iii.weight.model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeightJDBCDAO implements WeightDAO_interface {
	String driver= "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GROUP4";
	String passwd = "GROUP4";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, weightVO.getEmp_id());
			pstmt.setString(2, weightVO.getWeight_type());
			pstmt.setDouble(3, weightVO.getWeight_price());
			
			pstmt.executeUpdate();
			
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(5, weightVO.getWeight_id());
			pstmt.setString(1, weightVO.getEmp_id());
			pstmt.setString(2, weightVO.getWeight_type());
			pstmt.setDouble(3, weightVO.getWeight_price());
			pstmt.setTimestamp(4, weightVO.getWeight_updateTime());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, weight_id);
			
			pstmt.executeQuery();
			
		} catch (ClassNotFoundException e) {
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
	public WeightVO findByPrimaryKey(String weight_id) {
		
		WeightVO weightVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				weightVO = new WeightVO();
				
				weightVO.setWeight_id(rs.getString("WEIGHT_ID"));
				weightVO.setEmp_id(rs.getString("EMP_ID"));
				weightVO.setWeight_type(rs.getString("WEIGHT_TYPE"));
				weightVO.setWeight_price(rs.getDouble("WEIGHT_PRICE"));
				weightVO.setWeight_updateTime(rs.getTimestamp("WEIGHT_UPDATETIME"));;
				list.add(weightVO);
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
	
	public static void main(String[] args){
		WeightJDBCDAO dao = new WeightJDBCDAO();
		
//		WeightVO weightVO1 = new WeightVO();
//		weightVO1.setEmp_id("EMP003");
//		weightVO1.setWeight_type("超重");
//		weightVO1.setWeight_price(5151.21);
//		weightVO1.setWeight_updateTime(java.sql.Timestamp.valueOf("2015-01-03 11:12:13"));
//		dao.insert(weightVO1);
		
//		WeightVO weightVO2 = new WeightVO();
//		weightVO2.setWeight_id("WEI004");
//		weightVO2.setEmp_id("EMP005");
//		weightVO2.setWeight_type("無敵巨重");
//		weightVO2.setWeight_price(99999.88);
//		weightVO2.setWeight_updateTime(java.sql.Timestamp.valueOf("2014-05-10 13:10:55"));
//		dao.update(weightVO2);
//		
//		dao.delete("WEIGHT003");
//		System.out.println("delete OK!");
//		
//		System.out.println("======單筆查詢======");		
//		WeightVO weightVO3 = dao.findByPrimaryKey("WEI004");
//		System.out.println(weightVO3.getWeight_id());
//		System.out.println(weightVO3.getEmp_id());
//		System.out.println(weightVO3.getWeight_type());
//		System.out.println(weightVO3.getWeight_price());
//		System.out.println(weightVO3.getWeight_updateTime());
//		
		System.out.println("======多筆查詢======");
		List<WeightVO> list = dao.getAll();
		int i = 0;
		for(WeightVO aWeight : list){
			System.out.println("======"+ ++i +"======");
			System.out.println(aWeight.getWeight_id());
			System.out.println(aWeight.getEmp_id());
			System.out.println(aWeight.getWeight_type());
			System.out.println(aWeight.getWeight_price());
			System.out.println(aWeight.getWeight_updateTime());
		}
	}
}
