/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.apidiscotienda.utilidades.Archivo;
import co.edu.ucundinamarca.ejbdiscotienda.dto.DiscoDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Disco;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.IDiscoService;
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
 * Clase que posee los servicios de la API encargados de gestionar a los discos
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/discos")
public class DiscoController {
    
    /**
     * Objeto que permite la comunicación con el EJB
     */
    @EJB
    private IDiscoService service;
    
    /**
     * Permite obtener todos los discos
     * @return discos
     * @throws ObtencionException excepción lanzada al no encontrar discos
     */
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<DiscoDto> discos = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(discos).build();
        
    }
    
    /**
     * Permite obtener un disco por su id
     * @param id es la llave primaria del disco a encontrar
     * @return disco
     * @throws ObtencionException excepción lanzada al no encontrar el disco
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        DiscoDto disco = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(disco).build();
        
    }
    
    /**
     * Permite obtener los discos de una artista
     * @param id es la llave primaria del artista
     * @return disco
     * @throws ObtencionException excepción lanzada al no encontrar discos de un artista
     */
    @GET
    @Path("/obtenerPorArtista/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorArtista(@PathParam("id") Integer id) throws ObtencionException{
    
        List<DiscoDto> discos = this.service.obtenerPorArtista(id);
        return Response.status(Response.Status.OK).entity(discos).build();
        
    }
    
    /**
     * Permite crear un disco
     * @param disco es el disco a crear
     * @return Confirmación
     * @throws CreacionException excepción lanzada al no poder crear el disco
     * @throws IOException excepción lanzada al no poder guardar la portada del disco
     */
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid Disco disco) throws CreacionException, IOException{
    
        String ruta = "imagenes/discos/" +  disco.getNombre() + new Date().getTime() + ".jpg";
            
        if(disco.getPortadaEnBytes() == null)
            disco.setPortada(null);
        else
            disco.setPortada("http://localhost:8080/apiDiscotienda/" + ruta);
 
        
        DiscoDto discoRetorno = this.service.crear(disco);
        
        if(disco.getPortadaEnBytes() != null){
            Archivo.guardarArchivo(ruta, disco.getPortadaEnBytes());
        }
        
        return Response.status(Response.Status.CREATED).entity(discoRetorno).build();
        
    }
    
    /**
     * Permite editar un disco
     * @param disco objeto que posee los nuevos datos del disco
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el disco a editar
     * @throws EdicionException excepción lanzada al no poder editar el disco por un conflicto
     * @throws IOException excepción lanzada al no poder actualizar la portada del disco
     */
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Disco disco) throws ObtencionException, EdicionException, IOException{
    
        String ruta = "imagenes/discos/" + new Date().getTime() + ".jpg";
            
        if(disco.getPortadaEnBytes() == null)
            disco.setPortada(null);
        else
            disco.setPortada("http://localhost:8080/apiDiscotienda/" + ruta);
 
        this.service.editar(disco);
        
        if(disco.getPortadaEnBytes() != null){
            Archivo.guardarArchivo(ruta, disco.getPortadaEnBytes());
        }
        
        return Response.status(Response.Status.OK).build();
    
    }
    
    /**
     * Permite eliminar un disco
     * @param id es la llave primaria del disco a eliminar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el disco a eliminar
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
}
