package database.utilities;

import database.mapping.Department;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.JDBCException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class AdministratorDesktop {

    //the list of departments
    public static List<String> Departments() {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select dep_name "
                + "from department_selfjoin ";

        return (List<String>) s.createSQLQuery(prepared_statement).list();

    }

    //list of departments without children
    public static List<Department> HeadDepartments() {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT * "
                + "FROM department WHERE parent_id IS NULL ";

        return (List<Department>) s.createSQLQuery(prepared_statement).
                addEntity(Department.class).list();
    }

    //the list of departments which are subsidiary for the given
    public static List ChildDeps(Long id) {
        Session s = HibernateUtil.getSession();
//        String prepared_statement = "select dep_name "
//                + "from department "
//                + "where parent_id = "
//                + "(select id "
//                + "from department "
//                + "where dep_name=:dep)";

        String prepared_statement = "SELECT * "
                + "FROM department "
                + "WHERE parent_id=:dep";
        List res = null;
        try {
            res = (List) s.createSQLQuery(prepared_statement).
                addEntity(Department.class).setInteger("dep", id.intValue()).list();
//            res = (List) s.createQuery(prepared_statement).
//                addEntity(Department.class).setInteger("dep", id.intValue()).list();
        }
        catch(JDBCException e) {
            System.err.println(e.getMessage());
            System.out.println("Cause");
            System.out.println(e.getSQL());
        }
        finally{
        return res;
        }
    }

    //the list of employees who work in current department or its subsidiaries
    public static String[][] EmpNamePosForDepAndChildDep(Integer id) {
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

        String prepared_statement = "SELECT empid, first_name || ' ' || second_name, pos_name "
                + "FROM department_employee WHERE id IN (SELECT id FROM department "
                + "START WITH id=:id CONNECT BY prior id=parent_id) ORDER BY id, empid";
        SQLQuery query = s.createSQLQuery(prepared_statement);
        java.util.List resq = query.setInteger("id", id).list();
        String[][] res = new String[resq.size()][3];
        System.out.println((BigDecimal) ((Object[]) resq.get(0))[0]);
        for (int i = 0; i < resq.size(); i++) {
            BigDecimal empid = (BigDecimal) ((Object[]) resq.get(i))[0];
            String name = (String) ((Object[]) resq.get(i))[1];
            String pos = (String) ((Object[]) resq.get(i))[2];

            res[i][0] = empid.toString();
            res[i][1] = name;
            res[i][2] = pos;
        }

        return res;
    }
    //count the total number of employees from the previous query
    
    public static long CountEmpsInDepsAndSubdeps(Integer id) {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "SELECT COUNT(*) "
                + "FROM "
                + "(SELECT empid, first_name || ' ' || second_name, pos_name "
                + "FROM department_employee WHERE id IN (SELECT id FROM department "
                + "START WITH id=:id CONNECT BY prior id=parent_id) ORDER BY id, empid)";
        long res = Long.parseLong(s.createSQLQuery(prepared_statement).setInteger("id", id).list().get(0).toString());
        return res;
    }
    
    //The same query, only with paging output
    public static String[][] SubsidiaryEmployeesPaged(Integer id, int page, int rows) {
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
        //prepare paging
        int start = (page-1)*rows;
        int finish = start + rows;
        String prepared_statement = "SELECT empid, fullname, pos_name FROM "
                + "(SELECT empid, first_name || ' ' || second_name fullname, pos_name, ROWNUM r "
                + "FROM department_employee WHERE id IN (SELECT id FROM department "
                + "START WITH id=:id CONNECT BY prior id=parent_id) ORDER BY id, empid)"
                + " WHERE r>:start AND r<=:finish";
        SQLQuery query = s.createSQLQuery(prepared_statement);
        java.util.List resq = query.setInteger("id", id).setInteger("start", start)
                .setInteger("finish", finish).list();
        String[][] res = new String[resq.size()][3];
        System.out.println((BigDecimal) ((Object[]) resq.get(0))[0]);
        for (int i = 0; i < resq.size(); i++) {
            BigDecimal empid = (BigDecimal) ((Object[]) resq.get(i))[0];
            String name = (String) ((Object[]) resq.get(i))[1];
            String pos = (String) ((Object[]) resq.get(i))[2];

            res[i][0] = empid.toString();
            res[i][1] = name;
            res[i][2] = pos;
        }

        return res;
    }
    
    //the list of emps who work in current department
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
