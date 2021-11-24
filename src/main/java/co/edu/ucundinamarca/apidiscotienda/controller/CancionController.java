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
 * Clase que posee los servicios de la API encargados de gestionar a las canciones
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/canciones")
public class CancionController{
    
    /**
     * Objeto que permite la comunicación con el EJB
     */
    @EJB
    private ICancionService service;
    
    /**
     * Permite obtener todas las canciones
     * @return canciones
     * @throws ObtencionException excepción lanzada al no existir ninguna canción
     */
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
        
        List<CancionDto> canciones = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(canciones).build();
        
    }
    
    /**
     * Permite obtener una canción por su id
     * @param id es la llave primaria de la canción a obtener
     * @return canción
     * @throws ObtencionException excepción lanzada a no existir una canción con el id suministrado
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        CancionDto cancion = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(cancion).build();
        
    }
    
    /**
     * Permite crear una canción
     * @param cancion es la canción a crear
     * @return Confirmación
     * @throws CreacionException excepción lanzada al no poder crear la canción
     * @throws IOException excepción lanzada al no poder guardar la portada de la canción
     */
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid Cancion cancion) throws CreacionException, IOException{
    
        String ruta = "imagenes/canciones/" +  new Date().getTime() + ".jpg";
            
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
    
    /**
     * Permite editar una canción
     * @param cancion objeto que posee los datos de la cancion a editar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar la canción a editar
     * @throws EdicionException excepción lanzada al no poder editar la canción por un conflicto
     * @throws IOException excepción lanzada al no poder actualizar la portada de la canción
     */
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
    
    /**
     * Permite eliminar una canción por su id
     * @param id es la llave primaria de la canción a eliminar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar la canción a eliminar
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    /**
     * Permite obtener las canciones de una compra en específico
     * @param id es la llave primaria de la compra
     * @return canciones
     * @throws ObtencionException excepción lanzada al no encontrar ninguna canción en la compra
     */
    @GET
    @Path("/obtenerListaPorCompra/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerListaPorCompra(@PathParam("id") Integer id) throws ObtencionException{
    
        List<CancionDto> canciones = this.service.obtenerListaPorCompra(id);
        return Response.status(Response.Status.OK).entity(canciones).build();
        
    }
    
    /**
     * Permite obtener las canciones de un disco
     * @param id es la llave primaria del disco
     * @return discos
     * @throws ObtencionException excepción lanzada al no encontrar canciones en el disco
     */
    @GET
    @Path("/obtenerListaPorDisco/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerListaPorDisco(@PathParam("id") Integer id) throws ObtencionException{
    
        List<CancionDto> canciones = this.service.obtenerListaPorDisco(id);
        return Response.status(Response.Status.OK).entity(canciones).build();
    
    }
    
}
