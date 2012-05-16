package servlets.travelSupport;

import database.utilities.HibernateUtil;
import database.utilities.TravelSupportDesktop;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import servlets.ajax.AJAXTrfsHandler;

/**
 * Servlet, that sent date to allTRFs table.
 * @author OleksandrDudinskyi
 */
public class TravelSupportAllTRFs extends HttpServlet {

    String department = "All";
    boolean lastMonthSameCountry = true;
    Date beginDate = new Date();
    Date endDate = new Date();

    public int getTravelSupportId() {
        return TravelSupportId;
    }

    public void setTravelSupportId(int TravelSupportId) {
        this.TravelSupportId = TravelSupportId;
    }
    int TravelSupportId = 0;

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        System.out.println("Servlet TravelSuportAllTRFs runned (GET)");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String pageString = request.getParameter("page");
        String recordString = request.getParameter("rows");
        try {
            if (request.getParameter("type").equals("AllTRFS")) {
                setTravelSupportId(HibernateUtil.EmpIdByLogin(request.getSession().getAttribute("name").toString()).get(0).intValue());
                int page = Integer.parseInt(pageString);
                int rows = Integer.parseInt(recordString);
                int count = 100;
                String[][] alltrfs = null;
                if (getDepartment().equals("All") && lastMonthSameCountry) {      
                    alltrfs = TravelSupportDesktop.TrfLastMonthSameCountry(request.getSession().getAttribute("name").toString(), page-1, rows);
                }
                if (!getDepartment().equals("All") && lastMonthSameCountry) {
                    alltrfs = TravelSupportDesktop.TrfLastMonthSameCountryFilterByDepartment(getTravelSupportId(), getDepartment(), page, count);
                }
                if (getDepartment().equals("All") && !lastMonthSameCountry) {
                    alltrfs = TravelSupportDesktop.TrfSameCountryFilterByDate(getTravelSupportId(), getBeginDate(), getEndDate(), page, rows);
                }
                if (!getDepartment().equals("All") && !lastMonthSameCountry) {
                    alltrfs = TravelSupportDesktop.TrfSameCountryFilterByDateDepartment(getTravelSupportId(), getBeginDate(), getEndDate(), getDepartment(), page-1, rows);
                }
                JSONObject json = new JSONObject();
                if (alltrfs.length != 0) {
                    JSONArray ja = new JSONArray();
                    for (int i = 0; i < alltrfs.length; ++i) {
                        JSONObject jo = new JSONObject();
                        jo.put("id", alltrfs[i][0]);
                        JSONArray jaj = new JSONArray();

                        jaj.add(alltrfs[i][1] + " " + alltrfs[i][2]);
                        jaj.add(alltrfs[i][3] + " " + alltrfs[i][4]);
                        jaj.add(alltrfs[i][5]);
                        jaj.add(alltrfs[i][6]);
                        jaj.add(alltrfs[i][7]);
                        jaj.add(alltrfs[i][8]);
                        jo.put("cell", jaj);
                        ja.add(jo);
                    }
                    json.put("rows", ja);
                    json.put("records", count);
                    json.put("page", page);
                    json.put("error", "success");
                    json.put("success", "All TRFs");
                } else {
                    json.put("error", "info");
                    json.put("success", "No trfs there");
                }
                json.toJSONString();
                out.print(json.toString());
            }
        } finally {
            out.close();
        }
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
        System.out.println("Servlet TravelSuportAllTRFs runned (POST)");
        String ajaxdata = request.getParameter("ajaxdata");
        Object obj = JSONValue.parse(ajaxdata);
        JSONArray array = (JSONArray) obj;
        Map<String, String> resultStrings = new HashMap<String, String>();
        for (Object object : array) {
            JSONObject someObj = (JSONObject) object;
            resultStrings.putAll(someObj);
        }
        setDepartment(resultStrings.get("depatmentName"));
        try {
            if (!resultStrings.get("beginDate").equals("") && !resultStrings.get("endDate").equals("")) {
                lastMonthSameCountry = false;
                setBeginDate(AJAXTrfsHandler.getDateFromString(resultStrings.get("beginDate")));
                setEndDate(AJAXTrfsHandler.getDateFromString(resultStrings.get("endDate")));
            } else {
                lastMonthSameCountry = true;
            }
        } catch (ParseException ex) {
            Logger.getLogger(TravelSupportAllTRFs.class.getName()).log(Level.SEVERE, null, ex);
        }
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
