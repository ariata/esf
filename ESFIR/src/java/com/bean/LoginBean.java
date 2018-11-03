/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.dto.Alumno;
import java.util.ArrayList;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ariad
 */
@ManagedBean(name = "loginBean")
@SessionScoped

public class LoginBean extends Bean {

    private Alumno usuario;
    
    private String grado;
    private String grupo;
    private ArrayList<Alumno> listAlumno;

    

    public void logOut(ActionEvent actionEvent) {
        try {
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ESFIR/faces/pages/indexGrado.xhtml");
        } catch (IOException ex) {
            logError("No se encontro indexGrado", ex);
        }
    }
    
    public void handlerGrupo(ActionEvent actionEvent){
        grupo = (String)FacesContext.getCurrentInstance().getAttributes().get("grupo");
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("grupo", grupo);
            listAlumno = dao.findAlumnos(grado, grupo);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ESFIR/faces/pages/indexAlumnos.xhtml");
        } catch (IOException ex) {
            logError("No se encontro indexAlumnos", ex);
        }
    }
    
    public void handlerGrado(ActionEvent actionEvent){
        grado = (String)FacesContext.getCurrentInstance().getAttributes().get("grado");
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("grado", grado);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ESFIR/faces/pages/indexGrupo.xhtml");
        } catch (IOException ex) {
            logError("No se encontro indexGrup", ex);
        }
    }

    public ArrayList<Alumno> getListAlumno() {
        return listAlumno;
    }

    

}
