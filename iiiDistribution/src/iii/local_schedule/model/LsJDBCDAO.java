package iii.local_schedule.model;

import java.util.*;
import java.sql.*;

public class LsJDBCDAO implements LsDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GROUP4";
	String passwd = "GROUP4";
	
	private static final String INSERT_STMT = "INSERT INTO LOCAL_SCHEDULE"
			+ "(LOCAL_SCHEDULE_ID, CAR_ID, CAR_TYPE, LS_TIME, EMP_ID, LS_UPDATETIME)"
			+ "VALUES ('L_S'||LPAD(TO_CHAR(local_schedule_seq.NEXTVAL), 3, '0'),?,?,?,?,SYSDATE)";
	private static final String GET_ALL_STMT = "SELECT * FROM LOCAL_SCHEDULE order by LOCAL_SCHEDULE_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM LOCAL_SCHEDULE WHERE LOCAL_SCHEDULE_ID =?";
	private static final String DELETE = "DELETE FROM LOCAL_SCHEDULE WHERE LOCAL_SCHEDULE_ID = ?";
	private static final String UPDATE = "UPDATE LOCAL_SCHEDULE set  CAR_ID=?, CAR_TYPE=?, LS_TIME=?, EMP_ID=? LS_UPDATETIME=SYSDATE WHERE LOCAL_SCHEDULE_ID = ?";
	
	
	@Override
	public void insert(LsVO lsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, lsVO.getCar_id());
			pstmt.setString(2, lsVO.getCar_type());
			pstmt.setString(3, lsVO.getLs_time());
			pstmt.setString(4, lsVO.getEmp_id());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
	public void update(LsVO lsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, lsVO.getCar_id());
			pstmt.setString(2, lsVO.getCar_type());
			pstmt.setString(3, lsVO.getLs_time());
			pstmt.setString(4, lsVO.getEmp_id());
			pstmt.setString(5, lsVO.getLocal_schedule_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			//
			se.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
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
	public void delete(String LOCAL_SCHEDULE_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, LOCAL_SCHEDULE_ID);
			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException se) {
			// throw new RuntimeException("A database error occured. "
			// + se.getMessage());
			se.printStackTrace();
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
	public LsVO findByPrimaryKey(String local_schedule_id) {
		LsVO lsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, local_schedule_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lsVO = new LsVO();
				lsVO.setLocal_schedule_id(rs.getString("LOCAL_SCHEDULE_ID"));
				lsVO.setCar_id(rs.getString("CAR_ID"));
				lsVO.setCar_type(rs.getString("CAR_TYPE"));
				lsVO.setLs_time(rs.getString("LS_TIME"));
				lsVO.setEmp_id(rs.getString("EMP_ID"));
				lsVO.setLs_updatetime(rs.getTimestamp("LS_UPDATETIME"));
			}

		} catch (SQLException se) {
			// throw new RuntimeException("A database error occured. "
			// + se.getMessage());
			se.printStackTrace();
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		return lsVO;
	}
	@Override
	public List<LsVO> getAll() {
		List<LsVO> list = new ArrayList<LsVO>();
		LsVO lsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				lsVO = new LsVO();
				lsVO.setLocal_schedule_id(rs.getString("LOCAL_SCHEDULE_ID"));
				lsVO.setCar_id(rs.getString("CAR_ID"));
				lsVO.setCar_type(rs.getString("CAR_TYPE"));
				lsVO.setLs_time(rs.getString("LS_TIME"));
				lsVO.setEmp_id(rs.getString("EMP_ID"));
				lsVO.setLs_updatetime(rs.getTimestamp("LS_UPDATETIME"));
				list.add(lsVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			// throw new RuntimeException("A database error occured. "
			// + se.getMessage());
			se.printStackTrace();
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	@Override
	public Set<String> getCarTypeList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<LsVO> findByCarType(String car_type) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args){
		LsJDBCDAO dao =new LsJDBCDAO();
		
		LsVO lsVO1 = new LsVO();
		lsVO1.setCar_id("CAR004");
		lsVO1.setCar_type("冷凍車");
		lsVO1.setLs_time("11:00");
		dao.insert(lsVO1);
	}
	
	
}
