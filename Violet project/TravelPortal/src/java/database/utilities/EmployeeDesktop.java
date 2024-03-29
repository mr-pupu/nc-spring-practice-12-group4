package database.utilities;

import database.mapping.Trfstate;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

public class EmployeeDesktop {

    /**
     * @author Allan
     * @param login
     * @return the total number of "in progress" trfs of current user slow but
     * working
     */
    public static long countEnteringRejectedTRF(String login) {
        String prepared_statement = "select count(*) "
                + "from (select rownum r, employee_trfs.* "
                + "from employee_trfs "
                + "where (cur_state=1 or cur_state=0) and login=:login) ";

        Session s = HibernateUtil.getSession();
        long res = Long.parseLong(s.createSQLQuery(prepared_statement).setString("login", login).list().get(0).toString());
        return res;
    }

    //TRFs, the author of which is the given employee,
    //and state is Entering or Rejected
    public static String[][] EnteringRejectedTRF(String login, int page, int rows) {
        Session s = HibernateUtil.getSession();
        int from = (page - 1) * rows;
        int to = from + rows;
        String prepared_statement = "select id ,city_name, country_name, begin_date, end_date, cur_state, commentary "
                + "from (select rownum r, employee_trfs.* "
                + "from employee_trfs "
                + "where (cur_state=1 or cur_state=0) and login=:login) "
                + "where r> :from and r<= :to";

        List resq = s.createSQLQuery(prepared_statement).setInteger("from", from).setInteger("to", to).setString("login", login).list();

        DateFormat reportDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String[][] res = new String[resq.size()][7];
        for (int i = 0; i < resq.size(); i++) {
            BigDecimal trf_id = (BigDecimal) ((Object[]) resq.get(i))[0];
            String city_name = (String) ((Object[]) resq.get(i))[1];
            String country_name = (String) ((Object[]) resq.get(i))[2];
            String begin_date = reportDateFormat.format((Date) ((Object[]) resq.get(i))[3]);
            String end_date = reportDateFormat.format((Date) ((Object[]) resq.get(i))[4]);
            BigDecimal cur_state = (BigDecimal) ((Object[]) resq.get(i))[5];
            String comment = (String) ((Object[]) resq.get(i))[6];

            res[i][0] = trf_id.toString();
            res[i][1] = city_name;
            res[i][2] = country_name;
            res[i][3] = begin_date;
            res[i][4] = end_date;
            res[i][5] = database.mapping.Trf.getStatus(cur_state.intValue());
            res[i][6] = comment;
        }

        return res;
    }

    public static long countAllEmpsTRFS(String login) {
        String prepared_statement = "select count(*) "
                + "from employee_trfs "
                + "where login=:login ";

        Session s = HibernateUtil.getSession();
        long res = Long.parseLong(s.createSQLQuery(prepared_statement).setString("login", login).list().get(0).toString());
        return res;
    }

    //TRFs, which are created by given employee
    public static String[][] allEmpsTRFs(String login, int page, int rows) {
        Session s = HibernateUtil.getSession();
        int from = (page - 1) * rows;
        int to = from + rows;
        String prepared_statement = "select id ,city_name, country_name, begin_date, end_date, cur_state, commentary "
                + "from (select rownum r, employee_trfs.* "
                + "from employee_trfs "
                + "where login=:login) "
                + "where r> :from and r<= :to";

        List resq = s.createSQLQuery(prepared_statement).setInteger("from", from).setInteger("to", to).setString("login", login).list();

        DateFormat reportDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String[][] res = new String[resq.size()][7];
        for (int i = 0; i < resq.size(); i++) {
            BigDecimal trf_id = (BigDecimal) ((Object[]) resq.get(i))[0];
            String city_name = (String) ((Object[]) resq.get(i))[1];
            String country_name = (String) ((Object[]) resq.get(i))[2];
            String begin_date = reportDateFormat.format((Date) ((Object[]) resq.get(i))[3]);
            String end_date = reportDateFormat.format((Date) ((Object[]) resq.get(i))[4]);
            BigDecimal cur_state = (BigDecimal) ((Object[]) resq.get(i))[5];
            String comment = (String) ((Object[]) resq.get(i))[6];

            res[i][0] = trf_id.toString();
            res[i][1] = city_name;
            res[i][2] = country_name;
            res[i][3] = begin_date;
            res[i][4] = end_date;
            res[i][5] = database.mapping.Trf.getStatus(cur_state.intValue());
            res[i][6] = comment;
        }

        return res;
    }

    public static BigDecimal HistoryCount(int id) {
        String pps = "SELECT COUNT(*) "
                + "FROM trfstate "
                + "WHERE trf_id=:id";
        return (BigDecimal)HibernateUtil.getSession().createSQLQuery(pps).setInteger("id", id).list().get(0);
    }

    public static List<Trfstate> HistoryPaged(int id, int page, int rows) {
        //prepare paging
        int start = (page - 1) * rows;
        int finish = start + rows;
        String pps = "SELECT id, trf_id, commentary, change_date, status, changer "
                + "FROM (SELECT trfstate.*, rownum r "
                + "FROM trfstate "
                + "WHERE trf_id = :id) "
                + "WHERE r>:start AND r<:finish";
        return (List<Trfstate>) HibernateUtil.getSession().createSQLQuery(pps).
                addEntity(Trfstate.class).setInteger("id", id).setInteger("start", start).setInteger("finish", finish).list();
    }
}
