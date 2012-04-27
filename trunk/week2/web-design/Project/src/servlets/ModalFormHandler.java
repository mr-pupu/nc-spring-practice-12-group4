/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ModalFormBean;
import database.mapping.Destination;
import database.mapping.Employee;
import database.mapping.Trf;
import database.utilities.HibernateUtil;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Master
 */
public class ModalFormHandler extends HttpServlet {

    private static final SimpleDateFormat df = new SimpleDateFormat("dd-mm-YYYY");

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
            throws ServletException, IOException, ParseException {
        //get data from modalform and insert it into a new object
        Trf tr = null;
        ModalFormBean bean = new ModalFormBean();
        try {
            bean = (ModalFormBean) request.getAttribute("dataBean");
            if (((ModalFormBean) request.getAttribute("dataBean")).getTrf() == null) {
                tr = new Trf();
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            tr = new Trf();
        }
        finally {
//        private static final String[] fieldCollection =
//    {"employee", "begin", "end", "country", "city", "projectManager", "hotelName",
//    "hotelSite", "customer", "car", "cash"};
        if (request.getParameter("employee") != null && !request.getParameter("employee").equals("")) {
            tr.setEmployeeByEmpId((Employee) HibernateUtil.getSession().get(Employee.class,
                    new Long(request.getParameter("employee"))));
            bean.setEmployee(request.getParameter("employee"));
        }
        if (request.getParameter("begin") != null && !request.getParameter("begin").equals("")) {
            tr.setBeginDate(df.parse(request.getParameter("begin")));
            bean.setBegin(df.parse(request.getParameter("begin")));
        }
        if (request.getParameter("end") != null && !request.getParameter("end").equals("")) {
            tr.setEndDate(df.parse(request.getParameter("end")));
            bean.setEnd(df.parse(request.getParameter("end")));
        }
        if (request.getParameter("country") != null  && !request.getParameter("country").equals("")) {
//            tr.setD
        }
        if (request.getParameter("city") != null) {
//            tr.set(Long.getLong(request.getParameter("city")));
        }
        if (request.getParameter("hotelName") != null  && !request.getParameter("hotelName").equals("")) {
            tr.setDestination((Destination) HibernateUtil.getSession().get(Destination.class,
                    new Long(request.getParameter("hotelName"))));
        }
        if (request.getParameter("car") != null && !request.getParameter("car").equals("")) {
            tr.setCarRental(Boolean.valueOf(request.getParameter("car")));
        }
        if (request.getParameter("cash") != null  && !request.getParameter("cash").equals("")) {
            tr.setCarPayment(Boolean.valueOf(request.getParameter("cash")));
        }
        HibernateUtil.save(tr);
        System.out.println("Form Data Handled");
        response.sendRedirect("index.jsp");
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
        try {
            processRequest(request, response);
        } catch (ParseException e) {
            System.err.println("Exception while parsing date input");
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException e) {
            System.err.println("Exception while parsing date input");
        }
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
