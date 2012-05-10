package database.utilities;

import database.mapping.Trf;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

public class TravelSupportDesktop {
    //"in process" table
    //TRF the state of which is "Ready", and employee from "Employee Name field" 
    // works in the same country as logged employee
    //

    public static List<Trf> ReadyTRFsSameCountry(Integer id, Integer page, Integer num) {
        Session s = HibernateUtil.getSession();
        Integer from = page * num;
        Integer to = (page + 1) * num;
        String prepared_statement = " select id,destination_id, customer_id, emp_id, "
                + " begin_date,end_date, car_rental, pay_by_cash, cur_state, project_manager"
                + "    from(SELECT rownum r, tr.*"
                + "    FROM trf_office tr"
                + "    WHERE cur_state=3 AND country_name="
                + "   (SELECT country_name"
                + "   FROM emp_office "
                + "    WHERE id=:id))"
                + "    where r>:fromi and r<:toi";

//                "select * from trf join employee on  trf.emp_id=employee.id join "
//                + "office on employee.office_id=office.id join city on "
//                + "office.city_id=city.id join country on "
//                + "city.country_id=country.id "
//                + "where trf.cur_state=3 and country.id in "
//                + "(select country.id from country join city "
//                + "on country.id=city.country_id join office "
//                + "on office.city_id=city.id join employee "
//                + "on employee.office_id=office.id where employee.id=:id)";

        return (List<Trf>) s.createSQLQuery(prepared_statement).
                addEntity(Trf.class).setInteger("id", id).
                setInteger("fromi", from).setInteger("toi", to).list();
    }

    //TRFs, in which employee from "Employee Name" field works in the same country
    //as logged employee
    //filter by TRFs, in which the last change of status occured during last month
    //
    public static List<Trf> TrfLastMonthSameCountry(Integer id, Integer page, Integer num) {
        Session s = HibernateUtil.getSession();
        Integer from = page * num;
        Integer to = (page + 1) * num;

        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id,  "
                + "               begin_date, end_date, car_rental, pay_by_cash, cur_state, project_manager      "
                + "           from (SELECT rownum r, id, destination_id, customer_id, emp_id,   "
                + "              begin_date, end_date, car_rental, pay_by_cash, cur_state, project_manager       "
                + "          FROM trf_state_office             "
                + "    WHERE extract (month from maxdate)=(select to_char(sysdate,'mm') from dual)        "
                + "        AND country_name =            "
                + "     (SELECT country_name       "
                + "          FROM emp_office             "
                + "    WHERE id=:id))       "
                + "         where r>:fromi and r<:toi";

//                "select * from trf join employee on  trf.emp_id=employee.id join "+
//                 "office on employee.office_id=office.id join city on "+ 
//                 "office.city_id=city.id join country on  "+
//                 "city.country_id=country.id  "+
//                 "join department on department.id=employee.dep_id  "+
//                 "where country.id in  "+
//                   "              (select country.id from country join city  "+
//                   "              on country.id=city.country_id join office  "+
//                   "                             on office.city_id=city.id join employee  "+
//                   "                            on employee.office_id=office.id where employee.id=:id) "+ 
//                "and trf.id in (select trf_id from trfstate where change_date in "+
// "(select (max (change_date)) "+
// "from trfstate "+
// "group by trf_id) and extract (month from change_date)=(select to_char(sysdate,'mm') from dual))"; 

        return (List<Trf>) s.createSQLQuery(prepared_statement).
                addEntity(Trf.class).setInteger("id", id).
                setInteger("fromi", from).setInteger("toi", to).list();
    }

    //TRFs, in which employee from "Employee Name" field works in the same country
    //as logged employee
    //filter by TRFs, in which the last change of status occured during last month
    // and filter by given department
    //NE ZROBLENO!!!!!!!!
    public static List<Trf> TrfLastMonthSameCountryFilterByDepartment(Integer id, String department, Integer page, Integer num) {
        Integer from = page * num;
        Integer to = (page + 1) * num;

        Session s = HibernateUtil.getSession();

        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental, car_payment, cur_state, project_manager "
                + "FROM trf_state_department "
                + "WHERE extract (month from maxdate)=(select to_char(sysdate,'mm') from dual) "
                + "AND dep_name=:department "
                + "AND country_name = "
                + "(SELECT country_name "
                + "FROM emp_office "
                + "WHERE id=:id)";

//                "select * from trf join employee on  trf.emp_id=employee.id join "+
//                 "office on employee.office_id=office.id join city on "+ 
//                 "office.city_id=city.id join country on  "+
//                 "city.country_id=country.id  "+
//                 "join department on department.id=employee.dep_id  "+
//                 "where country.id in  "+
//                   "              (select country.id from country join city  "+
//                   "              on country.id=city.country_id join office  "+
//                   "                             on office.city_id=city.id join employee  "+
//                   "                            on employee.office_id=office.id where employee.id=:id) "+ 
//                "and trf.id in (select trf_id from trfstate where change_date in "+
// "(select (max (change_date)) "+
// "from trfstate "+
// "group by trf_id) and extract (month from change_date)=(select to_char(sysdate,'mm') from dual)) "+ 
//                "and department.dep_name=:department";

        return (List<Trf>) s.createSQLQuery(prepared_statement).
                addEntity(Trf.class).
                setInteger("fromi", from).setInteger("toi", to).
                setInteger("id", id).setString("department", department).list();
    }

