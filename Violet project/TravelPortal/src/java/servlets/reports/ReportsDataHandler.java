package servlets.reports;
import database.utilities.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.json.simple.JSONObject;
import servlets.ajax.AJAXSendHandler;

/**
 *
 * @author OleksandrDudinskyi
 */
@WebServlet(name = "ReportsDataHandler", urlPatterns = {"/reportsdatahandler"})
public class ReportsDataHandler extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        try {
            Session hibernateSession = HibernateUtil.getSession();
            request.getSession().setAttribute("hibernateSession", hibernateSession);
            AJAXSendHandler.putAllDepartmentToJSON(jsonObject);
            AJAXSendHandler.putOfficesToJSON(jsonObject);
            jsonObject.writeJSONString(response.getWriter());
        } finally {            
            out.close();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    processRequest(request, response);
}
    public ReportsDataHandler() {
        super();
    }
}
