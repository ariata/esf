/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dto;

import java.io.Serializable;
import com.util.Constantes;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mercadar
 */
public class Alumno implements Serializable {

    private String id;
    private String numeroLista;
    private String nombre;
    private String grado;
    private String grupo;
    
    public Alumno() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroLista() {
        return numeroLista;
    }

    public void setNumeroLista(String numeroLista) {
        this.numeroLista = numeroLista;
    }

   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    

}
