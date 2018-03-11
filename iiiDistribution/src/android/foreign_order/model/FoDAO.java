package android.foreign_order.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.local_order.model.LoVO;
import android.order_main.model.OrderVO;

import java.sql.*;
import java.sql.Date;




public class FoDAO implements FoDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO FOREIGN_ORDER(FOREIGN_ORDER_ID, EMP_ID, ORDER_ID, FOREIGN_SCHEDULE_ID, FO_DATE, FO_UPDATETIME) VALUES ('FO'||LPAD(TO_CHAR(foreign_order_seq.NEXTVAL), 3, '0'),?,?,?,?,SYSDATE)";
	private static final String GET_ALL_STMT = "SELECT * FROM FOREIGN_ORDER order by FOREIGN_ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM FOREIGN_ORDER where FOREIGN_ORDER_ID=?";
	private static final String DELETE = "DELETE FROM FOREIGN_ORDER WHERE FOREIGN_ORDER_ID = ?";
	private static final String UPDATE_OFF = "UPDATE FOREIGN_ORDER set FOREIGN_SCHEDULE_ID = NULL, FO_DATE=NULL, FO_UPDATETIME = SYSDATE, EMP_ID = ? WHERE FO_DATE=? AND FOREIGN_SCHEDULE_ID=?";
	private static final String UPDATE_ON = "UPDATE FOREIGN_ORDER set FOREIGN_SCHEDULE_ID = ?, FO_DATE=?, FO_UPDATETIME = SYSDATE, EMP_ID = ? WHERE ORDER_ID IN (";
	private static final String GET_BY_LO_DATE = "SELECT * FROM ORDER_MAIN WHERE DB_ID = ? AND ORDER_ID IN (SELECT ORDER_ID FROM FOREIGN_ORDER WHERE FO_DATE = ? AND FOREIGN_SCHEDULE_ID = ?)";
	private static final String GET_ORDs_TO_SHIP = "SELECT * FROM ORDER_MAIN WHERE DB_ID = ? AND ITEM_TYPE=? AND ORDER_ID IN (SELECT ORDER_ID FROM FOREIGN_ORDER WHERE FOREIGN_SCHEDULE_ID is NULL) ORDER BY EXPECTED_TIME";
	private static final String GET_LOs_Bind_LS = "SELECT * FROM FOREIGN_ORDER WHERE FOREIGN_SCHEDULE_ID�@IS NOT NULL";
	
	
	@Override
	public void insert(FoVO foVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, foVO.getEmp_id());
			pstmt.setString(2, foVO.getOrder_id());
			pstmt.setString(3, foVO.getForeign_schedule_id());
			pstmt.setDate(4, foVO.getFo_date());
			pstmt.executeUpdate();
			
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
	public void delete(String foreign_order_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, foreign_order_id);
			pstmt.executeUpdate();

			
		}  catch (SQLException e) {
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
			con = ds.getConnection();
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
			
		}  catch (SQLException e) {
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
			con = ds.getConnection();
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
	public int update_off(FoVO foVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateRow = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_OFF);
			
			pstmt.setString(1, foVO.getEmp_id());
			pstmt.setDate(2, foVO.getFo_date());
			pstmt.setString(3, foVO.getForeign_schedule_id());
			
			updateRow = pstmt.executeUpdate();
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
	return updateRow;
	}



	@Override
	public int update_on(FoVO foVO, String[] orderArray) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateRow = 0;
		
			try {
				con = ds.getConnection();
				String query = createQuery(UPDATE_ON,orderArray.length);
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, foVO.getForeign_schedule_id());
				pstmt.setDate(2, foVO.getFo_date());
				pstmt.setString(3, foVO.getEmp_id());
				for(int i = 0; i<orderArray.length; i++){
					pstmt.setString((i+4), orderArray[i]);
				}
				updateRow = pstmt.executeUpdate();
				
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
		return updateRow;
	}
	

	



	@Override
	public List<FoVO> get_FOs_Bind_FS() {
		List<FoVO> list = new ArrayList<FoVO>();
		FoVO foVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LOs_Bind_LS);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				foVO = new FoVO();
				foVO.setForeign_order_id(rs.getString("foreign_order_id"));
				foVO.setEmp_id(rs.getString("emp_id"));
				foVO.setOrder_id(rs.getString("order_id"));
				foVO.setForeign_schedule_id(rs.getString("foreign_schedule_id"));
				foVO.setFo_date(rs.getDate("fo_date"));
				foVO.setFo_updatetime(rs.getTimestamp("fo_updatetime"));
				list.add(foVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( rs != null){
				try {
					rs.close();
				} catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<OrderVO> findCarsFOs(String db_id, Date fo_date, String foreign_schedule_id) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_LO_DATE);
			pstmt.setString(1, db_id);
			pstmt.setDate(2, fo_date);
			pstmt.setString(3, foreign_schedule_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				orderVO = new OrderVO();
				orderVO.setOrder_id(rs.getString("ORDER_ID"));
				orderVO.setEmp_id(rs.getString("EMP_ID"));
				orderVO.setMem_id(rs.getString("MEMBER_ID"));
				orderVO.setDb_id(rs.getString("DB_ID"));
				orderVO.setOrder_status(rs.getString("ORDER_STATUS"));
				orderVO.setPayment_type(rs.getString("PAYMENT_TYPE"));
				orderVO.setFee(rs.getDouble("FEE"));
				orderVO.setExtrafee(rs.getDouble("Extrafee"));
				orderVO.setItem_size(rs.getDouble("ITEM_SIZE"));
				orderVO.setItem_weight(rs.getDouble("ITEM_WEIGHT"));
				orderVO.setItem_type(rs.getString("ITEM_TYPE"));
				orderVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				orderVO.setReceiver_name(rs.getString("RECEIVER_NAME"));
				orderVO.setReceiver_tel(rs.getString("RECEIVER_TEL"));
				orderVO.setReceiver_cell(rs.getString("RECEIVER_CELL"));
				orderVO.setReceiver_city(rs.getString("RECEIVER_CITY"));
				orderVO.setReceiver_county(rs.getString("RECEIVER_COUNTY"));
				orderVO.setReceiver_address(rs.getString("RECEIVER_ADDRESS"));
				orderVO.setReceiver_mail(rs.getString("RECEIVER_MAIL"));
				orderVO.setSender_name(rs.getString("SENDER_NAME"));
				orderVO.setSender_tel(rs.getString("SENDER_TEL"));
				orderVO.setSender_cell(rs.getString("SENDER_CELL"));
				orderVO.setSender_city(rs.getString("SENDER_CITY"));
				orderVO.setSender_county(rs.getString("SENDER_COUNTY"));
				orderVO.setSender_address(rs.getString("SENDER_ADDRESS"));
				orderVO.setExpected_time(rs.getTimestamp("EXPECTED_TIME"));
				orderVO.setOrder_note(rs.getString("ORDER_ID"));
				orderVO.setOrder_updatetime(rs.getTimestamp("ORDER_UPDATETIME"));		
				list.add(orderVO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if( rs != null){
				try {
					rs.close();
				} catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<OrderVO> getOrderToShip(String db_id, String item_type) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ORDs_TO_SHIP);
			pstmt.setString(1, db_id);
			pstmt.setString(2, item_type);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				orderVO = new OrderVO();
				orderVO.setOrder_id(rs.getString("ORDER_ID"));
				orderVO.setEmp_id(rs.getString("EMP_ID"));
				orderVO.setMem_id(rs.getString("MEMBER_ID"));
				orderVO.setDb_id(rs.getString("DB_ID"));
				orderVO.setOrder_status(rs.getString("ORDER_STATUS"));
				orderVO.setPayment_type(rs.getString("PAYMENT_TYPE"));
				orderVO.setFee(rs.getDouble("FEE"));
				orderVO.setExtrafee(rs.getDouble("Extrafee"));
				orderVO.setItem_size(rs.getDouble("ITEM_SIZE"));
				orderVO.setItem_weight(rs.getDouble("ITEM_WEIGHT"));
				orderVO.setItem_type(rs.getString("ITEM_TYPE"));
				orderVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				orderVO.setReceiver_name(rs.getString("RECEIVER_NAME"));
				orderVO.setReceiver_tel(rs.getString("RECEIVER_TEL"));
				orderVO.setReceiver_cell(rs.getString("RECEIVER_CELL"));
				orderVO.setReceiver_city(rs.getString("RECEIVER_CITY"));
				orderVO.setReceiver_county(rs.getString("RECEIVER_COUNTY"));
				orderVO.setReceiver_address(rs.getString("RECEIVER_ADDRESS"));
				orderVO.setReceiver_mail(rs.getString("RECEIVER_MAIL"));
				orderVO.setSender_name(rs.getString("SENDER_NAME"));
				orderVO.setSender_tel(rs.getString("SENDER_TEL"));
				orderVO.setSender_cell(rs.getString("SENDER_CELL"));
				orderVO.setSender_city(rs.getString("SENDER_CITY"));
				orderVO.setSender_county(rs.getString("SENDER_COUNTY"));
				orderVO.setSender_address(rs.getString("SENDER_ADDRESS"));
				orderVO.setExpected_time(rs.getTimestamp("EXPECTED_TIME"));
				orderVO.setOrder_note(rs.getString("ORDER_ID"));
				orderVO.setOrder_updatetime(rs.getTimestamp("ORDER_UPDATETIME"));		
				list.add(orderVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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


	private String createQuery(String query, int length) {
		StringBuilder queryBuilder = new StringBuilder(query);
		for( int i = 0; i< length; i++){
			queryBuilder.append(" ?");
			if(i != length -1) queryBuilder.append(",");
		}
		queryBuilder.append(")");
		return queryBuilder.toString();
	}
	



	
}
