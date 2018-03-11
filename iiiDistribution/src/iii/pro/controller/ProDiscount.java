package iii.pro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import iii.pro.model.ProService;
import iii.pro.model.ProVO;



@WebServlet("/ProDiscount")
public class ProDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out= res.getWriter();
		List message = new ArrayList();
		
		long time = new Date().getTime();
		
		ProService proSvc = new ProService();
		List<ProVO> list = proSvc.getAll();
		
		for(ProVO pro:list){
//			if(time>pro.getPromotion_end().getTime()){
//				message.add("1");
				//				out.print(1);
//			}else{
				
				if(time > pro.getPromotion_start().getTime() &&
			       time < pro.getPromotion_end().getTime()){
					
//					out.print(pro.getPromotion_discount());
					message.add(pro.getPromotion_discount());
					message.add(pro.getPromotion_title());
					break;
//				}else{
//					out.print(1);
//					message.add("1");
				}
			}
//		}
		
		Gson gson = new Gson();
		String json = gson.toJson(message);
		out.println(json.toString());
		out.close();
	}

}
