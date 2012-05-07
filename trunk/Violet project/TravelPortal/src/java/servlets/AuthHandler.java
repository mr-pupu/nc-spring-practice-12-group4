package servlets;

import database.utilities.HibernateUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthHendler
 */
public class AuthHandler extends ServletHandler {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        handle(request, response);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("Servlet AuthHandler was runned");
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String messageTitle;
        String messageText;
        String messageType;
        if ((login != null) && (pass != null)) {
//			String deprole = users.Users.getUserDeprole(login, pass);
            List deproles = HibernateUtil.DepDeproleByLogin(login, pass);
            //if (login.equals("user") && pass.equals("pass")) {
            if (!deproles.isEmpty()) {
                HttpSession session = request.getSession();
                session.setAttribute("name", login);

                session.setAttribute("deprole", deproles);

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
        response.sendRedirect(request.getContextPath() + "/mytrfs");
    }
}
