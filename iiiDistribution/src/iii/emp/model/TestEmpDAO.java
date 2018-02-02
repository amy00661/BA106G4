package iii.emp.model;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEmpDAO {
	EmpJDBCDAO empDAO = null;
	EmpVO empVO1 = null;
	
	@Before
	public void setUp() throws Exception {
		empDAO = new EmpJDBCDAO();
		empVO1 = new EmpVO();
	}

	@After
	public void tearDown() throws Exception {
		empDAO = null; 
		empVO1 = null;
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	// 新增
	/*@Test
	public void testInsert() {
		empVO1.setDb_id("DB03");
		empVO1.setEmp_pwd("abc12345");
		empVO1.setEmp_status("01");
		empVO1.setEmp_name("章子怡");
		empVO1.setEmp_title("warehouse_staff");
		empVO1.setEmp_email("warehouse@gmail.com");
		empVO1.setEmp_hireDate(java.sql.Date.valueOf("2018-01-20"));
		empVO1.setEmp_leaveDate(java.sql.Date.valueOf("2018-01-20"));
		System.out.println("新增"+empDAO.insert(empVO1)+"筆資料!");
	}*/
	//修改
	@Test
	public void testUpdate() {
		empVO1.setEmp_id("EMP006");
		empVO1.setDb_id("DB04");
		empVO1.setEmp_pwd("abc12345");
		empVO1.setEmp_status("01");
		empVO1.setEmp_name("曾咽蕓");
		empVO1.setEmp_title("driver");
		empVO1.setEmp_email("davidWu@gmail.com");
		empVO1.setEmp_hireDate(java.sql.Date.valueOf("2013-03-01"));
		empVO1.setEmp_leaveDate(java.sql.Date.valueOf("2018-01-20"));
		empDAO.update(empVO1);
	}
}
