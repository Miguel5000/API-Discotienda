/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.ejbdiscotienda.dto.GeneroDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Genero;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.IGeneroService;
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
 * Clase que posee los servicios de la API encargados de gestionar los géneros
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/generos")
public class GeneroController {
    
    /**
     * Objeto que permite la comunicación con el EJB
     */
    @EJB
    private IGeneroService service;
    
    /**
     * Permite obtener todos los géneros
     * @return géneros
     * @throws ObtencionException excepción lanzada al no encontrar géneros
     */
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<GeneroDto> generos = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(generos).build();
        
    }
    
    /**
     * Permite obtener un género por su id
     * @param id es la llave primaria del género a encontrar
     * @return género
     * @throws ObtencionException excepción lanzada al no encontrar el género
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        GeneroDto genero = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(genero).build();
        
    }
    
    /**
     * Permite crear un género
     * @param genero es el género a crear
     * @return Confirmación
     * @throws CreacionException excepción lanzada al no poder crear el género
     */
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid Genero genero) throws CreacionException{
    
        this.service.crear(genero);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    /**
     * Permite editar un género
     * @param genero objeto con los nuevos datos del género
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el género a editar
     * @throws EdicionException excepción lanzada al no poder editar el género por un conflicto
     */
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Genero genero) throws ObtencionException, EdicionException{
    
        this.service.editar(genero);
        return Response.status(Response.Status.OK).build();
    
    }
    
    /**
     * Permite eliminar un género
     * @param id es la llave primaria del género a eliminar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el género a eliminar
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
}
