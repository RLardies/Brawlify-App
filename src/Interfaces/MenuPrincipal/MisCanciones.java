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
    private Cancion[] resultados;
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

        this.add(scroll);
        this.add(reproducir);
        this.add(borrar);

        layout.putConstraint(SpringLayout.WEST, scroll, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.EAST, reproducir, 210, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, reproducir, 30, SpringLayout.NORTH, scroll);

        layout.putConstraint(SpringLayout.WEST, borrar, 0, SpringLayout.WEST, reproducir);
        layout.putConstraint(SpringLayout.NORTH, borrar, 50, SpringLayout.NORTH, reproducir);

    }
    public void setControlador(ActionListener c) {
        reproducir.setActionCommand("ReproducirMis");
        reproducir.addActionListener(c);

        borrar.setActionCommand("Borrar");
        borrar.addActionListener(c);
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


    public void guardarResultados(Cancion[] canciones) {
        resultados = canciones;
    }
}
