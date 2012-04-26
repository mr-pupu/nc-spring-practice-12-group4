package TRF;
import com.example.datamodel.Trf;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class Reports {

       //TRF with current date
        public static List CurrentTrf() {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select * "
                + "from trf "
                + "where begin_date<sysdate and end_date>sysdate ";
        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).list();
    }

      //TRF with current date filter by office
        public static List CurrentTrfSameOffice(String city, String country) {
        Session s = HibernateUtil.getSession();
//        String prepared_statement = "select * from trf join employee on  trf.emp_id=employee.id join "+ 
//                "office on employee.office_id=office.id join city on  "+
//                "office.city_id=city.id join country on  "+
//                "city.country_id=country.id  "+
//                "where country_name=:country  "+
//                "and city_name=:city "+
//                "and begin_date<(select sysdate from dual) and end_date>(select sysdate from dual) ";
        
        String prepared_statement = "select id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental, car_payment, cur_state "
                + "from trf_office " +
                "where country_name=:country "+
                "and city_name=:city "+
                "and begin_date<sysdate and end_date>sysdate ";
        
        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setString("city", city).
                setString("country", country).list();
    }

        //TRF with current date filter by department
        public static List CurrentTrfSameDepartment(String department) {
        Session s = HibernateUtil.getSession();
//        String prepared_statement = "select * "
//                + "from trf "
//                + "where begin_date<(select sysdate from dual) and end_date>(select sysdate from dual) "
//                + "and emp_id IN ( "
//                + "select id "
//                + "from employee "
//                + "where dep_id in (select id from department where dep_name=:department))";
        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental, car_payment, cur_state "
                + "FROM trf_department "
                + "WHERE begin_date<sysdate AND end_date>sysdate "
                + "AND dep_name = :department";
        
        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setString("department", department).list();
    }

        //TRF with current date filter by department and office
        public static List CurrentTrfSameDepartmentOffice(String city, String country, String department) {
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
        
        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental, car_payment, cur_state "
                + "FROM trf_department_office "
                + "WHERE city_name = :city AND country_name = :country "
                + "AND dep_name = :department "
                + "AND begin_date < sysdate AND end_date > sysdate";
        
        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setString("city", city).
                setString("department", department).setString("country", country).list();
    }
      
    //Excel Report Button
    //all TRFs with "Entering" state
    // with current year 
    //with the same country as logged employee
     public static List CurrentStatSameCountry(Integer id) {
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
                + "begin_date, end_date, car_rental, car_payment, cur_state "
                + "FROM trf_office "
                + "WHERE cur_state = 0 "
                + "AND (extract (year from begin_date))=(select to_char(sysdate,'yyyy') from dual) "
                + "AND (extract (year from end_date))=(select to_char(sysdate,'yyyy') from dual) "
                + "AND country_name = "
                + "(SELECT country_name "
                + "FROM emp_office "
                + "WHERE id = :id) ";

        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setInteger("id", id).list();
    }

    //planned trf with "Ready" current status
    public static List PlannedTrf() {
    Session s = HibernateUtil.getSession();
        String prepared_statement = "select * "
                + "from trf "
                + "where begin_date>sysdate and cur_state=3 ";
        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).list();
     }

     //planned trf with "Ready" current status + filter by department
        public static List PlannedTrfSameDepartment(String department) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental, car_payment, cur_state "
                + "FROM trf_department "
                + "WHERE begin_date>sysdate AND cur_state=3 "
                + "AND dep_name=:department";
        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setString("department", department).list();
    }
        //planned trf with "Ready" current status + filter by department+filter by office
        public static List PlannedTrfSameDepartmentOffice(String city, String country, String department) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental, car_payment, cur_state "
                + "FROM trf_department_office "
                + "WHERE begin_date > sysdate AND cur_state=3 "
                + "AND dep_name=:department AND city_name=:city "
                + "AND country_name=:country";
                
        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).
                setString("city", city).
                setString("country", country).
                setString("department", department).list();
    }
        //planned trf with "Ready" current status + filter by office
        public static List PlannedTrfSameOffice(String city, String country) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT id, destination_id, customer_id, emp_id, "
                + "begin_date, end_date, car_rental, car_payment, cur_state "
                + "FROM trf_office "
                + "WHERE begin_date>sysdate AND cur_date=3 "
                + "AND city_name=:city AND country_name=:country";
                
        return (List) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setString("city", city).setString("country", country).list();
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
