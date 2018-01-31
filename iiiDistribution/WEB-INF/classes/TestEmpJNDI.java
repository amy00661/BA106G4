

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iii.emp.model.EmpDAO;
import iii.emp.model.EmpDAO_interface;
import iii.emp.model.EmpVO;

/**
 * Servlet implementation class TestEmpJNDI
 */
@WebServlet("/TestEmpJNDI")
public class TestEmpJNDI extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = res.getWriter();
		EmpDAO_interface empDAO = new EmpDAO();
		EmpVO empVO1 = new EmpVO();
		
		// 測試新增員工
		/*
		empVO1.setDb_id("DB03");
		empVO1.setEmp_pwd("abc12345");
		empVO1.setEmp_status("01");
		empVO1.setEmp_name("章子怡");
		empVO1.setEmp_title("warehouse_staff");
		empVO1.setEmp_email("warehouse@gmail.com");
		empVO1.setEmp_hireDate(java.sql.Date.valueOf("2018-01-20"));
		empVO1.setEmp_leaveDate(java.sql.Date.valueOf("2018-01-20"));
		System.out.println("新增"+empDAO.insert(empVO1)+"筆資料!");*/
		
		//測試修改員工
		empVO1.setEmp_id("EMP006");
		empVO1.setDb_id("DB04");
		empVO1.setEmp_pwd("abc12345");
		empVO1.setEmp_status("01");
		empVO1.setEmp_name("曾焉畇");
		empVO1.setEmp_title("driver");
		empVO1.setEmp_email("davidWu@gmail.com");
		empVO1.setEmp_hireDate(java.sql.Date.valueOf("2013-03-01"));
		System.out.println("更新"+empDAO.update(empVO1)+"筆員工資料!");
		
		// 查詢:以員編
		empVO1 = empDAO.findByPrimaryKey("EMP008");
		System.out.printf("員工%s資訊如下：%n",empVO1.getEmp_id());
		System.out.println(empVO1);
		
		// 查詢:全部
		List<EmpVO> list = empDAO.getAll();
		System.out.println("全部的員工資訊如下：");
		for (EmpVO aEmp : list) {
			System.out.print(aEmp.getEmp_id() + "\t");
			System.out.print(aEmp.getDb_id() + "\t");
			System.out.print(aEmp.getEmp_pwd() + "\t");
			System.out.print(aEmp.getEmp_status() + "\t");
			System.out.print(aEmp.getEmp_name() + "\t");
			System.out.print(aEmp.getEmp_title() + "\t");
			System.out.print(aEmp.getEmp_email() + "\t");
			System.out.print(aEmp.getEmp_hireDate() + "\t");
			System.out.print(aEmp.getEmp_leaveDate() + "\t");
			System.out.println(aEmp.getEmp_updateTime());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
