/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.mapping.Country;
import database.mapping.Employee;
import database.utilities.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Allan
 */
public class AJAXTravelEmployeeHandler extends AJAXSendHandler {

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

        System.out.println("AJAXTravelEmployeeHandler runned");
        String idEmployee = request.getParameter("id");
        JSONObject jsonObject = new JSONObject();
        if (idEmployee != null) {
            try {
                Long id = Long.parseLong(idEmployee);
                Employee emp = (Employee) HibernateUtil.getSession().get(Employee.class, id);
                if (emp != null) {
                    putOfficebyCityCountryToJSON(jsonObject, emp.getOffice());
                    putLineManagerByEmployeeLogin(jsonObject, emp.getLogin());
                } else {
                    System.out.println("City = null");
                }
            } catch (NumberFormatException e) {
                System.out.print("Wrong id format");
            }
        }
        jsonObject.writeJSONString(response.getWriter());
    }
}
