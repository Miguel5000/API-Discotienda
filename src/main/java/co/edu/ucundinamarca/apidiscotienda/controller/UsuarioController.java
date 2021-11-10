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
 *
 * @author Miguel
 */

@Stateless
@Path("/usuarios")
public class UsuarioController {
    
    @EJB
    private IUsuarioService service;
    
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<UsuarioDto> usuarios = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(usuarios).build();
        
    }
    
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        UsuarioDto usuario = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(usuario).build();
        
    }
    
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid Usuario usuario) throws CreacionException{
    
        this.service.crear(usuario);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Usuario usuario) throws ObtencionException, EdicionException{
    
        this.service.editar(usuario);
        return Response.status(Response.Status.OK).build();
    
    }
    
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    @PUT
    @Path("/enviarCorreoRecuperacion/{correo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enviarCorreoRecuperacion(@PathParam("correo") String correo) throws ObtencionException{
    
        this.service.enviarCorreoRecuperacion(correo);
        return Response.status(Response.Status.OK).build();
        
    }
    
    @PUT
    @Path("/recuperarClave/{token}/{clave}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recuperarClave(@PathParam("token") String token, @PathParam("clave") String clave) throws ObtencionException{
    
        this.service.recuperarClave(token, clave);
        return Response.status(Response.Status.OK).build();
        
    }
    
    @PUT
    @Path("/enviarCorreoValidacion/{correo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enviarCorreoValidacion(@PathParam("correo") String correo) throws ObtencionException{
    
        this.service.enviarCorreoValidacion(correo);
        return Response.status(Response.Status.OK).build();
        
    }
    
    @PUT
    @Path("/modificarCorreo/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarCorreo(@PathParam("token") String token) throws ObtencionException{
    
        this.service.modificarCorreo(token);
        return Response.status(Response.Status.OK).build();
        
    }
    
}
