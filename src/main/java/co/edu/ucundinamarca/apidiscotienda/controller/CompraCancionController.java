/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;
import co.edu.ucundinamarca.ejbdiscotienda.dto.CompraCancionDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.CompraCancion;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.ICompraCancionService;
import co.edu.ucundinamarca.ejbdiscotienda.view.VentasCancion;
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
 * Clase que posee los servicios de la API encargados de gestionar a las asociaciones de canciones a compras
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/comprasCanciones")
public class CompraCancionController {
    
    /**
     * Objeto que permite la comunicación con el EJB
     */
    @EJB
    private ICompraCancionService service;
    
    /**
     * Permite obtener todas las compras de canciones
     * @return comprasCanciones
     * @throws ObtencionException excepción lanzada al no encontrar compras de canciones 
     */
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<CompraCancionDto> comprasCanciones = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(comprasCanciones).build();
        
    }
    
    /**
     * Permite obtener una compra de canción por su id
     * @param id es la llave primaria de la compra de canción a obtener
     * @return compraCanción
     * @throws ObtencionException excepción lanzada al no encontrar la compra de la canción
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ObtencionException{
    
        CompraCancionDto compraCancion = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(compraCancion).build();
        
    }
    
    /**
     * Permite crear la compra de la canción
     * @param compraCancion es la compra de la canción a crear
     * @return compraCancion
     * @throws CreacionException excepción lanzada al no poder crear la compra de la canción 
     */
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Valid CompraCancion compraCancion) throws CreacionException{
    
        this.service.crear(compraCancion);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    /**
     * Permite editar una compra de canción
     * @param compraCancion objeto que posee los datos de la compra de la canción a editar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar la compra de la canción a editar
     * @throws EdicionException excepción lanzada al no poder editar la compra de la canción por un conflicto
     */
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid CompraCancion compraCancion) throws ObtencionException, EdicionException{
    
        this.service.editar(compraCancion);
        return Response.status(Response.Status.OK).build();
    
    }
    
    /**
     * Permite eliminar una canción por su id
     * @param id es la llave primaria de la compra de la canción a eliminar
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar la compra de la cancíón a eliminar
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObtencionException{
    
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    /**
     * Permite retirar una canción de una compra
     * @param idCancion es la llave primaria de la canción a retirar
     * @param idCompra es la llave primaria de la compra de la cual se retirará la canción
     * @return Confirmación
     * @throws ObtencionException excepción lanzada al no encontrar la canción o compra a eliminar
     */
    @DELETE
    @Path("/retirarCancion/{idCancion}/{idCompra}")
    public Response retirarCancion(@PathParam("idCancion") Integer idCancion, @PathParam("idCompra") Integer idCompra) throws ObtencionException{
    
        this.service.retirarCancion(idCancion, idCompra);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    /**
     * Permite obtener las canciones con sus ventas
     * @return ventas
     * @throws ObtencionException excepción lanzada al no encontrar ninguna canción y por lo tanto ninguna venta
     */
    @GET
    @Path("/obtenerVentas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerVentas() throws ObtencionException{
    
        List<VentasCancion> ventas = this.service.obtenerVentas();
        return Response.status(Response.Status.OK).entity(ventas).build();
    
    }
    
}
