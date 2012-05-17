/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.mapping.*;
import database.utilities.HibernateUtil;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import utils.MailSender;

/**
 *
 * @author Merle
 */
public class AJAXTrfsProcess extends AJAXGetHandler {

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
        Long userId = (Long) request.getSession().getAttribute("userId");

        Object obj = JSONValue.parse(ajaxdata);
        JSONArray array = (JSONArray) obj;
        Map<String, String> resultStrings = new HashMap<String, String>();
        for (Object object : array) {
            JSONObject someObj = (JSONObject) object;
            resultStrings.putAll(someObj);
        }

        Long empId;
        Date begin_date;
        Date end_date;
        Long managerId;
        Long destId;
        Long customerId;
        boolean car_rental;
        Short status;
        String commentary;

        try {
            empId = Long.parseLong(resultStrings.get("employee"));
            begin_date = getDateFromString(resultStrings.get("beginDate"));
            end_date = getDateFromString(resultStrings.get("endDate"));
            managerId = Long.parseLong(resultStrings.get("projectManager"));
            destId = Long.parseLong(resultStrings.get("destination"));
            customerId = Long.parseLong(resultStrings.get("customer"));
            car_rental = Boolean.parseBoolean(resultStrings.get("carRental"));
            status = Short.parseShort(resultStrings.get("status"));
            commentary = resultStrings.get("commentary");

            Trf currTrf = (Trf) request.getSession().getAttribute("trf");
            Session hibernateSession = (Session) request.getSession().getAttribute("hibernateSession");

            Employee emp = (Employee) hibernateSession.get(Employee.class, empId.longValue());
            Employee manager = (Employee) hibernateSession.get(Employee.class, managerId.longValue());
            Destination dest = (Destination) hibernateSession.get(Destination.class, destId.longValue());
            Customer customer = (Customer) hibernateSession.get(Customer.class, customerId.longValue());

            Short old_status=currTrf.getCurState();
            
            currTrf.setEmployeeByEmpId(emp);
            currTrf.setBeginDate(begin_date);
            currTrf.setEndDate(end_date);
            currTrf.setEmployeeByProjectManager(manager);
            currTrf.setDestination(dest);
            currTrf.setCustomer(customer);
            currTrf.setCarRental(car_rental);
            currTrf.setPayByCash(true);
            if (status != old_status) {
                currTrf.setCurState(status);
                
                MailSender.notifyByMail(status, currTrf.getId());
            }

            Set<Trfstate> states = currTrf.getTrfstates();
            long idComparator = 0;
            Trfstate last = null;
            if (status == 0) {
                for (Trfstate st : states) {
                    if (st.getId() > idComparator) {
                        idComparator = st.getId();
                        last = st;
                    }
                }
                last.setCommentary(commentary);
                HibernateUtil.save(last);
            }

            HibernateUtil.save(currTrf);

            if (status != old_status) {
                System.out.println("Creating trfstate");

                Trfstate newstate = new Trfstate();
                newstate.setTrf(currTrf);
                newstate.setStatus(status);
                newstate.setCommentary(commentary);
                newstate.setChangeDate(new Timestamp(new Date().getTime()));
                newstate.setChanger(userId);

                HibernateUtil.save(newstate);
            }


            JSONObject js = new JSONObject();
            String answer;
            switch (status) {
                case 0:
                    answer = "TRF was saved";
                    js.put("error", "success");
                    break;
                case 2:
                    answer = "TRF was canceled";
                    js.put("error", "success");
                    break;
                case 3:
                    answer = "TRF was commited";
                    js.put("error", "success");
                    break;
                default:
                    answer = "Changes have been made";
                    js.put("error", "error");
            }

            response.setContentType("application/json");
            js.put("success", answer);
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
