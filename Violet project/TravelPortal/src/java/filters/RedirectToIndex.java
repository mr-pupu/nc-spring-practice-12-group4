package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * Servlet Filter implementation class RedirectToIndex
 */
public class RedirectToIndex implements Filter {

    /**
     * Default constructor. 
     */
    public RedirectToIndex() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request, response);
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
