package iii.news.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class ShowNewsPic extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			req.setCharacterEncoding("UTF-8");
			String news_id=new String(req.getParameter("news_id").getBytes("ISO-8859-1"),"UTF-8");
			Statement stmt = con.createStatement();
//			String news_id="NEWS003";
			ResultSet rs = stmt.executeQuery
				("SELECT NEWS_PICTURE FROM NEWS WHERE NEWS_ID = '"+news_id+"'");
			
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("NEWS_PICTURE"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in=getServletContext().getResourceAsStream("/backend/img/no.png");
				byte[] buf=new byte[in.available()];
				in.read(buf);
				out.write(buf);
				out.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			InputStream in=getServletContext().getResourceAsStream("/backend/img/null.png");
			byte[] buf=new byte[in.available()];
			in.read(buf);
			out.write(buf);
			out.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GROUP4DB");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
