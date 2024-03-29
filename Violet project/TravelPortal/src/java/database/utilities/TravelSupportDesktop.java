package database.utilities;

import database.mapping.Destination;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

public class TravelSupportDesktop {

    private static String[][] fillRes(List resq) {
        String[][] res = new String[resq.size()][9];
        DateFormat reportDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (int i = 0; i < resq.size(); i++) {
            BigDecimal trf_id = (BigDecimal) ((Object[]) resq.get(i))[0];
            String name = (String) ((Object[]) resq.get(i))[1];
            String surname = (String) ((Object[]) resq.get(i))[2];
            String dest_city_name = (String) ((Object[]) resq.get(i))[3];
            String dest_country_name = (String) ((Object[]) resq.get(i))[4];
            String begin_date = reportDateFormat.format((Date) ((Object[]) resq.get(i))[5]);
            String end_date = reportDateFormat.format((Date) ((Object[]) resq.get(i))[6]);
            BigDecimal status = (BigDecimal) ((Object[]) resq.get(i))[7];
            String comment = (String) ((Object[]) resq.get(i))[8];

            res[i][0] = trf_id.toString();
            res[i][1] = name;
            res[i][2] = surname;
            res[i][3] = dest_city_name;
            res[i][4] = dest_country_name;
            res[i][5] = begin_date;
            res[i][6] = end_date;
            res[i][7] = database.mapping.Trf.getStatus(status.intValue());
            res[i][8] = comment;
        }
        return res;
    }

    /**
     * @author Allan
     * @param login
     * @return the total number of trfs in status ready in same country slow,
     * but working
     */
    public static long countReadyTRFsSameCountry(String login) {
        String prepared_statement = "select count(*)"
                + " from trfs_report"
                + "    WHERE cur_state=3 AND office_country="
                + "   (SELECT country_name"
                + "   FROM emp_office "
                + "    WHERE login=:login)";
        Session s = HibernateUtil.getSession();
        Long res = Long.parseLong(s.createSQLQuery(prepared_statement).
                setString("login", login).list().get(0).toString());
        return res;
    }

    //"in process" table
    //TRF the state of which is "Ready", and employee from "Employee Name field" 
    // works in the same country as logged employee
    public static String[][] ReadyTRFsSameCountry(String login, int page, int rows) {
        System.out.println("login " + login);
        String prepared_statement = "select id, first_name, second_name, "
                + " dest_city, dest_country, begin_date,end_date, cur_state, commentary"
                + " from (select rownum r, trfs_report.*"
                + " from trfs_report"
                + "    WHERE cur_state=3 AND office_country="
                + "   (SELECT country_name"
                + "   FROM emp_office "
                + "    WHERE login=:login))"
                + "    where r> :from and r<= :to";

        Session s = HibernateUtil.getSession();
        int from = (page - 1) * rows;
        int to = from + rows;
        List resq = s.createSQLQuery(prepared_statement).setString("login", login).setInteger("from", from).setInteger("to", to).list();
        return fillRes(resq);
    }

    //TRFs, in which employee from "Employee Name" field works in the same country
    //as logged employee
    //filter by TRFs, in which the last change of status occured during last month
    //
    public static String[][] TrfLastMonthSameCountry(String login, Integer page, Integer num) {

        String prepared_statement = "select id, first_name, second_name,"
                + "  dest_city, dest_country, begin_date,end_date, cur_state, commentary"
                + "  from (select rownum r, trfs_report.* "
                + "  from trfs_report JOIN trf_state_office tso ON tso.id=trfs_report.id "
                + "    WHERE extract (month from maxdate)=(select to_char(sysdate,'mm') from dual)"
                + "       AND office_country =            "
                + "    (SELECT country_name       "
                + "        FROM emp_office             "
                + "  WHERE login=:login))       "
                + "       where r> :from and r<= :to ";
        Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        List resq = s.createSQLQuery(prepared_statement).setInteger("from", from).setInteger("to", to).setString("login", login).list();
        return fillRes(resq);
    }

