/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.ejbdiscotienda.dto.RolDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Rol;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.IRolService;
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
 * Clase que posee los servicios de la API encargados de gestionar a los roles
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/roles")
public class RolController {
    
    /**
     * Objeto que permite la comunicación con el EJB
     */
    @EJB
    private IRolService service;
    
    /**
     * Permite obtener todos los roles
     * @return roles
     * @throws ObtencionException excepción lanzada al no encontrar roles
     */
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<RolDto> rol = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(rol).build();
        
    }
    
    /**
     * Permite obtener un rol por su id
     * @param id es la llave primaria del rol a encontrar
     * @return rol
     * @throws ObtencionException excepción lanzada al no encontrar el rol
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        RolDto rol = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(rol).build();
        
    }
    
    /**
     * Permite crear un rol
     * @param rol es el rol a crear
     * @return Confirmación
     * @throws CreacionException excepción lanzada al no poder crear el rol
     */
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid Rol rol) throws CreacionException{
    
        this.service.crear(rol);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    /**
     * Permite editar un rol
     * @param rol objeto que posee los nuevos datos del rol
     * @return Confirmación
     * @throws ObtencionException excepcion lanzada al no encontrar el rol a editar
     * @throws EdicionException excepción lanzada al no poder editar el rol por un conflicto
     */
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Rol rol) throws ObtencionException, EdicionException{
    
        this.service.editar(rol);
        return Response.status(Response.Status.OK).build();
    
    }
    
    /**
     * Permite eliminar un rol
     * @param id es la llave primaria del rol a eliminar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el rol a eliminar
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
}
