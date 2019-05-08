package Interfaces.MenuPrincipal;

import Reproducible.Cancion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MisCanciones extends JPanel {
    private JTable tabla;
    private SpringLayout layout;
    private JButton reproducir;
    private JButton borrar;
    private JButton crear;
    private Cancion[] resultados;
    private JLabel nombreLabel;
    private JTextField nombre;
    DefaultTableModel modeloDatos;


    public MisCanciones() {
        layout = new SpringLayout();
        this.setLayout(layout);

        String[] titulos = {"Titulo","Estado","Duración"};
        Object[][] filas = new Object[0][3];
        modeloDatos = new DefaultTableModel(filas, titulos);
        tabla = new JTable(modeloDatos);
        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setPreferredSize(new Dimension(600, 530));



        reproducir = new JButton("Reproducir");
        borrar = new JButton("Borrar");
        crear = new JButton("Crear");
        nombreLabel = new JLabel("Nombre del Album:");
        nombre = new JTextField(15);

        this.add(scroll);
        this.add(reproducir);
        this.add(borrar);
        this.add(crear);
        this.add(nombre);
        this.add(nombreLabel);

        layout.putConstraint(SpringLayout.WEST, scroll, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.EAST, reproducir, 210, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, reproducir, 30, SpringLayout.NORTH, scroll);

        layout.putConstraint(SpringLayout.WEST, borrar, 0, SpringLayout.WEST, reproducir);
        layout.putConstraint(SpringLayout.NORTH, borrar, 50, SpringLayout.NORTH, reproducir);

        layout.putConstraint(SpringLayout.WEST, crear, 0, SpringLayout.WEST, borrar);
        layout.putConstraint(SpringLayout.NORTH, crear, 50, SpringLayout.NORTH, borrar);

        layout.putConstraint(SpringLayout.WEST, nombreLabel, 0, SpringLayout.WEST, crear);
        layout.putConstraint(SpringLayout.NORTH, nombreLabel, 40, SpringLayout.NORTH, crear);

        layout.putConstraint(SpringLayout.WEST, nombre, 0, SpringLayout.WEST, nombreLabel);
        layout.putConstraint(SpringLayout.NORTH, nombre, 10, SpringLayout.SOUTH, nombreLabel);

    }
    public void setControlador(ActionListener c) {
        reproducir.setActionCommand("ReproducirMis");
        reproducir.addActionListener(c);

        borrar.setActionCommand("Borrar");
        borrar.addActionListener(c);

        crear.setActionCommand("CrearAlbum");
        crear.addActionListener(c);
    }

    public JTable getTabla() {
        return tabla;
    }

    public void limpiarTabla() {
        int i;
        int rows = modeloDatos.getRowCount();
        for(i = 0; i < rows; i++) {
            modeloDatos.removeRow(0);
        }
        modeloDatos.fireTableDataChanged();
    }


    public Cancion[] getResultados() {
        return resultados;
    }

    public DefaultTableModel getModeloDatos() {
        return modeloDatos;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public void guardarResultados(Cancion[] canciones) {
        resultados = canciones;
    }
}
