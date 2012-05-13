package servlets.admin_servlets;

import database.mapping.Department;
import database.utilities.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import servlets.ServletHandler;

/**
 * Servlet implementation class AdministratorHandler
 */
public class DepartmentDeleteHandler extends ServletHandler {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentDeleteHandler() {
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
        
        System.out.println("Servlet EmployeeDeleteHandler was runned");
        String idString = request.getParameter("id");
        System.out.println("id:" + idString);
        Long val = Long.parseLong(idString);
        Session s = HibernateUtil.getSession();
//        if (request.getSession().getAttribute("name").toString()=="Viki"){
//            response.sendRedirect("http://www.java.com");
//        }
        Transaction tx = s.beginTransaction();
        Department dep = (Department) HibernateUtil.getSession().get(Department.class, val.longValue());
        JSONObject json = new JSONObject();
        //check if child deps exist
        //alert code
        //0 - successfully deleted
        //1 - unable to delete because of child nodes
        //2 - unable to delete for other reason
        if (!dep.getDepartments().isEmpty()) {
//            System.out.println("DOGGY");
            sendMessage(request, "Error", "Unable to delete department with child departments", "info");
            tx.rollback();
            json.put("success", 1);
        } else {
            try {
                s.delete(dep);
                tx.commit();
                s.flush();
                json.put("success", 0);
            } catch (Exception e) {
                tx.rollback();
                System.out.println("Rolling back");
                json.put("success", 2);
            } finally {
                try {
//                    s.close();
//                    doDispatcher(request, response, "Error while deleting");
                } catch (Exception he) {
//                    doDispatcher(request, response, he.getMessage());
                }
            }
        }
//        
//        System.out.println(json);
        json.writeJSONString(response.getWriter());
        
//        doDispatcher(request, response, "1");
    }
}
