/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.ejbdiscotienda.dto.CreadorDiscoDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.CreadorDisco;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.ICreadorDiscoService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Miguel
 */

@Stateless
@Path("/creadoresDiscos")
public class CreadorDiscoController {
    
    @EJB
    private ICreadorDiscoService service;
    
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<CreadorDiscoDto> creaciones = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(creaciones).build();
        
    }
    
    @GET
    @Path("/obtenerPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(Integer id) throws ObtencionException{
    
        CreadorDiscoDto creacion = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(creacion).build();
        
    }
    
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(CreadorDisco creacion) throws CreacionException{
    
        this.service.crear(creacion);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(CreadorDisco creacion) throws ObtencionException, EdicionException{
    
        this.service.editar(creacion);
        return Response.status(Response.Status.OK).build();
    
    }
    
    @DELETE
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(CreadorDisco creacion) throws ObtencionException{
    
        this.service.eliminar(creacion);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    @DELETE
    @Path("/eliminarPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarPorId(Integer id) throws ObtencionException{
    
        this.service.eliminarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
}
