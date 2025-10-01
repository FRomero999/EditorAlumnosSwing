package org.example;


public class Main {

    public static String ARCHIVO_DATOS = "alumnos.csv";

    public static void main(String[] args) {

        DataService dataService = new FileService(ARCHIVO_DATOS);

        Principal principal = new Principal(dataService);
        principal.start(true);
    }
}