    //TRFs, in which employee from "Employee Name" field works in the same country
    //as logged employee
    //filter by TRF's date
    //
    public static List<Trf> TrfSameCountryFilterByDate(Integer id, Date begin_date, Date end_date, Integer page, Integer num) {
        Session s = HibernateUtil.getSession();
        Integer from = page * num;
        Integer to = (page + 1) * num;

        String prepared_statement = "select id, destination_id, customer_id, emp_id, "
                + " begin_date, end_date, car_rental, pay_by_cash, cur_state, project_manager "
                + "   from(SELECT rownum r, id, destination_id, customer_id, emp_id, "
                + "  begin_date, end_date, car_rental, pay_by_cash, cur_state, project_manager "
                + "   FROM trf_office "
                + "    WHERE begin_date >:begin_date AND end_date>:end_date "
                + "    AND country_name="
                + "    (SELECT country_name"
                + "   FROM emp_office "
                + "    WHERE id=:id))"
                + "   where r>:fromi and r<:toi";

//                "select * from trf "
//                + "join employee on trf.emp_id=employee.id join "
//                + "office on employee.office_id=office.id join city on "
//                + "office.city_id=city.id join country on "
//                + "city.country_id =country.id "
//                + "where country.id in "
//                + "(select country.id from country join city "
//                + "on country.id=city.country_id join office "
//                + "on office.city_id=city.id join employee "
//                + "on employee.office_id=office.id where employee.id=:id)"
//                + "and trf.begin_date>:begin_date and trf.end_date<:end_date";

        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).
                setInteger("id", id).setDate("begin_date", begin_date).setDate("end_date", end_date)
                .setInteger("fromi", from).setInteger("toi", to).list();
    }

    //TRFs, in which employee from "Employee Name" field works in the same country
    //as logged employee
    //filter by TRF's date and department
    //check department_name field in view
    //
    public static List<Trf> TrfSameCountryFilterByDateDepartment(Integer id, Date begin_date, 
            Date end_date, String department, Integer page, Integer num) {
        Session s = HibernateUtil.getSession();
        Integer from = page * num;
        Integer to = (page + 1) * num;

        String prepared_statement = "select id, destination_id, customer_id, emp_id, "
                + "      begin_date, end_date, car_rental, pay_by_cash, cur_state, project_manager  "
                + "     from(sELECT rownum r, id, destination_id, customer_id, emp_id,  "
                + "    begin_date, end_date, car_rental, pay_by_cash, cur_state, project_manager  "
                + "      FROM trf_department_office  "
                + "      WHERE begin_date > :begin_date  AND end_date>:end_date  "
                + "     AND dep_name=:department  "
                + "      AND country_name= "
                + "      (SELECT country_name  "
                + "      FROM emp_office  "
                + "      WHERE id=:id)) "
                + "     where r > :toi and r< :fromi";

//                "select * from trf "
//                + "join employee on trf.emp_id=employee.id join "
//                + "office on employee.office_id=office.id join city on "
//                + "office.city_id=city.id join country on "
//                + "city.country_id =country.id "
//                + "join department on department.id=employee.dep_id "
//                + "where country.id in "
//                + "(select country.id from country join city "
//                + "on country.id=city.country_id join office "
//                + "on office.city_id=city.id join employee "
//                + "on employee.office_id=office.id where employee.id=:id)"
//                + "and trf.begin_date>:begin_date and trf.end_date<:end_date "
//                + "and department.dep_name=:department";
        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class)
                .setString("department", department).setInteger("id", id).setDate("begin_date", begin_date)
                .setInteger("fromi", from).setInteger("toi", to).setDate("end_date", end_date).list();
    }
}
