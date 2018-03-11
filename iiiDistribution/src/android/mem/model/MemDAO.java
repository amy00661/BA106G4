package android.mem.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.main.MyData;

public class MemDAO implements MemDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO MEMBER(member_id,emp_id,member_mail,member_psw,member_name,member_birth,"
			+ "member_gender,member_identification,member_cell,member_phone,member_addr"
			+ ",member_status) VALUES('MEM'||LPAD(TO_CHAR(member_seq.NEXTVAL), 3, '0')," + "?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT member_id,emp_id,member_mail,member_psw,member_name,to_char(member_birth,'yyyy-mm-dd')member_birth,"
			+ "member_gender,member_identification,member_cell,member_phone,member_addr,"
			+ "member_status,to_char(member_updatetime,'yyyy-mm-dd hh:mi:ss')member_updatetime FROM MEMBER order by member_id";
	private static final String GET_ONE_STMT = "SELECT member_id,emp_id,member_mail,member_psw,member_name,to_char(member_birth,'yyyy-mm-dd')member_birth,"
			+ "member_gender,member_identification,member_cell,member_phone,member_addr,"
			+ "member_status,to_char(member_updatetime,'yyyy-mm-dd hh:mi:ss')member_updatetime FROM MEMBER where member_id=?";
	private static final String DELETE = "DELETE FROM MEMBER where member_id=?";
	private static final String UPDATE = "UPDATE MEMBER set emp_id=?,member_mail=?, member_psw=?, member_name=?, member_birth=?, member_gender=?, member_identification=?, member_cell=?, member_phone=?,"
			+ "member_addr=?,member_status=?,member_updatetime=? where member_id=?";
	private static final String GET_ONE_MAIL = "SELECT member_id,emp_id,member_mail,member_psw,member_name,to_char(member_birth,'yyyy-mm-dd')member_birth,"
			+ "member_gender,member_identification,member_cell,member_phone,member_addr,member_status,to_char(member_updatetime,'yyyy-mm-dd hh:mi:ss')member_updatetime FROM MEMBER where member_mail=?";

	private static final String FIND_BY_ID_PASWD = "SELECT * FROM MEMBER WHERE member_mail = ? AND member_psw = ?";
	private static final String PWD_IS_CORRECT = "SELECT MEMBER_PSW FROM MEMBER WHERE MEMBER_ID=? ";

	@Override
	public void insert(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getEmp_id());
			pstmt.setString(2, memVO.getMember_mail());
			pstmt.setString(3, memVO.getMember_psw());
			pstmt.setString(4, memVO.getMember_name());
			pstmt.setDate(5, memVO.getMember_birth());
			pstmt.setString(6, memVO.getMember_gender());
			pstmt.setString(7, memVO.getMember_identification());
			pstmt.setString(8, memVO.getMember_cell());
			pstmt.setString(9, memVO.getMember_phone());
			pstmt.setString(10, memVO.getMember_addr());
			pstmt.setString(11, memVO.getMember_status());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
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

	@Override
	public Integer update(MemVO memVO) {
		Integer showResult = null;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getEmp_id());
			pstmt.setString(2, memVO.getMember_mail());
			pstmt.setString(3, memVO.getMember_psw());
			pstmt.setString(4, memVO.getMember_name());
			pstmt.setDate(5, memVO.getMember_birth());
			pstmt.setString(6, memVO.getMember_gender());
			pstmt.setString(7, memVO.getMember_identification());
			pstmt.setString(8, memVO.getMember_cell());
			pstmt.setString(9, memVO.getMember_phone());
			pstmt.setString(10, memVO.getMember_addr());
			pstmt.setString(11, memVO.getMember_status());
			pstmt.setTimestamp(12, memVO.getMember_updatetime());
			pstmt.setString(13, memVO.getMember_id());

			showResult = pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return showResult;

	}

	@Override
	public void delete(String member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, member_id);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MemVO findByMail(String member_mail) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MAIL);
			pstmt.setString(1, member_mail);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMember_id(rs.getString("member_id"));
				memVO.setEmp_id(rs.getString("emp_id"));
				memVO.setMember_mail(member_mail);
				memVO.setMember_psw(rs.getString("member_psw"));
				memVO.setMember_name(rs.getString("member_name"));
				memVO.setMember_birth(rs.getDate("member_birth"));
				memVO.setMember_gender(rs.getString("member_gender"));
				memVO.setMember_identification(rs.getString("member_identification"));
				memVO.setMember_cell(rs.getString("member_cell"));
				memVO.setMember_phone(rs.getString("member_phone"));
				memVO.setMember_addr(rs.getString("member_addr"));
				memVO.setMember_status(rs.getString("member_status"));
				memVO.setMember_updatetime(rs.getTimestamp("member_updatetime"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {

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
		}

		return memVO;
	}

	@Override
	public MemVO findByPrimaryKey(String member_id) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMember_id(rs.getString("member_id"));
				memVO.setEmp_id(rs.getString("emp_id"));
				memVO.setMember_mail(rs.getString("member_mail"));
				memVO.setMember_psw(rs.getString("member_psw"));
				memVO.setMember_name(rs.getString("member_name"));
				memVO.setMember_birth(rs.getDate("member_birth"));
				memVO.setMember_gender(rs.getString("member_gender"));
				memVO.setMember_identification(rs.getString("member_identification"));
				memVO.setMember_cell(rs.getString("member_cell"));
				memVO.setMember_phone(rs.getString("member_phone"));
				memVO.setMember_addr(rs.getString("member_addr"));
				memVO.setMember_status(rs.getString("member_status"));
				memVO.setMember_updatetime(rs.getTimestamp("member_updatetime"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {

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
		}
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMember_id(rs.getString("member_id"));
				memVO.setEmp_id(rs.getString("emp_id"));
				memVO.setMember_mail(rs.getString("member_mail"));
				memVO.setMember_psw(rs.getString("member_psw"));
				memVO.setMember_name(rs.getString("member_name"));
				memVO.setMember_birth(rs.getDate("member_birth"));
				memVO.setMember_gender(rs.getString("member_gender"));
				memVO.setMember_identification(rs.getString("member_identification"));
				memVO.setMember_cell(rs.getString("member_cell"));
				memVO.setMember_phone(rs.getString("member_phone"));
				memVO.setMember_addr(rs.getString("member_addr"));
				memVO.setMember_status(rs.getString("member_status"));
				memVO.setMember_updatetime(rs.getTimestamp("member_updatetime"));

				list.add(memVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MemVO isMember(String userId, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		MemVO memVO = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(FIND_BY_ID_PASWD);
			ps.setString(1, userId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMember_id(rs.getString("member_id"));
				memVO.setEmp_id(rs.getString("emp_id"));
				memVO.setMember_mail(rs.getString("member_mail"));
				memVO.setMember_psw(rs.getString("member_psw"));
				memVO.setMember_name(rs.getString("member_name"));
				memVO.setMember_birth(rs.getDate("member_birth"));
				memVO.setMember_gender(rs.getString("member_gender"));
				memVO.setMember_identification(rs.getString("member_identification"));
				memVO.setMember_cell(rs.getString("member_cell"));
				memVO.setMember_phone(rs.getString("member_phone"));
				memVO.setMember_addr(rs.getString("member_addr"));
				memVO.setMember_status(rs.getString("member_status"));
				memVO.setMember_updatetime(rs.getTimestamp("member_updatetime"));
			}
			return memVO;
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
		return memVO;
	}

//	@Override
//	public Boolean pwdIsCorrect(String mem_id, String mem_pwd) {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		MemVO memVO = null;
//		try {
//			conn = ds.getConnection();
//			ps = conn.prepareStatement(PWD_IS_CORRECT);
//			ps.setString(1, mem_id);
//			ResultSet rs = ps.executeQuery();
//			String str = "";
//			while (rs.next()) {
//				str = rs.getString("member_psw");
//			}
//			if (str.equals(mem_pwd)) {
//				return true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return false;
//	}

}
