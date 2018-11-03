/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.dto.Alumno;
import com.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ary
 */
public class Dao implements Serializable {

    private transient Session session;
    private static Logger log = Logger.getLogger(Dao.class);

    public Dao() {
    }

    public ArrayList<Alumno> findAlumnos(String grado, String grupo) {
        try {
            session = HibernateUtil.getInstance().getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("from Alumno a where a.grado =:grado and a.grupo =:grupo");
            query.setParameter("grado", grado);
            query.setParameter("grupo", grupo);
            ArrayList<Alumno> alumnos = (ArrayList<Alumno>) query.list();
            commit();
            logInfo("findAlumnos = " + alumnos == null ? "null" : " "+alumnos.size());
            
            return alumnos;
        } catch (HibernateException e) {
            logError("Error findAlumnos", e);
            rollback();
            return new ArrayList<>();
        }
    }
    
    public void commit() {
        if (session != null && session.isOpen()) {
            session.getTransaction().commit();
        }
    }

    public void rollback() {
        if (session != null && session.isOpen()) {
            session.getTransaction().rollback();
        }
    }

    public void logInfo(String mensaje) {
        if (log == null) {
            log = Logger.getLogger(this.getClass());
        }
        log.info(mensaje);
    }

    public void logWarn(String mensaje) {
        if (log == null) {
            log = Logger.getLogger(this.getClass());
        }
        log.warn(mensaje);
    }

    public void logError(String mensaje) {
        if (log == null) {
            log = Logger.getLogger(this.getClass());
        }
        log.error(mensaje);
    }

    public void logError(String mensaje, Exception e) {
        if (log == null) {
            log = Logger.getLogger(this.getClass());
        }
        log.error(mensaje, e);
    }

}
