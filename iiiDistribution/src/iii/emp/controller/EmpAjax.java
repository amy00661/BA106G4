package iii.emp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import iii.emp.model.EmpService;
import iii.emp.model.EmpVO;

@WebServlet("/employee/EmpAjax.do")
public class EmpAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String jsonStr = "";
        if(br != null){
        	jsonStr = br.readLine();
        }
        Gson gson = new Gson();
        JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(jsonStr);
			String action = jsonObj.getString("action");
			if ("getAll".equals(action)) {
				/***************************開始查詢資料 ****************************************/
				EmpService empSvc = new EmpService();
				List<EmpVO> empList = empSvc.getAllEmps();
				// 4. Set response type to JSON
		        response.setContentType("application/json;charset=UTF-8");
		        response.setHeader("Cache-Control", "no-cache"); 
		        //get the PrintWriter object to write the html page
		        PrintWriter out = response.getWriter();
				String empJson = gson.toJson(empList);
				System.out.println(empJson);
				out.print(empJson);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
        
        
	}

}
