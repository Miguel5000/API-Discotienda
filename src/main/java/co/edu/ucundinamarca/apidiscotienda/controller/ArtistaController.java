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
 * Clase que posee los servicios de la API encargados de gestionar a los artistas
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/artistas")
public class ArtistaController {
    
    /**
     * Objeto que permite la comunicación con el EJB
     */
    @EJB
    private IArtistaService service;
    
    /**
     * Permite obtener a todos los artistas
     * @return artistas
     * @throws ObtencionException excepción lanzada al no existir ningún artista
     */
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
        
        List<ArtistaDto> artistas = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(artistas).build();
        
    }
    
    /**
     * Permite obtener a un artista por su id
     * @param id es la llave primaria de entrada
     * @return artista
     * @throws ObtencionException excepción lanzada al no encontrar al artista con el id
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
        
        ArtistaDto artista = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(artista).build();
        
    }
    
    /**
     * Permite crear a un artista
     * @param artista es el artista a crear
     * @return confirmación
     * @throws CreacionException excepción lanzada al no poder crear al artista
     * @throws IOException excepción lanzada al no poder almacenar la foto del artista
     */
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
    
    /**
     * Permite editar a un artista
     * @param artista objeto que contienen los nuevos datos del artista
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar al artista a editar
     * @throws EdicionException excepción lanzada al no poder editar al artista por un conflicto
     * @throws IOException excepción lanzada al no poder editar la foto del artista
     */
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
    
    /**
     * Permite eliminar al artista por su id
     * @param id es la llave primaria del artista a eliminar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar al artista a eliminar
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
        
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
        
    }
    
    /**
     * Permite obtener las ventas de todos los artistas
     * @return ventas
     * @throws ObtencionException excepción lanzada al no encontrar ningún artista y por lo tanto ninguna venta
     */
    @GET
    @Path("/obtenerVentas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerVentas() throws ObtencionException{
        
        List<VentasArtista> ventas = this.service.obtenerVentas();
        return Response.status(Response.Status.OK).entity(ventas).build();
        
    }
    
}
