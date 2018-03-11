package iii.order_main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import iii.car.model.CarService;
import iii.car.model.CarVO;
import iii.news.model.NewsService;
import iii.news.model.NewsVO;
import iii.order_main.model.OrderService;
import iii.order_main.model.OrderVO;

public class OrderServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		//前端:得到order_id準備單筆查詢
		if("getOne_For_Update_Frontend".equals(action)){
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/************************ 1.接收請求參數 - 輸入格式的錯誤處理 *************************/	
				String order_id = null;
				String a = req.getParameter("order_id1");
				String b = req.getParameter("order_id");
				if(a == null){
					order_id = b;
				} else {
					order_id = a;
				}
					
				System.out.println(43+order_id);
				/*************************** 2.開始從VO取得資料 ***************************************/
				OrderService orderSvc = new OrderService();
				OrderVO orderVO = orderSvc.getOneOrder(order_id); 
				/**************************** 3.取得完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("orderVO",orderVO);
				RequestDispatcher successView = 
						req.getRequestDispatcher("/frontend/order_main/updateOrder.jsp");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			}catch(Exception e){
				errorMsgs.add("無法取得要修改資料: "+e.getMessage());
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/frontend/order_main/queryOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		//後端:得到order_id準備單筆查詢
		if("getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/************************ 1.接收請求參數 - 輸入格式的錯誤處理 *************************/	
				String order_id = req.getParameter("order_id");
				/*************************** 2.開始從VO取得資料 ***************************************/
				OrderService orderSvc = new OrderService();
				OrderVO orderVO = orderSvc.getOneOrder(order_id); 
				/**************************** 3.取得完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("orderVO",orderVO);
				RequestDispatcher successView = 
						req.getRequestDispatcher("/backend/order_main/updateOrder.jsp");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			}catch(Exception e){
				errorMsgs.add("無法取得要修改資料: "+e.getMessage());
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/backend/order_main/queryOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		//前端:更新訂單
		if ("update_Frontend".equals(action)) {
			Map<String, String> addError = new LinkedHashMap<String, String>();
			req.setAttribute("addError", addError);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String order_id = req.getParameter("order_id");
				
				String mem_id = "MEM001";
				
				String emp_id = "EMP001";
				if (emp_id == null || emp_id.trim().length() == 0) {
					addError.put("emp_id", "員工姓名: 請勿空白");
				}
				
				String receiver_city = req.getParameter("receiver_city");
				String cityToDB = receiver_city+"集貨站";
				
				String db_id = "";
				
				switch(cityToDB) {
					case "基隆市集貨站":
						db_id="DB02";
						break;
					case "台北市集貨站":
						db_id="DB03";
						break;
					case "新北市集貨站":
						db_id="DB04";
						break;
					case "桃園市集貨站":
						db_id="DB05";
						break;
					case "新竹市集貨站":
						db_id="DB06";
						break;
					case "新竹縣集貨站":
						db_id="DB07";
						break;
					case "苗栗縣集貨站":
						db_id="DB08";
						break;
					case "台中市集貨站":
						db_id="DB09";
						break;
					case "彰化縣集貨站":
						db_id="DB10";
						break;
					case "南投縣集貨站":
						db_id="DB11";
						break;
					case "雲林縣集貨站":
						db_id="DB12";
						break;
					case "嘉義市集貨站":
						db_id="DB13";
						break;
					case "嘉義縣集貨站":
						db_id="DB14";
						break;
					case "台南市集貨站":
						db_id="DB15";
						break;
					case "高雄市集貨站":
						db_id="DB16";
						break;
					case "屏東縣集貨站":
						db_id="DB17";
						break;
					case "台東縣集貨站":
						db_id="DB18";
						break;
					case "花蓮縣集貨站":
						db_id="DB19";
						break;
					case "宜蘭縣集貨站":
						db_id="DB20";
						break;
					case "澎湖縣集貨站":
						db_id="DB21";
						break;
					case "金門縣集貨站":
						db_id="DB22";
						break;
					case "連江縣集貨站":
						db_id="DB23";
						break;
					default:
						db_id="DB01";					
				}
				
				
				String order_status = "處理中";

				String payment_type = "信用卡";

				Double item_size = null;
			
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

				String receiver_name = req.getParameter("receiver_name");
				String receiver_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (receiver_name == null || receiver_name.trim().length() == 0) {
					addError.put("receiver_name", "收件者姓名: 請勿空白");
				} else if (!receiver_name.trim().matches(receiver_nameReg)) {
					addError.put("receiver_name", "收件者姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String receiver_county = req.getParameter("receiver_county");

				String receiver_address = req.getParameter("receiver_address");
				if (receiver_address == null || receiver_address.trim().length()==0)
					addError.put("receiver_address", "收件者地址: 請勿空白");
				
				String sender_city = req.getParameter("sender_city");

                String sender_county = req.getParameter("sender_county");
                
				String sender_address = req.getParameter("sender_address");

				String receiver_tel_front = req.getParameter("receiver_tel_front");
				String receiver_tel_back = req.getParameter("receiver_tel_back");
				String receiver_tel = null;
				if ((receiver_tel_front != null || receiver_tel_front.trim().length() != 0)
						&& (receiver_tel_back != null || receiver_tel_back.trim().length() != 0))
					receiver_tel = receiver_tel_front + "-" +receiver_tel_back;

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
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
				orderVO.setOrder_id(order_id);
				orderVO.setEmp_id(emp_id);
				orderVO.setMem_id(mem_id);
				orderVO.setDb_id(db_id);
				orderVO.setFee(fee);
				orderVO.setExtrafee(extrafee);
				orderVO.setItem_size(item_size);
				orderVO.setItem_type(item_type);
				orderVO.setItem_weight(item_weight);
				orderVO.setPayment_type(payment_type);
				orderVO.setReceiver_name(receiver_name);
				orderVO.setReceiver_tel(receiver_tel);
				orderVO.setReceiver_cell(receiver_cell);
				orderVO.setReceiver_city(receiver_city);
				orderVO.setReceiver_county(receiver_county);
				orderVO.setReceiver_address(receiver_address);
				orderVO.setReceiver_mail(receiver_mail);
				orderVO.setSender_name(sender_name);
				orderVO.setSender_city(sender_city);
				orderVO.setSender_county(sender_county);
				orderVO.setSender_address(sender_address);
				orderVO.setSender_tel(sender_tel);
				orderVO.setSender_cell(sender_cell);
				orderVO.setExpected_time(expected_time);
				orderVO.setOrder_note(order_note);
				if (!addError.isEmpty()) {
					req.setAttribute("orderVO", orderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/order_main/updateOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				OrderService orderSvc = new OrderService();
				orderSvc.updateOrder(order_id ,emp_id, mem_id, db_id, order_status, payment_type, fee, extrafee, 
		 				item_size, item_weight, item_type, receiver_name, receiver_tel, receiver_cell,
						receiver_city, receiver_county, receiver_address, receiver_mail, sender_name, sender_tel,
						sender_cell, sender_city, sender_county, sender_address, expected_time, order_note);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				req.setAttribute("orderVO", orderVO);
				String url = "/frontend/order_main/queryOneOrder.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				addError.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/order_main/updateOrder.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		//後端:更新訂單
		if ("update".equals(action)) {
			Map<String, String> addError = new LinkedHashMap<String, String>();
			
			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String order_id = req.getParameter("order_id");
				String emp_id = "EMP001";
				if (emp_id == null || emp_id.trim().length() == 0) {
					addError.put("emp_id", "員工姓名: 請勿空白");
				}
				
				String receiver_city = req.getParameter("receiver_city");
				String cityToDB = receiver_city+"集貨站";
				
				String db_id = "";
				
				switch(cityToDB) {
					case "基隆市集貨站":
						db_id="DB02";
						break;
					case "台北市集貨站":
						db_id="DB03";
						break;
					case "新北市集貨站":
						db_id="DB04";
						break;
					case "桃園市集貨站":
						db_id="DB05";
						break;
					case "新竹市集貨站":
						db_id="DB06";
						break;
					case "新竹縣集貨站":
						db_id="DB07";
						break;
					case "苗栗縣集貨站":
						db_id="DB08";
						break;
					case "台中市集貨站":
						db_id="DB09";
						break;
					case "彰化縣集貨站":
						db_id="DB10";
						break;
					case "南投縣集貨站":
						db_id="DB11";
						break;
					case "雲林縣集貨站":
						db_id="DB12";
						break;
					case "嘉義市集貨站":
						db_id="DB13";
						break;
					case "嘉義縣集貨站":
						db_id="DB14";
						break;
					case "台南市集貨站":
						db_id="DB15";
						break;
					case "高雄市集貨站":
						db_id="DB16";
						break;
					case "屏東縣集貨站":
						db_id="DB17";
						break;
					case "台東縣集貨站":
						db_id="DB18";
						break;
					case "花蓮縣集貨站":
						db_id="DB19";
						break;
					case "宜蘭縣集貨站":
						db_id="DB20";
						break;
					case "澎湖縣集貨站":
						db_id="DB21";
						break;
					case "金門縣集貨站":
						db_id="DB22";
						break;
					case "連江縣集貨站":
						db_id="DB23";
						break;
					default:
						db_id="DB01";					
				}
				
				String mem_id = "MEM001";

				String order_status = "處理中";

				String payment_type = "信用卡";

				Double item_size = null;
			
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

				String receiver_name = req.getParameter("receiver_name");
				String receiver_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (receiver_name == null || receiver_name.trim().length() == 0) {
					addError.put("receiver_name", "收件者姓名: 請勿空白");
				} else if (!receiver_name.trim().matches(receiver_nameReg)) {
					addError.put("receiver_name", "收件者姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String receiver_county = req.getParameter("receiver_county");

				String receiver_address = req.getParameter("receiver_address");
				if (receiver_address == null || receiver_address.trim().length()==0)
					addError.put("receiver_address", "收件者地址: 請勿空白");
				
				String sender_city = req.getParameter("sender_city");

                String sender_county = req.getParameter("sender_county");
                
				String sender_address = req.getParameter("sender_address");

				String receiver_tel_front = req.getParameter("receiver_tel_front");
				String receiver_tel_back = req.getParameter("receiver_tel_back");
				String receiver_tel = null;
				if ((receiver_tel_front != null || receiver_tel_front.trim().length() != 0)
						&& (receiver_tel_back != null || receiver_tel_back.trim().length() != 0))
					receiver_tel = receiver_tel_front + "-" +receiver_tel_back;

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
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
				orderVO.setOrder_id(order_id);
				orderVO.setEmp_id(emp_id);
				orderVO.setMem_id(mem_id);
				orderVO.setDb_id(db_id);
				orderVO.setFee(fee);
				orderVO.setExtrafee(extrafee);
				orderVO.setItem_size(item_size);
				orderVO.setItem_type(item_type);
				orderVO.setItem_weight(item_weight);
				orderVO.setPayment_type(payment_type);
				orderVO.setReceiver_name(receiver_name);
				orderVO.setReceiver_tel(receiver_tel);
				orderVO.setReceiver_cell(receiver_cell);
				orderVO.setReceiver_city(receiver_city);
				orderVO.setReceiver_county(receiver_county);
				orderVO.setReceiver_address(receiver_address);
				orderVO.setReceiver_mail(receiver_mail);
				orderVO.setSender_name(sender_name);
				orderVO.setSender_city(sender_city);
				orderVO.setSender_county(sender_county);
				orderVO.setSender_address(sender_address);
				orderVO.setSender_tel(sender_tel);
				orderVO.setSender_cell(sender_cell);
				orderVO.setExpected_time(expected_time);
				orderVO.setOrder_note(order_note);
				
				if (!addError.isEmpty()) {
					req.setAttribute("orderVO", orderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_main/updateOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				OrderService orderSvc = new OrderService();
				orderSvc.updateOrder(order_id, emp_id, mem_id, db_id, order_status, payment_type, fee, extrafee, 
		 				item_size, item_weight, item_type, receiver_name, receiver_tel, receiver_cell,
						receiver_city, receiver_county, receiver_address, receiver_mail, sender_name, sender_tel,
						sender_cell, sender_city, sender_county, sender_address, expected_time, order_note);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				req.setAttribute("orderVO", orderVO);
				String url = "/backend/order_main/queryOneOrder.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				addError.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_main/updateOrder.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		//後端:查詢單筆資料
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************ 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String order_id = req.getParameter("order_id");
				if (order_id == null || (order_id.trim()).length() == 0) {
					errorMsgs.add("訂單id有問題誒");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/order_main/queryOrder.jsp");
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
					System.out.println("isEmpty");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/order_main/queryOneOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/**************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("orderVO", orderVO); 
				System.out.println("successView");
				RequestDispatcher successView = req.getRequestDispatcher("/backend/order_main/queryOneOrder.jsp"); 
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
				
			} catch (Exception e) {
				System.out.println("FinalException");
				errorMsgs.add("Exception" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/order_main/queryOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		//前端:查詢單筆資料
		if ("getOne_For_Display_Frontend".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************ 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String order_id = req.getParameter("order_id");
				if (order_id == null || (order_id.trim()).length() == 0) {
					errorMsgs.add("訂單id有問題誒");
				}
				
				if (!errorMsgs.isEmpty()) {
					res.sendRedirect(req.getContextPath()+"/frontend/index.jsp");
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				
				OrderService orderSvc = new OrderService();
				OrderVO orderVO = orderSvc.getOneOrder(order_id);
				if (orderVO == null) {
					errorMsgs.add("訂單vo有問題誒");
				}
				
				if (!errorMsgs.isEmpty()) {
					res.sendRedirect(req.getContextPath()+"/frontend/index.jsp");
					return;
				}
				
				/**************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("orderVO", orderVO); 
				RequestDispatcher successView = req.getRequestDispatcher("/frontend/order_main/queryOneOrder.jsp"); 
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("Exception" + e.getMessage());
				res.sendRedirect(req.getContextPath()+"/frontend/index.jsp");
			}
		}
		
		if ("Readyinsert".equals(action)) {
			Map<String, String> addError = new LinkedHashMap<String, String>();
			req.setAttribute("addError", addError);
		
			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String order_id = req.getParameter("order_id");
				String emp_id = "EMP001";
				if (emp_id == null || emp_id.trim().length() == 0) {
					addError.put("emp_id", "員工姓名: 請勿空白");
				}
				
				String db_id = "DB01";
				if (db_id == null || db_id.trim().length() == 0) {
					addError.put("db_id", "貨運公司: 請勿空白");
				}
				String mem_id =req.getParameter("mem_id");
				if (mem_id== null ||  mem_id.trim().length() == 0) {
					addError.put(" mem_id", "會員: 請勿空白");
				}
		
				String order_status = "處理中";
		
				String payment_type = "信用卡";
		
				Double item_size = null;
			
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
		
				String receiver_name = req.getParameter("receiver_name");
				String receiver_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (receiver_name == null || receiver_name.trim().length() == 0) {
					addError.put("receiver_name", "收件者姓名: 請勿空白");
				} else if (!receiver_name.trim().matches(receiver_nameReg)) {
					addError.put("receiver_name", "收件者姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
		
				String receiver_city = req.getParameter("receiver_city");
		
				String receiver_county = req.getParameter("receiver_county");
		
				String receiver_address = req.getParameter("receiver_address");
				if (receiver_address == null || receiver_address.trim().length()==0)
					addError.put("receiver_address", "收件者地址: 請勿空白");
				
				String sender_city = req.getParameter("sender_city");
		
		        String sender_county = req.getParameter("sender_county");
		        
				String sender_address = req.getParameter("sender_address");
		
				String receiver_tel_front = req.getParameter("receiver_tel_front");
				String receiver_tel_back = req.getParameter("receiver_tel_back");
				String receiver_tel = null;
				if ((receiver_tel_front != null || receiver_tel_front.trim().length() != 0)
						&& (receiver_tel_back != null || receiver_tel_back.trim().length() != 0))
					receiver_tel = receiver_tel_front + "-" +receiver_tel_back;
		
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
				orderVO.setOrder_id(order_id);
				orderVO.setSender_name(sender_name);
				orderVO.setEmp_id(emp_id);
				orderVO.setMem_id(mem_id);
				orderVO.setFee(fee);
				orderVO.setExtrafee(extrafee);
				orderVO.setPayment_type(payment_type);
				orderVO.setDb_id(db_id);
				orderVO.setReceiver_name(receiver_name);
				orderVO.setSender_tel(sender_tel);
				orderVO.setReceiver_tel(receiver_tel);
				orderVO.setReceiver_cell(receiver_cell);
				orderVO.setSender_cell(sender_cell);
				orderVO.setReceiver_mail(receiver_mail);
				orderVO.setItem_size(item_size);
				orderVO.setItem_type(item_type);
				orderVO.setItem_weight(item_weight);
				orderVO.setReceiver_city(receiver_city);
				orderVO.setReceiver_county(receiver_county);
				orderVO.setSender_city(sender_city);
				orderVO.setSender_county(sender_county);
				orderVO.setExpected_time(expected_time);
				orderVO.setReceiver_address(receiver_address);
				orderVO.setSender_address(sender_address);
				orderVO.setOrder_note(order_note);
				if (!addError.isEmpty()) {
					req.setAttribute("orderVO", orderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/order_main/order_main.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				//orderVO.setOrder_id(order_id);
				req.setAttribute("orderVO", orderVO);
				String url = "/frontend/order_main/cardPay.jsp";
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
		//前端:新增訂單
		if ("insert".equals(action)) {
			Map<String, String> addError = new LinkedHashMap<String, String>();
			req.setAttribute("addError", addError);
			
			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String order_id = req.getParameter("order_id");
				
				String emp_id = "EMP001";
				if (emp_id == null || emp_id.trim().length() == 0) {
					addError.put("emp_id", "員工姓名: 請勿空白");
				}
				
				String receiver_city = req.getParameter("receiver_city");
				String cityToDB = receiver_city+"集貨站";
				
				String db_id = "";
				
				switch(cityToDB) {
					case "基隆市集貨站":
						db_id="DB02";
						break;
					case "台北市集貨站":
						db_id="DB03";
						break;
					case "新北市集貨站":
						db_id="DB04";
						break;
					case "桃園市集貨站":
						db_id="DB05";
						break;
					case "新竹市集貨站":
						db_id="DB06";
						break;
					case "新竹縣集貨站":
						db_id="DB07";
						break;
					case "苗栗縣集貨站":
						db_id="DB08";
						break;
					case "台中市集貨站":
						db_id="DB09";
						break;
					case "彰化縣集貨站":
						db_id="DB10";
						break;
					case "南投縣集貨站":
						db_id="DB11";
						break;
					case "雲林縣集貨站":
						db_id="DB12";
						break;
					case "嘉義市集貨站":
						db_id="DB13";
						break;
					case "嘉義縣集貨站":
						db_id="DB14";
						break;
					case "台南市集貨站":
						db_id="DB15";
						break;
					case "高雄市集貨站":
						db_id="DB16";
						break;
					case "屏東縣集貨站":
						db_id="DB17";
						break;
					case "台東縣集貨站":
						db_id="DB18";
						break;
					case "花蓮縣集貨站":
						db_id="DB19";
						break;
					case "宜蘭縣集貨站":
						db_id="DB20";
						break;
					case "澎湖縣集貨站":
						db_id="DB21";
						break;
					case "金門縣集貨站":
						db_id="DB22";
						break;
					case "連江縣集貨站":
						db_id="DB23";
						break;
					default:
						db_id="DB01";					
				}
				
				String mem_id =req.getParameter("mem_id");
				if (mem_id== null ||  mem_id.trim().length() == 0) {
					addError.put(" mem_id", "會員: 請勿空白");
				}
				
				String order_status = "待收貨";

				String payment_type = "信用卡";

				Double item_size = null;		
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

				String receiver_name = req.getParameter("receiver_name");
				String receiver_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (receiver_name == null || receiver_name.trim().length() == 0) {
					addError.put("receiver_name", "收件者姓名: 請勿空白");
				} else if (!receiver_name.trim().matches(receiver_nameReg)) {
					addError.put("receiver_name", "收件者姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String receiver_county = req.getParameter("receiver_county");

				String receiver_address = req.getParameter("receiver_address");
				if (receiver_address == null || receiver_address.trim().length()==0)
					addError.put("receiver_address", "收件者地址: 請勿空白");
				
				String sender_city = req.getParameter("sender_city");

                String sender_county = req.getParameter("sender_county");
                
				String sender_address = req.getParameter("sender_address");

				String receiver_tel_front = req.getParameter("receiver_tel_front");
				String receiver_tel_back = req.getParameter("receiver_tel_back");
				String receiver_tel = null;
				if ((receiver_tel_front != null || receiver_tel_front.trim().length() != 0)
						&& (receiver_tel_back != null || receiver_tel_back.trim().length() != 0))
					receiver_tel = receiver_tel_front + "-" +receiver_tel_back;

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
				orderVO.setOrder_id(order_id);
				orderVO.setSender_name(sender_name);
				orderVO.setEmp_id(emp_id);
				orderVO.setFee(fee);
				orderVO.setExtrafee(extrafee);
				orderVO.setPayment_type(payment_type);
				orderVO.setDb_id(db_id);
				orderVO.setReceiver_name(receiver_name);
				orderVO.setSender_tel(sender_tel);
				orderVO.setReceiver_tel(receiver_tel);
				orderVO.setReceiver_cell(receiver_cell);
				orderVO.setSender_cell(sender_cell);
				orderVO.setReceiver_mail(receiver_mail);
				orderVO.setItem_size(item_size);
				orderVO.setItem_type(item_type);
				orderVO.setItem_weight(item_weight);
				orderVO.setReceiver_city(receiver_city);
				orderVO.setReceiver_county(receiver_county);
				orderVO.setSender_city(sender_city);
				orderVO.setSender_county(sender_county);
				orderVO.setExpected_time(expected_time);
				orderVO.setReceiver_address(receiver_address);
				orderVO.setSender_address(sender_address);
				orderVO.setOrder_note(order_note);
				
				if (!addError.isEmpty()) {
					req.setAttribute("orderVO", orderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/order_main/order_main.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				OrderService orderSvc = new OrderService();
				
				orderVO = orderSvc.addOrder(emp_id, mem_id, db_id, order_status, payment_type, fee, extrafee, 
		 				item_size, item_weight, item_type, receiver_name, receiver_tel, receiver_cell,
						receiver_city, receiver_county, receiver_address, receiver_mail, sender_name, sender_tel,
						sender_cell, sender_city, sender_county, sender_address, expected_time, order_note);
				
				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				//orderVO.setOrder_id(order_id);
				req.setAttribute("orderVO", orderVO);
				String url = "/frontend/order_main/queryOneOrder.jsp";
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
		
		//後端:複合查詢
		if("listOrders_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				Map<String, String[]> map = req.getParameterMap();
				
				HttpSession session = req.getSession();
				/***************************2.開始複合查詢***************************************/
				OrderService orderSvc = new OrderService();
				List<OrderVO> list  = orderSvc.getAllByCoposite(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listOrders_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/backend/order_main/queryOrderByComposite.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/order_main/queryOrder.jsp");
				failureView.forward(req, res);
			}
		}
	}
}