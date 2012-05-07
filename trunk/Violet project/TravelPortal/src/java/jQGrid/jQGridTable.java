/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jQGrid;

import database.utilities.AdministratorDesktop;
import database.utilities.EmployeeDesktop;
import database.utilities.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author click
 */
public class jQGridTable extends HttpServlet {

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
       PrintWriter out = response.getWriter();
       try {
          if (request.getParameter("action").equals("fetchData")) {
                response.setContentType("text/xml;charset=UTF-8");                

                String status = request.getParameter("status");

                String rows = request.getParameter("rows");
                String page = request.getParameter("page");
                String dep = request.getParameter("dep");

                int totalPages = 0;
                int totalCount = 15;
                
                if (totalCount > 0) {
                    if (totalCount % Integer.parseInt(rows) == 0) {
                        totalPages = totalCount / Integer.parseInt(rows);
                    } else {
                        totalPages = (totalCount / Integer.parseInt(rows)) + 1;
                    }

                } else {
                    totalPages = 0;
                }
                out.print("<?xml version='1.0' encoding='utf-8'?>\n");
                out.print("<rows>");
                out.print("<page>" + request.getParameter("page") + "</page>");

                out.print("<total>" + totalPages + "</total>");
                out.print("<records>" + 15 + "</records>");
                int srNo = 1;
                
                for (int i=0;i<AdministratorDesktop.EmpNameForDep(dep).size();i++) {
                    out.print("<row id='" + i + "'>");
                    out.print("<cell>" + srNo + "</cell>");
                    out.print("<cell>"+AdministratorDesktop.EmpNameForDep(dep).get(i).toString()+"</cell>");
                    out.print("<cell>"+"3"+"</cell>");
                    out.print("<cell><![CDATA[<a href='View.jsp'>Personal Info</a>]]></cell>");
                    out.print("</row>");
                    srNo++;                
                }
                out.print("</rows>");
            }

        } finally {
            out.close();
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
