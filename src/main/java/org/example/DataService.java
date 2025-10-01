package org.example;

import java.util.ArrayList;
import java.util.List;

public interface DataService {
    List<String> leerDatos();
    Boolean guardarDatos(List<String> datos);
}
