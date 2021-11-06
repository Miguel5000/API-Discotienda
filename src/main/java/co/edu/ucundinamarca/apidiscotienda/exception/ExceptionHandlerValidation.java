/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Clase que captura las excepciones de validación
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Provider
public class ExceptionHandlerValidation implements ExceptionMapper<ConstraintViolationException>{

    /**
     * Es la variable que permite obtener la URL del método de la API que generó la excepción
     */
    @Context private UriInfo uriInfo;
    
    /**
     * Permite responder de acuerdo con la excepción capturada
     * @param exception es la excepción capturada
     * @return respuesta
     */
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        
        String enlace = uriInfo.getPath();
        
        String mensaje = "";

        for (ConstraintViolation constraint : exception.getConstraintViolations()) {
         
            mensaje += constraint.getMessage() + ". ";
            
        }
        
        ExceptionWrapper wrapper = new ExceptionWrapper("400", "BAD_REQUEST", mensaje , enlace);
        
        return Response.status(Response.Status.BAD_REQUEST).entity(wrapper).build();
        
    }
    
}
