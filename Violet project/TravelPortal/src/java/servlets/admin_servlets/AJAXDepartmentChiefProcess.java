/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin_servlets;

import database.mapping.Department;
import database.mapping.Employee;
import database.utilities.HibernateUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import servlets.ajax.AJAXGetHandler;

/**
 *
 * @author Allan
 */
public class AJAXDepartmentChiefProcess extends AJAXGetHandler {

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
        System.out.println("Servlet AJAXDepartmentChiefProcess runned (GET)");
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
        System.out.println("Servlet AJAXDepartmentChiefProcess runned (POST)");
        String ajaxdata = request.getParameter("ajaxdata");
        System.out.println(ajaxdata);
        Object obj = JSONValue.parse(ajaxdata);
        JSONArray array = (JSONArray) obj;
        Map<String, String> resultStrings = new HashMap<String, String>();
        for (Object object : array) {
            JSONObject someObj = (JSONObject) object;
            resultStrings.putAll(someObj);
        }

        Long chiefId;

        try {
            chiefId = Long.parseLong(resultStrings.get("chiefId"));
            
            Long id = Long.parseLong(String.valueOf(request.getSession().getAttribute("depId")));


            Department currDepartment = (Department) HibernateUtil.getSession()
                    .get(Department.class, id.longValue());

            currDepartment.setManagerId(chiefId);

            HibernateUtil.save(currDepartment);
            
            Employee emp = (Employee) HibernateUtil.getSession().get(Employee.class, chiefId);
            String answer= emp.getFirstName()+" "+emp.getSecondName()+
                    " is now the head of department";

            System.out.println("changes done");
            response.setContentType("application/json");
            JSONObject js = new JSONObject();
            js.put("error", "success");
            js.put("success", answer);
            System.out.println(js);
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