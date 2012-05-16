/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.mapping.*;
import database.utilities.HibernateUtil;
import database.utilities.TrfEdit;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.json.simple.JSONObject;

/**
 *
 * @author Merle
 */
public class AJAXTrfsHandler extends AJAXSendHandler {

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
    public AJAXTrfsHandler() {
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

        System.out.println("AJAXTrfEditHandler runned");

        String idString = request.getParameter("id");
        Long userId = (Long) request.getSession().getAttribute("userId");
        JSONObject jsonObject = new JSONObject();
        if (idString != null) {
            try {
                Long id = Long.parseLong(idString);
                Session hibernateSession = HibernateUtil.getSession();
                Trf trf;

                if (id > 0) {
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

                    jsonObject.writeJSONString(response.getWriter());
                } else {
                    trf = new Trf();

                    request.getSession().setAttribute("hibernateSession", hibernateSession);
                    request.getSession().setAttribute("trf", trf);

                    Employee emp = (Employee) hibernateSession.get(Employee.class, userId);

                    putEmployeesSameDepToJSON(jsonObject, emp.getDepartment());
                    jsonObject.put("employeeId", emp.getId());

                    putOfficebyCityCountryToJSON(jsonObject, emp.getOffice());

                    Date now = new Date();
                    Calendar c = Calendar.getInstance();
                    c.setTime(now);
                    c.add(Calendar.DATE, 7);  // adding 7 days

                    putDatesToJson(jsonObject, now, c.getTime());
                    putDestCountriesToJSON(jsonObject);
                    putCitiesToJSON(jsonObject, TrfEdit.destCountryList().get(0));

                    putLineManagerByEmployeeLogin(jsonObject, emp.getLogin());
                    putProjectManagerToJSON(jsonObject, emp);
                    jsonObject.put("projectManagerId", emp.getId());

                    City city = (City) TrfEdit.destCountryList().get(0).
                            getCities().toArray()[0];
                    putHotelNamesToJSON(jsonObject, city);

                    jsonObject.put("cityId", city.getId());

                    Destination dest = (Destination) city.getDestinations().toArray()[0];
                    jsonObject.put("destinationId", dest.getId());
                    jsonObject.put("hotelSite", dest.getHotelsite());
                    putCustomersToJSON(jsonObject);

                    response.setContentType("application/json");
                    jsonObject.writeJSONString(response.getWriter());
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
    }
}
