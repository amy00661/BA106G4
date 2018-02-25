package iii.mem.model;
import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MemJDBCDAO implements MemDAO_interface{
	
	private static DataSource ds=null;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GROUP4";
	String passwd = "GROUP4";
	
	private static final String INSERT_STMT =
			"INSERT INTO MEMBER(member_id,emp_id,member_mail,member_psw,member_name,member_birth,"
			+ "member_gender,member_identification,member_cell,member_phone,member_addr"
			+ ",member_status) VALUES('MEM'||LPAD(TO_CHAR(member_seq.NEXTVAL), 3, '0'),"
			+ "?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT member_id,emp_id,member_mail,member_psw,member_name,to_char(member_birth,'yyyy-mm-dd')member_birth,"
			+ "member_gender,member_identification,member_cell,member_phone,member_addr,"
			+ "member_status,to_char(member_updatetime,'yyyy-mm-dd hh:mi:ss')member_updatetime FROM MEMBER order by member_id";
	private static final String GET_ONE_STMT =
			"SELECT member_id,emp_id,member_mail,member_psw,member_name,to_char(member_birth,'yyyy-mm-dd')member_birth,"
			+ "member_gender,member_identification,member_cell,member_phone,member_addr,"
			+ "member_status,to_char(member_updatetime,'yyyy-mm-dd hh:mi:ss')member_updatetime FROM MEMBER where member_id=?";
	private static final String DELETE =
			"DELETE FROM MEMBER where member_id=?";
	private static final String UPDATE =
			"UPDATE MEMBER set emp_id=?,member_mail=?, member_psw=?, member_name=?, member_birth=?, member_gender=?, member_identification=?, member_cell=?, member_phone=?,"
			+ "member_addr=?,member_status=?,member_updatetime=? where member_id=?";
	private static final String GET_ONE_MAIL ="SELECT member_psw FROM MEMBER where member_mail=?";
	@Override
	public void insert(MemVO memVO){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se){
			throw new RuntimeException("A database error occured. "
			+ se.getMessage());
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
		}
		if(con != null){
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace(System.err);
			}
		}		
	}

	@Override
	public void update(MemVO memVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			pstmt.executeUpdate();	
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
	public void delete(String member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, member_id);
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
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

	@Override
	public MemVO findByMail(String member_mail) {
		MemVO memVO=null;
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MAIL);
			pstmt.setString(1, member_mail);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
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
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException se){
					
				}if(pstmt != null){
					try{
						pstmt.close();
					}catch (SQLException se){
						se.printStackTrace(System.err);
					}
				}
				if(con !=null){
					try{
						con.close();
					}catch(Exception e){
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
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
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
					
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
				if(rs != null){
					try{
						rs.close();
				}catch(SQLException se){
					
				}if(pstmt != null){
					try{
						pstmt.close();
					}catch (SQLException se){
						se.printStackTrace(System.err);
					}
				}
				if(con !=null){
					try{
						con.close();
					}catch(Exception e){
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
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
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
		
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
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
	
	MemJDBCDAO dao=new MemJDBCDAO();
	
	                        
//	MemVO memVO =new MemVO();
//	memVO.setEmp_id("EMP002");
//	memVO.setMember_mail("qqq@yahoo.com");
//	memVO.setMember_psw("1234");
//	memVO.setMember_name("GGGGG");
//	memVO.setMember_birth(java.sql.Date.valueOf("2004-06-08"));
//	memVO.setMember_gender("�k");
//	memVO.setMember_identification("B123456789");
//	memVO.setMember_cell("0922-123-100");
//	memVO.setMember_phone("04-7984471");
//	memVO.setMember_addr("�x�_�������ϭ��y�n���@�q122��");
//	memVO.setMember_status("00");
//
//	dao.insert(memVO);
	
	
	
	MemVO memVO =new MemVO();
	memVO.setEmp_id("EMP002");
	memVO.setMember_mail("ABC@yahoo.com");
	memVO.setMember_psw("123456");
	memVO.setMember_name("garbage");
	memVO.setMember_birth(java.sql.Date.valueOf("2018-06-08"));
	memVO.setMember_gender("�k");
	memVO.setMember_identification("C123456789");
	memVO.setMember_cell("0911-222-333");
	memVO.setMember_phone("04-7984471");
	memVO.setMember_addr("�x�����_�ٰϤ�߸��|�q269��");
	memVO.setMember_status("01");
	memVO.setMember_id("MEM001");
	
	dao.update(memVO);
	
	
	
//	dao.delete("MEM013");
	
	
//	MemVO memVO = dao.findByPrimaryKey("MEM005");
//	
//	System.out.println(memVO.getMember_id() +",");
//	System.out.println(memVO.getEmp_id() +",");
//	System.out.println(memVO.getMember_mail() +",");
//	System.out.println(memVO.getMember_psw() +",");
//	System.out.println(memVO.getMember_name() +",");
//	System.out.println(memVO.getMember_birth() +",");
//	System.out.println(memVO.getMember_gender() +",");
//	System.out.println(memVO.getMember_identification() +",");
//	System.out.println(memVO.getMember_cell() +",");
//	System.out.println(memVO.getMember_phone() +",");
//	System.out.println(memVO.getMember_addr() +",");
//	System.out.println(memVO.getMember_status() +",");
//	System.out.println(memVO.getMember_updatetime());
	
//	List<MemVO> list = dao.getAll();
//	for(MemVO Mem : list){
//		System.out.print(Mem.getMember_id() +",");
//		System.out.print(Mem.getEmp_id() +",");
//		System.out.print(Mem.getMember_mail() +",");
//		System.out.print(Mem.getMember_psw() +",");
//		System.out.print(Mem.getMember_name() +",");
//		System.out.print(Mem.getMember_birth() +",");
//		System.out.print(Mem.getMember_gender() +",");
//		System.out.print(Mem.getMember_identification() +",");
//		System.out.print(Mem.getMember_cell() +",");
//		System.out.print(Mem.getMember_phone() +",");
//		System.out.print(Mem.getMember_addr() +",");
//		System.out.print(Mem.getMember_status() +",");
//		System.out.println(Mem.getMember_updatetime());
//		System.out.println("============================");
//	}
	
	
	
	}
}
	


