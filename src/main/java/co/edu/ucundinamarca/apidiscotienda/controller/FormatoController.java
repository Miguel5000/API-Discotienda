/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.ejbdiscotienda.dto.FormatoDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Formato;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.IFormatoService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase que posee los servicios de la API encargados de gestionar a los formatos
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/formatos")
public class FormatoController {
    
    /**
     * Objeto que permite la comunicación con el EJB
     */
    @EJB
    private IFormatoService service;
    
    /**
     * Permite obtener todos los formatos
     * @return formatos
     * @throws ObtencionException excepción lanzada al no encontrar formatos
     */
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<FormatoDto> formatos = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(formatos).build();
        
    }
    
    /**
     * Permite obtener un formato por su id
     * @param id es la llave primaria del formato a obtener
     * @return formato
     * @throws ObtencionException excepción lanzada al no poder encontrar el formato
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        FormatoDto formato = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(formato).build();
        
    }
    
    /**
     * Permite crear un formato
     * @param formato es el formato a crear
     * @return Confirmación
     * @throws CreacionException excepción lanzada al no poder crear el formato
     */
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid Formato formato) throws CreacionException{
    
        this.service.crear(formato);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    /**
     * Permite editar un formato
     * @param formato objeto con los nuevos datos del formato a editar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no poder encontrar el formato a editar
     * @throws EdicionException excepción lanzada al no poder editar el formato por un conflicto
     */
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Formato formato) throws ObtencionException, EdicionException{
    
        this.service.editar(formato);
        return Response.status(Response.Status.OK).build();
    
    }
    
    /**
     * Permite eliminar un formato
     * @param id es la llave primaria del formato a eliminar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el formato a eliminar
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
}
