package iii.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import iii.mem.model.*;



@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
				doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action =req.getParameter("action");

		PrintWriter out= res.getWriter();
		
		if("login".equals(action)){
			
			HttpSession session = null;
				session =req.getSession();
				boolean flag=false;
					
				List<String> errorMsgs = new LinkedList<String>();
					
				req.setAttribute("errorMsgs",errorMsgs);
				
			try{
				String member_mail=req.getParameter("member_mail");
				String member_psw=req.getParameter("member_psw");
					
				MemService memSvc = new MemService();
				MemVO memVO= new MemVO();
				memVO = memSvc.getOneByMemMail(member_mail);
					
				if(!member_psw.equals(memVO.getMember_psw())){
					errorMsgs.add("密碼錯誤");
				}
				List<MemVO> list = memSvc.getAll();
				for(MemVO member : list){
					if(member.getMember_mail().equals(member_mail)){
						
						flag=true;
						break;
					}
				}
				if(flag==false){
					errorMsgs.add("無此信箱");
				}
				
				if(!errorMsgs.isEmpty()){
//					req.setAttribute("memVO",memVO);
					RequestDispatcher failureView=
						req.getRequestDispatcher("/frontend/index.jsp");
					failureView.forward(req, res);
					return;
				}
				else{
					
					session.setAttribute("memVO", memVO);
					
					try{
						String location = (String) session.getAttribute("location");
						if(location != null){
							session.removeAttribute("location");
							res.sendRedirect(location);
							return;
						}
					}catch(Exception e){}
//					RequestDispatcher successView=
//							req.getRequestDispatcher("/frontend/index.jsp");
//					successView.forward(req, res);
					res.sendRedirect(req.getContextPath()+"/frontend/index.jsp");
				}
			}catch(Exception e){
				errorMsgs.add("無此信箱");
				
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
						
			 }
			}
		if("checkMail".equals(action)){

			String member_mail=req.getParameter("mail");
			
			
			MemService memSvc = new MemService();
			List<MemVO> list = memSvc.getAll();
			
			
			
			for(MemVO mem : list){
				if(mem.getMember_mail().trim().equals(member_mail)){
					out.print("no");
					out.close();
					return;
				}
			}
			out.print("ok");
		}
		
		if("checkMail2".equals(action)){
			
			boolean flag=false;
			String member_mail=req.getParameter("mail");
			MemService memSvc = new MemService();
			List<MemVO> list = memSvc.getAll();
			for(MemVO member : list){
				if(member_mail.equals(member.getMember_mail())){
					flag=true;
					break;
				}
			}
			
			if(flag==false)
				out.print("無此信箱");
			
		}
		
		if("checkPsw".equals(action)){
			
			String member_mail=req.getParameter("mail");
			String member_psw=req.getParameter("psw");
			MemService memSvc = new MemService();
			MemVO memVO = new MemVO();
			memVO = memSvc.getOneByMemMail(member_mail);

			
			if(!member_psw.equals(memVO.getMember_psw()))
				out.print("密碼有誤");
			
			
			
		}
	}

}
