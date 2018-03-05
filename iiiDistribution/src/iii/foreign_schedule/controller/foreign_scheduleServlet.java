package iii.foreign_schedule.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iii.foreign_schedule.model.FsService;
import iii.foreign_schedule.model.FsVO;

public class foreign_scheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//=============================================================================
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String car_ID = req.getParameter("car_ID");
				if (car_ID == null || car_ID.trim().length() == 0)
					;

				String car_TYPE = req.getParameter("car_TYPE").trim();

				String star_DB = req.getParameter("star_DB").trim();

				String end_DB = req.getParameter("end_DB").trim();

				String fs_TIME = req.getParameter("fs_time").trim();
				if (fs_TIME == null || fs_TIME.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}

				FsVO fsVO = new FsVO();
				fsVO.setCar_ID(car_ID);
				fsVO.setCar_TYPE(car_TYPE);
				fsVO.setStar_DB(star_DB);
				System.out.println(star_DB);
				fsVO.setEnd_DB(end_DB);
				fsVO.setFs_TIME(fs_TIME);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fsVO", fsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/foreign_schedule/listAllFs.jsp");
					failureView.forward(req, res);
					return;
				}

				// 2._______________
				FsService fsSvc = new FsService();
				fsVO = fsSvc.addFs(car_ID, car_TYPE, star_DB, end_DB, fs_TIME);
				// 3._______________
				String url = "/backend/foreign_schedule/listAllFs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				// errorMsgs.add(e.getMessage());
				// RequestDispatcher failureView =
				// req.getRequestDispatcher("/backend/foreign_schedule/addfs.jsp");
				// failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
//				1.接受請求
				String foreign_schedule_ID = new String(req.getParameter("foreign_schedule_ID"));
//				2.開始查詢
				FsService fsSvc = new FsService();
				FsVO fsVO = fsSvc.getOneFs(foreign_schedule_ID);
//				3.轉交
				req.setAttribute("fsVO", fsVO);
				String url = "/backend/foreign_schedule/update_fs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);
//				處理其他錯誤
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				
				String foreign_schedule_ID = new String(req.getParameter("foreign_schedule_ID").trim());
				String car_ID = req.getParameter("car_ID");
				String car_TYPE = req.getParameter("car_TYPE").trim();
				String star_DB = req.getParameter("star_DB").trim();
				String end_DB = req.getParameter("end_DB").trim();
				String fs_TIME = req.getParameter("fs_time").trim();
				if (fs_TIME == null || fs_TIME.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}

				FsVO fsVO = new FsVO();
				fsVO.setCar_ID(car_ID);
				fsVO.setCar_TYPE(car_TYPE);
				fsVO.setStar_DB(star_DB);
				fsVO.setEnd_DB(end_DB);
				fsVO.setFs_TIME(fs_TIME);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fsVO", fsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/foreign_schedule/update_fs.jsp");
					failureView.forward(req, res);
					return;
				}
//				2.修改資料
				FsService fsSvc = new FsService();
				fsVO = fsSvc.updateFs(foreign_schedule_ID, car_ID, car_TYPE, star_DB, end_DB, fs_TIME);
//				3.修改完成準備轉交
				req.setAttribute("fsVO", fsVO);
				String url = "/backend/foreign_schedule/listAllFs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
//				處理其他錯誤
			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/foreign_schedule/listAllFs.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
			}
		}

		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				1.接受請求參數
				String foreign_schedule_ID =new String(req.getParameter("foreign_schedule_ID"));
//				2.刪除資料
				FsService fsSvc = new FsService();
				fsSvc.deleteFs(foreign_schedule_ID);
//				3.轉交
				String url = "/backend/foreign_schedule/listAllFs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
//				處理錯誤
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/foreign_schedule/listAllFs.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
	
