package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthHendler
 */
public class AuthHandler extends ServletHandler {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handle(request, response);
	}

	private void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Auth handler");
		String login = request.getParameter("login");
		String pass = request.getParameter("password");
		String messageTitle;
		String messageText;
		String messageType;
		if ((login != null) && (pass != null)) {
			String role = users.Users.getUserRole(login, pass);
			//if (login.equals("user") && pass.equals("pass")) {
			if (role != null) {
				HttpSession session = request.getSession();
				session.setAttribute("name", login);
				session.setAttribute("role", role);
				messageTitle = "Congratulations !";
				messageText = "Authorization process succed";
				messageType = "success";			
			} else {
				request.setAttribute("message", "Wrong name or password");
				messageTitle = "Error occured !";
				messageText = "Wrong login or password";
				messageType = "error";
			}
		} else {
			messageTitle = "Internal error !";
			messageText = "";
			messageType = "info";
		}
		sendMessage(request, messageTitle, messageText, messageType);
		System.out.println("Servlet AuthHandler was runned");
		response.sendRedirect(request.getContextPath()+"/");
	}
}
