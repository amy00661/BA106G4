package android.foreign_order.model;

import java.util.*;

import android.local_order.model.LoVO;
import android.order_main.model.OrderVO;

import java.sql.*;
import java.sql.Date;

public class FoJDBCDAO implements FoDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "BA106G4";
	String passwd = "BA106G4";

	private static final String INSERT_STMT = "INSERT INTO FOREIGN_ORDER(FOREIGN_ORDER_ID, EMP_ID, ORDER_ID, FOREIGN_SCHEDULE_ID, FO_DATE, FO_UPDATETIME) VALUES ('FO'||LPAD(TO_CHAR(foreign_order_seq.NEXTVAL), 3, '0'),?,?,?,?,SYSDATE)";
	private static final String GET_ALL_STMT = "SELECT * FROM FOREIGN_ORDER order by FOREIGN_ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM FOREIGN_ORDER where FOREIGN_ORDER_ID=?";
	private static final String DELETE = "DELETE FROM FOREIGN_ORDER WHERE FOREIGN_ORDER_ID = ?";
	private static final String UPDATE = "UPDATE FOREIGN_ORDER SET EMP_ID=?, ORDER_ID=?, FOREIGN_SCHEDULE_ID=?, FO_DATE=?, FO_UPDATETIME=SYSDATE WHERE FOREIGN_ORDER_ID = ?";
	private static final String GET_BY_DATE = "SELECT * FROM FOREIGN_ORDER where FO_DATE = ?";
	
	@Override
	public void insert(FoVO foVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, foVO.getEmp_id());
			pstmt.setString(2, foVO.getOrder_id());
			pstmt.setString(3, foVO.getForeign_schedule_id());
			pstmt.setDate(4, foVO.getFo_date());
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
	public void delete(String foreign_order_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, foreign_order_id);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
	}

	@Override
	public FoVO findByPrimaryKey(String foreign_order_id) {
		FoVO foVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, foreign_order_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				foVO = new FoVO();
				foVO.setForeign_order_id(rs.getString("foreign_order_id"));
				foVO.setOrder_id(rs.getString("order_id"));
				foVO.setForeign_schedule_id(rs.getString("foreign_schedule_id"));
				foVO.setEmp_id(rs.getString("emp_id"));
				foVO.setFo_date(rs.getDate("fo_date"));
				foVO.setFo_updatetime(rs.getTimestamp("fo_updatetime"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return foVO;
	}

	@Override
	public List<FoVO> getAll() {
		List<FoVO> list = new ArrayList<FoVO>();
		FoVO foVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				foVO = new FoVO();
				foVO.setForeign_order_id(rs.getString("foreign_order_id"));
				foVO.setOrder_id(rs.getString("order_id"));
				foVO.setForeign_schedule_id(rs.getString("foreign_schedule_id"));
				foVO.setEmp_id(rs.getString("emp_id"));
				foVO.setFo_date(rs.getDate("fo_date"));
				foVO.setFo_updatetime(rs.getTimestamp("fo_updatetime"));
				list.add(foVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
	public List<FoVO> get_FOs_Bind_FS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderVO> findCarsFOs(String db_id, Date fo_date, String foreign_schedule_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderVO> getOrderToShip(String db_id, String item_type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {

		FoJDBCDAO dao = new FoJDBCDAO();

//		 FoVO foVO1 = new FoVO();
//		 foVO1.setEmp_id("EMP001");
//		 foVO1.setOrder_id("ORD001");
//		 foVO1.setForeign_schedule_id("F_S001");
//		 foVO1.setFo_date(java.sql.Date.valueOf("2005-01-01"));
//		 dao.insert(foVO1);
		//
		//
//		 FoVO foVO2 = new FoVO();
//		 foVO2.setForeign_order_id("FO001");
//		 foVO2.setOrder_id("ORD001");
//		 foVO2.setEmp_id("EMP001");
//		 foVO2.setForeign_schedule_id("F_S001");
//		 foVO2.setFo_date(java.sql.Date.valueOf("2005-03-02"));
//		 dao.update(foVO2);
		//
		//
//		 dao.delete("FO003");
		//
		//
//		 FoVO foVO3 = dao.findByPrimaryKey("FO002");
//		 System.out.println(foVO3.getForeign_order_id());
//		 System.out.println(foVO3.getOrder_id());
//		 System.out.println(foVO3.getForeign_schedule_id());
//		 System.out.println(foVO3.getEmp_id());
//		 System.out.println(foVO3.getFo_date());
//		 System.out.println(foVO3.getFo_updatetime());
//		 System.out.println("---------------------");

		 List<FoVO> list = dao.getAll();
		 for (FoVO aFo : list) {
		 System.out.print(aFo.getOrder_id() + ",");
		 System.out.print(aFo.getForeign_schedule_id() + ",");
		 System.out.print(aFo.getEmp_id() + ",");
		 System.out.print(aFo.getFo_date() + ",");
		 System.out.print(aFo.getFo_updatetime() + ",");
		 System.out.println();
		 }

	}

	@Override
	public int update_off(FoVO foVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update_on(FoVO foVO, String[] orderArray) {
		// TODO Auto-generated method stub
		return 0;
	}
}

