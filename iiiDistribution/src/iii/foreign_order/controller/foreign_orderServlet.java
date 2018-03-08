package iii.foreign_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import iii.foreign_order.model.FoService;
import iii.foreign_order.model.FoVO;
import iii.foreign_schedule.model.FsService;
import iii.foreign_schedule.model.FsVO;
import iii.order_main.model.OrderVO;

public class foreign_orderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String str = req.getParameter("foreign_order_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("輸入排單編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/foreign_order/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String foreign_order_ID = null;
				try {
					foreign_order_ID = new String(str);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/foreign_order/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				FoService foSvc = new FoService();
				FoVO foVO = foSvc.getOneFo(foreign_order_ID);
				if (foVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/foreign_order/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("foVO", foVO);  // 資料庫取出的VO物件,存入req
				String url = "/backend/foreign_order/listOneFo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																					// listOne.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				// errorMsgs.add("無法取得資料:" + e.getMessage());
				// RequestDispatcher failureView = req
				// .getRequestDispatcher("/backend/foreign_order/select_page.jsp");
				// failureView.forward(req, res);
				e.printStackTrace();
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAll.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.****************************************/
				String foreign_order = new String(req.getParameter("foreign_order_ID"));

				/*************************** 2.****************************************/
				FoService foSvc = new FoService();
				FoVO foVO = foSvc.getOneFo(foreign_order);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("foVO", foVO); 
				String url = "/foreign_order/update_fo_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
																				
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("更新失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/foreign_order/listAllFo.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			java.sql.Date fo_date = null;
			int updateRow = 0;
			try {
				String foreignOrderDate = req.getParameter("foreignOrderDate").trim();
				if (!foreignOrderDate.isEmpty())
					fo_date = java.sql.Date.valueOf(foreignOrderDate);
				String foreign_schedule_id = req.getParameter("foreign_schedule_id");
				String[] orderArray = req.getParameterValues("foreignOrders[]");
				String emp_id = req.getParameter("emp_id");
				FoVO foVO = new FoVO();
				foVO.setEmp_id(emp_id);
				foVO.setFo_date(fo_date);
				foVO.setForeign_schedule_id(foreign_schedule_id);
				//將當天當班車已安排的訂單回復成未排單(回復成null)
				FoService foSvc = new FoService();
				foSvc.update_off(foVO);
				
				//再新的訂單押上日期及車次
				updateRow = foSvc.update_on(foVO, orderArray);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			out.print(updateRow);
		}

//		if ("insert".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String emp_ID = req.getParameter("emp_ID").trim();
//				if (emp_ID == null || emp_ID.trim().length() == 0) {
//					errorMsgs.add("員工ID請勿空白");
//				}
//
//				String order_ID = req.getParameter("order_ID").trim();
//				if (order_ID == null || order_ID.trim().length() == 0) {
//					errorMsgs.add("訂單ID請勿空白");
//				}
//
//				String foreign_schedule_ID = req.getParameter("foreign_schedule_ID").trim();
//				if (order_ID == null || order_ID.trim().length() == 0) {
//					errorMsgs.add("排單ID請勿空白");
//				}
//
//				FoVO foVO = new FoVO();
//				foVO.setEmp_id(emp_ID);
//				foVO.setOrder_id(order_ID);
//				foVO.setForeign_order_id(foreign_schedule_ID);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("foVO", foVO); 
//					RequestDispatcher failureView = req.getRequestDispatcher("/backend/foreign_order/addLo.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				FoService foSvc = new FoService();
//				foVO = foSvc.addFo(emp_ID, order_ID, foreign_schedule_ID);
//
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/backend/foreign_order/addLo.jsp");
//				failureView.forward(req, res);
//				// e.printStackTrace();
//			}
//		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String foreign_order_ID = new String(req.getParameter("/backend/foreign_order_ID"));

				FoService foSvc = new FoService();

				String url = "/backend/foreign_order/listAllFo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/foreign_order/listAllFo.jsp");
				failureView.forward(req, res);
			}
		}

		if ("get_FOs_Bind_FS".equals(action)) {
			try {
				FoService foService = new FoService();
				List<FoVO> foList = foService.get_FOs_Bind_FS();
				FsService fsService = new FsService();
				JSONArray calEventsArray = new JSONArray();
				for(FoVO foVO : foList){
					FsVO fsVO = fsService.getOneFs(foVO.getForeign_schedule_id());
					String fs_time = fsVO.getFs_TIME();
					JSONObject eventVO = new JSONObject();
					
					Date fo_date = foVO.getFo_date();
					SimpleDateFormat sdf = new SimpleDateFormat();
					sdf.applyPattern("yyyy-MM-dd");
					String startDate = sdf.format(fo_date);
					
					sdf.applyPattern("yyyy-MM-dd HH:mm");
					Date start_millisc = sdf.parse(startDate + " " + fs_time);
					// 計算8小時後的毫秒數
					Calendar cal = Calendar.getInstance();
					cal.setTime(start_millisc);
					cal.add(Calendar.HOUR_OF_DAY, 1);
					
					String endDate = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-"
							+ cal.get(Calendar.DATE) + "T" + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE);
					eventVO.put("id", foVO.getForeign_order_id());
					eventVO.put("title", foVO.getOrder_id());
					eventVO.put("resourceId", foVO.getForeign_schedule_id());
					eventVO.put("start", startDate + "T" + fs_time);
					// eventVO.put("end", endDate);
					calEventsArray.put(eventVO);
				}
				res.setContentType("application/json");
				out.print(calEventsArray);
				System.out.println("calEventsArray = " + calEventsArray);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
				
		
		
		
		if("getUnShipOrders".equals(action)) {
			Gson gson = new Gson();
			FoService foService = new FoService();
			String db_id = req.getParameter("db_id");
			String item_type = req.getParameter("item_type");
			List<OrderVO> orderList = foService.getUnShipOrders(db_id, item_type);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.setHeader("Cache-Control", "no-cache");
			String orderJson = gson.toJson(orderList);
			out.print(orderJson);
		}
		
		if ("getForeignOrders".equals(action)) {
			FoService foService = new FoService();
			String db_id = req.getParameter("db_id");
			String item_type = req.getParameter("item_type");
			java.sql.Date fo_date = null;
			try {
				String fodate = req.getParameter("fodate").trim();
				if (!fodate.isEmpty())
					fo_date = java.sql.Date.valueOf(fodate);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			List<OrderVO> foreign_OrderList = foService.findCarsFOs(db_id, fo_date, item_type);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.setHeader("Cache-Control", "no-cache");
			Gson gson = new Gson();
			String foJson = gson.toJson(foreign_OrderList);
			out.print(foJson);

		}
	}
}
