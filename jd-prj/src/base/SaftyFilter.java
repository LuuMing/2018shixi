package base;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/*")
public class SaftyFilter implements Filter {

   

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		//获取请求地址，形式如：/loginDo
		String path = req.getServletPath();
		
		//放过无需验证的地址
		if("/login.html".equals(path) || 
				"/logIn".equals(path) ||
				path.startsWith("/resources")) {
			
			chain.doFilter(request, response);//放行通过
			
			return;
			
		}
		
		//对需要验证的地址进行处理
		HttpSession session = req.getSession();
		if(session.getAttribute("loginUserId")!=null) {
			chain.doFilter(request, response);//放行通过
			return;
		}
		
		
		//未登录打回登录页!
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.sendRedirect(req.getContextPath()+"/login.html");//向登录页跳转
		
		
		// pass the request along the filter chain
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
