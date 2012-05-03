package database.utilities;

import database.mapping.Trf;
import java.util.List;
import org.hibernate.Session;

public class Reports {

    //TRF with current date
    public static List<Trf> CurrentTrf(Integer page, Integer num) {
        Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        String prepared_statement = "select id,destination_id,customer_id,"
                + "emp_id,begin_date,end_date, car_rental, pay_by_cash,"
                + " cur_state, project_manager "
                + "from (select rownum r, trf.* from trf "
                + "where begin_date<sysdate and end_date>sysdate) "
                + "where r>:from and r<:to";
        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).
                setInteger("from", from).
                setInteger("to", to).list();
    }

    //TRF with current date filter by office
    public static List<Trf> CurrentTrfSameOffice(String city, String country, Integer page, Integer num) {
        Integer from = num * page;
        Integer to = (page + 1) * num;

        Session s = HibernateUtil.getSession();
//        String prepared_statement = "select * from trf join employee on  trf.emp_id=employee.id join "+ 
//                "office on employee.office_id=office.id join city on  "+
//                "office.city_id=city.id join country on  "+
//                "city.country_id=country.id  "+
//                "where country_name=:country  "+
//                "and city_name=:city "+
//                "and begin_date<(select sysdate from dual) and end_date>(select sysdate from dual) ";

        String prepared_statement = "select id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental,pay_by_cash, cur_state, project_manager "
                + "from (select rownum r, trf_office.* from trf_office "
                + "where country_name=:country "
                + "and city_name=:city "
                + "and begin_date<sysdate and end_date>sysdate) where r>:from and r<:to";

        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).
                setInteger("from", from).
                setInteger("to", to).setString("city", city).
                setString("country", country).list();
    }

    //TRF with current date filter by department
    public static List<Trf> CurrentTrfSameDepartment(String department, Integer page, Integer num) {
        Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
//        String prepared_statement = "select * "
//                + "from trf "
//                + "where begin_date<(select sysdate from dual) and end_date>(select sysdate from dual) "
//                + "and emp_id IN ( "
//                + "select id "
//                + "from employee "
//                + "where dep_id in (select id from department where dep_name=:department))";
        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental, pay_by_cash, cur_state, project_manager "
                + "FROM (select rownum r,trf_department.* "
                + "from trf_department "
                + "WHERE begin_date<sysdate AND end_date>sysdate "
                + "AND dep_name = :department) where r>:from and r<:to ";

        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).
                setInteger("from", from).
                setInteger("to", to).
                setString("department", department).list();
    }

    //TRF with current date filter by department and office
    public static List<Trf> CurrentTrfSameDepartmentOffice(String city, String country, String department, Integer page, Integer num) {
        Session s = HibernateUtil.getSession();
//        String prepared_statement = "select * from trf join employee on  trf.emp_id=employee.id join "+ 
//                "office on employee.office_id=office.id join city on  "+
//                "office.city_id=city.id join country on  "+
//                "city.country_id=country.id  "+
//                "join department on employee.dep_id=department.id "+
//                "where country_name=:country  "+
//                "and city_name=:city "+
//                "and begin_date<(select sysdate from dual) and end_date>(select sysdate from dual) "+
//                "and dep_name=:department";
        Integer from = num * page;
        Integer to = (page + 1) * num;
        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id,  "
                + " begin_date, end_date, car_rental, pay_by_cash, cur_state, project_manager  "
                + "FROM (select rownum r,trf_department_office.*  "
                + "from trf_department_office "
                + "WHERE city_name =:city AND country_name = :country  "
                + "AND dep_name =:department "
                + "AND begin_date < sysdate AND end_date > sysdate) where r>:from and r<:to";

        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).
                setInteger("from", from).
                setInteger("to", to).
                setString("city", city).
                setString("department", department).setString("country", country).list();
    }

    //Excel Report Button
    //all TRFs with "Entering" state
    // with current year 
    //with the same country as logged employee
    public static List<Trf> CurrentStatSameCountry(Integer id) {
        Session s = HibernateUtil.getSession();
//        String prepared_statement = "select * from trf join employee on  trf.emp_id=employee.id join "
//                + "office on employee.office_id=office.id join city on "
//                + "office.city_id=city.id join country on "
//                + "city.country_id=country.id "
//                + "where country.id in "
//                + "(select country.id from country join city "
//                + "on country.id=city.country_id join office "
//                + "on office.city_id=city.id join employee "
//                + "on employee.office_id=office.id where employee.id=:id) "
//                + "and trf.cur_state=0 and "
//                + "(extract (year from trf.begin_date))=(select to_char(sysdate,'yyyy') from dual) "
//                + "and (extract (year from trf.end_date))=(select to_char(sysdate,'yyyy') from dual)";
        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental, car_payment, cur_state, project_manager "
                + "FROM trf_office "
                + "WHERE cur_state = 0 "
                + "AND (extract (year from begin_date))=(select to_char(sysdate,'yyyy') from dual) "
                + "AND (extract (year from end_date))=(select to_char(sysdate,'yyyy') from dual) "
                + "AND country_name = "
                + "(SELECT country_name "
                + "FROM emp_office "
                + "WHERE id = :id) ";

        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setInteger("id", id).list();
    }

    //planned trf with "Ready" current status
    public static List<Trf> PlannedTrf(Integer page, Integer num) {
        Integer from = num * page;
        Integer to = (page + 1) * num;

        Session s = HibernateUtil.getSession();
        String prepared_statement = "select id,destination_id,customer_id, "
                + "emp_id,begin_date,end_date, car_rental, "
                + "pay_by_cash, cur_state, project_manager "
                + "from (select rownum r, trf.* "
                + " from trf "
                + "where begin_date> sysdate and cur_state=3) "
                + "where r>:from and r<:to";

        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).
                setInteger("from", from).
                setInteger("to", to).
                list();
    }

    //planned trf with "Ready" current status + filter by department
    public static List<Trf> PlannedTrfSameDepartment(String department, Integer page, Integer num) {
        Integer from = num * page;
        Integer to = (page + 1) * num;
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental, pay_by_cash, cur_state, project_manager "
                + "FROM (select rownum r, trf_department.* from trf_department "
                + "WHERE begin_date>sysdate AND cur_state=3 "
                + "AND dep_name=:department) where r>:from and r<:to";
        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).
                setInteger("from", from).
                setInteger("to", to).setString("department", department).list();
    }
    //planned trf with "Ready" current status + filter by department+filter by office

    public static List<Trf> PlannedTrfSameDepartmentOffice(String city, String country, String department, Integer page, Integer num) {
        Integer from = num * page;
        Integer to = (page + 1) * num;

        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental, pay_by_cash, cur_state, project_manager "
                + "FROM (select rownum r, trf_department.* from trf_department "
                + "WHERE begin_date>sysdate AND cur_state=3 "
                + "AND dep_name=:department "
                + "AND city_name=:city "
                + "AND country_name=:country) "
                + "where r>:from and r<:to";


        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).
                setString("city", city).setInteger("from", from).
                setInteger("to", to).
                setString("country", country).
                setString("department", department).list();
    }
    //planned trf with "Ready" current status + filter by office

    public static List<Trf> PlannedTrfSameOffice(String city, String country, Integer page, Integer num) {
        Integer from = num * page;
        Integer to = (page + 1) * num;

        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental, pay_by_cash, cur_state, project_manager "
                + "FROM (select rownum r, trf_office.* from trf_office "
                + "WHERE begin_date>sysdate AND cur_state=3 "
                + "AND city_name=:city AND country_name=:country) where r>:from and r<:to";

        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setInteger("from", from).
                setInteger("to", to).setString("city", city).setString("country", country).list();
    }

    //location of offices
    public static List GetOfficesLocation() {
        Session s = HibernateUtil.getSession();
//        String stmt = "select country_name as country, city.city_name as name "
//                + "from country join city on country.id = city.country_id ";
        String stmt = "SELECT city_name || ' ' || country_name "
                + "FROM city_country";
//        SQLQuery query = s.createSQLQuery(stmt);
//
//        java.util.List resq = query.list();
//        String[][] res = new String[resq.size()][2];
//        
//        for (int i=0;i<resq.size();i++)
//        {
//            String country = (String) ((Object[]) resq.get(i))[0];
//            String city = (String) ((Object[]) resq.get(i))[1];
//            
//               res[i][0] = country;
//            res[i][1] = city;}
//        return res;
        return (List) s.createSQLQuery(stmt).list();
    }
}
