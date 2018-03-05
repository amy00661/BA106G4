package iii.authority.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;

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
			//JSONObject empJsonMenu =  getJSONMenu(menuList);
			res.setContentType("application/json");
//			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			JSONArray jArray = getJSONMenu(menuList);
//			System.out.println("empJsonMenu.toString() = "+empJsonMenu.toString());
			//jArray.put( jArray );
			out.print(jArray);
			System.out.println("jArray = "+jArray);
		}
	}

	public JSONArray getJSONMenu(Set<String> checkedList){
		JSONArray jsonArray = null;
		String jsonArraystr = null;
		try {
			JsonStreamParser parser = new JsonStreamParser(new InputStreamReader(getServletContext().getResourceAsStream("/backend/menu/menu.json"), "UTF-8"));
			System.out.println(parser.toString());
			while (parser.hasNext()) {
				JsonElement element = parser.next();
				//System.out.println(element.toString());
				jsonArraystr = element.toString();
			}
			jsonArray = new JSONArray(jsonArraystr);
//			jsonObj = new JSONObject(jsonStr);
			readJsonObject(jsonArray.getJSONObject(0),checkedList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		System.out.println("getJSONMenu return:"+jsonObj);
		return jsonArray;
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
