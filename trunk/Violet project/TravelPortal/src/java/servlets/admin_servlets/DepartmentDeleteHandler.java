package servlets.admin_servlets;


import database.mapping.Department;
import database.mapping.Deprole;
import database.mapping.Employee;
import database.utilities.HibernateUtil;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.LockMode;
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
		System.out.println("Servlet EmployeeDeleteHandler was runned");
                String idString = request.getParameter("id");
                System.out.println("id:"+idString);
                Long val = Long.parseLong(idString);
                Department dep = (Department)HibernateUtil.getSession().get(Department.class, val.longValue());
                //lock this department
                HibernateUtil.getSession().lock(dep, LockMode.UPGRADE);
                //remove manager
                dep.setManagerId(null);
                HibernateUtil.getSession().save(dep);
                //unemploy employees
                Set<Employee> empset = dep.getEmployees();
                for (Employee e : empset) {
                    e.setDepartment(null);
                    HibernateUtil.save(e);
                }
                Set<Deprole> deproleset = dep.getDeprole();
                for (Deprole r : deproleset) {
                    HibernateUtil.delete(r);
                }
                System.out.println(dep.getDepName());
                //delete department
                HibernateUtil.delete(dep);
                HibernateUtil.getSession().flush();
                doDispatcher(request, response, "administrator.jsp");
	}

}
