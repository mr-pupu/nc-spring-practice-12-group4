/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin_servlets;

import database.mapping.Employee;
import database.utilities.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.json.simple.JSONObject;
import servlets.ajax.AJAXSendHandler;

/**
 *
 * @author Allan (base by Vlad)
 */
public class AJAXEmployeeEditHandler extends AJAXSendHandler {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public AJAXEmployeeEditHandler() {
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
        handle(request, response);
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
        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

        System.out.println("AJAXEmployeeEditHandler runned");
        String idString = request.getParameter("id");
        JSONObject jsonObject = new JSONObject();
        if (idString != null) {
            try {
                Long id = Long.parseLong(idString);
                Session hibernateSession = HibernateUtil.getSession();
                Employee emp;
                if (id > 0) {
                    emp = (Employee) hibernateSession.get(Employee.class, (Long) id);

                    request.getSession().setAttribute("hibernateSession", hibernateSession);
                    request.getSession().setAttribute("employee", emp);

                    jsonObject.put("employeeId", emp.getId());
                    jsonObject.put("firstName", emp.getFirstName());
                    jsonObject.put("lastName", emp.getSecondName());

                    putPositionsToJSON(jsonObject);
                    jsonObject.put("positionId", emp.getOccupation().getId());
                    putOfficesToJSON(jsonObject);
                    jsonObject.put("officeId", emp.getOffice().getId());

                    putDepartmentsToJSON(jsonObject);
                    jsonObject.put("departmentId", emp.getDepartment().getId());

                    jsonObject.put("email", emp.getEmail());
                    jsonObject.put("login", emp.getLogin());

                    response.setContentType("application/json");
                    jsonObject.writeJSONString(response.getWriter());
                } else {
                    emp = new Employee();

                    request.getSession().setAttribute("hibernateSession", hibernateSession);
                    request.getSession().setAttribute("employee", emp);
                    putPositionsToJSON(jsonObject);
                    putOfficesToJSON(jsonObject);
                    putDepartmentsToJSON(jsonObject);
                    response.setContentType("application/json");
                    jsonObject.writeJSONString(response.getWriter());
                }
            } catch (NumberFormatException e) {
                response.setContentType("application/json");
                String answer = "Server problem occured";
                JSONObject js = new JSONObject();
                js.put("error", "error");
                js.put("success", answer);
                js.writeJSONString(response.getWriter());
            }
        }
    }
}
