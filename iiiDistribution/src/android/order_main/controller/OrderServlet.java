package android.order_main.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.db.model.DBDAO;
import android.db.model.DBVO;
import android.local_order.model.LoDAO;
import android.order_main.model.*;

public class OrderServlet extends HttpServlet {

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		ServletContext context = getServletContext();
		String contentType = context.getInitParameter("contentType");
		req.setCharacterEncoding("UTF-8");
		OrderDAO orderDAO = new OrderDAO();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
		String outStr = "";

		String action = req.getParameter("action");
		System.out.println(action);

		OrderVO orderVO = null;

		if (action.equals("findByPrimaryKey")) {
			String order_Id = req.getParameter("order_Id");
			orderVO = orderDAO.findByPrimaryKey(order_Id);
			outStr = gson.toJson(orderVO);
		} else if (action.equals("findByMem")) {
			String mem_Id = req.getParameter("mem_Id");
			System.out.println(mem_Id);
			outStr = gson.toJson(orderDAO.findByMem(mem_Id));
		} else if (action.equals("findByEmp")) {
			String emp_Id = req.getParameter("emp_Id");
			System.out.println(emp_Id);
			outStr = gson.toJson(orderDAO.findByEmp(emp_Id));
			// } else if (action.equals("insert")) {
			// String memberJson = req.getParameter("member");
			// System.out.println(memberJson);
			// MemVO memVO = gson.fromJson(memberJson, MemVO.class);
			// outStr = String.valueOf(memDao.insert(memVO));
			// } else if (action.equals("findById")) {
			// String userId = req.getParameter("userId");
			// Member member = memDao.findById(userId);
			// outStr = member == null ? "" : gson.toJson(member);
		} else if (action.equals("updateStatusDb")) {
			String order_id = req.getParameter("order_id");
			String emp_id = req.getParameter("emp_id");
			String db_id = req.getParameter("db_id");
			String btnClick = req.getParameter("btnClick");
			System.out.println(order_id);
			System.out.println(emp_id);
			System.out.println(db_id);
			System.out.println(btnClick);

			DBVO dbVO = null;
			DBDAO dbDAO = new DBDAO();

			dbVO = dbDAO.findByPrimaryKey(db_id);

			outStr = "0";

			orderVO = orderDAO.findByPrimaryKey(order_id);

			if (btnClick.equals("inStorage")) {
				if (orderVO.getOrder_status().equals("待收貨") || orderVO.getOrder_status().equals("轉移中")
						|| orderVO.getOrder_status().equals("配送中")) {
					orderVO.setOrder_status("已入庫");
					if (dbVO.getDb_name().contains(orderVO.getReceiver_city())) {
						orderVO.setDb_id(db_id);
						outStr = String.valueOf(orderDAO.insertOneWithOrderIDLo(emp_id, order_id));
					} else {
						orderVO.setDb_id("DB01");
						outStr = String.valueOf(orderDAO.insertOneWithOrderIDFo(emp_id, order_id));
					}

					outStr = String.valueOf(orderDAO.update(orderVO));
				} else {
					outStr = "0";
				}
			} else if (btnClick.equals("outStorage")) {
				if (orderVO.getOrder_status().equals("已入庫")) {
					if (dbVO.getDb_name().contains(orderVO.getReceiver_city())) {
						orderVO.setOrder_status("配送中");
						outStr = String.valueOf(orderDAO.update(orderVO));
					} else {
						orderVO.setOrder_status("轉移中");
						outStr = String.valueOf(orderDAO.update(orderVO));
					}
				} else {
					outStr = "0";
				}
			} else {
				outStr = "0";
			}
			System.out.println(outStr);
			// orderVO = gson.fromJson(req.getParameter("orderVO"),
			// OrderVO.class);
			// outStr = String.valueOf(memDao.update(member));
		}

		res.setContentType(contentType);
		PrintWriter out = res.getWriter();
		System.out.println(outStr);
		out.print(outStr);
		out.close();

	}
}
