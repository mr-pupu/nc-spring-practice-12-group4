/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.mapping.City;
import database.mapping.Destination;
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

/**
 *
 * @author Allan (base by Vlad)
 */
public class AJAXDestinationCreation extends AJAXGetHandler {

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
        System.out.println("Servlet AJAXTrfsProcess runned (GET)");
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
        System.out.println("Servlet AJAXTrfsProcess runned (POST)");
        String ajaxdata = request.getParameter("ajaxdata");
        Object obj = JSONValue.parse(ajaxdata);
        JSONArray array = (JSONArray) obj;
        Map<String, String> resultStrings = new HashMap<String, String>();
        for (Object object : array) {
            JSONObject someObj = (JSONObject) object;
            resultStrings.putAll(someObj);
        }

        String hotelName;
        String hotelSite;
        Long cityId;

        try {
            hotelName = resultStrings.get("hotelName");
            hotelSite = resultStrings.get("hotelSite");
            cityId = Long.parseLong(resultStrings.get("cityId"));

            //Creation of destination
            Destination dest = new Destination();
            
            Session hibernateSession = (Session) request.getSession().
                    getAttribute("hibernateSession");
            
            City city = (City) hibernateSession.get(City.class, cityId);
            
            dest.setCity(city);
            dest.setHotelname(hotelName);
            dest.setHotelsite(hotelSite);
            dest.setIsApproved(false);
            
            HibernateUtil.save(dest);
            
            JSONObject js = new JSONObject();

            response.setContentType("application/json");
            js.put("error", "success");
            js.put("success", "New destination was added");
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
