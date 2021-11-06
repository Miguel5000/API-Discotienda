/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.ejbdiscotienda.dto.ArtistaDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Artista;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.IArtistaService;
import co.edu.ucundinamarca.ejbdiscotienda.view.VentasArtista;
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
@Path("/artistas")
public class ArtistaController {
    
    @EJB
    private IArtistaService service;
    
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
        
        List<ArtistaDto> artistas = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(artistas).build();
        
    }
    
    @GET
    @Path("/obtenerPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(Integer id) throws ObtencionException{
        
        ArtistaDto artista = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(artista).build();
        
    }
    
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Artista artista) throws CreacionException{
        
        this.service.crear(artista);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(Artista artista) throws ObtencionException, EdicionException{
        
        this.service.editar(artista);
        return Response.status(Response.Status.OK).build();
        
    }
   
    @DELETE
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(Artista artista) throws ObtencionException{
        
        this.service.eliminar(artista);
        return Response.status(Response.Status.NO_CONTENT).build();
        
    }
    
    @DELETE
    @Path("/eliminarPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarPorId(Integer id) throws ObtencionException{
        
        this.service.eliminarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();
        
    }
    
    @GET
    @Path("/obtenerVentas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerVentas() throws ObtencionException{
        
        List<VentasArtista> ventas = this.service.obtenerVentas();
        return Response.status(Response.Status.OK).entity(ventas).build();
        
    }
    
}
