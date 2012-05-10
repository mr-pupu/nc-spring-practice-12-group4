package database.utilities;

import database.mapping.Trf;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

public class Reports {
private static String[][] fillRes(List resq)
{
        
     String[][] res = new String[resq.size()][9];
    DateFormat reportDateFormat = new SimpleDateFormat("dd-MM-yyyy");
   
        for (int i = 0;i < resq.size(); i++) {
            BigDecimal trf_id = (BigDecimal) ((Object[]) resq.get(i))[0];
            String name = (String) ((Object[]) resq.get(i))[1];
            String surname = (String) ((Object[]) resq.get(i))[2];
            String office_city_name = (String) ((Object[]) resq.get(i))[3];
            String office_country_name = (String) ((Object[]) resq.get(i))[4];
            String dest_city_name = (String) ((Object[]) resq.get(i))[5];
            String dest_country_name = (String) ((Object[]) resq.get(i))[6];
            String begin_date = reportDateFormat.format((Date) ((Object[]) resq.get(i))[7]);
            String end_date = reportDateFormat.format((Date) ((Object[]) resq.get(i))[8]);
           
            res[i][0] = trf_id.toString();
            res[i][1] = name;
            res[i][2] = surname;
            res[i][3] = office_city_name;
            res[i][4] = office_country_name;
            res[i][5] = dest_city_name;
            res[i][6] = dest_country_name;
            res[i][7] = begin_date;
            res[i][8] = end_date;
       }
      return res;
}
    //TRF with current date
    public static String[][] CurrentTrf(Integer page, Integer num) {
        String prepared_statement = "select id, first_name, second_name, "
                + " office_city, office_country, dest_city, dest_country, begin_date,end_date"
                + " from (select rownum r, trfs_report.* "
                + " from trfs_report "
                + " where begin_date<sysdate and end_date>sysdate) "
                + " where r>:from and r<:to ";
  
    Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        List resq = s.createSQLQuery(prepared_statement)
                .setInteger("from", from)
                .setInteger("to", to)
                .list();
        return fillRes(resq);
    }
    //TRF with current date filter by office
    public static String[][] CurrentTrfSameOffice(String city, String country, Integer page, Integer num) {
    
        String prepared_statement ="select id, first_name, second_name, "+
                 "office_city, office_country, dest_city, dest_country, begin_date,end_date "+
                "from (select rownum r,  trfs_report.* "+
                "from trfs_report  "+
                "where office_country=:country "+
                "and office_city=:city  "+
                "and begin_date<sysdate and end_date>sysdate) where r>:from and r<:to ; ";
          
    Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        List resq = s.createSQLQuery(prepared_statement)
                .setInteger("from", from)
                .setInteger("to", to).setString("country", country).setString("city", city)
                .list();
        return fillRes(resq);

    }

    //TRF with current date filter by department
    public static String[][] CurrentTrfSameDepartment(String department, Integer page, Integer num) {
        String prepared_statement ="select id, first_name, second_name, "+
                 "office_city, office_country, dest_city, dest_country, begin_date,end_date "+
                "from (select rownum r,  trfs_report.* "+
                "from trfs_report  "+
                "where dep_name =:department"+
                "and begin_date<sysdate and end_date>sysdate) where r>:from and r<:to ; ";
  
        Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        List resq = s.createSQLQuery(prepared_statement)
                .setInteger("from", from)
                .setInteger("to", to).setString("department", department)
                .list();
        return fillRes(resq);  }

    //TRF with current date filter by department and office
    public static String[][] CurrentTrfSameDepartmentOffice(String city, String country, String department, Integer page, Integer num) {
     
        String prepared_statement ="select id, first_name, second_name, "+
                 "office_city, office_country, dest_city, dest_country, begin_date,end_date "+
                "from (select rownum r,  trfs_report.* "+
                "from trfs_report  "+
                "WHERE city_name =:city AND country_name = :country  "+
                "AND dep_name =:department "+
                "and begin_date<sysdate and end_date>sysdate) where r>:from and r<:to; ";
          
    Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        List resq = s.createSQLQuery(prepared_statement)
                .setInteger("from", from)
                .setInteger("to", to).setString("country", country).setString("city", city)
                .setString("department", department)
                .list();
        return fillRes(resq);
    }

