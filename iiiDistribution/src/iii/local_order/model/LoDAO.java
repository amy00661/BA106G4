package iii.local_order.model;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import iii.order_main.model.OrderVO;

import java.sql.*;
import java.sql.Date;



public class LoDAO implements LoDAO_interface{
	
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e){
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO LOCAL_ORDER(LOCAL_ORDER_ID, EMP_ID, ORDER_ID, LOCAL_SCHEDULE_ID, LOCAL_ORDER_DATE, LOCAL_ORDER_UPDATETIME) VALUES ('LO'||LPAD(TO_CHAR(local_order_seq.NEXTVAL), 3, '0'), ?,?,?,?, SYSDATE)";
	private static final String GET_ALL_STMT = "SELECT * FROM LOCAL_ORDER order by LOCAL_ORDER_ID ";
	private static final String GET_ONE_STMT = "SELECT * FROM LOCAL_ORDER where LOCAL_ORDER_ID = ?";
	private static final String DELETE = "DELETE FROM LOCAL_ORDER WHERE LOCAL_ORDER_DATE =?";
	private static final String UPDATE_OFF = "UPDATE LOCAL_ORDER set LOCAL_SCHEDULE_ID = NULL, LOCAL_ORDER_DATE=NULL, LOCAL_ORDER_UPDATETIME = SYSDATE, EMP_ID = ? WHERE LOCAL_ORDER_DATE=? AND LOCAL_SCHEDULE_ID=?";
	private static final String UPDATE_ON = "UPDATE LOCAL_ORDER set LOCAL_SCHEDULE_ID = ?, LOCAL_ORDER_DATE=?, LOCAL_ORDER_UPDATETIME = SYSDATE, EMP_ID = ? WHERE ORDER_ID IN (";
	private static final String GET_BY_LO_DATE = "SELECT * FROM ORDER_MAIN WHERE DB_ID = ? AND ORDER_ID IN (SELECT ORDER_ID FROM LOCAL_ORDER WHERE LOCAL_ORDER_DATE = ? AND LOCAL_SCHEDULE_ID = ?)";
	private static final String GET_ORDs_TO_SHIP = "SELECT * FROM ORDER_MAIN WHERE DB_ID = ? AND ITEM_TYPE=? AND ORDER_ID IN (SELECT ORDER_ID FROM LOCAL_ORDER WHERE LOCAL_SCHEDULE_ID IS NULL) ORDER BY EXPECTED_TIME";
	private static final String GET_LOs_Bind_LS = "SELECT * FROM LOCAL_ORDER WHERE LOCAL_SCHEDULE_ID　IS NOT NULL AND ORDER_ID IN (SELECT ORDER_ID FROM ORDER_MAIN WHERE DB_ID=? )";
	private static final String GET_LO_COUNT = "SELECT COUNT(?) FROM LOCAL_ORDER WHERE LOCAL_SCHEDULE_ID = ?";
	
	@Override
	public void insert(LoVO loVO) {
	
		Connection con =null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, loVO.getEmp_ID());
			pstmt.setString(2, loVO.getOrder_ID());
			pstmt.setString(3, loVO.getLocal_schedule_ID());
			pstmt.setDate(4, loVO.getLocal_orderDate());
			pstmt.executeUpdate();
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null){
				 try {
				pstmt.close();
			} catch (SQLException se){
				se.printStackTrace(System.err);
				}
			}		 
			if (con != null) {
				try{
					con.close();
				} catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public int update_off(LoVO loVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateRow = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_OFF);
//			System.out.println(foVO.getEMP_ID()+" "+ foVO.getORDER_ID()+" "+foVO.getFOREIGN_ORDER_ID());
			pstmt.setString(1, loVO.getEmp_ID());
			pstmt.setDate(2, loVO.getLocal_orderDate());
			pstmt.setString(3, loVO.getLocal_schedule_ID());

			updateRow = pstmt.executeUpdate();
			
			}  catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
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
		return updateRow;
	}

	@Override
	public int update_on(LoVO loVO,String[] orderArray) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateRow = 0;
		try {
			con = ds.getConnection();
			String query = createQuery(UPDATE_ON,orderArray.length);
			pstmt = con.prepareStatement(query);
//			System.out.println(foVO.getEMP_ID()+" "+ foVO.getORDER_ID()+" "+foVO.getFOREIGN_ORDER_ID());
			pstmt.setString(1, loVO.getLocal_schedule_ID());
			pstmt.setDate(2, loVO.getLocal_orderDate());
			pstmt.setString(3, loVO.getEmp_ID());
			for(int i = 0; i <orderArray.length; i++){
				pstmt.setString( (i+4), orderArray[i]);
			}
			updateRow = pstmt.executeUpdate();
			
			}  catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
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
		return updateRow;
	}

	@Override
	public void delete(Date local_orderDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setDate(1, local_orderDate);
			pstmt.executeUpdate();
			
			}  catch (SQLException se) {
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
	public LoVO findByPrimaryKey(String local_order_id) {
		
		LoVO loVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, local_order_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				loVO = new LoVO();
				loVO.setLocal_order_ID(rs.getString("LOCAL_ORDER_ID"));
				loVO.setEmp_ID(rs.getString("EMP_ID"));
				loVO.setOrder_ID(rs.getString("ORDER_ID"));
				loVO.setLocal_schedule_ID(rs.getString("LOCAL_SCHEDULD_ID"));
				loVO.setLo_updatetime(rs.getTimestamp("LO_UPDATETIME"));
				}
	
			
			}  catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				
				loVO = new LoVO();
				loVO.setLocal_order_ID(rs.getString("LOCAL_ORDER_ID"));
				loVO.setEmp_ID(rs.getString("EMP_ID"));
				loVO.setOrder_ID(rs.getString("ORDER_ID"));
				loVO.setLocal_schedule_ID(rs.getString("LOCAL_SCHEDULE_ID"));
				loVO.setLocal_orderDate(rs.getDate("LOCAL_ORDER_DATE"));
				loVO.setLo_updatetime(rs.getTimestamp("LOCAL_ORDER_UPDATETIME"));
				list.add(loVO);
			}
		} catch (SQLException se){
			se.printStackTrace();
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
	public List<LoVO> get_LOs_Bind_LS(String db_id) {
		List<LoVO> list = new ArrayList<LoVO>();
		LoVO loVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LOs_Bind_LS);
			pstmt.setString(1,db_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				
				loVO = new LoVO();
				loVO.setLocal_order_ID(rs.getString("LOCAL_ORDER_ID"));
				loVO.setEmp_ID(rs.getString("EMP_ID"));
				loVO.setOrder_ID(rs.getString("ORDER_ID"));
				loVO.setLocal_schedule_ID(rs.getString("LOCAL_SCHEDULE_ID"));
				loVO.setLocal_orderDate(rs.getDate("LOCAL_ORDER_DATE"));
				loVO.setLo_updatetime(rs.getTimestamp("LOCAL_ORDER_UPDATETIME"));
				list.add(loVO);
			}
		} catch (SQLException se){
			se.printStackTrace();
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
	public List<OrderVO> findCarsLOs(String db_id,Date local_orderDate,String local_schedule_ID) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_LO_DATE);
			pstmt.setString(1, db_id);
			pstmt.setDate(2, local_orderDate);
			pstmt.setString(3, local_schedule_ID);
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
		} catch (SQLException se){
			se.printStackTrace();
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
	public List<OrderVO> getOrderToShip(String db_id,String item_type) {
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
	
	private static String createQuery(String query,int length) {
		StringBuilder queryBuilder = new StringBuilder(query);
		for( int i = 0; i< length; i++){
			queryBuilder.append(" ?");
			if(i != length -1) queryBuilder.append(",");
		}
		queryBuilder.append(")");
		return queryBuilder.toString();
	}

	@Override
	public Integer getCountLo(String local_order_ID, String local_schedule_ID) {
		Integer count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LoVO loVO = new LoVO();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LO_COUNT);
			
			pstmt.setString(1, local_order_ID);
			pstmt.setString(2, local_schedule_ID);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
		        System.out.println("numberOfRows= " + count);
			} else {
				System.out.println("error: could not get the record counts");
		    }

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(pstmt != null){
				try{
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return count;
	}
		

}
