/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.travelSupport;

import database.mapping.Trf;
import database.utilities.HibernateUtil;
import database.utilities.TravelSupportDesktop;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Servlet, that 
 * @author OleksandrDudinskyi
 */
public class TravelSupportAllTRFs extends HttpServlet {

    String department = "All";
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("jQGridTravelSupportTables Handler");
        String pageString = request.getParameter("page");
        System.out.println("Page:" + pageString);
        String recordString = request.getParameter("rows");
        System.out.println("Records " + recordString);
        try {
            if (request.getParameter("type").equals("AllTRFS")) {
                setTravelSupportId(HibernateUtil.EmpIdByLogin(request.getSession().getAttribute("name").toString()).intValue());
                int page = Integer.parseInt(pageString);
                int rows = Integer.parseInt(recordString);
                List<Trf> alltrfs = null;
                if (getDepartment().equals("All")) {
                    alltrfs = TravelSupportDesktop.TrfSameCountryFilterByDate(getTravelSupportId(), getBeginDate(), getEndDate(), page, rows);
                } else {
                    alltrfs = TravelSupportDesktop.TrfSameCountryFilterByDateDepartment(getTravelSupportId(), getBeginDate(), getEndDate(), getDepartment(), page, rows);
                }
//                System.out.println(getTravelSupportId());
//                System.out.println(getBeginDate().getDate() + " " + getBeginDate().getMonth());
//                System.out.println(getEndDate().getDate() + " " + getEndDate().getMonth());
//                System.out.println(getDepartment());
                JSONObject json = new JSONObject();
                json.put("page", page);
                json.put("total", rows);
                json.put("records", alltrfs.size());
                json.put("records", 1);
                JSONArray json3 = new JSONArray();
                for (int i = 0; i < alltrfs.size(); i++) {
                    JSONArray json1 = new JSONArray();
                    json1.add(alltrfs.get(i).getEmployeeByEmpId().getFirstName() + " " + alltrfs.get(i).getEmployeeByEmpId().getSecondName());
                    json1.add(alltrfs.get(i).getDestination().getCity().getCityName() + "," + alltrfs.get(i).getDestination().getCity().getCountry());
                    json1.add(alltrfs.get(i).getBeginDate().toString());
                    json1.add(alltrfs.get(i).getEndDate().toString());
                    json1.add(alltrfs.get(i).getCurState());
                    json1.add("Please approve with your manager as soon as possible");
                    JSONObject json2 = new JSONObject();
                    json2.put("id", 0);
                    json2.put("cell", json1);
                    json3.add(json2);
                }
                json.put("rows", json3);
                json.toJSONString();
                out.print(json.toString());
//                System.out.print(json.toString());
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
        if (resultStrings.containsKey("id")) {
            setDepartment(resultStrings.get("id"));
        }
        if (resultStrings.containsKey("beginDate")) {
            setBeginDate(parseDate(resultStrings.get("beginDate")));
        }
        if (resultStrings.containsKey("endDate")) {
            setEndDate(parseDate(resultStrings.get("endDate")));
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

    Date parseDate(String input) {
        Date returnDates = new Date();
        String[] beginDateMas = input.split("/");
        returnDates.setDate(Integer.parseInt(beginDateMas[0]));
        returnDates.setMonth(Integer.parseInt(beginDateMas[1]));
        returnDates.setYear(Integer.parseInt(beginDateMas[2]));
        return returnDates;
    }
}
