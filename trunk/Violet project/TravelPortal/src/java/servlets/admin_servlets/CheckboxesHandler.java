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
import org.json.simple.JSONObject;
import servlets.ServletHandler;

/**
 * Servlet implementation class AdministratorHandler
 */
public class CheckboxesHandler extends ServletHandler {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckboxesHandler() {
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
		System.out.println("Servlet CheckboxesHandler was runned");
                String idString = request.getParameter("id");
                System.out.println("id:"+idString);
                Long val = Long.parseLong(idString);
                Department dep = (Department)HibernateUtil.getSession().get(Department.class, val.longValue());
                //lock this employee
//                HibernateUtil.getSession().lock(emp, LockMode.WRITE);
                System.out.println(dep.getDepName());
                Set<Deprole> deproles = dep.getDeprole();
                JSONObject jo = new JSONObject();
                for(Deprole role: deproles){
                    jo.put("id", role.getId());
                }
                jo.put("size", deproles.size());
                jo.writeJSONString(response.getWriter());
                
//                HibernateUtil.getSession().lock(emp, LockMode.NONE);
                doDispatcher(request, response, "administrator.jsp");
	}

}
