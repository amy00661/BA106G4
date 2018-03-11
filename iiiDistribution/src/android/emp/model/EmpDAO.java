package android.emp.model;

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

import android.mem.model.MemVO;

public class EmpDAO implements EmpDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE (EMP_ID, DB_ID, EMP_PWD, EMP_STATUS, EMP_NAME, EMP_TITLE, EMP_EMAIL, EMP_HIREDATE, EMP_LEAVEDATE) "
			+ "VALUES ('EMP'||LPAD(TO_CHAR(employee_seq.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ONE_STMT = "SELECT * FROM EMPLOYEE where EMP_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM EMPLOYEE order by EMP_ID";
	private static final String UPDATE = "UPDATE EMPLOYEE set DB_ID=?, EMP_PWD=?, EMP_STATUS=?, EMP_NAME=?, EMP_TITLE=?, EMP_EMAIL=?, EMP_HIREDATE=?, EMP_LEAVEDATE=? "
			+ "WHERE EMP_ID=?";
	private static final String FIND_BY_ID_PASWD = "SELECT * FROM EMPLOYEE WHERE emp_id = ? AND emp_pwd = ?";
	
	
	@Override
	public EmpVO insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int insertRow=0;
		
		try {
			con = ds.getConnection();
			String[] col = {"emp_id"};
			pstmt = con.prepareStatement(INSERT_STMT,col);
			pstmt.setString(1,empVO.getDb_id());
			pstmt.setString(2,empVO.getEmp_pwd());
			pstmt.setString(3,empVO.getEmp_status());
			pstmt.setString(4,empVO.getEmp_name());
			pstmt.setString(5,empVO.getEmp_title());
			pstmt.setString(6,empVO.getEmp_email());
			pstmt.setDate(7,empVO.getEmp_hireDate());
			pstmt.setDate(8,empVO.getEmp_leaveDate());

			insertRow = pstmt.executeUpdate();
			// 取得對應的自增主鍵值
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				String next_empid = rs.getString(1);
				//System.out.println("自增主鍵值 = " + next_empid + "(剛新增成功的員工編號)");
				empVO.setEmp_id(next_empid);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
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
		return empVO;
	}
	
	@Override
	public int update(EmpVO empVO) {
		int updateRow = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
	
	public EmpVO findByPrimaryKey(String emp_id) {
		EmpVO empVO = new EmpVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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

	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
	
	@Override
	public EmpVO isEmp(String userId, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		EmpVO empVO = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(FIND_BY_ID_PASWD);
			ps.setString(1, userId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while( rs.next()){
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
			}
			return empVO;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empVO;
	}
}
