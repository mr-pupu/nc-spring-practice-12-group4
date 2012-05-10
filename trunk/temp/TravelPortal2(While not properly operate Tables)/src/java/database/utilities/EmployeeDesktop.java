package database.utilities;

import database.mapping.Trf;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

public class EmployeeDesktop {

    //TRFs, the author of which is the given employee,
    //and state is Entering or Rejected
    public static String[][] EnteringRejectedTRF(String login, Integer page, Integer num) {
        Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        String prepared_statement = "select id ,city_name, country_name, begin_date, end_date, cur_state, commentary "
                + "from (select rownum r, employee_trfs.* "
                + "from employee_trfs "
                + "where (cur_state=1 or cur_state=0) and login=:login) "
                + "where r> :from and r< :to";

        List resq = s.createSQLQuery(prepared_statement)
                .setInteger("from", from)
                .setInteger("to", to)
                .setString("login", login)
                .list();
        
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


    //TRFs, which are created by given employee
      public static String[][] allEmpsTRFs(String login, Integer page, Integer num) {
        Session s = HibernateUtil.getSession();
        Integer from = num * page;
        Integer to = (page + 1) * num;
        String prepared_statement = "select id ,city_name, country_name, begin_date, end_date, cur_state, commentary "
                + "from (select rownum r, employee_trfs.* "
                + "from employee_trfs "
                + "where login=:login) "
                + "where r> :from and r< :to";

        List resq = s.createSQLQuery(prepared_statement)
                .setInteger("from", from)
                .setInteger("to", to)
                .setString("login", login)
                .list();
        
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
}
