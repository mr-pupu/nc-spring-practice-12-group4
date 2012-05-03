/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jQGrid;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Александер
 */
public class JQGridEmployees extends HttpServlet {

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
        try {
            if (request.getParameter("action").equals("fetchRole")) {            
                response.setContentType("text/xml;charset=UTF-8"); 
                out.print("<?xml version='1.0' encoding='utf-8'?>\n");
                out.print("<rows>");
    out.print("<page>1</page>");
    out.print("<total>1</total>");
    out.print("<records>1</records>");
    out.print("<row><cell>1</cell><cell>Loading Data</cell><cell></cell><cell>0</cell><cell>1</cell><cell>10</cell><cell>false</cell><cell>false</cell></row>");
//    out.print("<row><cell>2</cell><cell>XML Data</cell><cell>xmlex.html</cell><cell>1</cell><cell>2</cell><cell>3</cell><cell>true</cell><cell>true</cell></row>");
//    out.print("<row><cell>3</cell><cell>JSON Data</cell><cell>jsonex.html</cell><cell>1</cell><cell>4</cell><cell>5</cell><cell>true</cell><cell>true</cell></row>");
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
