package servlets.admin_servlets;


import database.mapping.Employee;
import database.utilities.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlets.ServletHandler;

/**
 * Servlet implementation class AdministratorHandler
 */
public class EmployeeDeleteHandler extends ServletHandler {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeDeleteHandler() {
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
                Employee emp = (Employee)HibernateUtil.getSession().get(Employee.class, val.longValue());
                //lock this employee
//                HibernateUtil.getSession().lock(emp, LockMode.WRITE);
                System.out.println(emp.getFirstName());
                HibernateUtil.delete(emp);
//                HibernateUtil.getSession().lock(emp, LockMode.NONE);
                doDispatcher(request, response, "administrator.jsp");
	}

}
