package iii.order_main.model;

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

public class OrderDAO implements OrderDAO_interface {
	
	public static DataSource ds = null;
	static{
		try {
			Context con = new InitialContext();
			ds = (DataSource)con.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT=
			"INSERT INTO ORDER_MAIN(ORDER_ID, EMP_ID, MEMBER_ID, DB_ID, ORDER_STATUS, PAYMENT_TYPE, FEE, EXTRAFEE, ITEM_SIZE, ITEM_WEIGHT, "
			+ "ITEM_TYPE, RECEIVER_NAME, RECEIVER_TEL, RECEIVER_CELL, RECEIVER_CITY, RECEIVER_COUNTY, RECEIVER_ADDRESS, "
			+ "RECEIVER_MAIL, SENDER_NAME, SENDER_TEL, SENDER_CELL, SENDER_CITY, SENDER_COUNTY, SENDER_ADDRESS, EXPECTED_TIME, ORDER_NOTE, CREATE_TIME)"
			+ "VALUES('ORD'||LPAD(TO_CHAR(ORDER_MAIN_SEQ.NEXTVAL),3 ,'0'), ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, ?, ?, ? ,? ,? ,? ,? ,? ,?, ?, SYSDATE)";
//	private static final String UPDATE =	
//			"UPDATE ORDER_MAIN SET EMP_ID=?, MEMBER_ID=?, DB_ID=?, PAYMENT_TYPE=?, FEE=?, EXTRAFEE=?, ITEM_SIZE=?, ITEM_WEIGHT=?, ITEM_TYPE=?, "
//			+ "ORDER_STATUS=?, CREATE_TIME=?, RECEIVER_NAME=?, RECEIVER_TEL=?, RECEIVER_COUNTRY=?, RECEIVER_CITY=?, RECEIVER_COUNTY=?, "
//			+ "RECEIVER_ADDRESS=?, RECEIVER_MAIL=?, SENDER_NAME=?, SENDER_TEL=?, SENDER_MAIL=?, EXPECTED_TIME=?, ORDER_NOTE=?, ORDER_UPDATETIME=? WHERE ORDER_ID=?";
//	private static final String DELETE = 
//			"DELETE FROM ORDER_MAIN WHERE ORDER_ID = ?";
//	private static final String GET_ONE_STMT = 
//			"SELECT ORDER_ID, EMP_ID, MEMBER_ID, DB_ID, PAYMENT_TYPE, FEE, EXTRAFEE, ITEM_SIZE, ITEM_WEIGHT, ITEM_TYPE, ORDER_STATUS, "
//			+ "TO_CHAR(CREATE_TIME, 'yyyy-mm-dd hh:mm:ss')CREATE_TIME, RECEIVER_NAME, RECEIVER_TEL, RECEIVER_COUNTRY, RECEIVER_CITY, RECEIVER_COUNTY, "
//			+ "RECEIVER_ADDRESS, RECEIVER_MAIL, SENDER_NAME, SENDER_TEL, SENDER_MAIL, TO_CHAR(EXPECTED_TIME, 'yyyy-mm-dd hh:mm:ss')EXPECTED_TIME, "
//			+ "ORDER_NOTE, TO_CHAR(ORDER_UPDATETIME, 'yyyy-mm-dd hh:mm:ss')ORDER_UPDATETIME FROM ORDER_MAIN WHERE ORDER_ID = ?";
	private static final String GET_ALL_STMT = 
			"SELECT ORDER_ID, EMP_ID, MEMBER_ID, DB_ID, ORDER_STATUS, PAYMENT_TYPE, FEE, EXTRAFEE, ITEM_SIZE, ITEM_WEIGHT, ITEM_TYPE, "
			+ "TO_CHAR(CREATE_TIME, 'yyyy-mm-dd hh:mm:ss')CREATE_TIME, RECEIVER_NAME, RECEIVER_TEL, RECEIVER_CELL, RECEIVER_CITY, RECEIVER_COUNTY, "
			+ "RECEIVER_ADDRESS, RECEIVER_MAIL, SENDER_NAME, SENDER_TEL, SENDER_CELL, SENDER_CITY, SENDER_COUNTY, SENDER_ADDRESS, TO_CHAR(EXPECTED_TIME, 'yyyy-mm-dd hh:mm:ss')EXPECTED_TIME, "
			+ "ORDER_NOTE, TO_CHAR(ORDER_UPDATETIME, 'yyyy-mm-dd hh:mm:ss')ORDER_UPDATETIME FROM ORDER_MAIN ORDER BY ORDER_ID";
	
	private static final String GET_ONE_STMT = 
			"SELECT * FROM ORDER_MAIN WHERE ORDER_ID = ?";
	
//	private static final String GET_ALL_STMT = 
//			"SELECT * FROM ORDER_MAIN ORDER BY ORDER_ID";
	
	
	
	@Override
	public Integer insert(OrderVO orderVO) {
		Integer insertShow = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, orderVO.getEmp_id());
			pstmt.setString(2, orderVO.getMem_id());
			pstmt.setString(3, orderVO.getDb_id());
			pstmt.setString(4, orderVO.getOrder_status());
			pstmt.setString(5, orderVO.getPayment_type());
			pstmt.setDouble(6, orderVO.getFee());
			pstmt.setDouble(7, orderVO.getExtrafee());
			pstmt.setDouble(8, orderVO.getItem_size());
			pstmt.setDouble(9, orderVO.getItem_weight());
			pstmt.setString(10, orderVO.getItem_type());
			pstmt.setString(11, orderVO.getReceiver_name());
			pstmt.setString(12, orderVO.getReceiver_tel());
			pstmt.setString(13, orderVO.getReceiver_cell());
			pstmt.setString(14, orderVO.getReceiver_city());
			pstmt.setString(15, orderVO.getReceiver_county());
			pstmt.setString(16, orderVO.getReceiver_address());
			pstmt.setString(17, orderVO.getReceiver_mail());
			pstmt.setString(18, orderVO.getSender_name());
			pstmt.setString(19, orderVO.getSender_tel());
			pstmt.setString(20, orderVO.getReceiver_cell());
			pstmt.setString(21, orderVO.getSender_city());
			pstmt.setString(22, orderVO.getSender_county());
			pstmt.setString(23, orderVO.getSender_address());
			pstmt.setTimestamp(24, orderVO.getExpected_time());
			pstmt.setString(25, orderVO.getOrder_note());
				
			insertShow = pstmt.executeUpdate();		
			
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
		return insertShow;
	}

//	@Override
//	public Integer update(OrderVO orderVO) {
//		Integer updateShow = 0;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//			
//			pstmt.setString(25, orderVO.getOrder_id());
//			pstmt.setString(1, orderVO.getEmp_id());
//			pstmt.setString(2, orderVO.getMem_id());
//			pstmt.setString(3, orderVO.getDb_id());
//			pstmt.setString(4, orderVO.getPayment_type());
//			pstmt.setDouble(5, orderVO.getFee());
//			pstmt.setDouble(6, orderVO.getExtrafee());
//			pstmt.setDouble(7, orderVO.getItem_size());
//			pstmt.setDouble(8, orderVO.getItem_weight());
//			pstmt.setString(9, orderVO.getItem_type());
//			pstmt.setString(10, orderVO.getOrder_status());
//			pstmt.setTimestamp(11, orderVO.getCreate_time());
//			pstmt.setString(12, orderVO.getReceiver_name());
//			pstmt.setString(13, orderVO.getReceiver_tel());
//			pstmt.setString(14, orderVO.getReceiver_country());
//			pstmt.setString(15, orderVO.getReceiver_city());
//			pstmt.setString(16, orderVO.getReceiver_county());
//			pstmt.setString(17, orderVO.getReceiver_address());
//			pstmt.setString(18, orderVO.getReceiver_mail());
//			pstmt.setString(19, orderVO.getSender_name());
//			pstmt.setString(20, orderVO.getSender_tel());
//			pstmt.setString(21, orderVO.getSender_mail());
//			pstmt.setTimestamp(22, orderVO.getExpected_time());
//			pstmt.setString(23, orderVO.getOrder_note());
//			pstmt.setTimestamp(24, orderVO.getOrder_updatetime());
//			
//			updateShow = pstmt.executeUpdate();
//			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(con != null) {
//				try{
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//		}	
//		return updateShow;
//	}
//
//	@Override
//	public void delete(String order_id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet deleteShow = null;
//		
//		try {
//			Class.forName(driver);
//			con  = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//			
//			pstmt.setString(1, order_id);
//			
//			deleteShow = pstmt.executeQuery();
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}		
//	}
//
	@Override
	public OrderVO findByPrimaryKey(String order_id) {

		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, order_id);
			
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
//				ResultSetMetaData rm = rs.getMetaData();
//				System.out.println(rm.getColumnCount());
//				System.out.println(rm.getColumnDisplaySize(1));
//				System.out.println(rm.getColumnType(1));
//				System.out.println(rm.getPrecision(1));
//				System.out.println(rm.getScale(1));
//				System.out.println(rm.getColumnLabel(1));
//				System.out.println(rm.getColumnName(1));
//				System.out.println(rm.getColumnClassName(1));
//				System.out.println(rm.isCurrency(1));
				
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
		return orderVO;
	}
	@Override
	public List<OrderVO> getAll() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
	@Override
	public Integer update(OrderVO orderVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String order_id) {
		// TODO Auto-generated method stub
		
	}


}
