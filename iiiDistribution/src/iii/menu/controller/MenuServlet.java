package iii.menu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import iii.authority.model.AuthorityService;
import iii.menu.model.MenuNodeVO;
import iii.menu.model.MenuService;

public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		
		if("login".equals(action)){
			String emp_id = req.getParameter("empid");
			Set<String> menuList = (Set<String>)(req.getAttribute("menuList"));
			int menuCount = menuList.size();
			MenuService menuSvc = new MenuService();
			LinkedHashSet<MenuNodeVO> menuBar = menuSvc.getMenuBar_ByEmp(emp_id,menuCount);
//			 List to JSON
//			String jsonStr = new JSONArray(menuBar).toString();
//			System.out.println("JsonTree to JSON: " + jsonStr);
			HttpSession session = req.getSession();
			session.setAttribute("menuBar",menuBar);
		}
		
	}

}
