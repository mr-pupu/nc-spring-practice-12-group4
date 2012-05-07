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
        Transaction tx = s.beginTransaction();
        Department dep = (Department) HibernateUtil.getSession().get(Department.class, val.longValue());
        //check if child deps exist
        if (!dep.getDepartments().isEmpty()) {
            System.out.println("DOGGY");
            sendMessage(request, "Error", "Unable to delete department with child departments", "info");
            tx.rollback();
        } else {
            try {
                s.delete(dep);
                tx.commit();
                s.flush();
            } catch (Exception e) {
                tx.rollback();
                System.out.println("Rolling back");
            } finally {
                try {
                    s.close();
                    doDispatcher(request, response, "Error while deleting");
                } catch (Exception he) {
                    doDispatcher(request, response, he.getMessage());
                }
            }
        }

        doDispatcher(request, response,
                "administrator.jsp");
    }
}
