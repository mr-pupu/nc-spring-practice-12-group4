/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin_servlets;

import database.mapping.Department;
import database.mapping.Deprole;
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
public class AJAXDepartmentRolesProcess extends AJAXGetHandler {

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
        System.out.println("Servlet AJAXDepartmentRolesProcess runned (GET)");
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
        System.out.println("Servlet AJAXDepartmentRolesProcess runned (POST)");
        String ajaxdata = request.getParameter("ajaxdata");
        System.out.println(ajaxdata);
        Object obj = JSONValue.parse(ajaxdata);
        JSONArray array = (JSONArray) obj;
        Map<String, String> resultStrings = new HashMap<String, String>();
        for (Object object : array) {
            JSONObject someObj = (JSONObject) object;
            resultStrings.putAll(someObj);
        }
        
        Long depId;
        Long roleId;
        boolean checked;
        
        try {
            depId = Long.parseLong(resultStrings.get("depId"));
            
            roleId = Long.parseLong(resultStrings.get("roleId"));
            checked = Boolean.parseBoolean(resultStrings.get("checked"));
            
            Department currDepartment = (Department) HibernateUtil.getSession().get(Department.class, depId);
             Deprole role = (Deprole) HibernateUtil.getSession().get(Deprole.class, roleId);
            
            if (checked) {
                currDepartment.getDeprole().add(role);
            } else {
                currDepartment.getDeprole().remove(role);
            }

            HibernateUtil.save(currDepartment);
            
            System.out.println("changes done");
        } catch (Exception e) {
            e.printStackTrace();
        }


        //ToDo check values

        //ToDo commit changes
    }
}
