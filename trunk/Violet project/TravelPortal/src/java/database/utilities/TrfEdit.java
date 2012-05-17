package database.utilities;

import database.mapping.Country;
import database.mapping.Customer;
import database.mapping.Trfstate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class TrfEdit {
    
    public static List<Country> destCountryList() {
        Session s = HibernateUtil.getSession();
        String query = "SELECT distinct * from country "+
        "WHERE id in (select country_id from city where id not in "+
        "(select city_id from office))";
        SQLQuery q = s.createSQLQuery(query);
        return (List<Country>) q.addEntity(Country.class).list();
    }

    
    /**
     * @author Allan
     * @param login
     * @return 
     * giving employee id by login
     */
    public static List empIdByLogin(String login) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT id "
                + " FROM employee"
                + " WHERE login=:login";

        List resList = s.createSQLQuery(prepared_statement).setString("login", login).list();
//        BigDecimal bigId =(BigDecimal) ((Object) resList.get(0));
//        return bigId.longValue();
        return resList;
     }

    //list of customers
    public static List<Customer> Customers() {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select * "
                + "from customer";
        return (List<Customer>) s.createSQLQuery(prepared_statement).addEntity(database.mapping.Customer.class).list();
    }
    //current status of given trf

    public static List<String> CurrentTrfStatus(Integer trf_id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select cur_state "
                + "from trf "
                + "where id=:trf_id";

        return (List<String>) s.createSQLQuery(prepared_statement).setInteger("trf_id", trf_id).list();
    }

    //the list of given project manager's employee
    //@return List of String[2], where each list elem 
    //represents 2 columns: String[0]- first name, String[1]-second name
    public static List<String[]> ProjectManagerEmployees(Integer pm_id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT second_name, first_name "
                + "FROM department_employee "
                + "WHERE manager_id=:pm_id";
        List rows = s.createSQLQuery(prepared_statement).setInteger("pm_id", pm_id).list();
        List<String[]> res = new ArrayList<String[]>();

        if (!rows.isEmpty()) {
            //   for(Object row : rows)
            for (int i = 0; i < rows.size(); i++) {
                String[] srow = new String[2];
                srow[0] = (String) ((Object[]) rows.get(i))[0];
                srow[1] = (String) ((Object[]) rows.get(i))[1];
                res.add(srow);
            }
        }
        return res;
    }

    /**
     * Obtain the employee's project manager through his login
     */
    public static String[] DepManagerNameByLogin(String login) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT mansurname, manname "
                + "FROM employee_managers "
                + "WHERE login=:login AND manlogin != :login";

        List resList = s.createSQLQuery(prepared_statement).setString("login", login).list();

        String[] resPair = null;

        if (!resList.isEmpty()) {
            resPair = new String[2];
            resPair[0] = (String) ((Object[]) resList.get(0))[0];
            resPair[1] = (String) ((Object[]) resList.get(0))[1];
        }
        return resPair;
    }

    /**
     * Get
     *
     * @param login
     * @return
     */
    public static String[] ParentDepManagerNameByLogin(String login) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT mansurname, manname "
                + "FROM employee_managers_department "
                + "WHERE login=:login";
        List resList = s.createSQLQuery(prepared_statement).setString("login", login).list();

        String[] resPair = null;//new String[2];

        if (!resList.isEmpty()) {

            resPair = new String[2];
            resPair[0] = (String) ((Object[]) resList.get(0))[0];
            resPair[1] = (String) ((Object[]) resList.get(0))[1];
        }
        return resPair;


    }

    
    public static String[] LineManager(String login) {
        Session s = HibernateUtil.getSession();
        String[] dep = DepManagerNameByLogin(login);
        if (dep != null) {
            return dep;
        }
        String[] pardep = ParentDepManagerNameByLogin(login);
        if (pardep != null) {
            return pardep;
        }

        String pps = "SELECT second_name, first_name "
                + "FROM employee "
                + "WHERE login=:login";

        List resList = s.createSQLQuery(pps).setString("login", login).list();

        String[] resPair = null;//new String[2];

        if (!resList.isEmpty()) {
            resPair = new String[2];
            resPair[0] = (String) ((Object[]) resList.get(0))[0];
            resPair[1] = (String) ((Object[]) resList.get(0))[1];
        }
        return resPair;

    }
    //the list of countries

    public static List<String> Countries() {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select country_name "
                + "from country";

        return (List<String>) s.createSQLQuery(prepared_statement).list();
    }

    //name of manager of given employee
    public static String[] DepManagerName(Integer id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT mansurname, manname "
                + "FROM employee_managers "
                + "WHERE id=:id";
        List resList = s.createSQLQuery(prepared_statement).setInteger("id", id).list();

        String[] resPair = null;//new String[2];

        if (!resList.isEmpty()) {

            resPair = new String[2];
            resPair[0] = (String) ((Object[]) resList.get(0))[0];
            resPair[1] = (String) ((Object[]) resList.get(0))[1];
        }
        return resPair;
    }

    //name of the manager of higher deparment than the department of given employee
    public static String[] ParentDepManagerName(Integer id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT manname, mansurname "
                + "FROM employee_managers_department "
                + "WHERE id=:id";
        List resList = s.createSQLQuery(prepared_statement).setInteger("id", id).list();
        String[] resPair = null;

        if (!resList.isEmpty()) {

            resPair = new String[2];
            resPair[0] = (String) ((Object[]) resList.get(0))[0];
            resPair[1] = (String) ((Object[]) resList.get(0))[1];
        }
        return resPair;

    }

    //location of given employee (country and city)
    // @return String[2] res, where res[0] - country name and res[1] - city name 
    public static String[] GetLocationForEmp(int emp_id) {
        Session s = HibernateUtil.getSession();
        String stmt = "SELECT country_name, city_name "
                + "FROM emp_office "
                + "WHERE id=:emp_id";
        List resList = s.createSQLQuery(stmt).setInteger("emp_id", emp_id).list();

        String[] resPair = new String[2];

        if (!resList.isEmpty()) {
            resPair[0] = (String) ((Object[]) resList.get(0))[0];
            resPair[1] = (String) ((Object[]) resList.get(0))[1];
        }
        return resPair;
    }

    //get office name by employee's login
    public static String[] GetLocationForEmpByLogin(String login) {
        Session s = HibernateUtil.getSession();
        String stmt = "SELECT country_name, city_name "
                + "FROM emp_office "
                + "WHERE login=:login";

        List resList = s.createSQLQuery(stmt).setString("login", login).list();

        String[] resPair = new String[2];

        if (!resList.isEmpty()) {
            resPair[0] = (String) ((Object[]) resList.get(0))[0];
            resPair[1] = (String) ((Object[]) resList.get(0))[1];
        }
        return resPair;
    }
    
    //trf history
    public static List<Trfstate> TrfHistory(Integer trf_id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement =
                "select * "
                + "from trfstate "
                + "where trf_id=:trf_id";

        return (List<Trfstate>) s.createSQLQuery(prepared_statement).addEntity(Trfstate.class).setInteger("trf_id", trf_id).list();
    }
}
