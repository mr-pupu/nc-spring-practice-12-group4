
package TRF;

import com.example.datamodel.Trfstate;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class TrfEdit {
    
    //current status of given trf
    public static List CurrentTrfStatus(Integer trf_id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select cur_state "
                + "from trf "
                + "where id=:trf_id";

        return (List) s.createSQLQuery(prepared_statement).setInteger("trf_id", trf_id).list();
    }

    //the list of given project manager's employee
    public static List ProjectManagerEmployees(Integer pm_id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT first_name || ' ' || second_name "
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
//        String[][] res = new String[resq.size()][2];
//        for (int i = 0; i < resq.size(); i++) {
//            String first_name = (String) ((Object[]) resq.get(i))[0];
//            String second_name = (String) ((Object[]) resq.get(i))[1];
//            res[i][0] = first_name;
//            res[i][1] = second_name;
//        }
//        return res;
        return (List) s.createSQLQuery(prepared_statement).setInteger("pm_id", pm_id).list();
    }

    //the list of countries
    public static List Countries() {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select country_name "
                + "from country";

        return (List) s.createSQLQuery(prepared_statement).list();
    }

    //name of manager of given employee
    public static List DepManagerName(Integer id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT manname ||' '|| mansurname "
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
        return (List) s.createSQLQuery(prepared_statement).setInteger("id", id).list();
    }

    //name of the manager of higher deparment than the department of given employee
    public static List ParentDepManagerName(Integer id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT manname ||' '|| mansurname "
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
        return (List) s.createSQLQuery(prepared_statement).setInteger("id", id).list();
    }

    //list of customers
    public static List Customers() {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select cust_name "
                + "from customer";
        return (List) s.createSQLQuery(prepared_statement).list();
    }

    //location of given employee (country and city)
    public static List GetLocationForEmp(int emp_id) {
        Session s = HibernateUtil.getSession();
        String stmt = "SELECT country_name || ' ' || city_name "
                + "FROM employee_office "
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
        return (List) s.createSQLQuery(stmt).setInteger("emp_id", emp_id).list();
    }

    //the list of cities of given country
//    public static List СountryCities(String country) {
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
     public static List TrfHistory(Integer trf_id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement =//"select commentary, change_date, status "+
                "select * "
                + "from trfstate "
                + "where trf_id=:trf_id";

        return (List) s.createSQLQuery(prepared_statement).addEntity(Trfstate.class).setInteger("trf_id", trf_id).list();

        //return (List) s.createSQLQuery(prepared_statement).setInteger("trf_id", trf_id).list();
    }
}