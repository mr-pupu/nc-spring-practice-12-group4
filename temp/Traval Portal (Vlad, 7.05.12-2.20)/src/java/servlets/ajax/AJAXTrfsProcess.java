/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.mapping.City;
import database.mapping.Customer;
import database.mapping.Destination;
import database.mapping.Employee;
import database.mapping.Trf;
import database.utilities.HibernateUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Merle
 */
public class AJAXTrfsProcess extends AJAXGetHandler {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            throws ServletException, IOException {
        System.out.println("Servlet AJAXTrfsProcess runned (GET)");
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet AJAXTrfsProcess runned (POST)");
        String ajaxdata = request.getParameter("ajaxdata");
        System.out.println(ajaxdata);
        Object obj=JSONValue.parse(ajaxdata);
        JSONArray array=(JSONArray)obj;
        Map<String,String> resultStrings = new HashMap<String, String>();
        for (Object object : array) {
            JSONObject someObj = (JSONObject)object;
            resultStrings.putAll(someObj);
        }
        
        Short state;
        Long destinationId;
        Long customerId;
        Long projectManagerId;
        Date endDate;
        Date beginDate;
        boolean car;
        boolean payByCash;
        
        try {
            state = Short.parseShort(resultStrings.get("state"));
            car = Boolean.parseBoolean(resultStrings.get("car"));
            payByCash = Boolean.parseBoolean(resultStrings.get("payByCash"));
            destinationId = Long.parseLong(resultStrings.get("destinationId"));
            customerId = Long.parseLong(resultStrings.get("customerId"));
            projectManagerId = Long.parseLong(resultStrings.get("projectManagerId"));
            endDate = getDateFromString(resultStrings.get("endDate"));
            beginDate = getDateFromString(resultStrings.get("beginDate"));
            
            Trf currTrf = (Trf)request.getSession().getAttribute("trf");
            Session hibernateSession = (Session)request.getSession().getAttribute("hibernateSession");

            currTrf.setBeginDate(beginDate);
            currTrf.setEndDate(endDate);
            currTrf.setPayByCash(payByCash);
            currTrf.setCarRental(car);
            currTrf.setCustomer((Customer)hibernateSession.get(Customer.class, (Long) customerId));
            currTrf.setEmployeeByProjectManager((Employee)hibernateSession.get(Employee.class, (Long) projectManagerId));
            currTrf.setDestination((Destination)hibernateSession.get(Destination.class, (Long) destinationId));
            currTrf.setCurState(state);
            
            HibernateUtil.getSession().beginTransaction();
            HibernateUtil.getSession().save(currTrf);
            HibernateUtil.getSession().getTransaction().commit();

            System.out.println("changes done");
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        
        //ToDo check values
        
        //ToDo commit changes
    }
}
