/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin_servlets;

import database.mapping.Employee;
import database.mapping.Occupation;
import database.mapping.Office;
import database.utilities.HibernateUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import servlets.ajax.AJAXGetHandler;

/**
 *
 * @author Allan
 */
public class AJAXEmployeeProcess extends AJAXGetHandler {

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
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet AJAXEmployeeProcess runned (GET)");
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet AJAXEmployeeProcess runned (POST)");
        String ajaxdata = request.getParameter("ajaxdata");
        System.out.println(ajaxdata);
        Object obj = JSONValue.parse(ajaxdata);
        JSONArray array = (JSONArray) obj;
        Map<String, String> resultStrings = new HashMap<String, String>();
        for (Object object : array) {
            JSONObject someObj = (JSONObject) object;
            resultStrings.putAll(someObj);
        }

        String firstName;
        String lastName;
        Long positionId;
        Long officeId;
        String email;
        String login;

        try {
            firstName = resultStrings.get("firstName");
            lastName = resultStrings.get("lastName");
            positionId = Long.parseLong(resultStrings.get("positionId"));
            officeId = Long.parseLong(resultStrings.get("officeId"));
            email = resultStrings.get("email");
            login = resultStrings.get("login");

            Employee currEmployee = (Employee) request.getSession().getAttribute("employee");
            Session hibernateSession = (Session) request.getSession().getAttribute("hibernateSession");

            currEmployee.setFirstName(firstName);
            currEmployee.setSecondName(lastName);
            currEmployee.setOccupation((Occupation) hibernateSession.get(Occupation.class, (Long) positionId));
            currEmployee.setOffice((Office) hibernateSession.get(Office.class, (Long) officeId));
            currEmployee.setEmail(email);
            currEmployee.setLogin(login);

            HibernateUtil.getSession().beginTransaction();
            HibernateUtil.getSession().save(currEmployee);
            HibernateUtil.getSession().getTransaction().commit();

            System.out.println("changes done");
        } catch (Exception e) {
            e.printStackTrace();
        }


        //ToDo check values

        //ToDo commit changes
    }
}