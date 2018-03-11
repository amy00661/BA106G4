package android.local_order.model;

import java.util.*;

import android.order_main.model.OrderVO;

import java.sql.*;
import java.sql.Date;

public class LoJDBCDAO implements LoDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "BA106G4";
	String passwd = "BA106G4";

	private static final String INSERT_STMT = "INSERT INTO LOCAL_ORDER(LOCAL_ORDER_ID, EMP_ID, ORDER_ID, LOCAL_SCHEDULE_ID, LOCAL_ORDER_DATE, LO_UPDATETIME) VALUES ('L_O'||LPAD(TO_CHAR(local_order_seq.NEXTVAL), 3, '0'), ?,?,?,?, SYSDATE)";
	private static final String GET_ALL_STMT = "SELECT * FROM LOCAL_ORDER order by LOCAL_ORDER_ID ";
	private static final String GET_ONE_STMT = "SELECT * FROM LOCAL_ORDER where LOCAL_ORDER_ID = ?";
	private static final String DELETE = "DELETE FROM LOCAL_ORDER WHERE LOCAL_ORDER_DATE =?";
	private static final String UPDATE = "UPDATE LOCAL_ORDER set EMP_ID = ?, ORDER_ID = ?, LOCAL_SCHEDULE_ID=?, LO_UPDATETIME= SYSDATE";
	private static final String GET_BY_LO_DATE = "SELECT * FROM LOCAL_ORDER where LOCAL_ORDER_DATE = ?";
	
	
	@Override
	public void insert(LoVO loVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			System.out.println(loVO.getEmp_ID()+" "+ loVO.getOrder_ID()+" "+loVO.getLocal_schedule_ID());
			pstmt.setString(1, loVO.getEmp_ID());
			pstmt.setString(2, loVO.getOrder_ID());
			pstmt.setString(3, loVO.getLocal_schedule_ID());
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
	public int update_off(LoVO loVO) {
		return 0;
	}
	
	@Override
	public int update_on(LoVO loVO, String[] orderArray) {
		return 0;		
	}

	@Override
	public void delete(Date local_orderDate) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setDate(1, local_orderDate);
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
	public LoVO findByPrimaryKey(String local_order_id) {
		LoVO loVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, local_order_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				loVO = new LoVO();
				loVO.setLocal_order_ID(rs.getString("LOCAL_ORDER_ID"));
				loVO.setEmp_ID(rs.getString("EMP_ID"));
				loVO.setOrder_ID(rs.getString("ORDER_ID"));
				loVO.setLocal_schedule_ID(rs.getString("LOCAL_SCHEDULD_ID"));
				loVO.setLo_updatetime(rs.getTimestamp("LO_UPDATETIME"));
			}
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

		return loVO;
	}

	@Override
	public List<LoVO> getAll() {
		List<LoVO> list = new ArrayList<LoVO>();
		LoVO loVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				loVO = new LoVO();
				loVO.setLocal_order_ID(rs.getString("LOCAL_ORDER_ID"));
				loVO.setEmp_ID(rs.getString("EMP_ID"));
				loVO.setOrder_ID(rs.getString("ORDER_ID"));
				loVO.setLocal_schedule_ID(rs.getString("LOCAL_SCHEDULE_ID"));
				loVO.setLo_updatetime(rs.getTimestamp("LO_UPDATETIME"));
				list.add(loVO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
	public List<LoVO> get_LOs_Bind_LS(String db_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderVO> findCarsLOs(String db_id, Date local_orderDate, String local_schedule_ID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<OrderVO> getOrderToShip(String db_id, String item_type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		LoJDBCDAO dao = new LoJDBCDAO();
		
		/*LoVO loVO1 = new LoVO();
		loVO1.setEmp_ID("EMP001");
		loVO1.setOrder_ID("ORD001");
		loVO1.setLocal_schedule_ID("L_S001");
		dao.insert(loVO1);*/
		
		/*List<LoVO> list = dao.findByLoDate(java.sql.Date.valueOf("2010-04-08"));
		System.out.println("派�?�任??��?��?��??");
		for(LoVO loVO1 : list){
			System.out.println(loVO1.getLocal_order_ID());
		}*/
	}


	
}
