/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.mapping.City;
import database.utilities.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Merle
 */
public class AJAXHotelsHandler extends AJAXSendHandler {

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

        System.out.println("AJAXHotelsHandler runned");

        String idCity = request.getParameter("id");
        JSONObject jsonObject = new JSONObject();
        if (idCity != null) {
            try {
                Long id = Long.parseLong(idCity);
                City city = (City) HibernateUtil.getSession().
                        get(City.class, (long) id);

                if (city != null) {
                    putDestinationsToJSON(jsonObject, city);
                } else {
                    response.setContentType("application/json");
                    String answer = "Server problem occured";
                    JSONObject js = new JSONObject();
                    js.put("error", "error");
                    js.put("success", answer);
                    js.writeJSONString(response.getWriter());
                }
            } catch (NumberFormatException e) {
                response.setContentType("application/json");
                String answer = "Server problem occured";
                JSONObject js = new JSONObject();
                js.put("error", "error");
                js.put("success", answer);
                js.writeJSONString(response.getWriter());
            }
        }
        response.setContentType("application/json");
        jsonObject.writeJSONString(response.getWriter());
    }
}
