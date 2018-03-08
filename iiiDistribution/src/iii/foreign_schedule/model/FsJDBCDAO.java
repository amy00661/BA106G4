package iii.foreign_schedule.model;

import java.util.*;
import java.sql.*;

public class FsJDBCDAO implements FsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GROUP4";
	String passwd = "GROUP4";

	private static final String INSERT_STMT = "INSERT INTO FOREIGN_SCHEDULE"
			+ "(FOREIGN_SCHEDULE_ID, CAR_ID, CAR_TYPE, STAR_DB, END_DB, FS_TIME, EMP_ID, FS_UPDATETIME)"
			+ "VALUES ('F_S'||LPAD(TO_CHAR(foreign_schedule_seq.NEXTVAL), 3, '0'),?,?,?,?,?,?,SYSDATE)";
	private static final String GET_ALL_STMT = "SELECT * FROM FOREIGN_SCHEDULE order by FOREIGN_SCHEDULE_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM FOREIGN_SCHEDULE WHERE FOREIGN_SCHEDULE_ID =?";
	private static final String DELETE = "DELETE FROM FOREIGN_SCHEDULE WHERE FOREIGN_SCHEDULE_ID = ?";
	private static final String UPDATE = "UPDATE FOREIGN_SCHEDULE set  CAR_ID=?, CAR_TYPE=?, STAR_DB=?, END_DB=?, FS_TIME=?, EMP_ID=? FS_UPDATETIME=SYSDATE WHERE FOREIGN_SCHEDULE_ID = ?";

	@Override
	public void insert(FsVO fsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, fsVO.getCar_ID());
			pstmt.setString(2, fsVO.getCar_TYPE());
			pstmt.setString(3, fsVO.getStar_DB());
			pstmt.setString(4, fsVO.getEnd_DB());
			pstmt.setString(5, fsVO.getFs_TIME());
			pstmt.setString(6, fsVO.getEmp_ID());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
	public void update(FsVO fsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, fsVO.getCar_ID());
			pstmt.setString(2, fsVO.getCar_TYPE());
			pstmt.setString(3, fsVO.getStar_DB());
			pstmt.setString(4, fsVO.getEnd_DB());
			pstmt.setString(5, fsVO.getFs_TIME());
			pstmt.setString(6, fsVO.getEmp_ID());
			pstmt.setString(5, fsVO.getForeign_schedule_ID());

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
	public void delete(String FOREIGN_SCHEDULE_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, FOREIGN_SCHEDULE_ID);
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
	public FsVO findByPrimaryKey(String FOREIGN_SCHEDULE_ID) {
		FsVO fsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, FOREIGN_SCHEDULE_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				fsVO = new FsVO();
				fsVO.setForeign_schedule_ID(rs.getString("FOREIGN_SCHEDULE_ID"));
				fsVO.setCar_ID(rs.getString("CAR_ID"));
				fsVO.setCar_TYPE(rs.getString("CAR_TYPE"));
				fsVO.setStar_DB(rs.getString("STAR_DB"));
				fsVO.setEnd_DB(rs.getString("END_DB"));
				fsVO.setFs_TIME(rs.getString("FS_TIME"));
				fsVO.setEmp_ID(rs.getString("EMP_ID"));
				fsVO.setFs_updatetime(rs.getTimestamp("FS_UPDATETIME"));
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

		return fsVO;
	}

	@Override
	public List<FsVO> getAll() {
		List<FsVO> list = new ArrayList<FsVO>();
		FsVO fsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				fsVO = new FsVO();
				fsVO.setForeign_schedule_ID(rs.getString("FOREIGN_SCHEDULE_ID"));
				fsVO.setCar_ID(rs.getString("CAR_ID"));
				fsVO.setCar_TYPE(rs.getString("CAR_TYPE"));
				fsVO.setStar_DB(rs.getString("STAR_DB"));
				fsVO.setEnd_DB(rs.getString("END_DB"));
				fsVO.setFs_TIME(rs.getString("FS_TIME"));
				fsVO.setEmp_ID(rs.getString("EMP_ID"));
				fsVO.setFs_updatetime(rs.getTimestamp("FS_UPDATETIME"));
				list.add(fsVO); // Store the row in the list
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

	public static void main(String[] args) {
		FsJDBCDAO dao = new FsJDBCDAO();

		// �憓�
		FsVO fsVO1 = new FsVO();
		fsVO1.setCar_ID("CAR002");
		fsVO1.setCar_TYPE("撣豢澈頠�");
		fsVO1.setStar_DB("DB02");
		fsVO1.setEnd_DB("DB04");
		fsVO1.setFs_TIME("12:00");
		fsVO1.setEmp_ID("EMP004");
		dao.insert(fsVO1);

		// FsVO fsVO2 = new FsVO();
		// fsVO2.setForeign_schedule_id("F_S005");
		// fsVO2.setEmp_id("EMP005");
		// fsVO2.setCar_id("CAR002");
		// dao.update(fsVO2);
		//

		// dao.delete("F_S014");

		// FsVO fsVO3 = dao.findByPrimaryKey("F_S001");
		// System.out.println(fsVO3.getForeign_schedule_id());
		// System.out.println(fsVO3.getEmp_id());
		// System.out.println(fsVO3.getCar_id());

		// List<FsVO> list = dao.getAll();
		// for (FsVO aFo : list){
		// System.out.print(aFo.getForeign_schedule_id()+ ",");
		// System.out.print(aFo.getEmp_id()+ ",");
		// System.out.print(aFo.getCar_id()+ ",");
		// System.out.print(aFo.getForeign_schedule_date()+ ",");
		// System.out.print(aFo.getForeign_schedule_updatetime()+ ",");
		// System.out.println();
		// }
	}

	@Override
	public Set<String> getCarTypeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<FsVO> findByCarType(String car_type) {
		// TODO Auto-generated method stub
		return null;
	}
}
