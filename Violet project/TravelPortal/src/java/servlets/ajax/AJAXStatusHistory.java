/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.mapping.Trf;
import database.mapping.Trfstate;
import database.utilities.EmployeeDesktop;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
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
public class AJAXStatusHistory extends HttpServlet {

    private static final SimpleDateFormat sf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss" );
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
        response.setContentType("application/json");
        JSONObject jsonObject = new JSONObject();
        System.out.println("AJAXStatusHistory runned");
//       int id=Integer.parseInt(request.getParameter("id"));
//        System.out.println(id);
        String idString = request.getParameter("id");
        System.out.println("Id " + idString);
        String pageString = request.getParameter("page");
        System.out.println("Page:" + pageString);
        String recordString = request.getParameter("rows");
        String login = (String) request.getSession().getAttribute("name");
        if (idString != null) {
            try {
            if (request.getSession().getAttribute("name") != null) {

                System.out.println("Records " + recordString);
                
                Long id = Long.parseLong(idString);



                int page = Integer.parseInt(pageString);
                int rows = Integer.parseInt(recordString);
                long count = EmployeeDesktop.HistoryCount(id.intValue()).longValue();
                if ((page - 1) * rows > count) {
                    page = 1;
                }
                System.out.println(count);
                List<Trfstate> statuses = EmployeeDesktop.HistoryPaged(id.intValue(), page, rows);



                JSONArray ja = new JSONArray();

                for (int i = 0; i < statuses.size(); ++i) {

                    JSONObject jo = new JSONObject();
                    jo.put("id", 1);
                    JSONArray jaj = new JSONArray();

                    jaj.add(sf.format(statuses.get(i).getChangeDate()));
                    jaj.add(Trf.getStatus(statuses.get(i).getStatus()));
                    jaj.add(statuses.get(i).getTrf().getEmployeeByEmpId().getFirstName()
                            + " " + statuses.get(i).getTrf().getEmployeeByEmpId().getSecondName());
                    jaj.add(statuses.get(i).getCommentary());

                    jo.put("cell", jaj);
                    ja.add(jo);
                }
                jsonObject.put("rows", ja);
                jsonObject.put("records", count);
                jsonObject.put("page", page);
                jsonObject.writeJSONString(response.getWriter());
                System.out.print(jsonObject);
            } else {
                ((HttpServletResponse) response).sendRedirect(request.getContextPath() + "/");
                jsonObject.put("error", "error");
                jsonObject.put("success", "Access not permitted");
                jsonObject.writeJSONString(response.getWriter());

            }
            }
            catch (RuntimeException e) {
                jsonObject.put("error", "error");
                jsonObject.put("success", e.getMessage());
                jsonObject.writeJSONString(response.getWriter());
            }
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
