package org.example;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileService implements DataService{

    private final Logger logger =Logger.getLogger("FileService");
    private String archivo;

    FileService(String archivo) {
        this.archivo = archivo;
    }

    public List<String> leerDatos(){
        List<String> salida = new ArrayList<String>();
        Path path = Path.of(Main.ARCHIVO_DATOS);
        try {
            salida = Files.readAllLines(path);
            logger.info("Archivo leido "+archivo);
        } catch(IOException e) {
            logger.severe("Error al leer el archivo");
        }
        return salida;
    }

    @Override
    public Boolean guardarDatos(List<String> datos) {
        try( BufferedWriter bfw = new BufferedWriter(new FileWriter(archivo)) ){
            for(String s: datos){
                bfw.write(s);
                bfw.newLine();
            };
            logger.info("Archivo escrito "+archivo);
            return true;
        }catch(IOException ex){
            logger.severe("Error en la escritura");
            return false;
        }
    }

}
