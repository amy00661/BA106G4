package iii.local_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import iii.local_schedule.model.LsService;
import iii.local_schedule.model.LsVO;
import iii.order_main.model.OrderVO;
import iii.local_order.model.LoService;
import iii.local_order.model.LoVO;

@WebServlet("/local_orderServlet")
public class Local_OrderServlet extends HttpServlet {
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

				String str = req.getParameter("local_order_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入排單編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/local_order/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String local_order_ID = null;
				try {
					local_order_ID = new String(str);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/local_order/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				LoService loSvc = new LoService();
				LoVO loVO = loSvc.getOneLo(local_order_ID);
				if (loVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/local_order/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("loVO", loVO); // 資料庫取出的VO物件,存入req
				String url = "/backend/local_order/listOneFo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOne.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				// errorMsgs.add("無法取得資料:" + e.getMessage());
				// RequestDispatcher failureView = req
				// .getRequestDispatcher("/backend/local_order/select_page.jsp");
				// failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);
						
						try {
							/***************************1.接收請求參數****************************************/
							String local_order = new String(req.getParameter("local_order_ID"));
							
							/***************************2.開始查詢資料****************************************/
							LoService loSvc = new LoService();
							LoVO loVO = loSvc.getOneLo(local_order);
											
							/***************************3.查詢完成,準備轉交(Send the Success view)************/
							req.setAttribute("loVO", loVO);         // 資料庫取出的empVO物件,存入req
							String url = "/local_order/update_fo_input.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
							successView.forward(req, res);
			
							/***************************其他可能的錯誤處理**********************************/
						} catch (Exception e) {
							errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/local_order/listAllFo.jsp");
							failureView.forward(req, res);
						}
					}
		

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				
				String local_schedule_ID = new String(req.getParameter("local_schedule_ID").trim());
				
				String car_ID = req.getParameter("car_ID");
				if (car_ID == null || car_ID.trim().length() == 0)
					;

				String car_TYPE= req.getParameter("car_TYPE").trim();



				String ls_TIME = req.getParameter("ls_TIME").trim();
				if (ls_TIME == null || ls_TIME.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}

				LsVO lsVO = new LsVO();
				lsVO.setCar_id(car_ID);
				lsVO.setCar_type(car_TYPE);
				lsVO.setLs_time(ls_TIME);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("LsVO", lsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/local_schedule/update_ls_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				LsService lsSvc = new LsService();
				lsVO = lsSvc.updateLs(local_schedule_ID, car_ID, car_TYPE, ls_TIME);
				
				String url = "/backend/local_schedule/listAllLs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				
			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/local_schedule/listAllFs.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String emp_ID = req.getParameter("emp_ID").trim();
				if (emp_ID == null || emp_ID.trim().length() == 0) {
					errorMsgs.add("員工ID請勿空白");
				}

				String order_ID = req.getParameter("order_ID").trim();
				if (order_ID == null || order_ID.trim().length() == 0) {
					errorMsgs.add("訂單ID請勿空白");
				}

				String local_schedule_ID = req.getParameter("local_schedule_ID").trim();
				if (local_schedule_ID == null || local_schedule_ID.trim().length() == 0) {
					errorMsgs.add("排單ID請勿空白");
				}

				LoVO loVO = new LoVO();
				loVO.setEmp_ID(emp_ID);
				loVO.setOrder_ID(order_ID);
				loVO.setLocal_schedule_ID(local_schedule_ID);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("loVO", loVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/local_order/addLo.jsp");
					failureView.forward(req, res);
					return;
				}

				// 2.-------------------------------------------------------
				LoService loSvc = new LoService();
				loVO = loSvc.addLo(emp_ID, order_ID, local_schedule_ID);

				// 3.--------------------------------------------------------
				String url = "/backend/local_order/listAllFo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/local_order/addLo.jsp");
				failureView.forward(req, res);
				// e.printStackTrace();
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String local_order_ID = new String(req.getParameter("/backend/local_order_ID"));
				
				/***************************2.開始刪除資料***************************************/
				LoService loSvc = new LoService();
				//loSvc.deleteLo(local_order_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/local_order/listAllFo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/local_order/listAllFo.jsp");
				failureView.forward(req, res);
			}
		}
		if("getUnShipOrders".equals(action)){	//取得所有未出貨訂單
			Gson gson = new Gson();
			LoService loService = new LoService();
			String db_id = req.getParameter("db_id");
			String item_type = req.getParameter("item_type");
			List<OrderVO> local_OrderList = loService.getUnShipOrders(db_id, item_type, "未出貨");
			res.setContentType("application/json");
	        res.setCharacterEncoding("UTF-8");
	        res.setHeader("Cache-Control", "no-cache");
	        String loJson = gson.toJson(local_OrderList);
			out.print(loJson);
		}
		if("getAll".equals(action)){
			try {
				LoService loService = new LoService();
				List<LoVO> loList = loService.getAll();
				LsService lsService = new LsService();
				JSONArray calEventsArray = new JSONArray();
				for(LoVO loVO:loList){
					LsVO lsVO = lsService.getOneLs(loVO.getLocal_schedule_ID());
					String ls_time = lsVO.getLs_time();
					JSONObject eventVO = new JSONObject();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String date = df.format(loVO.getLocal_orderDate());  
					eventVO.put("id", loVO.getLocal_schedule_ID());
					eventVO.put("title", loVO.getLocal_order_ID());
					eventVO.put("start", date+"T"+ls_time+":00");
					calEventsArray.put( eventVO );
				}
				res.setContentType("application/json");
				out.print(calEventsArray);
				System.out.println("calEventsArray = "+calEventsArray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}
