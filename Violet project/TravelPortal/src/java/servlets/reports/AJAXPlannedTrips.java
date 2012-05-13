package servlets.reports;

import database.mapping.Office;
import database.utilities.HibernateUtil;
import database.utilities.Reports;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import servlets.ajax.AJAXSendHandler;

/**
 *
 * @author Merle
 */
public class AJAXPlannedTrips extends AJAXSendHandler {
String department = "All";
    String city = "All";
    String country = "All";

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String depNAme) {
        this.department = depNAme;
    }
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
        System.out.println("Servlet AJAXCurrentTrips runned (GET)");
        String ajaxdata = request.getParameter("ajaxdata");
        Object obj = JSONValue.parse(ajaxdata);
        JSONArray array = (JSONArray) obj;
        Map<String, String> resultStrings = new HashMap<String, String>();
        for (Object object : array) {
            JSONObject someObj = (JSONObject) object;
            resultStrings.putAll(someObj);
        }
        if (resultStrings.containsKey("department")) {
            setDepartment(resultStrings.get("department"));
        }
        if (resultStrings.containsKey("officeName")) {
            if (resultStrings.get("officeName").equals("All")) {
                setCity("All");
                setCountry("All");
            } else {
                Office office = (Office) HibernateUtil.getSession().get(Office.class, Long.parseLong(resultStrings.get("officedId")));
                setCity(office.getCity().getCityName());
                setCountry(office.getCity().getCountry().getCountryName());
            }
        }
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
                    String[][] trfs = null;
                    System.out.println(getCity());
                    System.out.println(getCountry());
                    System.out.println(getDepartment());
                    if (getDepartment().equals("All") && getCountry().equals("All")) {
                        trfs = Reports.plannedTrfs(page-1, rows);
                        System.out.println("what a fuck");
                    }
                    if (getDepartment().equals("All") && !getCountry().equals("All")) {
                        trfs = Reports.PlannedTrfSameOffice(getCity(), getCountry(), page - 1, rows);
                    }
                    if (!getDepartment().equals("All") && getCountry().equals("All")) {
                        trfs = Reports.PlannedTrfSameDepartment(getDepartment(), page - 1, rows);
                    }
                    if (!getDepartment().equals("All") && !getCountry().equals("All")) {
                        trfs = Reports.PlannedTrfSameDepartmentOffice(getCity(), getCountry(), getDepartment(), page - 1, rows);
                    }
                    
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