    //Excel Report Button
    //all TRFs with "Entering" state
    // with current year 
    //with the same country as logged employee
    public static List CurrentStatSameCountry(String login) {
        String prepared_statement = "SELECT first_name, second_name, "
                + " office_city, office_country, dest_city, dest_country, begin_date,end_date, cur_state "
                + "FROM trfs_report "
                + "WHERE cur_state = 0 "
                + "AND (extract (year from begin_date))=(select to_char(sysdate,'yyyy') from dual) "
                + "AND (extract (year from end_date))=(select to_char(sysdate,'yyyy') from dual) "
                + "AND office_country = "
                + "(SELECT country_name "
                + "FROM emp_office "
                + "WHERE login = :name) ";
    Session s = HibernateUtil.getSession();
        List resq = s.createSQLQuery(prepared_statement)
                .setString("name", login)
                .list();
        return resq;
    }

    //planned trf with "Ready" current status
    public static String[][] plannedTrfs(Integer page, Integer num) {
        String prepared_statement = "select id, first_name, second_name, "
                + " office_city, office_country, dest_city, dest_country, begin_date,end_date"
                + " from (select rownum r, trfs_report.* "
                + " from trfs_report "
                + " where begin_date> sysdate and cur_state=3) "
                + " where r>:from and r<:to ";

    Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        List resq = s.createSQLQuery(prepared_statement)
                .setInteger("from", from)
                .setInteger("to", to)
                .list();
        return fillRes(resq);         }

    //planned trf with "Ready" current status + filter by department
    public static String[][] PlannedTrfSameDepartment(String department, Integer page, Integer num) {
        String prepared_statement = "select id, first_name, second_name, "
                + " office_city, office_country, dest_city, dest_country, begin_date,end_date"
                + " from (select rownum r, trfs_report.* "
                + " from trfs_report "
                + " where begin_date> sysdate and cur_state=3 AND dep_name=:department) "
                + " where r>:from and r<:to ";
   
        Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        List resq = s.createSQLQuery(prepared_statement)
                .setInteger("from", from)
                .setInteger("to", to).setString("department", department)
                .list();
        return fillRes(resq);
    }
    
    //planned trf with "Ready" current status + filter by department+filter by office
    public static String[][] PlannedTrfSameDepartmentOffice(String city, String country, String department, Integer page, Integer num) {

           String prepared_statement = "select id, first_name, second_name, "
                + " office_city, office_country, dest_city, dest_country, begin_date,end_date"
                + " from (select rownum r, trfs_report.* "
                + " from trfs_report "
                + " where begin_date> sysdate and cur_state=3 AND dep_name=:department "
                +" AND city_name=:city "
                + "AND country_name=:country) "
                + " where r>:from and r<:to ";
    
    Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        List resq = s.createSQLQuery(prepared_statement)
                .setInteger("from", from)
                .setInteger("to", to).setString("department", department).
                setString("country", country).setString("city", city)
                .list();
        return fillRes(resq);
}
    //planned trf with "Ready" current status + filter by office
 public static String[][] PlannedTrfSameOffice(String city, String country, Integer page, Integer num) {
           String prepared_statement = "select id, first_name, second_name, "
                + " office_city, office_country, dest_city, dest_country, begin_date,end_date"
                + " from (select rownum r, trfs_report.* "
                + " from trfs_report "
                + " where begin_date> sysdate and cur_state=3 "
                +" AND city_name=:city "
                + "AND country_name=:country) "
                + " where r>:from and r<:to ";

    Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        List resq = s.createSQLQuery(prepared_statement)
                .setInteger("from", from)
                .setInteger("to", to).setString("country", country).setString("city", city)
                .list();
        return fillRes(resq);    }

    //location of offices
    public static List GetOfficesLocation() {
        Session s = HibernateUtil.getSession();
        String stmt = "SELECT city_name || ' ' || country_name "
                + "FROM city_country";
        return (List) s.createSQLQuery(stmt).list();
    }

}