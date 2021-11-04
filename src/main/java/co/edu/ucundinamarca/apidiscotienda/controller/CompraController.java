/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.ejbdiscotienda.entity.Carrito;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Compra;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Usuario;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.ICompraService;
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
@Path("/compras")
public class CompraController {
    
    @EJB
    private ICompraService service;
    
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<Compra> compras = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(compras).build();
        
    }
    
    @GET
    @Path("/obtenerPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(Integer id) throws ObtencionException{
    
        Compra compra = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(compra).build();
        
    }
    
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Compra compra) throws CreacionException{
    
        this.service.crear(compra);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(Compra compra) throws ObtencionException, EdicionException{
    
        this.service.editar(compra);
        return Response.status(Response.Status.OK).build();
    
    }
    
    @DELETE
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(Compra compra) throws ObtencionException{
    
        this.service.eliminar(compra);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    @DELETE
    @Path("/eliminarPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarPorId(Integer id) throws ObtencionException{
    
        this.service.eliminarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    @PUT
    @Path("/comprar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response comprar(Compra compra) throws ObtencionException{
    
        this.service.comprar(compra);
        return Response.status(Response.Status.OK).build();
    
    }
    
    @POST
    @Path("/obtenerCarrito")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCarrito(Usuario usuario) throws ObtencionException{
    
        Carrito carrito = this.service.obtenerCarrito(usuario);
        return Response.status(Response.Status.OK).entity(carrito).build();
    
    }
    
}
