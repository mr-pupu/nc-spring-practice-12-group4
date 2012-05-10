package database.utilities;

import database.mapping.Customer;
import database.mapping.Trfstate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class TrfEdit {

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
//                "select first_name, second_name "
//                + "from employee "
//                + "where dep_id in "
//                + "(select dep_id "
//                + "from employee "
//                + "where id=:pm_id)";
//        SQLQuery query = s.createSQLQuery(prepared_statement);
//        query.setInteger("pm_id", pm_id).list();
//        java.util.List resq = query.list();
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
//        String[][] res = new String[resq.size()][2];
//        for (int i = 0; i < resq.size(); i++) {
//            String first_name = (String) ((Object[]) resq.get(i))[0];
//            String second_name = (String) ((Object[]) resq.get(i))[1];
//            res[i][0] = first_name;
//            res[i][1] = second_name;
//        }
//        return res;

    }

    /**
     * Obtain the employee's project manager through his login
     */
    public static String[] DepManagerNameByLogin(String login) {
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

    // РЅРµ РїСЂР°РІРёР»СЊРЅРѕ СЂРµР°Р»С–Р·РѕРІР°РЅР° Р»РѕС–РіРєР° РјРµС‚РѕРґСѓ: 
    // РїРµСЂРµРІС–СЂ
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
//                "select first_name, second_name "
//                + "from employee join  department on employee.id=department.manager_id "
//                + "where department.id in "
//                + "(select dep_id  from  employee where id=:id)";
//        String[] res = new String[2];
//        SQLQuery query = s.createSQLQuery(prepared_statement);
//        query.setInteger("id", id).list();
//        java.util.List resq = query.list();
//        String first_name = (String) ((Object[]) resq.get(0))[0];
//        String second_name = (String) ((Object[]) resq.get(0))[1];
//        res[0] = first_name;
//        res[1] = second_name;
//
//        return res;
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
//                "select first_name, second_name "
//                + "from employee join department on employee.id=department.manager_id "
//                + "where department.id in "
//                + "(select parent_id "
//                + "from department join employee on department.id=employee.dep_id "
//                + "where employee.id=:id)";

//        String[] res = new String[2];
//        SQLQuery query = s.createSQLQuery(prepared_statement);
//        query.setInteger("id", id).list();
//        java.util.List resq = query.list();
//        String first_name = (String) ((Object[]) resq.get(0))[0];
//        String second_name = (String) ((Object[]) resq.get(0))[1];
//        res[0] = first_name;
//        res[1] = second_name;
//
//        return res;
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
//                "select country_name as country, city.city_name as name "
//                + "from country join city on country.id = city.country_id "
//                + "where city.id IN"
//                + "(select office.city_id "
//                + "from office "
//                + "where office.id = (select employee.office_id "
//                + "from employee "
//                + "where employee.id=:emp_id))";
//        SQLQuery query = s.createSQLQuery(stmt);
//        //   .addScalar("country", Hibernate.STRING)
//        // .addScalar("name", Hibernate.STRING);
//        query.setInteger("emp_id", emp_id);
//        String[] res = new String[2];
//
//        java.util.List resq = query.list();
//        if (!resq.isEmpty() && resq.size() == 1) {
//            String country = (String) ((Object[]) resq.get(0))[0];
//            String city = (String) ((Object[]) resq.get(0))[1];
//            res[0] = city;
//            res[1] = country;
//        }
//        return res;
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
    //the list of cities of given country
//    public static List РЎountryCities(String country) {
//        Session s = HibernateUtil.getSession();
//        String prepared_statement = 
//                "select city_name "
//                + "from city "
//                + "where country_id in "
//                + "(select id "
//                + "from country "
//                + "where country_name=:country)";
//
//        return (List) s.createSQLQuery(prepared_statement).setString("country", country).list();
//    }
    //this list can be obtained simply by calling something like this:
    //Country c = new Country()
    //Set<City> a = c.getCities()
    //trf history

    public static List<Trfstate> TrfHistory(Integer trf_id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement =//"select commentary, change_date, status "+
                "select * "
                + "from trfstate "
                + "where trf_id=:trf_id";

        return (List<Trfstate>) s.createSQLQuery(prepared_statement).addEntity(Trfstate.class).setInteger("trf_id", trf_id).list();

        //return (List) s.createSQLQuery(prepared_statement).setInteger("trf_id", trf_id).list();
    }
}
