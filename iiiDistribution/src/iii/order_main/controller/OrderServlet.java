package iii.order_main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import iii.car.model.CarService;
import iii.car.model.CarVO;
import iii.order_main.model.OrderService;
import iii.order_main.model.OrderVO;

public class OrderServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************ 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String order_id = req.getParameter("order_id");
				if (order_id == null || (order_id.trim()).length() == 0) {
					errorMsgs.add("訂單id有問題誒");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/order_main/queryOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				
				OrderService orderSvc = new OrderService();
				OrderVO orderVO = orderSvc.getOneOrder(order_id);
				if (orderVO == null) {
					errorMsgs.add("訂單vo有問題誒");
				}
				
				if (!errorMsgs.isEmpty()) {
					System.out.println("877777");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/order_main/queryOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/**************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("orderVO", orderVO); 
				RequestDispatcher successView = req.getRequestDispatcher("/frontend/order_main/queryOneOrder.jsp"); 
				successView.forward(req, res);
				System.out.println(order_id);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("Exception" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/order_main/queryOrder.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			// JsonObject json = new JsonObject();
			Map<String, String> addError = new LinkedHashMap<String, String>();
			req.setAttribute("addError", addError);
			//
			//
			// Gson gson = new Gson();
			//
			// String gg = gson.toJson(addError);
			//
			// json.addProperty("error", gg);
			// String xx = json.toString();
			// PrintWriter out = res.getWriter();
			// out.write(xx);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String emp_id = "EMP001";
				if (emp_id == null || emp_id.trim().length() == 0) {
					addError.put("emp_id", "員工姓名: 請勿空白");
				}

				String db_id = "DB01";
				if (db_id == null || db_id.trim().length() == 0) {
					addError.put("db_id", "貨運公司: 請勿空白");
				}
				
				String mem_id = "MEM001";
				if (mem_id== null ||  mem_id.trim().length() == 0) {
					addError.put(" mem_id", "會員: 請勿空白");
				}

				// String order_status = req.getParameter("order_status");
				String order_status = "處理中";

				String payment_type = "信用卡";

//				Double item_size_length = null;
//				try {
//					item_size_length = new Double(req.getParameter("item_size_length").trim());
//				} catch (NumberFormatException e) {
//					addError.put("item_size_length", "物品長: 請填數字 ex:232.22");
//				}
//
//				Double item_size_width = null;
//				try {
//					item_size_width = new Double(req.getParameter("item_size_width").trim());
//				} catch (NumberFormatException e) {
//					addError.put("item_size_width", "物品寬: 請填數字 ex:232.22");
//				}
//
				Double item_size = null;
				System.out.println(req.getParameter("item_size"));
				try {
					item_size = new Double(req.getParameter("item_size").trim());
				} catch (NumberFormatException e) {
					addError.put("item_size", "物品高: 請填數字 ex:232.22");
				}
				Double item_weight = null;
				try {
					item_weight = new Double(req.getParameter("item_weight").trim());
				} catch (NumberFormatException e) {
					addError.put("item_weight", "物品重量: 請填數字 ex:232.22");
				}

				String item_type = req.getParameter("item_type");
				if (item_type == null)
					addError.put("item_type", "物品類型: 請選擇");
				

//				Double item_size = new Double(req.getParameter("item_size").trim());
				
//				if (item_size_height != null && item_size_length != null && item_size_width != null)
//					item_size = item_size_length * item_size_width * item_size_height;

				String receiver_name = req.getParameter("receiver_name");
				String receiver_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (receiver_name == null || receiver_name.trim().length() == 0) {
					addError.put("receiver_name", "收件者姓名: 請勿空白");
				} else if (!receiver_name.trim().matches(receiver_nameReg)) {
					addError.put("receiver_name", "收件者姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String receiver_city = req.getParameter("receiver_city");
//				if (receiver_city.equals("請選擇縣市別"))
//					addError.put("receiver_city", "請選擇縣市別");

				String receiver_county = req.getParameter("receiver_county");
//				if (receiver_county.equals("請選擇鄉鎮區"))
//					addError.put("receiver_county", "請選擇鄉鎮區");

				String receiver_address = req.getParameter("receiver_address");
				if (receiver_address == null || receiver_address.trim().length()==0)
					addError.put("receiver_address", "收件者地址: 請勿空白");
				
				String sender_city = req.getParameter("sender_city");
//                if (sender_city.equals("請選擇縣市別"))
//                    addError.put("sender_city", "請選擇縣市別");
                

                String sender_county = req.getParameter("sender_county");
//                if (sender_county.equals("請選擇鄉鎮區") )
//                    addError.put("sender_county", "請選擇鄉鎮區");
                
				String sender_address = req.getParameter("sender_address");
				

				String receiver_tel_front = req.getParameter("receiver_tel_front");
				String receiver_tel_back = req.getParameter("receiver_tel_back");
				String receiver_tel = null;
				if ((receiver_tel_front != null || receiver_tel_front.trim().length() != 0)
						&& (receiver_tel_back != null || receiver_tel_back.trim().length() != 0))
					receiver_tel = receiver_tel_front + "-" +receiver_tel_back;
				System.out.println(receiver_tel);

				String receiver_cell = req.getParameter("receiver_cell");
				String receiver_cellReg = "^09\\d{2}-?\\d{3}-?\\d{3}$";
				if (receiver_cell == null || receiver_cell.trim().length() == 0) {
					addError.put("receiver_cell", "手機號碼: 請勿空白");
				} else if (!receiver_cell.trim().matches(receiver_cellReg)) {
					addError.put("receiver_cell", "手機號碼: 請輸入台灣標準手機號碼 ex:0922-877-169");
				}

				String sender_name = req.getParameter("sender_name");
				String sender_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (sender_name == null || sender_name.trim().length() == 0) {
					addError.put("sender_name", "收件者姓名: 請勿空白");
				} else if (!sender_name.trim().matches(sender_nameReg)) {
					addError.put("sender_name", "收件者姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String sender_tel_front = req.getParameter("sender_tel_front");
				String sender_tel_back = req.getParameter("sender_tel_back");
				String sender_tel = null;
				if ((sender_tel_front != null || sender_tel_front.trim().length() != 0)
						&& (sender_tel_back != null || sender_tel_back.trim().length() != 0))
					sender_tel = sender_tel_front +"-"+sender_tel_back;

				String sender_cell = req.getParameter("sender_cell");
				String sender_cellReg = "^09\\d{2}-?\\d{3}-?\\d{3}$";
				if (sender_cell == null || sender_cell.trim().length() == 0) {
					addError.put("sender_cell", "手機號碼: 請勿空白");
				} else if (!sender_cell.trim().matches(sender_cellReg)) {
					addError.put("sender_cell", "手機號碼: 請輸入台灣標準手機號碼 ex:0922-877-169");
				}

				String receiver_mail = req.getParameter("receiver_mail");
				String receiver_mailReg = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$";
				if (receiver_mail == null || receiver_mail.trim().length() == 0) {
					addError.put("receiver_mail", "使用人E-Mail: 請勿空白");
				} else if (!receiver_mail.trim().matches(receiver_mailReg)) {
					addError.put("receiver_mail", "使用人E-Mail: 請輸入標準email格式 ex:example@gmail.com");
				}

				java.sql.Timestamp expected_time = null;
				try {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date parseDate = df.parse(req.getParameter("expected_time"));
					expected_time = new java.sql.Timestamp(parseDate.getTime());

				} catch (IllegalArgumentException | ParseException e) {

					expected_time = new java.sql.Timestamp(System.currentTimeMillis());
					addError.put("expected_time", "預期送達時間: 請選擇時間");
				}
				
				String order_note = req.getParameter("order_note");
				
				Double fee = null;
				try {
					fee = new Double(req.getParameter("fee").trim());
				} catch (NumberFormatException e) {
					addError.put("fee", "金額: 請按下試算按鈕");
				}
				
				Double extrafee = 200.0;
				
				OrderVO orderVO = new OrderVO();
				orderVO.setItem_size(item_size);
				orderVO.setItem_type(item_type);
				orderVO.setItem_weight(item_weight);
				orderVO.setReceiver_city(receiver_city);
				orderVO.setReceiver_county(receiver_county);
				orderVO.setSender_city(sender_city);
				orderVO.setSender_county(sender_county);
				
				if (!addError.isEmpty()) {
					req.setAttribute("orderVO", orderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/order_main/order_main.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				OrderService orderSvc = new OrderService();
				orderSvc.addOrder(emp_id, mem_id, db_id, order_status, payment_type, fee, extrafee, 
		 				item_size, item_weight, item_type, receiver_name, receiver_tel, receiver_cell,
						receiver_city, receiver_county, receiver_address, receiver_mail, sender_name, sender_tel,
						sender_cell, sender_city, sender_county, sender_address, expected_time, order_note);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/frontend/order_main/queryOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				addError.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/order_main/order_main.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
	}
}