/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.ejbdiscotienda.dto.PaisDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Pais;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.IPaisService;
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
 * Clase que posee los servicios de la API encargados de gestionar los países
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/paises")
public class PaisController {
    
    /**
     * Objeto que permite la comunicación con el EJB
     */
    @EJB
    private IPaisService service;
    
    /**
     * Permite obtener todos los países
     * @return países
     * @throws ObtencionException excepción lanzada al no encontrar países
     */
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<PaisDto> paises = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(paises).build();
        
    }
    
    /**
     * Permite obtener un país por su id
     * @param id es la llave primaria del país a obtener
     * @return país
     * @throws ObtencionException excepción lanzada al no encontrar el país
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        PaisDto pais = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(pais).build();
        
    }
    
    /**
     * Permite crear un país
     * @param pais es el país a crear
     * @return país
     * @throws CreacionException excepción lanzada al no poder crear el país
     */
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid Pais pais) throws CreacionException{
    
        this.service.crear(pais);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    /**
     * Permite editar un país
     * @param pais objeto con los nuevos datos del país
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el país a editar
     * @throws EdicionException excepción lanzada al no poder editar el país por un conflicto
     */
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Pais pais) throws ObtencionException, EdicionException{
    
        this.service.editar(pais);
        return Response.status(Response.Status.OK).build();
    
    }
    
    /**
     * Permite eliminar un país
     * @param id es la llave primaria del país a eliminar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el país a eliminar
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
}
