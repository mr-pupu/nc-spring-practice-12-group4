/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.mapping.City;
import database.mapping.Country;
import database.mapping.Customer;
import database.mapping.Employee;
import database.mapping.Office;
import database.utilities.HibernateUtil;
import database.utilities.TrfEdit;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Merle
 */
abstract public class AJAXSendHandler extends AJAXHandler {
    
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
    
    public static void putCustomersToJSON(JSONObject jsonObject, Customer customer){
        List<Customer> customers = TrfEdit.Customers();
        Map<Long, String> customersMap = new HashMap<Long, String>();
        for (Customer eachCustomer : customers) {
            customersMap.put(eachCustomer.getId(), eachCustomer.getCustName());
        }
        jsonObject.put("customers", customersMap );
        jsonObject.put("customerId", customer.getId());
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
