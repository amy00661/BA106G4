package iii.local_schedule.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import iii.local_schedule.model.LsService;
import iii.local_schedule.model.LsVO;

@WebServlet("/local_scheduleServlet")
public class Local_ScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String car_id = req.getParameter("car_id");

				String car_type = req.getParameter("car_type");

				String ls_time = req.getParameter("ls_time").trim();
				if (ls_time == null || ls_time.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}

				LsVO lsVO = new LsVO();
				lsVO.setCar_id(car_id);
				lsVO.setCar_type(car_type);
				lsVO.setLs_time(ls_time);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("lsVO", lsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/local_schedule/listAllLs.jsp");
					failureView.forward(req, res);
					return;
				}

				// 2.___________________
				LsService lsSvc = new LsService();
				lsVO = lsSvc.addLs(car_id, car_type, ls_time);
				// 3.___________________
				String url = "/backend/local_schedule/listAllLs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String local_schedule_ID = new String(req.getParameter("local_schedule_ID"));
				LsService lsSvc = new LsService();
				LsVO lsVO = lsSvc.getOneLs(local_schedule_ID);

				req.setAttribute("lsVO", lsVO);
				String url = "/backend/local_schedule/XXXXXXXXX.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				String local_schedule_id = new String(req.getParameter("local_schedule_id").trim());

				String car_id = req.getParameter("car_id");
				if (car_id == null || car_id.trim().length() == 0)
					;

				String car_type = req.getParameter("car_type").trim();

				String ls_time = req.getParameter("fs_time").trim();
				if (ls_time == null || ls_time.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}

				LsVO lsVO = new LsVO();
				lsVO.setCar_id(car_id);
				lsVO.setCar_type(car_type);
				lsVO.setLs_time(ls_time);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("lsVO", lsVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/local_schedule/update_ls_input.jsp");
					failureView.forward(req, res);
					return;
				}
				LsService lsSvc = new LsService();
				lsVO = lsSvc.updateLs(local_schedule_id, car_id, car_type, ls_time);

				String url = "/backend/local_schedule/listAllFs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				// errorMsgs.add(e.getMessage());
				// RequestDispatcher failureView = req
				// .getRequestDispatcher("/backend/foreign_schedule/listAllFs.jsp");
				// failureView.forward(req, res);
				e.printStackTrace();
			}
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				String local_schedule_id = new String(req.getParameter(""));

				LsService lsSvc = new LsService();
				LsVO lsVO = lsSvc.getOneLs(local_schedule_id);
				lsSvc.deleteLs(local_schedule_id);

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if("getLSbyCarType".equals(action)){
			Gson gson = new Gson();
			LsService lsService = new LsService();
			String car_type = req.getParameter("car_type");
			Set<LsVO> local_ScheduleList = lsService.getLSbyCarType(car_type);
			res.setContentType("application/json");
	        res.setCharacterEncoding("UTF-8");
	        res.setHeader("Cache-Control", "no-cache");
			String lsJson = gson.toJson(local_ScheduleList);
			out.print(lsJson);
		}
		
	}
}
