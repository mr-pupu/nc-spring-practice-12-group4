package filters;

import database.mapping.Deprole;
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

/**
 * Servlet Filter implementation class AdministratorAccess
 */
public class AdministratorAccess implements Filter {

    /**
     * Default constructor. 
     */
    public AdministratorAccess() {
    }
	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpServletRequest requestHttp = (HttpServletRequest)request;
		List<String> deprole = (List<String>) requestHttp.getSession().getAttribute("deprole");
//		if ((deprole!= null ) && deprole.equals("Administrator")) {
                if ((deprole!= null ) && deprole.contains("IT Department")) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect(requestHttp.getContextPath()+"/");
		}
		// pass the request along the filter chain
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
