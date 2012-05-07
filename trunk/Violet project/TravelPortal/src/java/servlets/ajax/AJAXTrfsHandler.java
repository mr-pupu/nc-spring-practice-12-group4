/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.mapping.Trf;
import database.utilities.HibernateUtil;
import java.io.IOException;
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
        JSONObject jsonObject = new JSONObject();
        if (idString != null) {
            try {
                Long id = Long.parseLong(idString);
                Session hibernateSession = HibernateUtil.getSession();
                Trf trf = (Trf) hibernateSession.get(Trf.class, (Long) id);
                if (trf != null) {

                    request.getSession().setAttribute("hibernateSession", hibernateSession);
                    request.getSession().setAttribute("trf", trf);

                    putEmployeeToJSON(jsonObject, trf.getEmployeeByEmpId());
                    putOfficeToJSON(jsonObject, trf.getEmployeeByEmpId().getOffice());

                    putDatesToJson(jsonObject, trf.getBeginDate(), trf.getEndDate());

                    putCitiesAndCountriesToJSON(jsonObject, trf.getDestination().getCity());

                    putLineManagerByEmployeeLogin(jsonObject, trf.getEmployeeByEmpId().getLogin());
                    putProjectManagerToJSON(jsonObject, trf.getEmployeeByProjectManager());

                    jsonObject.put("destinationId", trf.getDestination().getId());
                    putDestinationsToJSON(jsonObject, trf.getDestination().getCity());

                    putCustomersToJSON(jsonObject, trf.getCustomer());

                    jsonObject.put("carRental", trf.getCarRental());
                    jsonObject.put("payByCash", trf.getPayByCash());

                    jsonObject.put("state", trf.getCurState());

                    jsonObject.writeJSONString(response.getWriter());
                } else {
                    System.out.println("TRF = null");
                    jsonObject.writeJSONString(response.getWriter());
                }
            } catch (NumberFormatException e) {
                System.out.print("Wrong id format");
            }
        }
    }
}
