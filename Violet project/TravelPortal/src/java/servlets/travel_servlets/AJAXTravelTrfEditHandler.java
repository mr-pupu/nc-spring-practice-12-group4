/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.travel_servlets;

import database.mapping.Trf;
import database.mapping.Trfstate;
import database.utilities.HibernateUtil;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.json.simple.JSONObject;
import servlets.ajax.AJAXSendHandler;

/**
 *
 * @author Allan
 */
public class AJAXTravelTrfEditHandler extends AJAXSendHandler {

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
    public AJAXTravelTrfEditHandler() {
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

        System.out.println("AJAXTravelTrfEditHandler runned");
        String idString = request.getParameter("id");
        JSONObject jsonObject = new JSONObject();
        if (idString != null) {
            try {
                Long id = Long.parseLong(idString);
                Session hibernateSession = HibernateUtil.getSession();
                Trf trf;
                
                    trf = (Trf) hibernateSession.get(Trf.class, (Long) id);

                    request.getSession().setAttribute("hibernateSession", hibernateSession);
                    request.getSession().setAttribute("trf", trf);

                    putEmployeesSameDepToJSON(jsonObject, trf.getEmployeeByEmpId().getDepartment());
                    jsonObject.put("employeeId", trf.getEmployeeByEmpId().getId());
                    
                    putOfficebyCityCountryToJSON(jsonObject, trf.getEmployeeByEmpId().getOffice());

                    putDatesToJson(jsonObject, trf.getBeginDate(), trf.getEndDate());

                    putDestCountriesToJSON(jsonObject);
                    jsonObject.put("countryId", trf.getDestination().getCity().getCountry().getId());
                    
                    putCitiesToJSON(jsonObject, trf.getDestination().getCity().getCountry());
                    jsonObject.put("cityId", trf.getDestination().getCity().getId());

                    putLineManagerByEmployeeLogin(jsonObject, trf.getEmployeeByEmpId().getLogin());
                    putProjectManagerToJSON(jsonObject, trf.getEmployeeByProjectManager());
                    
                    jsonObject.put("projectManagerId", trf.getEmployeeByProjectManager().getId());

                    putHotelNamesToJSON(jsonObject, trf.getDestination().getCity());
                    jsonObject.put("destinationId", trf.getDestination().getId());
                    jsonObject.put("hotelSite", trf.getDestination().getHotelsite());

                    putCustomersToJSON(jsonObject);
                    jsonObject.put("customerId", trf.getCustomer().getId());

                    jsonObject.put("carRental", trf.getCarRental());
                    jsonObject.put("payByCash", trf.getPayByCash());
                    
                    Set<Trfstate> states = trf.getTrfstates();
                    long idComparator = 0;
                    Trfstate last = null;
                    for (Trfstate st : states) {
                        if (st.getId() > idComparator) {
                            idComparator = st.getId();
                            last = st;
                        }
                    }
                    jsonObject.put("commentary", last.getCommentary());

                    response.setContentType("application/json");
                    jsonObject.writeJSONString(response.getWriter());

            } catch (NumberFormatException e) {
                response.setContentType("application/json");
                String answer = "Server problem occured";
                JSONObject js = new JSONObject();
                js.put("error", "error");
                js.put("success", answer);
                js.writeJSONString(response.getWriter());
            }
        }
    }
}