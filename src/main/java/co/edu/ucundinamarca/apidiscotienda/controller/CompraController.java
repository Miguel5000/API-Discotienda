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
 * Clase que posee los servicios de la API encargados de gestionar las compras
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/compras")
public class CompraController {
    
    /**
     * Objeto que permite la comunicación con el EJB
     */
    @EJB
    private ICompraService service;
    
    /**
     * Permite obtener todas las compras
     * @return compras
     * @throws ObtencionException excepción lanzada al no encontrar ninguna compra
     */
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<CompraDto> compras = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(compras).build();
        
    }
    
    /**
     * Permite obtener una compra por su id
     * @param id es la llave primaria de la compra a obtener
     * @return compra
     * @throws ObtencionException excepción lanzada al no encontrar la compra
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        CompraDto compra = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(compra).build();
        
    }
    
    /**
     * Permite crear una compra
     * @param compra es la compra a crear
     * @return Confirmación
     * @throws CreacionException excepción lanzada al no poder crear la compra
     */
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid Compra compra) throws CreacionException{
    
        this.service.crear(compra);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    /**
     * Permite editar una compra
     * @param compra objeto que posee los datos de la compra
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar la compra a editar
     * @throws EdicionException excepción lanzada al no poder editar la compra por un conflicto
     */
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Compra compra) throws ObtencionException, EdicionException{
    
        this.service.editar(compra);
        return Response.status(Response.Status.OK).build();
    
    }
    
    /**
     * Permite eliminar una compra por su id
     * @param id es la llave primaria de la compra a eliminar
     * @return compra
     * @throws ObtencionException excepción lanzada al no encontrar la compra a eliminar
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    /**
     * Permite comprar el carrito
     * @param id es el id de la compra que representa al carrito
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar la compra
     */
    @PUT
    @Path("/comprar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response comprar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.comprar(id);
        return Response.status(Response.Status.OK).build();
    
    }
    
    /**
     * Permite obtener el carrito de un usuario
     * @param id es la llave primaria del usuario
     * @return carrito
     * @throws ObtencionException excepción lanzada al no encontrar el carrito del usuario
     */
    @GET
    @Path("/obtenerCarrito/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCarrito(@PathParam("id") Integer id) throws ObtencionException{
    
        Carrito carrito = this.service.obtenerCarrito(id);
        return Response.status(Response.Status.OK).entity(carrito).build();
    
    }
    
    /**
     * Permite obtener la compra del carrito del usuario
     * @param id es la llave primaria del usuario
     * @return compra
     * @throws ObtencionException excepción lanzada al no encontrar una compra de carrito para el usuario
     */
    @GET
    @Path("/obtenerCompraCarrito/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCompraCarrito(@PathParam("id") Integer id) throws ObtencionException{
    
        CompraDto compra = this.service.obtenerCompraCarrito(id);
        return Response.status(Response.Status.OK).entity(compra).build();
        
    }
    
    /**
     * Permite obtener el carrito de una compra en específico
     * @param id es la llave primaria de la compra
     * @return carrito
     * @throws ObtencionException excepción lanzada al no encontrar la compra
     */
    @GET
    @Path("/obtenerCarritoPorCompra/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCarritoPorCompra(@PathParam("id") Integer id) throws ObtencionException{
    
        Carrito carrito = this.service.obtenerCarritoPorCompra(id);
        return Response.status(Response.Status.OK).entity(carrito).build();
        
    }
    
    /**
     * Permite obtener las compras realizadas por un usuario
     * @param id es la llave primaria del usuario
     * @return compras
     * @throws ObtencionException excepción lanzada al no encontrar compras asociadas al usuario
     */
    @GET
    @Path("/obtenerComprasDeUsuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComprasDeUsuario(@PathParam("id") Integer id) throws ObtencionException{
    
        List<CompraDto> compras = this.service.obtenerComprasDeUsuario(id);
        return Response.status(Response.Status.OK).entity(compras).build();
        
    }
    
}
