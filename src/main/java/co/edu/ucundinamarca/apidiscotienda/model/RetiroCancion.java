/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.model;

import co.edu.ucundinamarca.ejbdiscotienda.entity.Cancion;
import co.edu.ucundinamarca.ejbdiscotienda.entity.Compra;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Miguel
 */
public class RetiroCancion {
    
    @NotNull(message = "Para efectuar el retiro se requiere una canción")
    private Cancion cancion;
    
    @NotNull(message = "Para efectuar el retiro se requiere una compra")
    private Compra compra;

    /**
     * @return the cancion
     */
    public Cancion getCancion() {
        return cancion;
    }

    /**
     * @param cancion the cancion to set
     */
    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    /**
     * @return the compra
     */
    public Compra getCompra() {
        return compra;
    }

    /**
     * @param compra the compra to set
     */
    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    
    
}
