package android.mem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.mem.model.*;

public class MemServlet extends HttpServlet {

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		ServletContext context = getServletContext();
		String contentType = context.getInitParameter("contentType");
		req.setCharacterEncoding("UTF-8");
		MemDAO memDAO = new MemDAO();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
		String outStr = "";

		String action = req.getParameter("action");
		System.out.println(action);

		MemVO memVO = null;

		if (action.equals("isMember")) {
			String userId = req.getParameter("userId");
			String password = req.getParameter("password");
			memVO = memDAO.isMember(userId, password);
			outStr = gson.toJson(memVO);
			// } else if (action.equals("isUserIdExist")) {
			// String userId = req.getParameter("userId");
			// outStr = String.valueOf(memDao.isUserIdExist(userId));
		} else if (action.equals("findByPrimaryKey")) {
			String mem_id = req.getParameter("mem_id");
			System.out.println(mem_id);
			outStr = gson.toJson(memDAO.findByPrimaryKey(mem_id));
		} else if (action.equals("pwdIsCorrect")) {
			String mem_id = req.getParameter("mem_id");
			String mem_pwd = req.getParameter("mem_pwd");
			System.out.println(mem_id);
			System.out.println(mem_pwd);
			memVO = memDAO.findByPrimaryKey(mem_id);
			if (memVO.getMember_psw().equals(mem_pwd)) {
				outStr = "true";
			} else {
				outStr = "false";
			}
		} else if (action.equals("updatePwd")) {
			String mem_id = req.getParameter("mem_id");
			String mem_pwd = req.getParameter("mem_pwd");
			memVO = memDAO.findByPrimaryKey(mem_id);
			memVO.setMember_psw(mem_pwd);
			outStr = gson.toJson(memDAO.update(memVO));
		} else if (action.equals("update")) {
			memVO = gson.fromJson(req.getParameter("memVO"), MemVO.class);
			System.out.println(memVO);
			outStr = gson.toJson(memDAO.update(memVO));
		}

		res.setContentType(contentType);
		PrintWriter out = res.getWriter();
		System.out.println(outStr);
		out.print(outStr);
		out.close();

	}
}
