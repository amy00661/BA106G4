package iii.fee_transition.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TraJDBCDAO implements TraDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GROUP4";
	String passwd = "GROUP4";
	
	private static final String INSERT_STMT =
	"INSERT INTO FEE_TRANSITION (transition_id,emp_id,transition_type,transition_price)"
			+"VALUES ('TRA'||LPAD(TO_CHAR(news_seq.NEXTVAL), 3, '0'),?,?,?)";	
	private static final String GET_ALL_STMT =
	"SELECT transition_id,emp_id,transition_type,transition_price,to_char(transition_updatetime,'yyyy-mm-dd hh:mi:ss')transition_updatetime FROM FEE_TRANSITION order by transition_id";
			
	private static final String GET_ONE_STMT =
	"SELECT transition_id,emp_id,transition_type,transition_price,to_char(transition_updatetime,'yyyy-mm-dd hh:mi:ss')transition_updatetime FROM FEE_TRANSITION where transition_id=?";
	private static final String DELETE = 
	"DELETE FROM FEE_TRANSITION where transition_id = ?";
	private static final String UPDATE =
    "UPDATE FEE_TRANSITION set emp_id=?, transition_type=?, transition_price=?, transition_updatetime=? where transition_id=?";
	
	
	@Override
	public void insert(TraVO traVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, traVO.getEmp_id());
			pstmt.setString(2, traVO.getTransition_type());
			pstmt.setDouble(3, traVO.getTransition_price());
			
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
	public void update(TraVO traVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,traVO.getEmp_id());
			pstmt.setString(2,traVO.getTransition_type());
			pstmt.setDouble(3, traVO.getTransition_price());
			pstmt.setTimestamp(4, traVO.getUpdatetime());
			pstmt.setString(5, traVO.getTransition_id());
			
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
	public void delete(String transition_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
	
			pstmt.setString(1, transition_id);
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
	public TraVO findByPrimaryKey(String transition_id) {
		
		TraVO traVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, transition_id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				traVO = new TraVO();
				traVO.setTransition_id(rs.getString("transition_id"));
				traVO.setEmp_id(rs.getString("emp_id"));
				traVO.setTransition_type(rs.getString("transition_type"));
				traVO.setTransition_price(rs.getDouble("transition_price"));
				traVO.setUpdatetime(rs.getTimestamp("transition_updatetime"));
				
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

		return traVO;
	}

	@Override
	public List<TraVO> getAll() {
		
		List<TraVO> list = new ArrayList<TraVO>();
		TraVO traVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				traVO = new TraVO();
				traVO.setTransition_id(rs.getString("transition_id"));
				traVO.setEmp_id(rs.getString("emp_id"));
				traVO.setTransition_type(rs.getString("transition_type"));
				traVO.setTransition_price(rs.getDouble("transition_price"));
				traVO.setUpdatetime(rs.getTimestamp("transition_updatetime"));
				
				list.add(traVO);
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
		return list;
	}	
	public static void main(String[] args) {
		
		TraJDBCDAO dao = new TraJDBCDAO();
		
		
//      �s�W
//		TraVO traVO = new TraVO();
//		traVO.setEmp_id("EMP002");
//		traVO.setTransition_type("�Q��");
//		traVO.setTransition_price(1.5);	
//		dao.insert(traVO);
		
//      �ק�
//		TraVO traVO = new TraVO();
//		traVO.setEmp_id("EMP004");
//		traVO.setTransition_type("�N��");
//		traVO.setTransition_price(new Double(1.4));
//		traVO.setUpdatetime(java.sql.Timestamp.valueOf("2018-06-08 05:33:22"));
//		traVO.setTransition_id("TRA019");
//		
//		dao.update(traVO);
		
//		dao.delete("TRA017");
		
		
		
//		TraVO traVO = dao.findByPrimaryKey("TRA019");
//		
//		System.out.println(traVO.getTransition_id());
//		System.out.println(traVO.getEmp_id());
//		System.out.println(traVO.getTransition_type());
//		System.out.println(traVO.getTransition_price());
//		System.out.println(traVO.getUpdatetime());
		
		List<TraVO> list =dao.getAll();
		for(TraVO tra:list){
			System.out.print(tra.getTransition_id()+", ");
			System.out.print(tra.getEmp_id()+", ");
			System.out.print(tra.getTransition_type()+", ");
			System.out.print(tra.getTransition_price()+", ");
			System.out.println(tra.getUpdatetime());
			System.out.println("=================================");
			
		}

	}
}
