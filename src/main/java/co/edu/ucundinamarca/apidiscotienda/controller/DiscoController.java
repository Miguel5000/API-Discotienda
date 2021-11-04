/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.ejbdiscotienda.entity.Disco;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.IDiscoService;
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
@Path("/discos")
public class DiscoController {
    
    @EJB
    private IDiscoService service;
    
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<Disco> discos = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(discos).build();
        
    }
    
    @GET
    @Path("/obtenerPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(Integer id) throws ObtencionException{
    
        Disco disco = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(disco).build();
        
    }
    
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Disco disco) throws CreacionException{
    
        this.service.crear(disco);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(Disco disco) throws ObtencionException, EdicionException{
    
        this.service.editar(disco);
        return Response.status(Response.Status.OK).build();
    
    }
    
    @DELETE
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(Disco disco) throws ObtencionException{
    
        this.service.eliminar(disco);
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
