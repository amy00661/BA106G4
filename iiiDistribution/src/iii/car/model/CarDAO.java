package iii.car.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import iii.car.model.CarDAO_interface;
import iii.car.model.CarVO;



public class CarDAO implements CarDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT=
			"INSERT INTO CAR(CAR_ID, DB_ID, EMP_ID, CAR_PLATE, CAR_BRAND, CAR_DRIVER, CAR_STATUS, CAR_COLOR, CAR_PDV, CAR_LOAD, CAR_NOTE)"
			+ "VALUES('CAR'||LPAD(TO_CHAR(CAR_SEQ.NEXTVAL),3 ,'0'), ?, ?, ?, ?, ? ,?, ?, ? ,?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT CAR_ID, DB_ID, EMP_ID, CAR_PLATE, CAR_BRAND, CAR_DRIVER, CAR_STATUS, CAR_COLOR, CAR_PDV, CAR_LOAD, TO_CHAR(CAR_UPDATETIME, 'yyyy-mm-dd hh:mm:ss')CAR_UPDATETIME, CAR_NOTE FROM CAR ORDER BY CAR_ID DESC";
	private static final String GET_ONE_STMT = 
			"SELECT CAR_ID, DB_ID, EMP_ID, CAR_PLATE, CAR_BRAND, CAR_DRIVER, CAR_STATUS, CAR_COLOR, CAR_PDV, CAR_LOAD, TO_CHAR(CAR_UPDATETIME, 'yyyy-mm-dd hh:mm:ss')CAR_UPDATETIME, CAR_NOTE FROM CAR WHERE CAR_ID = ?";
	private static final String DELETE = 
			"DELETE FROM CAR WHERE CAR_ID = ?";
	private static final String UPDATE = 
			"UPDATE CAR SET DB_ID=?, EMP_ID=?, CAR_PLATE=?, CAR_BRAND=?, CAR_DRIVER=?, CAR_STATUS=?, CAR_COLOR=?, CAR_PDV=?, CAR_LOAD=?, CAR_UPDATETIME=?, CAR_NOTE=? WHERE CAR_ID=?";
	
	public int insert(CarVO carVO){
		
		int iii = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
				
			pstmt.setString(1, carVO.getDb_id());
			pstmt.setString(2, carVO.getEmp_id());
			pstmt.setString(3, carVO.getCar_plate());
			pstmt.setString(4, carVO.getCar_brand());
			pstmt.setString(5, carVO.getCar_driver());
			pstmt.setString(6, carVO.getCar_status());
			pstmt.setString(7, carVO.getCar_color());
			pstmt.setDouble(8, carVO.getCar_pdv());
			pstmt.setDouble(9, carVO.getCar_load());
			pstmt.setString(10, carVO.getCar_note());
			
			iii = pstmt.executeUpdate();
			
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
			se.printStackTrace();
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
		return iii;
	}
	
	public void update (CarVO carVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, carVO.getDb_id());
			System.out.println("Car_id = "+carVO.getCar_id());
			pstmt.setString(2, carVO.getEmp_id());
			pstmt.setString(3, carVO.getCar_plate());
			System.out.println("Car_plate = "+carVO.getCar_plate());
			
			pstmt.setString(4, carVO.getCar_brand());
			pstmt.setString(5, carVO.getCar_driver());
			System.out.println("Car_driver = " + carVO.getCar_driver());
			
			pstmt.setString(6, carVO.getCar_status());
			pstmt.setString(7, carVO.getCar_color());
			pstmt.setDouble(8, carVO.getCar_pdv());
			pstmt.setDouble(9, carVO.getCar_load());
			pstmt.setTimestamp(10, (Timestamp) carVO.getCar_updatetime());
			System.out.println("Car_updatetime = " + carVO.getCar_updatetime());
			
			pstmt.setString(11, carVO.getCar_note());
			pstmt.setString(12, carVO.getCar_id());
			int i = pstmt.executeUpdate();
			System.out.println("pstmt.executeUpdate return = "+i);
			
			
		} catch (SQLException se) {
			se.printStackTrace();
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
	
	public void delete(String car_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
			
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, car_id);
			
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
	
	public CarVO findByPrimaryKey(String car_id){
		
		CarVO carVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, car_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {				
				carVO = new CarVO();
				carVO.setCar_id(rs.getString("car_id"));
				carVO.setDb_id(rs.getString("db_id"));
				carVO.setEmp_id(rs.getString("emp_id"));
				carVO.setCar_plate(rs.getString("car_plate"));
				carVO.setCar_brand(rs.getString("car_brand"));
				carVO.setCar_driver(rs.getString("car_driver"));
				carVO.setCar_status(rs.getString("car_status"));
				carVO.setCar_color(rs.getString("car_color"));
				carVO.setCar_pdv(rs.getDouble("car_pdv"));
				carVO.setCar_load(rs.getDouble("car_load"));
				carVO.setCar_updatetime(rs.getTimestamp("car_updatetime"));
				carVO.setCar_note(rs.getString("car_note"));	
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
		return carVO;
	}	
	
	public List<CarVO> getAll(){
		List<CarVO> list = new ArrayList<CarVO>();
		CarVO carVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				carVO = new CarVO();
				carVO.setCar_id(rs.getString("CAR_ID"));
				carVO.setDb_id(rs.getString("DB_ID"));
				carVO.setEmp_id(rs.getString("EMP_ID"));
				carVO.setCar_plate(rs.getString("CAR_PLATE"));
				carVO.setCar_brand(rs.getString("car_brand"));
				carVO.setCar_driver(rs.getString("CAR_DRIVER"));
				carVO.setCar_status(rs.getString("CAR_STATUS"));
				carVO.setCar_color(rs.getString("CAR_COLOR"));
				carVO.setCar_pdv(rs.getDouble("CAR_PDV"));
				carVO.setCar_load(rs.getDouble("CAR_LOAD"));
				carVO.setCar_updatetime(rs.getTimestamp("CAR_UPDATETIME"));
				carVO.setCar_note(rs.getString("CAR_NOTE"));
				list.add(carVO);
				
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