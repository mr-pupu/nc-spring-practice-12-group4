/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin_servlets;

import database.mapping.Department;
import database.utilities.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import servlets.ajax.AJAXSendHandler;

/**
 *
 * @author Allan
 */
public class AJAXDepartmentChiefHandler extends AJAXSendHandler {

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
    public AJAXDepartmentChiefHandler() {
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

        System.out.println("AJAXDepartmentChiefHandler runned");
        String idString = request.getParameter("id");

        JSONObject jsonObject = new JSONObject();
        if (idString != null) {
            try {
                Long id = Long.parseLong(idString);
                System.out.println("ID: " + String.valueOf(id));

                Department dep = (Department) HibernateUtil.getSession().get(Department.class, (Long) id);

                request.getSession().setAttribute("depId", id);

                putDepEmployersToJSON(jsonObject, dep);
                jsonObject.put("depemployeeId", dep.getManagerId());

                response.setContentType("application/json");
                jsonObject.writeJSONString(response.getWriter());
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