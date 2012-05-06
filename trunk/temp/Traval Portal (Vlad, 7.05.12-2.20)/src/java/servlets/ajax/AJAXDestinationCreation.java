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
public class AJAXDestinationCreation extends AJAXGetHandler {

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
        
        String hotelName;
        String hotelSite;
        Long cityId;
        
        try {
            hotelName = resultStrings.get("hotelName");
            hotelSite = resultStrings.get("hotelSite");
            cityId = Long.parseLong(resultStrings.get("cityId"));
            
            Trf currTrf = (Trf)request.getSession().getAttribute("trf");
            Session hibernateSession = (Session)request.getSession().getAttribute("hibernateSession");
               
            //Creation of destination
            
            HibernateUtil.getSession().beginTransaction();
            HibernateUtil.getSession().save(new Destination());
            HibernateUtil.getSession().getTransaction().commit();
            JSONObject jsonObject = new JSONObject();
            
            
            System.out.println("changes done");
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        
        //ToDo check values
        
        //ToDo commit changes
    }
}
