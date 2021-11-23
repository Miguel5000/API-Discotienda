/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.apidiscotienda.utilidades.Archivo;
import co.edu.ucundinamarca.ejbdiscotienda.dto.CancionDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Cancion;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.ICancionService;
import java.io.IOException;
import java.util.Date;
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
 *
 * @author Miguel
 */

@Stateless
@Path("/canciones")
public class CancionController{
    
    @EJB
    private ICancionService service;
    
    
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException, IOException{
        
        List<CancionDto> canciones = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(canciones).build();
        
    }
    
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        CancionDto cancion = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(cancion).build();
        
    }
    
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid Cancion cancion) throws CreacionException, IOException{
    
        String ruta = "imagenes/canciones/" +  cancion.getDisco().getId() + "_" + cancion.getNombre() + ".jpg";
            
        if(cancion.getPortadaEnBytes() == null)
            cancion.setPortada(null);
        else
            cancion.setPortada("http://localhost:8080/apiDiscotienda/" + ruta);
 
        this.service.crear(cancion);
        
        if(cancion.getPortadaEnBytes() != null){
            Archivo.guardarArchivo(ruta, cancion.getPortadaEnBytes());
        }
        
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Cancion cancion) throws ObtencionException, EdicionException, IOException{
    
        String ruta = "imagenes/canciones/" +  new Date().getTime() + ".jpg";
            
        if(cancion.getPortadaEnBytes() == null)
            cancion.setPortada(null);
        else
            cancion.setPortada("http://localhost:8080/apiDiscotienda/" + ruta);
        
        this.service.editar(cancion);
        
        if(cancion.getPortadaEnBytes() != null){
            Archivo.guardarArchivo(ruta, cancion.getPortadaEnBytes());
        }
        
        return Response.status(Response.Status.OK).build();
    
    }
    
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    @GET
    @Path("/obtenerListaPorCompra/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerListaPorCompra(@PathParam("id") Integer id) throws ObtencionException{
    
        List<CancionDto> canciones = this.service.obtenerListaPorCompra(id);
        return Response.status(Response.Status.OK).entity(canciones).build();
        
    }
    
    @GET
    @Path("/obtenerListaPorDisco/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerListaPorDisco(@PathParam("id") Integer id) throws ObtencionException{
    
        List<CancionDto> canciones = this.service.obtenerListaPorDisco(id);
        return Response.status(Response.Status.OK).entity(canciones).build();
    
    }
    
}
