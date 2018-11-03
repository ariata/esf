/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
 package mpro.util;

 import org.hibernate.cfg.AnnotationConfiguration;
 import org.hibernate.SessionFactory;


 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Ariad
 
 public class HibernateUtil {

 private static final SessionFactory sessionFactory;
    
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
 }
 */
package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil {

    private HibernateUtil() {
    }
    private static String CONFIG_FILE_LOCATION = "/resources/hibernate.cfg.xml";
    private static final Configuration cfg = new Configuration();
    private static org.hibernate.SessionFactory sessionFactory;

    private static String url = "jdbc:sqlserver://localhost;databaseName=EMPRESAS";
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String pwd = "mpro/2008";
    private static String user = "sa";

    public static SessionFactory getInstance() {
        if (sessionFactory == null) {
            initSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * Returns the ThreadLocal Session instance. Lazy initialize the
     * <code>SessionFactory</code> if needed.
     *
     * @return Session
     * @throws HibernateException
     */
    public Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * The behaviour of this method depends on the session context you have
     * configured. This factory is intended to be used with a hibernate.cfg.xml
     * including the following property <property
     * name="current_session_context_class">thread</property> This would return
     * the current open session or if this does not exist, will create a new
     * session
     *
     * @return
     */
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * initializes the sessionfactory in a safe way even if more than one thread
     * tries to build a sessionFactory
     */
    private static synchronized void initSessionFactory() {
        /*
         * [laliluna] check again for null because sessionFactory may have been
         * initialized between the last check and now
         *
         */

        if (sessionFactory == null) {

            try {
                
                cfg.setProperty("hibernate.connection.driver_class", driver);
                cfg.setProperty("hibernate.connection.url", url);
                cfg.setProperty("hibernate.connection.username", user);
                cfg.setProperty("hibernate.connection.password", pwd);
                cfg.setProperty("hibernate.dialect","org.hibernate.dialect.SQLServerDialect");
                
               

                cfg.configure(CONFIG_FILE_LOCATION);
                String sessionFactoryJndiName = cfg.getProperty(Environment.SESSION_FACTORY_NAME);
                if (sessionFactoryJndiName != null) {
                    cfg.buildSessionFactory();
                    sessionFactory = (SessionFactory) (new InitialContext()).lookup(sessionFactoryJndiName);
                } else {
                    sessionFactory = cfg.buildSessionFactory();
                }

            } catch (Exception e) {
                System.err.println("%%%% Error Creating HibernateSessionFactory %%%%");
                e.printStackTrace();
                throw new HibernateException("Could not initialize the Hibernate configuration");
            }
        }
    }

    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        sessionFactory = null;
    }
}
