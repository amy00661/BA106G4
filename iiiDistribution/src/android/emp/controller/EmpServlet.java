package android.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.emp.model.EmpDAO;
import android.emp.model.EmpVO;

public class EmpServlet extends HttpServlet {

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		ServletContext context = getServletContext();
		String contentType = context.getInitParameter("contentType");
		req.setCharacterEncoding("UTF-8");
		EmpDAO empDAO = new EmpDAO();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
		String outStr = "";

		String action = req.getParameter("action");
		System.out.println(action);

		EmpVO empVO = null;

		if (action.equals("isEmp")) {
			String userId = req.getParameter("userId");
			String password = req.getParameter("password");
			empVO = empDAO.isEmp(userId, password);
			outStr = gson.toJson(empVO);
			// } else if (action.equals("isUserIdExist")) {
			// String userId = req.getParameter("userId");
			// outStr = String.valueOf(memDao.isUserIdExist(userId));
			// } else if (action.equals("insert")) {
			// String memberJson = req.getParameter("member");
			// System.out.println(memberJson);
			// MemVO memVO = gson.fromJson(memberJson, MemVO.class);
			// outStr = String.valueOf(memDao.insert(memVO));
			// } else if (action.equals("findById")) {
			// String userId = req.getParameter("userId");
			// Member member = memDao.findById(userId);
			// outStr = member == null ? "" : gson.toJson(member);
			// } else if (action.equals("update")) {
			// Member member = gson.fromJson(req.getParameter("member"),
			// Member.class);
			// outStr = String.valueOf(memDao.update(member));
		}

		res.setContentType(contentType);
		PrintWriter out = res.getWriter();
		System.out.println(outStr);
		out.print(outStr);
		out.close();

	}
}
