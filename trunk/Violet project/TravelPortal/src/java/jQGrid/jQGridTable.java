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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
          
          
      
          if (request.getParameter("action").equals("fetchData")) {
         
                response.setContentType("application/json;charset=UTF-8");                
                        String dep = request.getParameter("dep"); 
                        String id = request.getParameter("id"); 
                       
                      //  System.out.print(dep);
                        List trfs=EmployeeDesktop.EmpTRFs(Integer.parseInt(id));
                       List cells = new ArrayList();
                      

  JSONObject json = new JSONObject();
  json.put("total",trfs.size());
  json.put("page",1);
  json.put("records",10);
  //{"total": ,"page": ,"records": ,"rows": [{"id": ,"cell":["value"]}]}
  //json .put("rows",rows);
  
  for(int i=0;i<trfs.size();i++){
      JQGridRow row=new JQGridRow();
      for(int j=0; j<trfs.size();j++){
        row.setId(j);
      
      cells.add(trfs.get(j).toString());
      cells.add(trfs.get(j).toString());
      cells.add(trfs.get(j).toString());
      cells.add(trfs.get(j).toString());
      cells.add(trfs.get(j).toString());
      }
     row.setCell(cells);
     json.put("row", row);
  }
          }
         
// List rows = new ArrayList();
//  for (int i=0; i<AdministratorDesktop.EmpNameForDep(dep).size();i++){
//      JQGridRow row = new JQGridRow();
//row.setId(i);
//List cells = new ArrayList();
//cells.add(i);
//cells.add(2);
//cells.add(i);
//cells.add(2);
//cells.add(i);
//row.setCell(cells);
//rows.add(row);
//}
//    
//}
//  json.put("rows", rows);



       
   

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
}