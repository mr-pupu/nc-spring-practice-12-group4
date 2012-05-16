/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.travelSupport;

import database.mapping.Destination;
import database.utilities.HibernateUtil;
import database.utilities.TravelSupportDesktop;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import servlets.ajax.AJAXSendHandler;

/**
 *
 * @author Gangbang34 
 */
public class DestinationApproval extends AJAXSendHandler {

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
    public DestinationApproval() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
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
        //response.sendRedirect("index.jsp");
        System.out.println("DestinationApproval runned");
        
        String pageString = request.getParameter("page");
        String recordString = request.getParameter("rows");

        int page = Integer.parseInt(pageString);
        int rows = Integer.parseInt(recordString);
        long count = TravelSupportDesktop.CountNoneApprovedDestinations().longValue();
        if ((page - 1) * rows > count) {
            page = 1;
        }
        JSONObject resp = new JSONObject();
        resp.put("records", count);
        resp.put("total", ((count/rows) + ((count%rows > 0) ? 1 : 0)));
        resp.put("page", page);
        JSONArray ja = new JSONArray();
        //get paged list, process it
        List<Destination> dlist = TravelSupportDesktop.NonApprovedDestinationListPaged(page, rows);
        Session s = HibernateUtil.getSession();
        for (Destination d: dlist) {
            s.refresh(d);
            JSONObject jo = new JSONObject();
            JSONArray jaj = new JSONArray();
            jo.put("id", d.getId());
            jaj.add(d.getHotelname());
            jaj.add(d.getHotelsite());
            jaj.add(d.getCity().getCityName());
            jaj.add(d.getCity().getCountry().getCountryName());
            jaj.add("<a href='#' onclick=\"if (confirm('Approve destination?')) {"
                    + "approver("+d.getId()+", true)}\">"
                    + "<i class=\"icon-ok\"></i></a>");
            jaj.add("<a href='#' onclick=\"if (confirm('Reject destination?')) {"
                    + "approver("+d.getId()+", false)}\">"
                    + "<i class=\"icon-remove\"></i></a>");
            jo.put("cell", jaj);
            ja.add(jo);
        }
        resp.put("rows", ja);

        response.setContentType("application/json");
        resp.writeJSONString(response.getWriter());
    }
}
