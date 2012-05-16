/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin_servlets;

import database.mapping.Department;
import database.mapping.Employee;
import database.mapping.Occupation;
import database.mapping.Office;
import database.utilities.HibernateUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import servlets.ajax.AJAXGetHandler;
import utils.PasswordGenerator;

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
        Long departmentId;
        String email;
        String login;
        boolean password;

        try {
            firstName = resultStrings.get("firstName");
            lastName = resultStrings.get("lastName");
            positionId = Long.parseLong(resultStrings.get("positionId"));
            officeId = Long.parseLong(resultStrings.get("officeId"));
            departmentId = Long.parseLong(resultStrings.get("departmentId"));
            email = resultStrings.get("email");
            login = resultStrings.get("login");
            password = Boolean.parseBoolean(resultStrings.get("password"));

            Employee currEmployee = (Employee) request.getSession().getAttribute("employee");
            Session hibernateSession = (Session) request.getSession().getAttribute("hibernateSession");
            
            String answer;
            if(currEmployee.getFirstName()==null){
                answer = firstName + " "+ lastName + " was added to database";
            }
            else{
                answer = firstName + " "+ lastName + " was successfully edited";
            }

            currEmployee.setFirstName(firstName);
            currEmployee.setSecondName(lastName);
            currEmployee.setOccupation((Occupation) hibernateSession.get(Occupation.class, (Long) positionId));
            currEmployee.setOffice((Office) hibernateSession.get(Office.class, (Long) officeId));
            currEmployee.setDepartment((Department) hibernateSession.get(Department.class, (Long) departmentId));
            currEmployee.setEmail(email);
            currEmployee.setLogin(login);
            if (password) {
                String newPassword = PasswordGenerator.createPassword(10);
                currEmployee.setPassword(newPassword);
                utils.MailSender.sendPassword(login, email, newPassword);
            }

            HibernateUtil.save(currEmployee);

            response.setContentType("application/json");
            JSONObject js = new JSONObject();
            js.put("error", "success");
            js.put("success", answer);
            js.writeJSONString(response.getWriter());
            
        } catch (Exception e) {
            response.setContentType("application/json");
            String answer = "Server problem, changes could not be done";
            JSONObject js = new JSONObject();
            js.put("error", "error");
            js.put("success", answer);
            js.writeJSONString(response.getWriter());
        }
    }
}