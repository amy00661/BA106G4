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
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import iii.mem.model.*;

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
		
		if("login".equals(action)){    //首頁登入頁面進入此驗證
			
			HttpSession session = null;
				session =req.getSession();
				boolean flag=false;
				String flagPass=new String("yes");
				String flagNoMail=new String("yes");
				String flagStopMail=new String("yes");
				List<String> errorMsgs = new LinkedList<String>();
					
				req.setAttribute("errorMsgs",errorMsgs);
				
			try{
				String member_mail=req.getParameter("member_mail");
				String member_psw=req.getParameter("member_psw");
					
				MemService memSvc = new MemService();
				MemVO memVO= new MemVO();
				memVO = memSvc.getOneByMemMail(member_mail);
					
				if(!member_psw.equals(memVO.getMember_psw())){
					flagPass="no";
					errorMsgs.add("密碼錯誤");
				}
				List<MemVO> list = memSvc.getAll();
				for(MemVO member : list){
					if(member.getMember_mail().equals(member_mail)){
						
						flag=true;
						break;
					}
				}
				
				if("00".equals(memVO.getMember_status())){
					flagStopMail="no";
					errorMsgs.add("信箱停權");
				}
				
				
				if(!errorMsgs.isEmpty()){
					req.setAttribute("flagPass",flagPass);
					req.setAttribute("flagStopMail",flagStopMail);
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
					res.sendRedirect(req.getContextPath()+"/frontend/index.jsp");
				}
			}catch(Exception e){
				errorMsgs.add("無此信箱");
				
				flagNoMail="no";
				req.setAttribute("flagNoMail",flagNoMail);
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
						
			 }
			}
		
if("loginMain".equals(action)){ //動畫登入頁面進入此驗證 
			
			HttpSession session = null;
				session =req.getSession();
				String flag=new String("yes");
				String flagNoMail=new String("yes");
					
				List<String> errorMsgs = new LinkedList<String>();
					
				req.setAttribute("errorMsgs",errorMsgs);
				
			try{
				String member_mail=req.getParameter("member_mail");
				String member_psw=req.getParameter("member_psw");
					
				MemService memSvc = new MemService();
				MemVO memVO= memSvc.getOneByMemMail(member_mail);
				
					
				if(!member_psw.equals(memVO.getMember_psw())){
					errorMsgs.add("密碼錯誤");
				}
				
				memVO.setMember_mail(member_mail);
				
				if(!errorMsgs.isEmpty()){
						flag="no";
						req.setAttribute("flag",flag);
						req.setAttribute("memVO",memVO);
						RequestDispatcher failureView=
							req.getRequestDispatcher("/frontend/logIn.jsp");
						failureView.forward(req, res);
						return;
					
				}
					session.setAttribute("memVO", memVO);
					try{
						String location = (String) session.getAttribute("location");
						if(location != null){
							session.removeAttribute("location");
							res.sendRedirect(location);
							return;
						}
					}catch(Exception e){}
					res.sendRedirect(req.getContextPath()+"/frontend/index.jsp");
				
			}catch(Exception e){
				errorMsgs.add("無此帳號");
				flagNoMail="no";
				req.setAttribute("flagNoMail",flagNoMail);
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/frontend/logIn.jsp");
				failureView.forward(req, res);
			 }
			}
		
		if("checkMail".equals(action)){               //ajax index 信箱及時判斷

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
		
		if("checkMail2".equals(action)){            //ajax login 信箱及時判斷
			
			boolean flag=false;
			String member_mail=req.getParameter("mail");
			MemService memSvc = new MemService();
			
			MemVO memVO = memSvc.getOneByMemMail(member_mail);
			List<MemVO> list = memSvc.getAll();
			for(MemVO member : list){
				if(member_mail.equals(member.getMember_mail())){
					flag=true;
					break;
				}
			}
			
			if(flag==false)
				out.print("no");
			
			else if("00".equals(memVO.getMember_status())){
				out.print("stop");
			}
			
		}
		
		if("foundPsw".equals(action)){
			
			String member_mail=req.getParameter("member_mail");
			String member_identification=req.getParameter("member_identification");
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneByMemMail(member_mail);
			
			String membeer_psw = memVO.getMember_psw();
			String membeer_name = memVO.getMember_name();
			
			String to = "az654884az@gmail.com";
		      
		    String subject = "密碼通知";
		      
		    String ch_name = membeer_name;
		    String passRandom = membeer_psw;
		    String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)";
			
			if(memVO.getMember_identification().equals(member_identification)){
				 try {
					   // 設定使用SSL連線至 Gmail smtp Server
					   Properties props = new Properties();
					   props.put("mail.smtp.host", "smtp.gmail.com");
					   props.put("mail.smtp.socketFactory.port", "465");
					   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
					   props.put("mail.smtp.auth", "true");
					   props.put("mail.smtp.port", "465");

			       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			       // ●須將myGmail的【安全性較低的應用程式存取權】打開
				     final String myGmail = "ixlogic.wu@gmail.com";
				     final String myGmail_password = "BBB45678";
					   Session session = Session.getInstance(props, new Authenticator() {
						   protected PasswordAuthentication getPasswordAuthentication() {
							   return new PasswordAuthentication(myGmail, myGmail_password);
						   }
					   });

					   Message message = new MimeMessage(session);
					   message.setFrom(new InternetAddress(myGmail));
					   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
					  
					   //設定信中的主旨  
					   message.setSubject(subject);
					   //設定信中的內容 
					   message.setText(messageText);

					   Transport.send(message);
					   System.out.println("傳送成功!");
					   res.setHeader("refresh","1;URL="+req.getContextPath()+"/frontend/logIn.jsp" );
					   
			     }catch (MessagingException e){
				     System.out.println("傳送失敗!");
				     e.printStackTrace();
			     }
			   }
				
			}
		
	}
}
