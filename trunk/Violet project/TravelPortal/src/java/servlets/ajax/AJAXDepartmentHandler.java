/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Gangbang34
 */
public class AJAXDepartmentHandler extends AJAXSendHandler {

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
    public AJAXDepartmentHandler() {
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
        System.out.println("AJAXDepartmentHandler runned");

        String nodeid = request.getParameter("nodeid");
        String n_level = request.getParameter("n_level");

        JSONObject jsonObject = new JSONObject();
        if (nodeid != null) {
            try {
                Long id = Long.parseLong(nodeid);
                int level = Integer.parseInt(n_level);

                putChildDepartments(jsonObject, id, level);
                
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
        } else {
            putHeadDepartments(jsonObject);
            jsonObject.put("level", 0);

            response.setContentType("application/json");
            jsonObject.writeJSONString(response.getWriter());
        }
    }
}