    //TRFs, in which employee from "Employee Name" field works in the same country
    //as logged employee
    //filter by TRFs, in which the last change of status occured during last month
    // and filter by given department
    //NE ZROBLENO!!!!!!!!
    public static String[][] TrfLastMonthSameCountryFilterByDepartment(Integer id, String department, Integer page, Integer num) {

        String prepared_statement = "select id, first_name, second_name,"
                + "  dest_city, dest_country, begin_date,end_date, cur_state, commentary"
                + "  from (select rownum r, trfs_report.* "
                + "  from trfs_report JOIN trf_state_office tso ON tso.id=trfs_report.id "
                + "    WHERE extract (month from maxdate)=(select to_char(sysdate,'mm') from dual)"
                + "AND dep_name=:department "
                + "       AND office_country =            "
                + "    (SELECT country_name       "
                + "        FROM emp_office             "
                + "  WHERE id=:id))       "
                + "       where r> :from and r<= :to ";

        Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        List resq = s.createSQLQuery(prepared_statement).setInteger("from", from).setInteger("id", id).setInteger("to", to).setString("department", department).list();
        return fillRes(resq);
    }

    //TRFs, in which employee from "Employee Name" field works in the same country
    //as logged employee
    //filter by TRF's date
    //
    public static String[][] TrfSameCountryFilterByDate(Integer id, Date begin_date, Date end_date, int page, int rows) {
        String prepared_statement = "select id, first_name, second_name, "
                + " dest_city, dest_country, begin_date,end_date, cur_state, commentary"
                + "  from (select rownum r, trfs_report.* "
                + " from trfs_report "
                + "    WHERE begin_date >:begin_date AND end_date<:end_date "
                + "    AND office_country="
                + "    (SELECT country_name"
                + "   FROM emp_office "
                + "    WHERE id=:id))"
                + "   where r> :from and r<= :to";

        Session s = HibernateUtil.getSession();
        int from = (page - 1) * rows;
        int to = from + rows;
        List resq = s.createSQLQuery(prepared_statement).setInteger("from", from).setInteger("to", to).setInteger("id", id).setDate("begin_date", begin_date).setDate("end_date", end_date).list();
        return fillRes(resq);
    }

    //TRFs, in which employee from "Employee Name" field works in the same country
    //as logged employee
    //filter by TRF's date and department
    //check department_name field in view
    //
    public static String[][] TrfSameCountryFilterByDateDepartment(Integer id, Date begin_date,
            Date end_date, String department, int page, int rows) {

        String prepared_statement = "select id, first_name, second_name, "
                + " dest_city, dest_country, begin_date,end_date, cur_state, commentary"
                + " from (select rownum r, trfs_report.* "
                + " from trfs_report "
                + "      WHERE begin_date > :begin_date  AND end_date<:end_date  "
                + "     AND dep_name=:department  "
                + "      AND office_country= "
                + "      (SELECT country_name  "
                + "      FROM emp_office  "
                + "      WHERE id=:id)) "
                + "     where r > :to and r<= :from";
        Session s = HibernateUtil.getSession();
        int from = (page - 1) * rows;
        int to = from + rows;
        List resq = s.createSQLQuery(prepared_statement).setInteger("from", from).setInteger("to", to).setDate("begin_date", begin_date).setDate("end_date", end_date).setInteger("id", id).setString("department", department).list();
        return fillRes(resq);
    }

    public static long countTrfLastMonthSameCountry(String login) {
        String prepared_statement = "select count(*)"
                + "  from trfs_report JOIN trf_state_office tso ON tso.id=trfs_report.id "
                + "    WHERE extract (month from maxdate)=(select to_char(sysdate,'mm') from dual)"
                + "       AND office_country =            "
                + "    (SELECT country_name       "
                + "        FROM emp_office             "
                + "  WHERE login=:login)       ";
        Session s = HibernateUtil.getSession();
        Long resq = Long.parseLong(s.createSQLQuery(prepared_statement).
                setString("login", login).list().get(0).toString());
        return resq;
    }

