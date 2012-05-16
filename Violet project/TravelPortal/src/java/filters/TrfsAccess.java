package filters;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class TrfsAccess
 */
public class TrfsAccess implements Filter {

    /**
     * Default constructor. 
     */
    public TrfsAccess() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpServletRequest requestHttp = (HttpServletRequest)request;
		List<String> deprole = (List<String>) requestHttp.getSession().getAttribute("deprole");
		if ((deprole!= null ) && deprole.contains("Travel Department")) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect(requestHttp.getContextPath()+"/");
		}	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
