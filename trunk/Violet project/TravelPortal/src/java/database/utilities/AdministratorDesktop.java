package database.utilities;

import database.mapping.Department;
import java.math.BigDecimal;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class AdministratorDesktop {

    //the list of departments
    public static List Departments() {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select dep_name "
                + "from department_selfjoin ";

        return (List) s.createSQLQuery(prepared_statement).list();
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

        String prepared_statement = "select id, dep_name "
                + "from department_selfjoin "
                + "where parent_id=:dep";
        
        return (List) s.createSQLQuery(prepared_statement).addEntity(Department.class)
                .setLong("dep", id).list();
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
        
        String prepared_statement = "SELECT empid, first_name || ' ' || second_name, pos_name "+ 
                "FROM department_employee WHERE id IN (SELECT id FROM department "+
                "START WITH id=:id CONNECT BY prior id=parent_id) ORDER BY id, empid";
        SQLQuery query = s.createSQLQuery(prepared_statement);
        java.util.List resq = query.setInteger("id", id).list();
        String[][] res = new String[resq.size()][3];
        System.out.println((BigDecimal)((Object[]) resq.get(0))[0]);
        for (int i=0;i<resq.size();i++)
        {
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
    public static List EmpNameForDep(String dep_name) {
        Session s = HibernateUtil.getSession();
//        String prepared_statement = "SELECT employee.first_name "
//                + //"SELECT employee.first_name, employee.second_name "+
//                "from employee join department on employee.dep_id=department.id "
//                + "where department.dep_name=:dep_name";
        
        String prepared_statement = "SELECT first_name || ' ' || second_name, pos_name "
                + "FROM department_employee "
                + "where dep_name=:dep_name";
        
        return (List) s.createSQLQuery(prepared_statement).setString("dep_name", dep_name).list();
    }
}
