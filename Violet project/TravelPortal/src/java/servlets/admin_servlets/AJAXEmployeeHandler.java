/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin_servlets;

import database.utilities.AdministratorDesktop;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import servlets.ajax.AJAXSendHandler;

/**
 *
 * @author Gangbang34 and Allan
 */
public class AJAXEmployeeHandler extends AJAXSendHandler {

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
    public AJAXEmployeeHandler() {
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

        System.out.println("AJAXEmployeeHandler runned");
        String idString = request.getParameter("id");
        String pageString = request.getParameter("page");
        System.out.println("Page:" + pageString);
        String recordString = request.getParameter("rows");
        System.out.println("Records " + recordString);
        JSONObject jsonObject = new JSONObject();
        if (idString != null) {
            try {
                Long id = Long.parseLong(idString);
                int page = Integer.parseInt(pageString);
                int rows = Integer.parseInt(recordString);
                //total amount of entries
                long count = AdministratorDesktop.CountEmpsInDepsAndSubdeps(id.intValue());
                if ((page-1)*rows > count) {
                    page = 1;
                }
                if (id != null) {
                    String[][] emps = AdministratorDesktop.SubsidiaryEmployeesPaged(id.intValue(), page, rows);
                    JSONArray ja = new JSONArray();

                    for (int i = 0; i < emps.length; i++) {
                        JSONObject jo = new JSONObject();
                        jo.put("id", emps[i][0].toString());
                        JSONArray jaj = new JSONArray();
                        jaj.add(emps[i][1]);
                        jaj.add(emps[i][2]);
                        jo.put("cell", jaj);
                        ja.add(jo);
                    }

                    jsonObject.put("rows", ja);
                    jsonObject.put("records", count);
                    jsonObject.put("total", ((count/rows) + ((count%rows > 0) ? 1 : 0)));
                    jsonObject.put("page", page);
                    System.out.println(jsonObject);
                    jsonObject.writeJSONString(response.getWriter());
                } else {
                    System.out.println("Employee = null");
                    jsonObject.writeJSONString(response.getWriter());
                }
            } catch (NumberFormatException e) {
                System.out.print("Wrong id format");
            }
        }
    }
}
