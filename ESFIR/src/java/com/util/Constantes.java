/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author Ariad
 */
public class Constantes {
    
    public static SimpleDateFormat sdfddmmyyyy = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "MX"));
    public static SimpleDateFormat sdfddmmyyyyHHmm = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("es", "MX"));
    public static SimpleDateFormat sdfHHmm = new SimpleDateFormat("HH:mm", new Locale("es", "MX"));
    public static SimpleDateFormat sdfM = new SimpleDateFormat("yyyyMM", new Locale("es", "MX"));
    public static SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyyMMdd", new Locale("es", "MX"));
    public static NumberFormat nf = new DecimalFormat("#0.00");  
    public static DecimalFormat nfID = new DecimalFormat("0000000");
    public static String DIRECTORIO_ARCHIVOS = "c:\\files\\documentos\\documentacion\\";
    public static String DIRECTORIO_IMAGENES = "C:\\files\\documentos\\imagenes\\";
    
    
    
    
    public static String getEmpresa(String idEmpresa){
        switch(idEmpresa){
            case "001" : return "027";
        }
        return "";
    }
    
    public static String getBodega(String idBodega){
        switch(idBodega){
            case "0001" : return "PRINCIPAL";
        }
        return "";
    }
    
     
    public enum tipoRol{
        ADMIN(1,"Administrador"),
        EMPLEADO(2,"Empleado");
        private final int id;
        private final String tipo;
        tipoRol(int id, String tipo) {
            this.id = id;
            this.tipo = tipo;
        }

        public int getId() {
            return id;
        }

        public String getTipo() {
            return tipo;
        }
    }
     
    
    //*****************************************************LOGIN
    
    
    public static String INFO_LOGIN = "Ingrese la información correspondiente para el ingreso"; 
    public static String ERROR_LOGIN = "Error en las credenciales del sistema";
    public static String EXITO_LOGIN = "Ingreso correcto";
    
    //*****************************************************BUSQUEDA    
    public static String ERROR_BUSQUEDA = "Error en la búsqueda";
    public static String EXITO_BUSQUEDA = "Búsqueda exitosa";
    
    //*****************************************************GUARDADO
    public static String ERROR_GUARDADO = "Error en el guardado";
    public static String EXITO_GUARDADO = "Guardado exitoso";
    
    //*****************************************************EDICIÓN
    public static String ERROR_EDICION = "Error en la edición";
    public static String EXITO_EDICION = "Edición exitosa";
    
    //*****************************************************ELIMINACIÓN
    public static String ERROR_ELIMINAR = "Error en la eliminación";
    public static String EXITO_ELIMINAR = "Eliminación exitosa";
               
}
