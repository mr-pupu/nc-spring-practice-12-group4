package database.utilities;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class AdministratorDesktop {

    //the list of departments
    public static List<String> Departments() {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select dep_name "
                + "from department_selfjoin ";

        return (List<String>) s.createSQLQuery(prepared_statement).list();
    }

    //the list of departments which are subsidiary for the given
    public static List<String> ChildDeps(String dep) {
        Session s = HibernateUtil.getSession();
//        String prepared_statement = "select dep_name "
//                + "from department "
//                + "where parent_id = "
//                + "(select id "
//                + "from department "
//                + "where dep_name=:dep)";

        String prepared_statement = "select dep_name "
                + "from department_selfjoin "
                + "where supname=:dep";

        return (List<String>) s.createSQLQuery(prepared_statement).setString("dep", dep).list();
    }

    //the list of employees who work in current department or its subsidiaries
    //Олег, проверь, нужно ли выводить емплои рекурсивно и внеси соотв. изменения
    public static List<String[]> EmpNamePosForDepAndChildDep(Integer id, Integer page, Integer num) {
        Session s = HibernateUtil.getSession();
//        String prepared_statement = "SELECT employee.first_name "
//                + //"SELECT employee.first_name, employee.second_name, occupation.pos_name "+
//                "from employee join occupation on employee.position_id=occupation.id "
//                + "join department  on employee.dep_id=department.id "
//                + "where department.id=:id "
//                + "or department.id in( "
//                + "select id "
//                + "from department "
//                + "where department.parent_id=:id)";
        Integer from = num * page;
        Integer to = (page + 1) * num;
        String prepared_statement = "SELECT first_name, second_name"
                + " FROM  (select rownum r, first_name, second_name from department_employee "
                + "WHERE id =:id OR parent_id=:id) where r>:from and r<:to";


        List rows = s.createSQLQuery(prepared_statement).setInteger("id", id).setInteger("from", from).
                setInteger("to", to).list();
        List<String[]> res = new ArrayList<String[]>();

        if (!rows.isEmpty()) {
            //   for(Object row : rows)
            for (int i = 0; i < rows.size(); i++) {
                String[] srow = new String[2];
                srow[0] = (String) ((Object[]) rows.get(i))[0];
                srow[1] = (String) ((Object[]) rows.get(i))[1];
                res.add(srow);
            }
        }
        return res;
    }

    //the list of emps who work in current department
    //ne fiksit - Pasha sam sdelal konvertacujy nygnyjy
    public static List<String[]> EmpNameForDep(String dep_name) {
        Session s = HibernateUtil.getSession();
//        String prepared_statement = "SELECT employee.first_name "
//                + //"SELECT employee.first_name, employee.second_name "+
//                "from employee join department on employee.dep_id=department.id "
//                + "where department.dep_name=:dep_name";

        String prepared_statement = "SELECT first_name, second_name, pos_name "
                + "FROM department_employee "
                + "where dep_name=:dep_name";

        List rows = s.createSQLQuery(prepared_statement).setString("dep_name", dep_name).list();
        List<String[]> res = new ArrayList<String[]>();

        if (!rows.isEmpty()) {
            //   for(Object row : rows)
            for (int i = 0; i < rows.size(); i++) {
                String[] srow = new String[3];
                srow[0] = (String) ((Object[]) rows.get(i))[0];
                srow[1] = (String) ((Object[]) rows.get(i))[1];
                srow[2] = (String) ((Object[]) rows.get(i))[2];
                res.add(srow);
            }
        }
        return res;
    }
    
    public static String[] getLeafsDeps() {
        Session s = HibernateUtil.getSession();
        String statement = "SELECT dep_name"
                + " FROM department dep"
                + "  WHERE id NOT IN"
                + "   (SELECT id"
                + "   FROM department"
                + "   WHERE id IN"
                + "     (SELECT parent_id FROM department"
                + "     )"
                + "   )";
        List rows = s.createSQLQuery(statement).list();
        String[] res = null;
        if (!rows.isEmpty()) {
            res = new String[rows.size()];
            for (int i = 0; i < rows.size(); ++i) {
                res[i] = (String) rows.get(i);
            }
        }
        return res;
    }
}
