/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apidiscotienda.utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel
 */
public class Archivo {
    
    public static void guardarArchivo(String ruta, Byte[] archivoEnBytes) throws FileNotFoundException, IOException{
    
        String rutaCompleta = "C:/Users/Miguel/Documents/NetBeansProjects/apiDiscotienda/src/main/webapp/" + ruta;
        
        byte[] archivoConvertido = new byte[archivoEnBytes.length];
        
        for (int i=0; i<archivoEnBytes.length; i++) {
            
            archivoConvertido[i] = archivoEnBytes[i];
            
        }
        
        try (FileOutputStream fos = new FileOutputStream(rutaCompleta)){
            fos.write(archivoConvertido);
            fos.close();
        }
        
    }
    
}
