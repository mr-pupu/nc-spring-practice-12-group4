package servlets.admin_servlets;

import database.mapping.Department;
import database.mapping.Deprole;
import database.utilities.HibernateUtil;
import java.io.IOException;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import servlets.ServletHandler;

/**
 * Servlet implementation class AdministratorHandler
 */
public class DepartmentAugmentHandler extends ServletHandler {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentAugmentHandler() {
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
        System.out.println("Servlet DepartmentAugmentHandler was runned");
        
        String action = request.getParameter("action");
        String idString = request.getParameter("depid");
        String level = request.getParameter("level");
        JSONObject resp = new JSONObject();
        try {
            if (action.equals("edit")) {
                String depName = request.getParameter("depEdit");
                if ((depName == null) || (depName.equals(""))) {
                    throw new RuntimeException("Invalid department name");
                }
                Long depid = Long.parseLong(idString);
                Department edit = (Department) HibernateUtil.getSession().get(Department.class, depid.longValue());
                edit.setDepName(depName);
                HibernateUtil.save(edit);
                resp.put("success", "Edit successfull");
                resp.put("errorCode", 0);
                resp.put("error", "success");
                JSONObject res = new JSONObject();
                res.put("id", edit.getId());
                res.put("name", edit.getDepName());
                resp.put("jsdata", res);
                HibernateUtil.getSession().clear();
                
                response.setContentType("application/json");
                resp.writeJSONString(response.getWriter());

            } else if (action.equals("new")) {
                String depName = request.getParameter("depNew");
                if ((depName == null) || (depName.equals(""))) {
                    throw new RuntimeException("Invalid department name");
                }
                Department newdep = new Department();
                newdep.setDepName(depName);

                if ((idString != null) && (!idString.equals("")) && (!idString.equals("null"))) {
                    Long depid = Long.parseLong(idString);
                    newdep.setDepartment((Department) HibernateUtil.getSession().get(Department.class, depid.longValue()));
                    Deprole dp = (Deprole) HibernateUtil.getSession().get(Deprole.class, (long) 1);
                    HashSet<Deprole> dset = new HashSet<Deprole>();
                    dset.add(dp);
                    newdep.setDeprole(dset);
                }
                HibernateUtil.save(newdep);
                HibernateUtil.getSession().refresh(newdep);
                JSONObject res = new JSONObject();
                res.put("id", newdep.getId());
                res.put("name", newdep.getDepName());
                if ((idString != null) && (!idString.equals("")) && (!idString.equals("null"))) {
                    res.put("parent", Long.parseLong(idString));
                } else {
                    res.put("parent", null);
                }
                if ((level != null) && (!level.equals(""))) {
                    res.put("level", Integer.parseInt(level));
                } else {
                    res.put("level", 0);
                }
                resp.put("success", "Data saved successfully");
                resp.put("error", "success");
                resp.put("errorCode", 0);
                resp.put("nodeid", newdep.getId());
                resp.put("jsdata", res);

                response.setContentType("application/json");
                resp.writeJSONString(response.getWriter());
            }
        } catch (RuntimeException exp) {
            response.setContentType("application/json");
            resp.put("success", exp.getMessage());
            resp.put("errorCode", 1);
            resp.put("error", "error");
            resp.writeJSONString(response.getWriter());
        }
    }
}
