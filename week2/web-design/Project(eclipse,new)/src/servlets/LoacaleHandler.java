package servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LocalizationsBean;

import localizations.TextLocalization;

/**
 * Servlet implementation class LoacaleHandler
 */
public class LoacaleHandler extends ServletHandler {
	private static final long serialVersionUID = 1L;
	private static Set<String> boundles = new HashSet<String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoacaleHandler() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//handle(request, response);
		handle(request, response);
	}
	
	private void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		System.out.println("Servlet LocaleHandler was runned");
		response.sendRedirect(request.getContextPath()+"/");
	}
}
