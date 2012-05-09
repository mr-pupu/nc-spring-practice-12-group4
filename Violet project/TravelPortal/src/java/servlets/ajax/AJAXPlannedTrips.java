package servlets.ajax;

import database.mapping.Trf;
import database.utilities.AdministratorDesktop;
import database.utilities.EmployeeDesktop;
import database.utilities.Reports;
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
public class AJAXPlannedTrips extends AJAXSendHandler {

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
    public AJAXPlannedTrips() {
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

        System.out.println("AJAXInProgressTrfs runned");
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
                int count = 100;
                if (id != null) {
                    
                    String[][] trfs = Reports.plannedTrfs(page-1, rows);

                    JSONArray ja = new JSONArray();

                    for (int i = 0; i < trfs.length; ++i) {

                        JSONObject jo = new JSONObject();
                        jo.put("id", trfs[i][0]);
                        JSONArray jaj = new JSONArray();

                        jaj.add(trfs[i][1] + ", " + trfs[i][2]);
                        jaj.add(trfs[i][3]+" "+trfs[i][4]);
                        jaj.add(trfs[i][5]+ " "+trfs[i][6]);
                        jaj.add(trfs[i][7]);
                        jaj.add(trfs[i][8]);
                        
                        jo.put("cell", jaj);
                        ja.add(jo);
                    }
                    jsonObject.put("rows", ja);
                    jsonObject.put("records", count);
                    jsonObject.put("page", page);
                } 
                    jsonObject.writeJSONString(response.getWriter());
            } catch (NumberFormatException e) {
                System.out.print("Wrong id format");
            }
        }
    }
}
