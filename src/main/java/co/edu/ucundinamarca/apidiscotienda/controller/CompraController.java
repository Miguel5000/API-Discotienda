/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.ejbdiscotienda.dto.CompraDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Carrito;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Compra;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.ICompraService;
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
@Path("/compras")
public class CompraController {
    
    @EJB
    private ICompraService service;
    
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<CompraDto> compras = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(compras).build();
        
    }
    
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        CompraDto compra = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(compra).build();
        
    }
    
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid Compra compra) throws CreacionException{
    
        this.service.crear(compra);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Compra compra) throws ObtencionException, EdicionException{
    
        this.service.editar(compra);
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
    @Path("/comprar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response comprar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.comprar(id);
        return Response.status(Response.Status.OK).build();
    
    }
    
    @GET
    @Path("/obtenerCarrito/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCarrito(@PathParam("id") Integer id) throws ObtencionException{
    
        Carrito carrito = this.service.obtenerCarrito(id);
        return Response.status(Response.Status.OK).entity(carrito).build();
    
    }
    
    @GET
    @Path("/obtenerCompraCarrito/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCompraCarrito(@PathParam("id") Integer id) throws ObtencionException{
    
        CompraDto compra = this.service.obtenerCompraCarrito(id);
        return Response.status(Response.Status.OK).entity(compra).build();
        
    }
    
    @GET
    @Path("/obtenerCarritoPorCompra/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCarritoPorCompra(@PathParam("id") Integer id) throws ObtencionException{
    
        Carrito carrito = this.service.obtenerCarritoPorCompra(id);
        return Response.status(Response.Status.OK).entity(carrito).build();
        
    }
    
    @GET
    @Path("/obtenerComprasDeUsuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComprasDeUsuario(@PathParam("id") Integer id) throws ObtencionException{
    
        List<CompraDto> compras = this.service.obtenerComprasDeUsuario(id);
        return Response.status(Response.Status.OK).entity(compras).build();
        
    }
    
}
