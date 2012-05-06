package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class QuitHandler
 */
public class QuitHandler extends ServletHandler {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuitHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handle(request, response);
	}
	
	private void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String messageTitle = "Congratulations !";		
		String messageText = "You just left all your previlegies behind !";
		String messageType = "info";
		HttpSession session = request.getSession();
		String bundle = (String) session.getAttribute("bundle");
		request.getSession().invalidate();
		request.getSession().setAttribute("bundle", bundle);
		sendMessage(request, messageTitle, messageText, messageType);
		System.out.println("Servlet QuitHandler was runned");
		response.sendRedirect(request.getContextPath()+"/");
	}
}
