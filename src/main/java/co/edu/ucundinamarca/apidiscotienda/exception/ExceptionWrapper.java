/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.exception;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que permite crear un wrapper de respuesta HTTP para el cliente
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
public class ExceptionWrapper implements Serializable{
    
    /**
     * Es el código HTTP de respuesta
     */
    private String codigo;
    
    /**
     * Es el nombre del error
     */
    private String error;
    
    /**
     * Es la fecha en la que se generó el error
     */
    private String fecha;
    
    /**
     * Es el mensaje que indica el motivo del error
     */
    private String mensaje;
    
    /**
     * Es el enlace del método en la API
     */
    private String url;

    /**
     * Constructor de ExceptionWrapper
     * @param codigo es el código de error HTTP
     * @param error es el nombre del error
     * @param mensaje es el mensaje de error
     * @param url es el enlace del método
     */
    public ExceptionWrapper(String codigo, String error, String mensaje, String url) {
        this.codigo = codigo;
        this.error = error;
        this.fecha = new Date().toString();
        this.mensaje = mensaje;
        this.url = url;
    }

    /**
     * Permite obtener el código de error
     * @return codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Permite establecer el código de error
     * @param codigo es el código a establecer
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Permite obtener el nombre del error
     * @return error
     */
    public String getError() {
        return error;
    }

    /**
     * Permite establecer el nombre del error
     * @param error es el nombre del error a establecer
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Permite obtener la fecha del error
     * @return fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Permite establecer la fecha del error
     * @param fecha es la fecha a establecer
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Permite obtener el mensaje del error
     * @return mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Permite establecer el mensaje del error
     * @param mensaje es el mensaje a establecer
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Permite obtener el enlace del error
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Permite establecer el enlace del error
     * @param url es el enlace a establecer
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
}
