/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package jQGrid;

import database.mapping.Department;
import database.utilities.AdministratorDesktop;
import database.utilities.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


    /**
 * Servlet that build department tree
 * @author OleksandrDudinskyi
 */
public class JQGridTree extends HttpServlet {
/** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("arrr");
        try {
            if (request.getParameter("action").equals("fetchData")) {
//                Department treebuild = (Department) HibernateUtil.getSession().get(Department.class, (long) 9);
//                Set<Department> qw = treebuild.getDepartments();
//                for (Department as:qw) {
//                    System.out.println("department= " + as.getDepName());
//                }
//                System.out.println(AdministratorDesktop.Departments().get(0).toString());
                System.out.println("arrrr");
                response.setContentType("text/xml;charset=UTF-8");                

//                int level = Integer.parseInt(request.getParameter("n_level"));
//                int node = Integer.parseInt(request.getParameter("nodeid"));
//                if (node > 0) {
//                    level++;
//                } else {
//                    
//                }
//                if (totalCount > 0) {
//                    if (totalCount % Integer.parseInt(rows) == 0) {
//                        totalPages = totalCount / Integer.parseInt(rows);
//                    } else {
//                        totalPages = (totalCount / Integer.parseInt(rows)) + 1;
//                    }
//
//                } else {
//                    totalPages = 0;
//                }
                out.print("<?xml version='1.0' encoding='utf-8'?>\n");
                out.print("<rows>");
                out.print("<page>"+ request.getParameter("page") +"</page>");
                out.print("<total>1</total>");
                out.print("<records>1</records>");
                out.print("<row>");
                out.print("<cell>1</cell>");
                out.print("<cell>Main department, Santiago, Chili</cell>");
                out.print("<cell>0</cell>");
                out.print("<cell>null</cell>");
                out.print("<cell>false</cell>");
                out.print("<cell>true</cell>");
                out.print("<cell>true</cell>");
                out.print("</row>");
                out.print("</rows>");
            }
        } finally {            
            out.close();
        }
    }
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