    public static long countTrfLastMonthSameCountryFilterByDepartment(Integer id, String department) {

        String prepared_statement = "select count(*)"
                + "  from trfs_report JOIN trf_state_office tso ON tso.id=trfs_report.id "
                + "    WHERE extract (month from maxdate)=(select to_char(sysdate,'mm') from dual)"
                + "AND dep_name=:department "
                + "       AND office_country =            "
                + "    (SELECT country_name       "
                + "        FROM emp_office             "
                + "  WHERE id=:id)      ";

        Session s = HibernateUtil.getSession();
        Long resq = Long.parseLong(s.createSQLQuery(prepared_statement).setInteger("id", id).setString("department", department).list().get(0).toString());
        return resq;
    }

    public static long countTrfSameCountryFilterByDate(Integer id, Date begin_date, Date end_date) {
        String prepared_statement = "select count(*) "
                + " from trfs_report "
                + "    WHERE begin_date >:begin_date AND end_date<:end_date "
                + "    AND office_country="
                + "    (SELECT country_name"
                + "   FROM emp_office "
                + "    WHERE id=:id)";

        Session s = HibernateUtil.getSession();
        Long resq = Long.parseLong(s.createSQLQuery(prepared_statement).
                setInteger("id", id).setDate("begin_date", begin_date).
                setDate("end_date", end_date).list().get(0).toString());
        return resq;
    }

    public static long countTrfSameCountryFilterByDateDepartment(Integer id, Date begin_date,
            Date end_date, String department) {

        String prepared_statement = "select count(*) "
                + " from trfs_report "
                + "      WHERE begin_date > :begin_date  AND end_date<:end_date  "
                + "     AND dep_name=:department  "
                + "      AND office_country= "
                + "      (SELECT country_name  "
                + "      FROM emp_office  "
                + "      WHERE id=:id) ";
        Session s = HibernateUtil.getSession();
        Long resq = Long.parseLong(s.createSQLQuery(prepared_statement).setDate("begin_date", begin_date).setDate("end_date", end_date).setInteger("id", id).setString("department", department).list().get(0).toString());
        return resq;
    }

//Destination cities
    public static List<String> DestinationCities() {
        String prepared_statement = "select distinct city_name "
                + "from city "
                + "where id not in (select city_id from office)";

        Session s = HibernateUtil.getSession();
        List resq = s.createSQLQuery(prepared_statement).list();
        return (List<String>) resq;
    }

//Destination countries
    public static List<String> DestinationCountries() {
        String prepared_statement =
                "select distinct country_name "
                + "from country where id in "
                + "(select country_id "
                + " from city where id "
                + "not in (select city_id from office))";

        Session s = HibernateUtil.getSession();
        List resq = s.createSQLQuery(prepared_statement).list();
        return (List<String>) resq;
    }

    /**
     * Select all destinations, which have the "approved" field set to zero,
     * with respect to page number and amount of elements on the page
     *
     * @return a list of destinations
     * @author Gangbang34
     */
    public static List<Destination> NonApprovedDestinationListPaged(int page, int rows) {
        //prepare paging
        int start = (page - 1) * rows;
        int finish = start + rows;
        String pps = "SELECT id, city_id, hotelname, hotelsite, is_approved "
                + "FROM (SELECT destination.*, rownum r "
                + "FROM destination "
                + "WHERE is_approved = 0) "
                + "WHERE r>:start AND r<:finish";
        return (List<Destination>) HibernateUtil.getSession().createSQLQuery(pps).
                addEntity(Destination.class).setInteger("start", start).
                setInteger("finish", finish).list();
    }

    /**
     * Count the amount of non-approved destinations
     *
     * @return the amount of non-approved destinations
     * @author Gangbang34
     */
    public static BigDecimal CountNoneApprovedDestinations() {
        String pps = "SELECT COUNT(*) "
                + "FROM destination "
                + "WHERE is_approved = 0";
        List res = HibernateUtil.getSession().createSQLQuery(pps).list();
        return (BigDecimal) res.get(0);
    }
}
