package database.utilities;

import database.mapping.Trf;
import java.util.List;
import org.hibernate.Session;

public class EmployeeDesktop {

    //TRFs, the author of which is the given employee,
    //and state is Entering or Rejected
    public static List<Trf> EnteringRejectedTRF(Integer creator_id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select * "
                + "from trf "
                + "where ((cur_state=1 "
                + "or cur_state=0) and emp_id=:creator_id)";

        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setInteger("creator_id", creator_id).list();
    }

    //TRFs, which are created by given employee
    public static List<Trf> EmpTRFs(int empid) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select * "
                + "from trf "
                + "where emp_id=:emp";

        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setInteger("emp", empid).list();
    }
}
