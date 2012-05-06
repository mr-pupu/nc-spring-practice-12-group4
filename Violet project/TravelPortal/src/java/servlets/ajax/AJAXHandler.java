/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.mapping.*;
import database.utilities.AdministratorDesktop;
import database.utilities.HibernateUtil;
import database.utilities.TrfEdit;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Merle
 */
@WebServlet(name = "AJAXHandler", urlPatterns = {"/AJAXHandler"})
abstract public class AJAXHandler extends HttpServlet {
    private static SimpleDateFormat formate = new SimpleDateFormat("dd-MMM-yy");
    
    public static String getEmployeeFullName(Employee employee){
        return employee.getFirstName() + ", " + employee.getSecondName();
    }
    
    public static String getDateFormated(Date date){
        return formate.format(date);
    }
    
    
    public static void putEmployeeToJSON(JSONObject jsonObject, Employee employee){
        jsonObject.put("employeeName", getEmployeeFullName(employee));
        jsonObject.put("employeeId", employee.getId());
    }
    
    public static void putOfficeToJSON(JSONObject jsonObject, Office office){
        City city = office.getCity();
        String officeName = city.getCountry().getCountryName() + ", " + city.getCityName();
        jsonObject.put("officeName", officeName);
        jsonObject.put("officeId", office.getId());
    }
   
    public static void putDatesToJson(JSONObject jsonObject, Date beginDate, Date endDate){
        jsonObject.put("beginDate", getDateFormated(beginDate));
        jsonObject.put("endDate", getDateFormated(beginDate));
    }
    
    public static void putCountriesToJson(JSONObject jsonObject){
        List<Country> countries = HibernateUtil.CountryList();
        Map<Long, String> countriesMap = new HashMap<Long, String>();
        for (Country country : countries) {
            countriesMap.put(country.getId(), country.getCountryName());
        }
        jsonObject.put("countries", countriesMap);
    }
    
    public static void putCitiesToJson(JSONObject jsonObject, Country country){
        Set<City> cities= country.getCities();
        Map<Long, String> citiesMap = new HashMap<Long, String>();
        for (City city : cities) {
            citiesMap.put(city.getId(), city.getCityName());
        }
        jsonObject.put("cities", citiesMap);
    }
    
    public static void putLineManagerByEmployeeLogin(JSONObject jsonObject, String login){
        List listWithLineManager = TrfEdit.LineManager(login);
        String lineMgrName = (String)listWithLineManager.get(0);
        jsonObject.put("lineManagerName", lineMgrName);
    }
    
    public static void putProjectManagerToJSON(JSONObject jsonObject, Employee manager){
        Map<Long, String> managersMap = new HashMap<Long, String>();
        Set<Employee> managers = manager.getDepartment().getEmployees();
        for (Employee employee : managers) {
            managersMap.put(employee.getId(), getEmployeeFullName(employee));
        }
        jsonObject.put("projectManagers", managersMap);
        jsonObject.put("projectManagerId", manager.getId());
    }
    
    public static void putCitiesAndCountriesToJSON(JSONObject jsonObject, City city){
        jsonObject.put("cityId", city.getId());
        jsonObject.put("countryId", city.getCountry().getId());
        putCountriesToJson(jsonObject);
        putCitiesToJson(jsonObject, city.getCountry());
    }
    
     public static void putChildDepartments(JSONObject json, Long id, int level) {
        List<Department> deps = AdministratorDesktop.ChildDeps(id);
        JSONArray jsarr = new JSONArray();
        for (Department dep : deps) {
            JSONObject js = new JSONObject();
            js.put("id", dep.getId());
            js.put("name", dep.getDepName());
            if (!dep.getDepartments().isEmpty()) {
                js.put("expanded", true);
                js.put("isLeaf", false);
            } else {
                js.put("expanded", false);
                js.put("isLeaf", true);
            }
            js.put("parent", id.longValue());
            js.put("loadonce", true);
            js.put("level", (level+1));
            jsarr.add(js);
        }
        json.put("rows", jsarr);
    }

    public static void putHeadDepartments(JSONObject jsonObject) {
        List<Department> deps = AdministratorDesktop.HeadDepartments();
//        Map<Long, String> depmap = new HashMap<Long, String>();
        JSONArray jsarr = new JSONArray();
        for (Department dep : deps) {
            JSONObject js = new JSONObject();
//            epmap.put(dep.getId(), dep.getDepName());
            js.put("id", dep.getId());
            js.put("name", dep.getDepName());
            if (!dep.getDepartments().isEmpty()) {
                js.put("expanded", true);
                js.put("isLeaf", false);
            } else {
                js.put("expanded", false);
                js.put("isLeaf", true);
            }
            js.put("parent", null);
            js.put("loadonce", true);
            js.put("level", 0);
            jsarr.add(js);
        }
        jsonObject.put("rows", jsarr);

//        jsonObject.put("deps", depmap);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{};
    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{};

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
