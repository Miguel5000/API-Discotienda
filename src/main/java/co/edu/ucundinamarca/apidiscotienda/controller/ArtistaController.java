/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.apidiscotienda.utilidades.Archivo;
import co.edu.ucundinamarca.ejbdiscotienda.dto.ArtistaDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Artista;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.IArtistaService;
import co.edu.ucundinamarca.ejbdiscotienda.view.VentasArtista;
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
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
        
        ArtistaDto artista = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(artista).build();
        
    }
    
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid Artista artista) throws CreacionException, IOException{
        
        String ruta = "imagenes/artistas/" +  artista.getNombres() + artista.getApellidos() + artista.getFechaDeNacimiento().toString() + ".jpg";
            
        if(artista.getFotoEnBytes() == null)
            artista.setFoto(null);
        else
            artista.setFoto("http://localhost:8080/apiDiscotienda/" + ruta);
        
        this.service.crear(artista);
        
        if(artista.getFotoEnBytes() != null){
            Archivo.guardarArchivo(ruta, artista.getFotoEnBytes());
        }
        
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Artista artista) throws ObtencionException, EdicionException, IOException{
        
        String ruta = "imagenes/artistas/" + new Date().getTime() + ".jpg";
            
        if(artista.getFotoEnBytes() == null)
            artista.setFoto(null);
        else
            artista.setFoto("http://localhost:8080/apiDiscotienda/" + ruta);
        
        this.service.editar(artista);
        
        if(artista.getFotoEnBytes() != null){
            Archivo.guardarArchivo(ruta, artista.getFotoEnBytes());
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
    @Path("/obtenerVentas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerVentas() throws ObtencionException{
        
        List<VentasArtista> ventas = this.service.obtenerVentas();
        return Response.status(Response.Status.OK).entity(ventas).build();
        
    }
    
}
