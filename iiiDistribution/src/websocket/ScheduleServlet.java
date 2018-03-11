package websocket;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import java.util.*;

public class ScheduleServlet extends HttpServlet {
	 Timer timer ; 
	    int count = 0;        
	    public void destroy(){
	        timer.cancel();
	    }
	    
	    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	    }
	            
	    public void init(){        
	        timer = new Timer();
	        TimerTask task = new TimerTask(){
	                   
	            public void run(){
	              
	            	
	            }
	        };
	        timer.scheduleAtFixedRate(task, new Date(), 60*1000); 
	    }
}