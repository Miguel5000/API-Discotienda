/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.controller;

import co.edu.ucundinamarca.ejbdiscotienda.dto.CompraDiscoDto;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Compra;
import co.edu.ucundinamarca.ejbdiscotienda.entity.CompraDisco;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Disco;
import co.edu.ucundinamarca.ejbdiscotienda.exception.CreacionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.EdicionException;
import co.edu.ucundinamarca.ejbdiscotienda.exception.ObtencionException;
import co.edu.ucundinamarca.ejbdiscotienda.service.ICompraDiscoService;
import co.edu.ucundinamarca.ejbdiscotienda.view.VentasDisco;
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
@Path("/comprasDiscos")
public class CompraDiscoController {
    
    @EJB
    private ICompraDiscoService service;
    
    @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() throws ObtencionException{
    
        List<CompraDiscoDto> comprasDiscos = this.service.obtenerTodos();
        return Response.status(Response.Status.OK).entity(comprasDiscos).build();
        
    }
    
    @GET
    @Path("/obtenerPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(Integer id) throws ObtencionException{
    
        CompraDiscoDto compraDisco = this.service.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(compraDisco).build();
        
    }
    
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(CompraDisco compraDisco) throws CreacionException{
    
        this.service.crear(compraDisco);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(CompraDisco compraDisco) throws ObtencionException, EdicionException{
    
        this.service.editar(compraDisco);
        return Response.status(Response.Status.OK).build();
    
    }
    
    @DELETE
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(CompraDisco compraDisco) throws ObtencionException{
    
        this.service.eliminar(compraDisco);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    @DELETE
    @Path("/eliminarPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarPorId(Integer id) throws ObtencionException{
    
        this.service.eliminarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    @DELETE
    @Path("/retirarDisco")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response retirarDisco(Disco disco, Compra compra) throws ObtencionException{
    
        this.service.retirarDisco(disco, compra);
        return Response.status(Response.Status.NO_CONTENT).build();
    
    }
    
    @GET
    @Path("/obtenerVentas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerVentas() throws ObtencionException{
    
        List<VentasDisco> ventas = this.service.obtenerVentas();
        return Response.status(Response.Status.OK).entity(ventas).build();
    
    }
    
}
