package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.LocalizationsBean;

/**
 * Servlet Filter implementation class LocaleCheck
 */
public class LocaleCheck implements Filter {

    /**
     * Default constructor. 
     */
    public LocaleCheck() {
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
		System.out.println("Filter LocalCheck was entered");
		HttpServletRequest requestHttp = (HttpServletRequest)request;
		HttpSession session = requestHttp.getSession();
		//java.util.ResourceBundle locale = (java.util.ResourceBundle)session.getAttribute("resourceBoundle");
		if (session.getAttribute("bundle") == null) {
			session.setAttribute("bundle", "en_EN");
		}
		String bundle=(String) request.getParameter("locale");		
		if (bundle != null & LocalizationsBean.checkBundle(bundle)){
			session.setAttribute("bundle", bundle);
		}
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
