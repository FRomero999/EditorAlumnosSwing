package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Principal extends javax.swing.JFrame {
    private JList<String> lista;
    private JPanel panel1;
    private JButton boton;
    private JLabel info;

    public Principal() {
        this.setTitle("Alumnos");
        this.setResizable(false);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        lista.setModel(new DefaultListModel<String>());

        boton.addActionListener(e -> {
            Path archivo = Path.of("alumnos.csv");
            try {
                var lineas = Files.readAllLines(archivo);

                ((DefaultListModel<String>)lista.getModel()).clear();

                lineas.forEach(line -> {
                    ((DefaultListModel<String>)lista.getModel()).addElement(line);
                });

                info.setText("Alumnos leidos : "+lineas.size());

            } catch (IOException ex) {
                info.setText("Error al leer el archivo");
            }
        });

        lista.addListSelectionListener(e -> {
            int indice = lista.getSelectedIndex();
            if(!lista.getValueIsAdjusting() && indice>=0 ){
                String alumno = lista.getSelectedValue();
                ((DefaultListModel<String>)lista.getModel()).remove(indice);
                info.setText("Alumno eliminado: "+alumno);

                try( BufferedWriter bfw = new BufferedWriter(new FileWriter("alumnos.csv")) ){
                    var modelo = ((DefaultListModel<String>)lista.getModel());
                    for(int i=0; i<modelo.size(); i++){
                        bfw.write(modelo.get(i));
                        bfw.newLine();
                    }
                }catch(IOException ex){
                    info.setText("Error al escribir el archivo");
                }

            }
        });
    }

    public void start(Boolean b){
        this.setVisible(b);
    }

}
