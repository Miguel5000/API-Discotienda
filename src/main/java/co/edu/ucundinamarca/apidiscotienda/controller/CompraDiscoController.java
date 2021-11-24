/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.ejbdiscotienda.dto.CompraDiscoDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.CompraDisco;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.ICompraDiscoService;
import co.edu.ucundinamarca.ejbdiscotienda.view.VentasDisco;
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
 * Clase que posee los servicios de la API encargados de gestionar la relación entre compras y discos
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/comprasDiscos")
public class CompraDiscoController {
    
    /**
     * Objeto que permite la comunicación con el EJB
     */
    @EJB
    private ICompraDiscoService service;
    
    /**
     * Permite obtener todas las compras de discos
     * @return compras
     * @throws ObtencionException excepción lanzada al no encontrar compras de discos
     */
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<CompraDiscoDto> comprasDiscos = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(comprasDiscos).build();
        
    }
    
    /**
     * Permite obtener una compra de disco por su id
     * @param id es la llave primaria de la compra de disco a obtener
     * @return compra
     * @throws ObtencionException excepción lanzada al no encontrar la compra
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        CompraDiscoDto compraDisco = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(compraDisco).build();
        
    }
    
    /**
     * Permite crear una compra de disco
     * @param compraDisco es la compra de disco a crear
     * @return Confirmación
     * @throws CreacionException excepción lanzada al no poder crear la compra de disco
     */
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid CompraDisco compraDisco) throws CreacionException{
    
        this.service.crear(compraDisco);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    /**
     * Permite editar una compra de disco
     * @param compraDisco objeto que posee los nuevos datos de la compra del disco
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar la compra de disco a editar
     * @throws EdicionException excepción lanzada al no poder editar la compra del disco por un conflicto
     */
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid CompraDisco compraDisco) throws ObtencionException, EdicionException{
    
        this.service.editar(compraDisco);
        return Response.status(Response.Status.OK).build();
    
    }

    /**
     * Permite eliminar la compra del disco por su id
     * @param id es la llave primaria de la compra del disco a eliminar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar la compra del disco a eliminar 
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    /**
     * Permite retirar un disco de una compra
     * @param idDisco es la llave primaria del disco a retirar de la compra
     * @param idCompra es la llave primaria de la compra de la cual se retirará el disco
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar el disco o la compra
     */
    @DELETE
    @Path("/retirarDisco/{idDisco}/{idCompra}")
    public Response retirarDisco(@PathParam("idDisco") Integer idDisco, @PathParam("idCompra") Integer idCompra) throws ObtencionException{
    
        this.service.retirarDisco(idDisco, idCompra);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    /**
     * Permite obtener todos los discos con sus ventas
     * @return ventas
     * @throws ObtencionException excepción lanzada al no encontrar discos y por lo tanto, tampoco encontrar sus ventas
     */
    @GET
    @Path("/obtenerVentas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerVentas() throws ObtencionException{
    
        List<VentasDisco> ventas = this.service.obtenerVentas();
        return Response.status(Response.Status.OK).entity(ventas).build();
    
    }
    
}
