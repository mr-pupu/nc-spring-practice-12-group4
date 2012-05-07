package database.utilities;

import database.mapping.Trf;
import java.util.List;
import org.hibernate.Session;

public class EmployeeDesktop {

    //TRFs, the author of which is the given employee,
    //and state is Entering or Rejected
    public static List<Trf> EnteringRejectedTRF(Integer creator_id,Integer page,Integer num) {
        Session s = HibernateUtil.getSession();
        Integer from=num*page;
        Integer to=(page+1)*num;
        String prepared_statement = "select id,destination_id,customer_id,"
                + "emp_id,begin_date,end_date, car_rental, pay_by_cash, cur_state, project_manager "+
                "from (select rownum r, trf.* from trf " +
                "where ((cur_state=1 "+
                "or cur_state=0) and emp_id=:creator_id)) where r>:from and r<:to";

        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).
                setInteger("from", from).
                setInteger("to",to).setInteger("creator_id", creator_id).list();
    }


    //TRFs, which are created by given employee
    public static List<Trf> EmpTRFs(int id,Integer page,Integer num) {
        Integer from=num*page;
        Integer to=(page+1)*num;
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select id,destination_id,customer_id,"
                                  + "emp_id,begin_date,end_date, car_rental,"
                                  + " pay_by_cash, cur_state, project_manager "+ 
                                  "from (select rownum r, trf.* from trf where emp_id=:id) "+
                                  "where r>:from and r<:to";

        return (List<Trf>) s.createSQLQuery(prepared_statement).addEntity(Trf.class).setInteger("from", from).
                setInteger("to",to).setInteger("id", id).list();
    }

}
