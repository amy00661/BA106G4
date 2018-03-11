package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

	private FilterConfig config;
	
    public LoginFilter() {
    }

    public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		Object member_mail = session.getAttribute("memVO");
		System.out.println("test");
		if(member_mail==null){
			System.out.println("test1");
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/frontend/logIn.jsp");
			return;
		}else{
			
			chain.doFilter(request, response);
		}
		
	}

	public void destroy() {
		config = null;
	}
}
