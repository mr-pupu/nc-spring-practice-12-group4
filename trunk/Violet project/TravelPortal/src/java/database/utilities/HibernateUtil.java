/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utilities;

//import database.mapping.Employee;
import database.mapping.Country;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author allan
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final ThreadLocal curSession = new ThreadLocal();
//    private static final ThreadLocal curTransaction = new ThreadLocal();
//    private static Session session;
//    private static Transaction transaction;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static List<Country> CountryList() {
        Session s = getSession();
        String query = "SELECT * "
                + "FROM country";
        SQLQuery q = s.createSQLQuery(query);
        return (List<Country>) q.addEntity(Country.class).list();
    }
    //the list of occupations

    public static List<String> OccupationsList() {
        Session s = getSession();
        String stmt = "select pos_name "
                + "from occupation";
        SQLQuery query = s.createSQLQuery(stmt);
        return (List<String>) query.list();
    }

    //id of employee with given login
    public static List<String> EmpIdByLogin(String login) {
        Session s = getSession();
        String prepared_statement = "select id "
                + "from employee "
                + "where login=:login";
        return (List<String>) s.createSQLQuery(prepared_statement).setString("login", login).list();
    }

    //ID of office in which given employee works
    public static List<String> EmpOffice(Integer emp_id) {
        Session s = getSession();
        String prepared_statement = "select office_id "
                + "from employee "
                + "where id=:emp_id";

        return (List<String>) s.createSQLQuery(prepared_statement).setInteger("emp_id", emp_id).list();
    }

    //Deprole of employee with given login and password
    public static List<String> DepDeproleByLogin(String login, String password) {
        Session s = getSession();
        String prepared_statement = "SELECT id, role_name "
                + "FROM emp_role "
                + "WHERE login=:login AND password="
                //+ "to_char(dbms_obfuscation_toolkit.MD5(input_string =>:password))";
                + "to_char(DBMS_UTILITY.GET_HASH_VALUE ( :password, 0, 4096))";

//                "select deprole.id "
//                + "from deprole join roledep on deprole.id=roledep.role_id "
//                + "join department on roledep.dep_id=department.id "
//                + "join employee on employee.dep_id=department.id "
//                + "where employee.login=:login";

        ArrayList arrList = (ArrayList) s.createSQLQuery(prepared_statement).
                setString("login", login).setString("password", password).list();

        //editor : Vlad
        //ArrayList<Objects[BigDecimal, String]>  =>  ArrayList<String>
        ArrayList<String> outList = new ArrayList<String>();
        for (Iterator it = arrList.iterator(); it.hasNext();) {
            Object[] objects = (Object[]) it.next();
            outList.add((String) objects[1]);
        }

        return outList;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Get current Hibernate session or initialize it if it's null
     *
     * @return Hibernate session instance
     */
    public static Session getSession() {
        Session s = (Session) curSession.get();
        try {
            if (s == null) {
                s = sessionFactory.openSession();
                curSession.set(s);
                System.out.println("Session opened " + s);
            }
//            if (s == null) {
//                s = sessionFactory.openSession();
//                curSession.set(s);
//            }
        } catch (HibernateException ex) {
            System.out.println("Error during session creation " + ex);
        }
        return s;
//        return sessionFactory.getCurrentSession();
    }

    /**
     * Close current Hibernate session
     */
    public static void closeSession() {
        Session s = (Session) curSession.get();
        if (curSession != null) {
            try {
                if (s != null) {
                    s.close();
                    System.out.println("Session flushed");
                }
                curSession.set(null);
            } catch (HibernateException e) {
                System.out.println("Close current session error: " + e.getMessage());
            }
        }
//        getSession().close();
    }

    /**
     * Begin Hibernate transaction
     */
    public static void beginTransaction() {
        System.out.println("Begin transaction");
//        Transaction tx = getSession().getTransaction();
//        try {
//            if (tx == null) {
//                tx = getSession().beginTransaction();
//                System.err.println("DOGGY");
//                curTransaction.set(tx);
//            }
//        } catch (HibernateException e) {
//            System.out.println("Begin transaction error: " + e.getMessage());
//        }
        getSession().beginTransaction();
    }

    /**
     * Commit Hibernate transaction
     */
    public static void commitTransaction() {
        System.out.println("Commit transaction");
//        Transaction tx = (Transaction) curTransaction.get();
//        try {
//            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
//                tx.commit();
//                System.out.println("commit");
//            }
//            curTransaction.set(null);
//        } catch (HibernateException e) {
//            rollbackTransaction();
//            System.out.println("Commit transaction error: " + e);
//        }
        getSession().getTransaction().commit();
    }

    /**
     * Rollback Hibernate transaction
     */
    public static void rollbackTransaction() {
        System.out.println("Rollback transaction");
//        Transaction tx = (Transaction) curTransaction.get();
//        try {
//            curTransaction.set(null);
//            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
//                tx.rollback();
//            }
//        } catch (HibernateException e) {
//            System.out.println("Rollback transaction error: " + e);
//        }
        getSession().getTransaction().rollback();
    }

    /**
     * Save given object into database
     */
    public static void save(Object o) {
        Session s = getSession();
        Transaction tx = s.beginTransaction();
        try {
            tx.begin();
            s.saveOrUpdate(o);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            s.close();
            System.out.println("Something went wrong");
        }
    }

    /**
     * Delete given object from database
     */
    public static void delete(Object o) {
        Session s = getSession();
        Transaction tx = s.beginTransaction();
        try {
            s.delete(o);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Exception while removing data " + e);
            tx.rollback();
            s.close();
        }
    }
}