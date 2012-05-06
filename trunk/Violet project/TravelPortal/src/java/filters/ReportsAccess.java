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

/**
 * Servlet Filter implementation class ReportsAccess
 */
public class ReportsAccess implements Filter {

    /**
     * Default constructor. 
     */
    public ReportsAccess() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("Filter ReportsAccess was entered");
		HttpServletRequest requestHttp = (HttpServletRequest)request;
		List<String> deprole = (List<String>) requestHttp.getSession().getAttribute("deprole");
		if ((deprole!= null ) && (deprole.contains("Common Department") 
                        || deprole.contains("Travel Department") || deprole.contains("IT Department"))) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect(requestHttp.getContextPath()+"/");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
