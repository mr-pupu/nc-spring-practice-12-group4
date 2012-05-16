/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.utilities.EmployeeDesktop;
import java.io.IOException;
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
public class AJAXAllMyTRFs extends AJAXSendHandler {

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
    public AJAXAllMyTRFs() {
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

        System.out.println("AJAXAllMyTRFs runned");

        String travelString = request.getParameter("travel");
        String pageString = request.getParameter("page");
        String recordString = request.getParameter("rows");
        String login = (String) request.getSession().getAttribute("name");
        JSONObject jsonObject = new JSONObject();
        if (travelString != null) {
            try {
                boolean travel = Boolean.parseBoolean(travelString);
                int page = Integer.parseInt(pageString);
                int rows = Integer.parseInt(recordString);
                long count = EmployeeDesktop.countAllEmpsTRFS(login);
                if ((page - 1) * rows > count) {
                    page = 1;
                }
                String[][] trfs;
                if (travel) {
                    // trfs = TravelSupportDesktop.TrfLastMonthSameCountry(login, 0, 20);
                    trfs = EmployeeDesktop.allEmpsTRFs(login, page, rows);
                } else {
                    trfs = EmployeeDesktop.allEmpsTRFs(login, page, rows);
                }

                JSONArray ja = new JSONArray();

                for (int i = 0; i < trfs.length; ++i) {

                    JSONObject jo = new JSONObject();
                    jo.put("id", trfs[i][0]);
                    JSONArray jaj = new JSONArray();

                    jaj.add(trfs[i][1] + ", " + trfs[i][2]);
                    jaj.add(trfs[i][3]);
                    jaj.add(trfs[i][4]);
                    jaj.add(trfs[i][5]);
                    jaj.add(trfs[i][6]);
                    jo.put("cell", jaj);
                    ja.add(jo);
                }
                jsonObject.put("rows", ja);
                jsonObject.put("records", count);
                jsonObject.put("page", page);
                jsonObject.put("total", ((count / rows) + ((count % rows > 0) ? 1 : 0)));

                response.setContentType("application/json");
                jsonObject.writeJSONString(response.getWriter());
            } catch (NumberFormatException e) {
                response.setContentType("application/json");
                String answer = "Server problem occured";
                JSONObject js = new JSONObject();
                js.put("error", "error");
                js.put("success", answer);
                js.writeJSONString(response.getWriter());
            }
        }
    }
}
