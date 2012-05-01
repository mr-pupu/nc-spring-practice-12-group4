package database.utilities;

import java.util.List;
import org.hibernate.Session;

public class AdministratorDesktop {

    //the list of departments
    public static List Departments() {
        Session s = HibernateUtil.getSession();
        String prepared_statement = "select dep_name "
                + "from department_selfjoin ";

        return (List) s.createSQLQuery(prepared_statement).list();
    }

    //the list of departments which are subsidiary for the given
    public static List ChildDeps(String dep) {
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
        
        return (List) s.createSQLQuery(prepared_statement).setString("dep", dep).list();
    }

    //the list of employees who work in current department or its subsidiaries
    public static List EmpNamePosForDepAndChildDep(Integer id) {
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
        
        String prepared_statement = "SELECT first_name || ' ' || second_name "
                + "FROM department_employee "
                + "WHERE id = :id OR parent_id = :id";
                
        return (List) s.createSQLQuery(prepared_statement).setInteger("id", id).list();
    }

    //the list of emps who work in current department
    public static List EmpNameForDep(String dep_name) {
        Session s = HibernateUtil.getSession();
//        String prepared_statement = "SELECT employee.first_name "
//                + //"SELECT employee.first_name, employee.second_name "+
//                "from employee join department on employee.dep_id=department.id "
//                + "where department.dep_name=:dep_name";
        
        String prepared_statement = "SELECT first_name || ' ' || second_name "
                + "FROM department_employee "
                + "where dep_name=:dep_name";
        
        return (List) s.createSQLQuery(prepared_statement).setString("dep_name", dep_name).list();
    }
}
