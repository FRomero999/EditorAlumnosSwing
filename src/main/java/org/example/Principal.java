package org.example;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Principal extends javax.swing.JFrame {
    private JList<String> lista;
    private JPanel panel1;
    private JButton boton;
    private JLabel info;

    private DefaultListModel<String> listaModel =  new DefaultListModel<String>();
    private DataService dataService;

    public Principal(DataService fs) {
        this.setTitle("Alumnos");
        this.setResizable(false);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.dataService = fs;
        this.lista.setModel(listaModel);

        boton.addActionListener(e -> {

            var datos = dataService.leerDatos();

            if (datos.isEmpty()) {
                info.setText("Error al leer el archivo");
                return;
            }

            datos.forEach(line -> {
                listaModel.addElement(line);
            });

           info.setText("Alumnos leidos : "+datos.size());

        });

        lista.addListSelectionListener(e -> {
            int indice = lista.getSelectedIndex();
            if(!lista.getValueIsAdjusting() && indice>=0 ){

                ContextService.getInstance().setData("alumno",lista.getSelectedValue());
                ContextService.getInstance().setData("alumno_id",indice);

                var dialogo = new Info(this);
                dialogo.setVisible(true);

            }
        });
    }

    public void removeItem(Integer indice){

        var alumno = listaModel.get(indice);
        listaModel.remove(indice);
        info.setText("Alumno eliminado: "+alumno);

        var contenido = new ArrayList<String>();
        for(int i=0; i<listaModel.size(); i++) contenido.add(listaModel.get(i));

        Boolean result = dataService.guardarDatos(contenido);
        if(!result){
            info.setText("Error al escribir el archivo");
        }
    }

    public void start(Boolean b){
        this.setVisible(b);
    }

}
