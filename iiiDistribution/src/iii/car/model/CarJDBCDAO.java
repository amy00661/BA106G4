package iii.car.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import iii.car.model.CarDAO_interface;
import iii.car.model.CarVO;



public class CarJDBCDAO implements CarDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.56.101:1521:XE";
	String userid = "GROUP4";
	String passwd = "GROUP4";
	
	private static final String INSERT_STMT=
			"INSERT INTO CAR(CAR_ID, DB_ID, EMP_ID, CAR_PLATE, CAR_BRAND, CAR_DRIVER, CAR_STATUS, CAR_COLOR, CAR_PDV, CAR_LOAD, CAR_NOTE)"
			+ "VALUES('CAR'||LPAD(TO_CHAR(CAR_SEQ.NEXTVAL),3 ,'0'), ?, ?, ?, ?, ? ,?, ?, ? ,?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT CAR_ID, DB_ID, EMP_ID, CAR_PLATE, CAR_BRAND, CAR_DRIVER, CAR_STATUS, CAR_COLOR, CAR_PDV, CAR_LOAD, TO_CHAR(CAR_UPDATETIME, 'yyyy-mm-dd hh:mm:ss')CAR_UPDATETIME, CAR_NOTE FROM CAR ORDER BY CAR_ID";
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return iii;
	}
	
	public void update (CarVO carVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, carVO.getDb_id());
			pstmt.setString(2, carVO.getEmp_id());
			pstmt.setString(3, carVO.getCar_plate());
			pstmt.setString(4, carVO.getCar_brand());
			pstmt.setString(5, carVO.getCar_driver());
			pstmt.setString(6, carVO.getCar_status());
			pstmt.setString(7, carVO.getCar_color());
			pstmt.setDouble(8, carVO.getCar_pdv());
			pstmt.setDouble(9, carVO.getCar_load());
			pstmt.setTimestamp(10, (Timestamp) carVO.getCar_updatetime());
			pstmt.setString(11, carVO.getCar_note());
			pstmt.setString(12, carVO.getCar_id());
		
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, car_id);
			
			pstmt.executeUpdate();
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
	
	public CarVO findByPrimaryKey(String car_id){
		
		CarVO carVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return carVO;
	}	
	
	public List<CarVO> getAll(){
		List<CarVO> list = new ArrayList<CarVO>();
		CarVO carVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			// Handle any driver errors
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
		return list;
	}
	
	public static void main(String[] args) {
		CarJDBCDAO dao = new CarJDBCDAO();
		
		CarVO carVO1 = new CarVO();
		carVO1.setDb_id("DB01");
		carVO1.setEmp_id("EMP001");
		carVO1.setCar_plate("2R-4848");
		carVO1.setCar_brand("audi");
		carVO1.setCar_driver("Andy");
		carVO1.setCar_status("23132");
		carVO1.setCar_color("yellow");
		carVO1.setCar_pdv(1354584.11);
		carVO1.setCar_load(99.12);
		carVO1.setCar_note(". 1.Daily. A: to_date( to_char( sysdate, 'dd-mm-yy' ), 'dd-mm-yy' )sysdate, 'dd-mm-yy' )");
		dao.insert(carVO1);
		System.out.println("新稱" + dao.insert(carVO1) + "資料");
		
		CarVO carVO2 = new CarVO();
		carVO2.setCar_id("CAR022");
		carVO2.setDb_id("DB01");
		carVO2.setEmp_id("EMP001");
		carVO2.setCar_plate("2R-4848");
		carVO1.setCar_brand("audi");
		carVO2.setCar_driver("Andy");
		carVO2.setCar_status("1111");
		carVO2.setCar_color("yellow");
		carVO2.setCar_pdv(1354584.11);
		carVO2.setCar_load(99.12);
		carVO2.setCar_updatetime(java.sql.Timestamp.valueOf("2002-01-01 12:01:01"));
		carVO2.setCar_note("1.Daily. A: to_date( to_char( sysdate, 'dd-mm-yy' ), 'dd-mm-yy' )sysdate, 'dd-mm-yy' )");
		dao.update(carVO2);
		System.out.println("SONG");
		
		dao.delete("CAR004");
		System.out.println("SONG");
		
		CarVO carVO3 = dao.findByPrimaryKey("CAR006");
		System.out.println(carVO3.getCar_id() + ",");
		System.out.println(carVO3.getDb_id() + ",");
		System.out.println(carVO3.getEmp_id() + ",");
		System.out.println(carVO3.getCar_plate() + ",");
		System.out.println(carVO3.getCar_brand() + ",");
		System.out.println(carVO3.getCar_status() + ",");
		System.out.println(carVO3.getCar_color() + ",");
		System.out.println(carVO3.getCar_pdv() + ",");
		System.out.println(carVO3.getCar_load() + ",");
		System.out.println(carVO3.getCar_updatetime() + ",");
		System.out.println(carVO3.getCar_note() + ",");
		
		List<CarVO> list = dao.getAll();
		for(CarVO aCar : list) {
			System.out.println(aCar.getCar_id() + ",");
			System.out.println(aCar.getDb_id() + ",");
			System.out.println(aCar.getEmp_id() + ",");
			System.out.println(aCar.getCar_plate() + ",");
			System.out.println(aCar.getCar_brand() + ",");
			System.out.println(aCar.getCar_driver() + ",");
			System.out.println(aCar.getCar_status() + ",");
			System.out.println(aCar.getCar_color() + ",");
			System.out.println(aCar.getCar_pdv() + ",");
			System.out.println(aCar.getCar_load() + ",");
			System.out.println(aCar.getCar_updatetime());
			System.out.println(aCar.getCar_note());
			System.out.println();
		}
	}
}