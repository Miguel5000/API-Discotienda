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
 * Clase que permite guardar archivos desde cualquier lugar
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
public class Archivo {
    
    /**
     * Permite guardar un archivo
     * @param ruta es la ruta del archivo a guardar
     * @param archivoEnBytes es el contenido del archivo
     * @throws FileNotFoundException excepción lanzada al no encontrar el directorio
     * @throws IOException excepción lanzada al no poder guardar el archivo
     */
    public static void guardarArchivo(String ruta, Integer[] archivoEnBytes) throws FileNotFoundException, IOException{
    
        String rutaCompleta = "C:/Users/Miguel/Documents/NetBeansProjects/apiDiscotienda/src/main/webapp/" + ruta;
        
        byte[] archivoConvertido = new byte[archivoEnBytes.length];
        
        for (int i=0; i<archivoEnBytes.length; i++) {
            
            archivoConvertido[i] = archivoEnBytes[i].byteValue();
            
        }
        
        try (FileOutputStream fos = new FileOutputStream(rutaCompleta)){
            fos.write(archivoConvertido);
            fos.close();
        }
        
    }
    
}
