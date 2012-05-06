/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.mapping.Employee;
import database.utilities.AdministratorDesktop;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Merle
 */
public class AJAXEmployeeHandler extends AJAXHandler {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        handle(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
        System.out.println("Page:"+pageString);
        JSONObject jsonObject = new JSONObject();
        if (idString != null){
            try {
//                AdministratorDesktop ad = new AdministratorDesktop();
                Long id = Long.parseLong(idString);
                if (id != null) {
                    String[][] emps = AdministratorDesktop.EmpNamePosForDepAndChildDep(id.intValue());
                    JSONArray ja = new JSONArray();
                     
                    for(int i=0; i<emps.length; i++){
                        JSONObject jo = new JSONObject();
                        jo.put("id", emps[i][0].toString());
//                        jo.put("id", String.valueOf(i+1));
                        JSONArray jaj = new JSONArray();
//                        jo.put("Name", emps[i][1]);
//                        jo.put("Position", emps[i][2]);
                        jaj.add(emps[i][1]);
                        jaj.add(emps[i][2]);
                        jo.put("cell", jaj);
                        ja.add(jo);
                    }
                    int rows_per_page=10;
                    
                    jsonObject.put("rows", ja);
                    jsonObject.put("records", emps.length);
                    jsonObject.put("total", emps.length/rows_per_page +1);
                    jsonObject.put("page", pageString);
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

