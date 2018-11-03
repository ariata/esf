/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.dto.Alumno;
import com.dao.Dao;
import com.util.Constantes;
import org.apache.log4j.Logger;

/**
 *
 * @author mercadar
 */
public class Bean implements Serializable {

    private static Logger log = Logger.getLogger(Bean.class);
    protected Dao dao = new Dao();
    protected String mensaje;
    protected Alumno usuario;

    
    public static void logInfo(String mensaje) {
        if (log == null) {
            log = Logger.getLogger(Bean.class);
        }
        log.info(mensaje);
    }

    public static void logWarn(String mensaje) {
        if (log == null) {
            log = Logger.getLogger(Bean.class);
        }
        log.warn(mensaje);
    }

    public static void logError(String mensaje) {
        if (log == null) {
            log = Logger.getLogger(Bean.class);
        }
        log.error(mensaje);
    }

    public static void logError(String mensaje, Exception e) {
        if (log == null) {
            log = Logger.getLogger(Bean.class);
        }
        log.error(mensaje, e);
    }

     public void addMessage(String summary, String complemento) {
        FacesMessage.Severity severity;
        if (Constantes.ERROR_BUSQUEDA.equals(summary) || Constantes.ERROR_EDICION.equals(summary)
                || Constantes.ERROR_ELIMINAR.equals(summary) || Constantes.ERROR_GUARDADO.equals(summary)) {
            severity = FacesMessage.SEVERITY_ERROR;
        } else {
            severity = FacesMessage.SEVERITY_INFO;
        }
        
        FacesMessage message = new FacesMessage(severity, summary + " " + complemento, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getMensaje() {
        return mensaje;
    }
    
}
