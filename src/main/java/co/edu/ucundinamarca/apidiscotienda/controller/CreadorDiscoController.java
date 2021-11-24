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
 * Clase que posee los servicios de la API encargados de gestionar las relaciones de autores con los discos que crearon
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/creadoresDiscos")
public class CreadorDiscoController {
    
    /**
     * Objeto que permite la comunicación con el EJB
     */
    @EJB
    private ICreadorDiscoService service;
    
    /**
     * Permite obtener todas las creaciones de discos
     * @return creacionesDiscos
     * @throws ObtencionException 
     */
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<CreadorDiscoDto> creaciones = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(creaciones).build();
        
    }
    
    /**
     * Permite obtener una creación de disco en específico
     * @param id es la llave primaria de la creación de disco a encontrar
     * @return creacionDisco
     * @throws ObtencionException excepción lanzada al no encontar la creación del disco
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        CreadorDiscoDto creacion = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(creacion).build();
        
    }
    
    /**
     * Permite obtener una creación de disco por su creador y por el disco
     * @param idArtista es la llave primaria del artista creador
     * @param idDisco es la llave primaria del disco
     * @return creaciónDisco
     * @throws ObtencionException excepción lanzada al no encontrar la creación del disco
     */
    @GET
    @Path("/obtenerPorCreadorYDisco/{idArtista}/{idDisco}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorCreadorYDisco(@PathParam("idArtista") Integer idArtista, @PathParam("idDisco") Integer idDisco) throws ObtencionException{
    
        CreadorDiscoDto creacion = this.service.obtenerPorCreadorYDisco(idArtista, idDisco);
        return Response.status(Response.Status.OK).entity(creacion).build();
        
    }
    
    /**
     * Permite crear una creación de disco
     * @param creacion es la creación de disco a crear
     * @return Confirmación
     * @throws CreacionException excepción lanzada al no poder crear la creación de disco
     */
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid CreadorDisco creacion) throws CreacionException{
    
        this.service.crear(creacion);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    /**
     * Permite editar un disco
     * @param creacion objeto que posee los nuevos datos de la creación de disco
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar la creación de disco a editar
     * @throws EdicionException excepción lanzada al no poder editar la creación de disco por un conflicto
     */
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid CreadorDisco creacion) throws ObtencionException, EdicionException{
    
        this.service.editar(creacion);
        return Response.status(Response.Status.OK).build();
    
    }

    /**
     * Permite eliminar una creación de disco
     * @param id es la llave primaria de la creación de disco a eliminar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar la creación de disco a eliminar
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
}
