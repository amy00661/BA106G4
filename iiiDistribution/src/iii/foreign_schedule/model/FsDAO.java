package iii.foreign_schedule.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FsDAO implements FsDAO_interface{
	
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e){
			e.printStackTrace();
		}
	}

	
	private static final String INSERT_STMT = "INSERT INTO FOREIGN_SCHEDULE"
			+ "(FOREIGN_SCHEDULE_ID, CAR_ID, CAR_TYPE, STAR_DB, END_DB, FS_TIME, EMP_ID, FS_UPDATETIME)"
			+ "VALUES ('F_S'||LPAD(TO_CHAR(foreign_schedule_seq.NEXTVAL), 3, '0'),?,?,?,?,?,?,SYSDATE)";
	private static final String GET_ALL_STMT = "SELECT * FROM FOREIGN_SCHEDULE order by FOREIGN_SCHEDULE_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM FOREIGN_SCHEDULE WHERE FOREIGN_SCHEDULE_ID =?";
	private static final String DELETE = "DELETE FROM FOREIGN_SCHEDULE WHERE FOREIGN_SCHEDULE_ID = ?";
	private static final String UPDATE = "UPDATE FOREIGN_SCHEDULE set CAR_ID=?, CAR_TYPE=?, STAR_DB=?, END_DB=?, FS_TIME=?, EMP_ID=? , FS_UPDATETIME=SYSDATE WHERE FOREIGN_SCHEDULE_ID = ?";

	@Override
	public void insert(FsVO fsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, fsVO.getCar_ID());
			pstmt.setString(2, fsVO.getCar_TYPE());
			pstmt.setString(3, fsVO.getStar_DB());
			pstmt.setString(4, fsVO.getEnd_DB());
			pstmt.setString(5, fsVO.getFs_TIME());
			pstmt.setString(6, fsVO.getEmp_ID());
			
			pstmt.executeUpdate();

			
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
		
	}
	
	@Override
	public void update(FsVO fsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, fsVO.getCar_ID());
			pstmt.setString(2, fsVO.getCar_TYPE());
			pstmt.setString(3, fsVO.getStar_DB());
			pstmt.setString(4, fsVO.getEnd_DB());
			pstmt.setString(5, fsVO.getFs_TIME());
			pstmt.setString(6, fsVO.getEmp_ID());
			pstmt.setString(7, fsVO.getForeign_schedule_ID());
			
		
			pstmt.executeUpdate();

			
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
		}
		
	@Override
	public void delete(String FOREIGN_SCHEDULE_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
				
			pstmt.setString(1, FOREIGN_SCHEDULE_ID);
			pstmt.executeUpdate();

			
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
		
	}
	
	@Override
	public FsVO findByPrimaryKey(String FOREIGN_SCHEDULE_ID) {
		FsVO fsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
try {	
			
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, FOREIGN_SCHEDULE_ID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
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

			
			con = ds.getConnection();
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
				list.add(fsVO); 
			}

			// Handle any driver errors
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
			se.printStackTrace();
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
