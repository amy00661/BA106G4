package iii.opinion.model;

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


public class OpinionDAO implements OpinionDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO OPINION(OPINION_ID, OPINION_TYPE, OPINION_NAME, OPINION_PHONE, OPINION_CELL, OPINION_EMAIL, OPINION_CONTENT, OPINION_STATUS, OPINION_CREATETIME, OPINION_UPDATETIME) "
	+"VALUES ('OPI'||LPAD(TO_CHAR(opinion_seq.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE)";
	private static final String UPDATE = "UPDATE OPINION set EMP_ID=?, OPINION_TYPE=?, OPINION_PHONE=?, OPINION_CELL=?, OPINION_EMAIL=?, OPINION_CONTENT=?, OPINION_STATUS=?, OPINION_UPDATETIME=SYSDATE WHERE OPINION_ID=?";
	private static final String GET_ONE_STMT = "SELECT * FROM OPINION where OPINION_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM OPINION order by OPINION_ID";
	
	
	
	@Override
	public int insert(OpinionVO opinionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int insertRow=0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,opinionVO.getOpinion_type());
			pstmt.setString(2,opinionVO.getOpinion_name());
			pstmt.setString(3,opinionVO.getOpinion_phone());
			pstmt.setString(4,opinionVO.getOpinion_cell());
			pstmt.setString(5,opinionVO.getOpinion_email());
			pstmt.setString(6,opinionVO.getOpinion_content());
			pstmt.setString(7,"00");
			insertRow = pstmt.executeUpdate();
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

	public int update(OpinionVO opinionVO){
		int updateRow = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1,opinionVO.getEmp_id());			
			pstmt.setString(2,opinionVO.getOpinion_type());
			pstmt.setString(3, opinionVO.getOpinion_phone());
			pstmt.setString(4, opinionVO.getOpinion_cell());
			pstmt.setString(5, opinionVO.getOpinion_email());
			pstmt.setString(6,opinionVO.getOpinion_content());
			pstmt.setString(7,opinionVO.getOpinion_status());
			pstmt.setString(8,opinionVO.getOpinion_id());
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

	@Override
	public OpinionVO findByPrimaryKey(String opinion_id) {
		OpinionVO opinionVO = new OpinionVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, opinion_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				opinionVO.setOpinion_id(rs.getString("OPINION_ID"));
				opinionVO.setEmp_id(rs.getString("EMP_ID"));
				opinionVO.setOpinion_type(rs.getString("OPINION_TYPE"));
				opinionVO.setOpinion_name(rs.getString("OPINION_NAME"));
				opinionVO.setOpinion_phone(rs.getString("OPINION_PHONE"));
				opinionVO.setOpinion_cell(rs.getString("OPINION_CELL"));
				opinionVO.setOpinion_email(rs.getString("OPINION_EMAIL"));
				opinionVO.setOpinion_content(rs.getString("OPINION_CONTENT"));
				opinionVO.setOpinion_status(rs.getString("OPINION_STATUS"));
				opinionVO.setOpinion_createTime(rs.getTimestamp("OPINION_CREATETIME"));
				opinionVO.setOpinion_updateTime(rs.getTimestamp("OPINION_UPDATETIME"));
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
		return opinionVO;
	}

	@Override
	public List<OpinionVO> getAll() {
		List<OpinionVO> list = new ArrayList<OpinionVO>();
		OpinionVO opinionVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// opinionVO 也稱為 Domain objects
				opinionVO = new OpinionVO();
				opinionVO.setOpinion_id(rs.getString("OPINION_ID"));
				opinionVO.setEmp_id(rs.getString("EMP_ID"));
				opinionVO.setOpinion_type(rs.getString("OPINION_TYPE"));
				opinionVO.setOpinion_name(rs.getString("OPINION_NAME"));
				opinionVO.setOpinion_phone("OPINION_PHONE");
				opinionVO.setOpinion_cell(rs.getString("OPINION_CELL"));
				opinionVO.setOpinion_email(rs.getString("OPINION_EMAIL"));
				opinionVO.setOpinion_content(rs.getString("OPINION_CONTENT"));
				opinionVO.setOpinion_status(rs.getString("OPINION_STATUS"));
				opinionVO.setOpinion_createTime(rs.getTimestamp("OPINION_CREATETIME"));
				opinionVO.setOpinion_updateTime(rs.getTimestamp("OPINION_UPDATETIME"));
				list.add(opinionVO);
			}
			// Handle any SQL errors
		}  catch (SQLException se) {
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
