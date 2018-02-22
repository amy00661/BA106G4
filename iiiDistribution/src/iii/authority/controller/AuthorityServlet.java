package iii.authority.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import iii.authority.model.AuthorityDAO;
import iii.authority.model.AuthorityDAO_interface;
import iii.authority.model.AuthorityService;

public class AuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");

		
		//System.out.println("這裡是AuthorityServlet!");
		if("insert".equals(action)){
			String emp_id = (String) req.getAttribute("emp_id");
			Set<String> menuList = (HashSet<String>) req.getAttribute("menuList");
			AuthorityService authSvc = new AuthorityService();
			authSvc.addAuthority(emp_id, menuList);
		}
		
		if("update".equals(action)){
			String emp_id = (String) req.getAttribute("emp_id");
			Set<String> menuList = (HashSet<String>) req.getAttribute("menuList");
			AuthorityService authSvc = new AuthorityService();
			authSvc.deleteEmpAuth(emp_id);
			authSvc.addAuthority(emp_id, menuList);
		}
		
		if("login".equals(action)){
			String emp_id = req.getParameter("empid");
			AuthorityService authSvc = new AuthorityService();
			Set<String> menuList = authSvc.getAuthsByEmpid(emp_id);
			req.setAttribute("menuList", menuList);
		}
		
		if("getMenuTree".equals(action)){
			String emp_id = req.getParameter("empid");
			AuthorityService authSvc = new AuthorityService();
//			JSONArray jsonArray = authSvc.getAuthsByEmpid(emp_id);
//			AuthorityDAO_interface authorityDAO = new AuthorityDAO();
			Set<String> menuList = authSvc.getAuthsByEmpid(emp_id);
			JSONObject empJsonMenu =  getJSONMenu(menuList);
			res.setContentType("application/json");
//			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			JSONArray jArray = new JSONArray();
//			System.out.println("empJsonMenu.toString() = "+empJsonMenu.toString());
			jArray.put( empJsonMenu );
			out.print(jArray);
			System.out.println("jArray = "+jArray);
		}
	}

	public JSONObject getJSONMenu(Set<String> checkedList){
//		String jsonStr = "[{\"id\":\"MENU001\",\"text\":\"後台權限\",\"checked\":true,\"children\":[{\"id\":\"MENU002\",	\"text\":\"車輛定位\",\"checked\":true,\"children\":[{\"id\":\"MENU003\",\"text\":\"車輛列表\",\"checked\":true}]},{	\"id\":\"MENU004\",	\"text\":\"會員管理\",\"checked\":true,\"children\":[{\"id\":\"MENU005\",\"text\":\"會員資料維護\",\"checked\":}]},{	\"id\":\"MENU006\",	\"text\":\"權限管理\",\"checked\":true,\"children\":[{\"id\":\"MENU007\",\"text\":\"員工權限設定\",\"checked\":}]},{	\"id\":\"MENU008\",	\"text\":\"基礎資料管理\",\"checked\":true,\"children\":[{\"id\":\"MENU009\",\"text\":\"運費管理\",\"checked\":,\"children\":[{	\"id\":\"MENU010\",	\"text\":\"運費設定\",\"checked\":},{	\"id\":\"MENU011\",	\"text\":\"估算出車成本\",\"checked\":}]},{\"id\":\"MENU012\",\"text\":\"排班管理\",\"checked\":},{\"id\":\"MENU013\",\"text\":\"車輛管理\",\"checked\":}]},{	\"id\":\"MENU014\",	\"text\":\"訂單管理\",\"checked\":true,\"children\":[{\"id\":\"MENU015\",\"text\":\"新增訂單\",\"checked\":},{\"id\":\"MENU016\",\"text\":\"查詢訂單\",\"checked\":},{\"id\":\"MENU017\",\"text\":\"訂單修改\",\"checked\":},{\"id\":\"MENU018\",\"text\":\"訂單自動分類\",\"checked\":},{\"id\":\"MENU019\",\"text\":\"訂單分配\",\"checked\":}]},{	\"id\":\"MENU020\",	\"text\":\"倉儲管理\",\"checked\":true,\"children\":[{\"id\":\"MENU021\",\"text\":\"入庫作業\",\"checked\":},{\"id\":\"MENU022\",\"text\":\"出庫作業\",\"checked\":}]},{\"id\":\"MENU023\",	\"text\":\"最新資訊\",\"checked\":true,\"children\":[{\"id\":\"MENU024\",\"text\":\"優惠活動維護\",\"checked\":},{\"id\":\"MENU025\",\"text\":\"即時公告維護\",\"checked\":},{\"id\":\"MENU026\",\"text\":\"內部公告\",\"checked\":},{\"id\":\"MENU027\",\"text\":\"內部公告維護\",\"checked\":}]},{	\"id\":\"MENU028\",	\"text\":\"客服系統\",\"checked\":true,\"children\":[{\"id\":\"MENU029\",\"text\":\"問與答維護\",\"checked\":},{\"id\":\"MENU030\",\"text\":\"回報單處理\",\"checked\":},{\"id\":\"MENU031\",\"text\":\"線上客服\",\"checked\":}]}]}]";
		String jsonStr = " {\"id\":\"MENU001\",\"text\":\"後台權限\",\"checked\":false,\"children\":[{\"id\":\"MENU002\",\"text\":\"車輛定位\",\"checked\":false,\"children\":[{\"id\":\"MENU003\",\"text\":\"車輛列表\",\"checked\":false}]},{\"id\":\"MENU004\",\"text\":\"會員管理\",\"checked\":false,\"children\":[{\"id\":\"MENU005\",\"text\":\"會員資料維護\",\"checked\":false}]},{\"id\":\"MENU006\",\"text\":\"權限管理\",\"checked\":false,\"children\":[{\"id\":\"MENU007\",\"text\":\"員工權限設定\",\"checked\":false}]},{\"id\":\"MENU008\",\"text\":\"基礎資料管理\",\"checked\":false,\"children\":[{\"id\":\"MENU009\",\"text\":\"運費管理\",\"checked\":false,\"children\":[{\"id\":\"MENU010\",\"text\":\"運費設定\",\"checked\":false},{\"id\":\"MENU011\",\"text\":\"估算出車成本\",\"checked\":false}]},{\"id\":\"MENU012\",\"text\":\"排班管理\",\"checked\":false},{\"id\":\"MENU013\",\"text\":\"車輛管理\",\"checked\":false}]},{\"id\":\"MENU014\",\"text\":\"訂單管理\",\"checked\":false,\"children\":[{\"id\":\"MENU015\",\"text\":\"新增訂單\",\"checked\":false},{\"id\":\"MENU016\",\"text\":\"查詢訂單\",\"checked\":false},{\"id\":\"MENU017\",\"text\":\"訂單修改\",\"checked\":false},{\"id\":\"MENU018\",\"text\":\"訂單自動分類\",\"checked\":false},{\"id\":\"MENU019\",\"text\":\"訂單分配\",\"checked\":false}]},{\"id\":\"MENU020\",\"text\":\"倉儲管理\",\"checked\":false,\"children\":[{\"id\":\"MENU021\",\"text\":\"入庫作業\",\"checked\":false},{\"id\":\"MENU022\",\"text\":\"出庫作業\",\"checked\":false}]},{\"id\":\"MENU023\",\"text\":\"最新資訊\",\"checked\":false,\"children\":[{\"id\":\"MENU024\",\"text\":\"優惠活動維護\",\"checked\":false},{\"id\":\"MENU025\",\"text\":\"即時公告維護\",\"checked\":false},{\"id\":\"MENU026\",\"text\":\"內部公告\",\"checked\":false},{\"id\":\"MENU027\",\"text\":\"內部公告維護\",\"checked\":false}]},{\"id\":\"MENU028\",\"text\":\"客服系統\",\"checked\":false,\"children\":[{\"id\":\"MENU029\",\"text\":\"問與答維護\",\"checked\":false},{\"id\":\"MENU030\",\"text\":\"回報單處理\",\"checked\":false},{\"id\":\"MENU031\",\"text\":\"線上客服\",\"checked\":false}]}]} ";
		JSONObject jsonObj = null;
		String str = null;
		try {
			jsonObj = new JSONObject(jsonStr);
			readJsonObject(jsonObj,checkedList);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
//		System.out.println("getJSONMenu return:"+jsonObj);
		return jsonObj;
	}
	
	public void readJsonObject(JSONObject jsonObj,Set<String> checkedList){
		try {
			String id = jsonObj.getString("id");
//			String text = jsonObj.getString("text");
			if(checkedList.contains(id)){
				jsonObj.put("checked", true);
			}
			
			if(jsonObj.has("children")){
				JSONArray jArray = jsonObj.getJSONArray("children");
				for(int i = 0; i < jArray.length(); i++){
					JSONObject childData = jArray.getJSONObject(i);
					readJsonObject(childData,checkedList);
				}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
