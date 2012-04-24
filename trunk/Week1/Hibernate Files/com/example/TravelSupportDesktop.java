package TRF;

import java.util.List;
import mapping.Trf;
import org.hibernate.Session;

public class TravelSupportDesktop {
    //"in process" table
    //TRF the state of which is "Ready", and employee from "Employee Name field" 
    // works in the same country as logged employee
    public static List<Trf> ReadyTRFsSameCountry(Integer id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select * from trf join employee on  trf.emp_id=employee.id join "
                + "office on employee.office_id=office.id join city on "
                + "office.city_id=city.id join country on "
                + "city.country_id=country.id "
                + "where trf.cur_state=3 and country.id in "
                + "(select country.id from country join city "
                + "on country.id=city.country_id join office "
                + "on office.city_id=city.id join employee "
                + "on employee.office_id=office.id where employee.id=:id)";

        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setInteger("id", id).list();
    }

    //TRFs, in which employee from "Employee Name" field works in the same country
    //as logged employee
    //filter by TRFs, in which the last change of status occured during last month
    public static List TrfLastMonthSameCountry(Integer id) {
        Session s = HibernateUtil.getSession();

        String prepared_statement = "select * from trf join employee on  trf.emp_id=employee.id join "+
                 "office on employee.office_id=office.id join city on "+ 
                 "office.city_id=city.id join country on  "+
                 "city.country_id=country.id  "+
                 "join department on department.id=employee.dep_id  "+
                 "where country.id in  "+
                   "              (select country.id from country join city  "+
                   "              on country.id=city.country_id join office  "+
                   "                             on office.city_id=city.id join employee  "+
                   "                            on employee.office_id=office.id where employee.id=:id) "+ 
                "and trf.id in (select trf_id from trfstate where change_date in "+
 "(select (max (change_date)) "+
 "from trfstate "+
 "group by trf_id) and extract (month from change_date)=(select to_char(sysdate,'mm') from dual))"; 

        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setInteger("id", id).list();
    }

    //TRFs, in which employee from "Employee Name" field works in the same country
    //as logged employee
    //filter by TRFs, in which the last change of status occured during last month
    // and filter by given department
    public static List TrfLastMonthSameCountryFilterByDepartment(Integer id, String department) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select * from trf join employee on  trf.emp_id=employee.id join "+
                 "office on employee.office_id=office.id join city on "+ 
                 "office.city_id=city.id join country on  "+
                 "city.country_id=country.id  "+
                 "join department on department.id=employee.dep_id  "+
                 "where country.id in  "+
                   "              (select country.id from country join city  "+
                   "              on country.id=city.country_id join office  "+
                   "                             on office.city_id=city.id join employee  "+
                   "                            on employee.office_id=office.id where employee.id=:id) "+ 
                "and trf.id in (select trf_id from trfstate where change_date in "+
 "(select (max (change_date)) "+
 "from trfstate "+
 "group by trf_id) and extract (month from change_date)=(select to_char(sysdate,'mm') from dual)) "+ 
                "and department.dep_name=:department";

        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setInteger("id", id).setString("department", department).list();
    }


    //TRFs, in which employee from "Employee Name" field works in the same country
    //as logged employee
    //filter by TRF's date
    public static List TrfSameCountryFilterByDate(Integer id, String begin_date, String end_date) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select * from trf "
                + "join employee on trf.emp_id=employee.id join "
                + "office on employee.office_id=office.id join city on "
                + "office.city_id=city.id join country on "
                + "city.country_id =country.id "
                + "where country.id in "
                + "(select country.id from country join city "
                + "on country.id=city.country_id join office "
                + "on office.city_id=city.id join employee "
                + "on employee.office_id=office.id where employee.id=:id)"
                + "and trf.begin_date>:begin_date and trf.end_date<:end_date";

        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setInteger("id", id).setString("begin_date", begin_date).
                setString("end_date", end_date).list();
    }

  
    //TRFs, in which employee from "Employee Name" field works in the same country
    //as logged employee
    //filter by TRF's date and department
          public static List TrfSameCountryFilterByDateDepartment(Integer id, String begin_date, String end_date, String department) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select * from trf "
                + "join employee on trf.emp_id=employee.id join "
                + "office on employee.office_id=office.id join city on "
                + "office.city_id=city.id join country on "
                + "city.country_id =country.id "
                + "join department on department.id=employee.dep_id "
                + "where country.id in "
                + "(select country.id from country join city "
                + "on country.id=city.country_id join office "
                + "on office.city_id=city.id join employee "
                + "on employee.office_id=office.id where employee.id=:id)"
                + "and trf.begin_date>:begin_date and trf.end_date<:end_date "
                + "and department.dep_name=:department";
        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setString("department", department).
                setInteger("id", id).setString("begin_date", begin_date).
                setString("end_date", end_date).list();
    }
}
