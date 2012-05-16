package database.utilities;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author G-Wan
 */
public class MailLists {

    public static List<String> notifyingGroupOnApprove(long trf_id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement =
                 "SELECT email "
                + "FROM employee "
                + "WHERE id IN"
                + "  (SELECT id"
                + "  FROM emp_office "
                + "  WHERE country_name = "
                + " (SELECT country_name"
                + " FROM emp_office WHERE id = "
                + " (select emp_id from trf where id = :id)))"
                + " AND id IN"
                + " (SELECT empid "
                + "FROM emp_role "
                + "WHERE role_name = 'Travel Department')";

        List resq = s.createSQLQuery(prepared_statement)
                .setLong("id", trf_id).list();
        
        return (List<String>)resq;
    }
    
    public static List<String> TRFOwnerMail(long trf_id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement =
        "select email "+
        "from employee "+ 
        "where id in "+
                        "(select emp_id "+
                        "from trf "+
                        "where id=:trf_id)";
        List resq = s.createSQLQuery(prepared_statement)
                .setLong("trf_id", trf_id).list();
        
        return (List<String>)resq;
    }
}


