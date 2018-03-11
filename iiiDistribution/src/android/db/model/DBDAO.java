package android.db.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBDAO implements DBDAO_interface{
	
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
	
	private static final String INSERT = 
			"INSERT INTO DB (DB_ID, DB_NAME) VALUES ('DB'||LPAD(TO_CHAR(db_seq.NEXTVAL), 3, '0'),?)";
	private static final String UPDATE = 
			"UPDATE DB SET DB_NAME=? WHERE DB_ID=?";
	private static final String DELETE = 
			"DELETE FROM DB WHERE DB_ID=?";
	private static final String GET_ONE = 
			"SELECT DB_ID, DB_NAME FROM DB WHERE DB_ID=?";
	private static final String GET_ALL = 
			"SELECT DB_ID, DB_NAME FROM DB ORDER BY DB_ID";
	
	@Override
	public Integer insert(DBVO dbVO) {
		
		int insertDB = 0;
		Connection con = null;	
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, dbVO.getDb_name());
			
			insertDB = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}			
		return insertDB;
	}

	@Override
	public Integer update(DBVO dbVO) {
		
		int updateDB = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, dbVO.getDb_name());
			pstmt.setString(2, dbVO.getDb_id());
			
			updateDB = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		return updateDB;
	}

	@Override
	public void delete(String db_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, db_id);
			
			pstmt.executeQuery();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public DBVO findByPrimaryKey(String db_id) {
		DBVO dbVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			
			pstmt.setString(1, db_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				dbVO = new DBVO();
				dbVO.setDb_id(rs.getString("db_id"));
				dbVO.setDb_name(rs.getString("db_name"));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+e.getMessage());
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
		}
		return dbVO;
	}

	@Override
	public List<DBVO> getAll() {
		List<DBVO> list = new ArrayList<DBVO>();
		DBVO dbVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				dbVO = new DBVO();
				dbVO.setDb_id(rs.getString("db_id"));
				dbVO.setDb_name(rs.getString("db_name"));
				list.add(dbVO);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}  finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}	
		}
		return list;
	}
	
	public static void main(String args[]){
		DBJDBCDAO dao = new DBJDBCDAO();
		
		DBVO dbVO1 = new DBVO();
		dbVO1.setDb_name("貨運站");
		System.out.println(dao.insert(dbVO1));
		
		DBVO dbVO2 = new DBVO();
		dbVO2.setDb_name("三星貨運站");
		dbVO2.setDb_id("DB02");
		System.out.println(dao.update(dbVO2));
		
		dao.delete("DB20");
		System.out.println("delete");
		
		DBVO dbVO3 = dao.findByPrimaryKey("DB09");
		System.out.println(dbVO3.getDb_id() + ",");
		System.out.println(dbVO3.getDb_name() + ",");
		
		List<DBVO> list = dao.getAll();
		for(DBVO aDB : list){
			System.out.println(aDB.getDb_id() + ",");
			System.out.println(aDB.getDb_name() + ",");
		}
		
	}
	
}
