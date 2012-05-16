/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.travelSupport;

import database.mapping.Destination;
import database.mapping.Trf;
import database.mapping.Trfstate;
import database.utilities.HibernateUtil;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

/**
 *
 * @author Master
 */
public class DestinationApprover extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Ran DestinationApprover");
        String idstr = request.getParameter("id");
        String approve = request.getParameter("approve");
        System.out.println(idstr + " " + approve);
        JSONObject json = new JSONObject();
        Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        try {
            Boolean app = Boolean.valueOf(approve);
            System.out.println(app);
            Long id = Long.valueOf(idstr);
            //approving destination
            if (app) {
                System.out.println("COCK");
                Destination des = (Destination) s.get(Destination.class, id.longValue());
                des.setIsApproved(true);
                s.save(des);
                tx.commit();
                json.put("error", "success");
                json.put("success", "Destination approved");
                json.writeJSONString(response.getWriter());
            }
            //delete destination
            //set all associated trfs to rejected state
            //comment that the destination is wrong
            else {
                Destination des = (Destination) s.get(Destination.class, id.longValue());
                Set<Trf> tset = des.getTrfs();
                for (Trf tr : tset) {
                    tr.setCurState((short)1);
                    s.save(tr);
                    Trfstate tstate = new Trfstate();
                    tstate.setChanger(Long.parseLong(request.getSession().getAttribute("userId").toString()));
                    tstate.setTrf(tr);
                    tstate.setStatus((short)1);
                    tstate.setChangeDate(new Timestamp(new Date().getTime()));
                    tstate.setCommentary("Please choose another destination");
                    s.save(tstate);
                }
                s.delete(des);
                tx.commit();
                json.put("error", "success");
                json.put("success", "Destination deleted");
                
                response.setContentType("application/json");
                json.writeJSONString(response.getWriter());
            }
        }
        catch (Exception e) {
            tx.rollback();
            json.put("error", "error");
            json.put("success", e.getMessage());
            
            response.setContentType("application/json");
            json.writeJSONString(response.getWriter());
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
