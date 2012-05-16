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
        Long val = Long.parseLong(idString);
        Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        Department dep = (Department) s.get(Department.class, val.longValue());
        s.refresh(dep);
        JSONObject json = new JSONObject();
        //check if child deps exist
        //alert code
        //0 - successfully deleted
        //1 - unable to delete because of child nodes
        //2 - unable to delete for other reason
        if (!dep.getDepartments().isEmpty()) {
            tx.rollback();
            json.put("error", "error");
            json.put("success", "Unable to delete department with child departments");
            json.put("code", 1);
        } else {
            try {
                s.delete(dep);
                tx.commit();
                s.flush();
                json.put("error", "success");
                json.put("success", "Department deleted");
                json.put("code", 0);
            } catch (Exception e) {
                tx.rollback();
                json.put("error", "error");
                json.put("error", "The following database issue occured during deletion procedure " +
                        e.getMessage());
                json.put("code", 2);
            } finally {
                try {
//                    s.close();
//                    doDispatcher(request, response, "Error while deleting");
                } catch (Exception he) {
//                    doDispatcher(request, response, he.getMessage());
                }
            }
        }

        response.setContentType("application/json");
        json.writeJSONString(response.getWriter());
    }
}
