/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Master
 */
public class TRFHibernateUtil {

    private static final SessionFactory sessionFactory;
    public static final ThreadLocal curSession = new ThreadLocal();
    public static final ThreadLocal curTransaction = new ThreadLocal();

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    /**
     * Get current Hibernate session or initialize it if it's null
     * @return Hibernate session instance
     */
    public static Session getSession() {
        Session s = (Session)curSession.get();
        try{
            if (s == null) {
                s.getSessionFactory().openSession();
                curSession.set(s);
            }
        }
        catch (HibernateException ex) {
            System.out.println("Error during session creation " + ex);
        }
        return s;
    }
    
    /**
     * Close current Hibernate session
     */
    public static void closeSession() {
        if (curSession != null) {
            Session s = (Session) curSession.get();
            curSession.set(null);
            try {
                if (s != null) {
                    s.flush();
                }
                s.close();
            } catch (HibernateException e) {
                System.out.println("Close current session error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Begin Hibernate transaction
     */
    public static void beginTransaction() {
        System.out.println("Begin transaction");
        Transaction tx = (Transaction) curTransaction.get();
        try {
            if (tx == null) {
                tx = getSession().beginTransaction();
                curTransaction.set(tx);
            }
        } catch (HibernateException e) {
            System.out.println("Begin transaction error: " + e.getMessage());
        }
    }

    /**
     * Commit Hibernate transaction
     */
    public static void commitTransaction() {
        System.out.println("Commit transaction");
        Transaction tx = (Transaction) curTransaction.get();
        try {
            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
                tx.commit();
                System.out.println("commit");
            }
            curTransaction.set(null);
        } catch (HibernateException e) {
            rollbackTransaction();
            System.out.println("Commit transaction error: " + e);
        }
    }

    /**
     * Rollback Hibernate transaction
     */
    public static void rollbackTransaction() {
        System.out.println("Rollback transaction");
        Transaction tx = (Transaction) curTransaction.get();
        try {
            curTransaction.set(null);
            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
                tx.rollback();
            }
        } catch (HibernateException e) {
            System.out.println("Rollback transaction error: " + e);
        }
    }
    
    /**
     * Save given object into database
     */
    public static void save(Object o) {
        try {
            beginTransaction();
            getSession().save(o);
            commitTransaction();
        }
        catch(RuntimeException e) {
            System.out.println("Exception while saving data " + e);
            rollbackTransaction();
        }
    }
    
    
    /**
     * Delete given object from database
     */
    public static void delete(Object o) {
        try {
            beginTransaction();
            getSession().delete(o);
            commitTransaction();
        }
        catch (RuntimeException e) {
            System.out.println("Exception while removing data " + e);
            rollbackTransaction();
        }
    }
}
