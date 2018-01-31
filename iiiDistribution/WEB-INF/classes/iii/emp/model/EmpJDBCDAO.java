package iii.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpJDBCDAO implements EmpDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GROUP4";
	String passwd = "GROUP4";
	
	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE (EMP_ID, DB_ID, EMP_PWD, EMP_STATUS, EMP_NAME, EMP_TITLE, EMP_EMAIL, EMP_HIREDATE, EMP_LEAVEDATE) "
											+ "VALUES ('EMP'||LPAD(TO_CHAR(employee_seq.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ONE_STMT = "SELECT * FROM EMPLOYEE where EMP_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM EMPLOYEE order by EMP_ID";
	private static final String UPDATE = "UPDATE EMPLOYEE set DB_ID=?, EMP_PWD=?, EMP_STATUS=?, EMP_NAME=?, EMP_TITLE=?, EMP_EMAIL=?, EMP_HIREDATE=?, EMP_LEAVEDATE=? "
										+ "WHERE EMP_ID=?";
	
	@Override
	public int insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int insertRow=0;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,empVO.getDb_id());
			pstmt.setString(2,empVO.getEmp_pwd());
			pstmt.setString(3,empVO.getEmp_status());
			pstmt.setString(4,empVO.getEmp_name());
			pstmt.setString(5,empVO.getEmp_title());
			pstmt.setString(6,empVO.getEmp_email());
			pstmt.setDate(7,empVO.getEmp_hireDate());
			pstmt.setDate(8,empVO.getEmp_leaveDate());

			insertRow = pstmt.executeUpdate();

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
		return insertRow;

	}
	
	@Override
	public int update(EmpVO empVO) {
		int updateRow = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1,empVO.getDb_id());
			pstmt.setString(2,empVO.getEmp_pwd());
			pstmt.setString(3,empVO.getEmp_status());
			pstmt.setString(4,empVO.getEmp_name());
			pstmt.setString(5,empVO.getEmp_title());
			pstmt.setString(6,empVO.getEmp_email());
			pstmt.setDate(7,empVO.getEmp_hireDate());
			pstmt.setDate(8,empVO.getEmp_leaveDate());
			pstmt.setString(9,empVO.getEmp_id());
			
			updateRow = pstmt.executeUpdate();

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
	public EmpVO findByPrimaryKey(String emp_id) {
		EmpVO empVO = new EmpVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, emp_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				empVO.setEmp_id(rs.getString("EMP_ID"));
				empVO.setDb_id(rs.getString("DB_ID"));
				empVO.setEmp_pwd(rs.getString("EMP_PWD"));
				empVO.setEmp_status(rs.getString("EMP_STATUS"));
				empVO.setEmp_name(rs.getString("EMP_NAME"));
				empVO.setEmp_title(rs.getString("EMP_TITLE"));
				empVO.setEmp_email(rs.getString("EMP_EMAIL"));
				empVO.setEmp_hireDate(rs.getDate("EMP_HIREDATE"));
				empVO.setEmp_leaveDate(rs.getDate("EMP_LEAVEDATE"));
				empVO.setEmp_updateTime(rs.getTimestamp("EMP_UPDATETIME"));
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getString("EMP_ID"));
				empVO.setDb_id(rs.getString("DB_ID"));
				empVO.setEmp_pwd(rs.getString("EMP_PWD"));
				empVO.setEmp_status(rs.getString("EMP_STATUS"));
				empVO.setEmp_name(rs.getString("EMP_NAME"));
				empVO.setEmp_title(rs.getString("EMP_TITLE"));
				empVO.setEmp_email(rs.getString("EMP_EMAIL"));
				empVO.setEmp_hireDate(rs.getDate("EMP_HIREDATE"));
				empVO.setEmp_leaveDate(rs.getDate("EMP_LEAVEDATE"));
				empVO.setEmp_updateTime(rs.getTimestamp("EMP_UPDATETIME"));
				list.add(empVO); // Store the row in the list
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
		EmpJDBCDAO empDAO = new EmpJDBCDAO();
		// 新增
		/*EmpVO empVO1 = new EmpVO();
		empVO1.setDb_id("DB03");
		empVO1.setEmp_pwd("abc12345");
		empVO1.setEmp_status("01");
		empVO1.setEmp_name("章子怡");
		empVO1.setEmp_title("warehouse_staff");
		empVO1.setEmp_email("warehouse@gmail.com");
		empVO1.setEmp_hireDate(java.sql.Date.valueOf("2018-01-20 00:00:00"));
		empVO1.setEmp_leaveDate(java.sql.Date.valueOf("2018-01-20 00:00:00"));
		System.out.println("新增"+empDAO.insert(empVO1)+"筆資料!");*/
		
		// 修改
		EmpVO empVO2 = new EmpVO();
		empVO2.setEmp_id("EMP006");
		empVO2.setDb_id("DB04");
		empVO2.setEmp_pwd("abc12345");
		empVO2.setEmp_status("01");
		empVO2.setEmp_name("曾咽蕓");
		empVO2.setEmp_title("driver");
		empVO2.setEmp_email("davidWu@gmail.com");
		empVO2.setEmp_hireDate(java.sql.Date.valueOf("2013-03-01"));
		empVO2.setEmp_leaveDate(java.sql.Date.valueOf("2018-01-20"));
		empDAO.update(empVO2);
		
		
		// 查詢:以員編
		EmpVO empVO3 = empDAO.findByPrimaryKey("EMP008");
		//System.out.println(empVO3);
		
		// 查詢:全部
		List<EmpVO> list = empDAO.getAll();
		for(EmpVO aEmp:list){
			/*System.out.print(aEmp.getEmp_id()+"\t");
			System.out.print(aEmp.getDb_id()+"\t");
			System.out.print(aEmp.getEmp_pwd()+"\t");
			System.out.print(aEmp.getEmp_status()+"\t");
			System.out.print(aEmp.getEmp_name()+"\t");
			System.out.print(aEmp.getEmp_title()+"\t");
			System.out.print(aEmp.getEmp_email()+"\t");
			System.out.print(aEmp.getEmp_start()+"\t");
			System.out.print(aEmp.getEmp_end()+"\t");
			System.out.println(aEmp.getEmp_updateTime());*/
		}
	}


}
