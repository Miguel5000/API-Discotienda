/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.ejbdiscotienda.dto.UsuarioDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Usuario;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.IUsuarioService;
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
 * Clase que posee los servicios de la API encargados de gestionar a los usuarios
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/usuarios")
public class UsuarioController {
    
    /**
     * Objeto que permite la comunicación con el EJB
     */
    @EJB
    private IUsuarioService service;
    
    /**
     * Permite obtener todos los usuarios
     * @return usuarios
     * @throws ObtencionException excepción lanzada al no encontrar usuarios
     */
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<UsuarioDto> usuarios = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(usuarios).build();
        
    }
    
    /**
     * Permite obtener un usuario por su id
     * @param id es la llave primaria del usuario a encontrar
     * @return usuario
     * @throws ObtencionException excepción lanzada al no encontrar el usuario
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        UsuarioDto usuario = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(usuario).build();
        
    }
    
    /**
     * Permite crear un usuario
     * @param usuario es el usuario a crear
     * @return Confirmación
     * @throws CreacionException excepción lanzada al no poder crear el usuario
     */
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid Usuario usuario) throws CreacionException{
    
        this.service.crear(usuario);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    /**
     * Permite editar un usuario
     * @param usuario objeto con los nuevos datos del usuario
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el usuario a editar
     * @throws EdicionException excepción lanzada al no poder editar el usuario por un conflicto
     */
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Usuario usuario) throws ObtencionException, EdicionException{
    
        this.service.editar(usuario);
        return Response.status(Response.Status.OK).build();
    
    }
    
    /**
     * Permite eliminar un usuario por su id
     * @param id es la llave primaria del usuario a eliminar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el usuario a eliminar
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    /**
     * Permite obtener los datos del usuario al iniciar sesión
     * @param correo es el correo del usuario
     * @param clave es la clave del usuario
     * @return usuario
     * @throws ObtencionException excepción lanzada al no encontrar el usuario
     */
    @GET
    @Path("/iniciarSesion/{correo}/{clave}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response iniciarSesion(@PathParam("correo") String correo, @PathParam("clave") String clave) throws ObtencionException{
    
        UsuarioDto usuario = this.service.iniciarSesion(correo, clave);
        return Response.status(Response.Status.OK).entity(usuario).build();
    
    }
    
    /**
     * Permite enviar un correo de recuperación
     * @param correo es la dirección del correo
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar un usuario con el correo suministrado
     */
    @PUT
    @Path("/enviarCorreoRecuperacion/{correo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enviarCorreoRecuperacion(@PathParam("correo") String correo) throws ObtencionException{
    
        this.service.enviarCorreoRecuperacion(correo);
        return Response.status(Response.Status.OK).build();
        
    }
    
    /**
     * Permite que un usuario recupere su clave
     * @param token es el token que usa el usuario para recuperar su contraseña
     * @param clave es la nueva clave
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el usuario
     */
    @PUT
    @Path("/recuperarClave/{token}/{clave}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recuperarClave(@PathParam("token") String token, @PathParam("clave") String clave) throws ObtencionException{
    
        this.service.recuperarClave(token, clave);
        return Response.status(Response.Status.OK).build();
        
    }
    
    /**
     * Permite enviar un correo de validación para comprobar la existencia de la dirección
     * @param correo es el correo a validar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el correo en un usuario
     */
    @PUT
    @Path("/enviarCorreoValidacion/{correo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enviarCorreoValidacion(@PathParam("correo") String correo) throws ObtencionException{
    
        this.service.enviarCorreoValidacion(correo);
        return Response.status(Response.Status.OK).build();
        
    }
    
    /**
     * Permite modificar el correo de un usuario
     * @param token es el token usado para validar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no poder encontrar un usuario con el correo
     */
    @PUT
    @Path("/modificarCorreo/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarCorreo(@PathParam("token") String token) throws ObtencionException{
    
        this.service.modificarCorreo(token);
        return Response.status(Response.Status.OK).build();
        
    }
    
}
