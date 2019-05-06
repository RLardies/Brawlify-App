package Interfaces.MenuPrincipal;

import Reproducible.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class MisListas extends JPanel {

    private JTable tabla;
    private JTable tabla2;
    private SpringLayout layout;
    private JButton reproducir;
    private JButton borrar;
    private Lista[] resultados;
    private JButton mostrar;
    DefaultTableModel modeloDatos;
    DefaultTableModel modeloReproducibles;


    public MisListas() {
        layout = new SpringLayout();
        this.setLayout(layout);

        String[] titulos = {"Listas", "Nº de Canciones", "Duración"};
        Object[][] filas = new Object[0][3];
        modeloDatos = new DefaultTableModel(filas, titulos);

        String[] titulos2 = {"Titulo", "Tipo", "Nº de Canciones"};
        Object[][] filas2 = new Object[0][3];
        modeloReproducibles = new DefaultTableModel(filas2,titulos2);

        tabla = new JTable(modeloDatos);
        tabla2 = new JTable(modeloReproducibles);

        JScrollPane scroll = new JScrollPane(tabla);
        JScrollPane scroll2 = new JScrollPane(tabla2);

        scroll.setPreferredSize(new Dimension(600, 230));
        scroll2.setPreferredSize(new Dimension(600,250));



        reproducir = new JButton("Reproducir");
        borrar = new JButton("Borrar");
        mostrar = new JButton("Mostrar");

        this.add(scroll);
        this.add(scroll2);
        this.add(reproducir);
        this.add(borrar);
        this.add(mostrar);

        layout.putConstraint(SpringLayout.WEST, scroll, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, scroll2, 0, SpringLayout.WEST, scroll);
        layout.putConstraint(SpringLayout.NORTH, scroll2, 40, SpringLayout.SOUTH, scroll);

        layout.putConstraint(SpringLayout.EAST, mostrar, 210, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, mostrar, 30, SpringLayout.NORTH, scroll);

        layout.putConstraint(SpringLayout.WEST, reproducir, 0, SpringLayout.WEST, mostrar);
        layout.putConstraint(SpringLayout.NORTH, reproducir, 50, SpringLayout.NORTH, mostrar);

        layout.putConstraint(SpringLayout.WEST, borrar, 0, SpringLayout.WEST, reproducir);
        layout.putConstraint(SpringLayout.NORTH, borrar, 50, SpringLayout.NORTH, reproducir);
    }

    public void setControlador(ActionListener c) {
        reproducir.setActionCommand("ReproducirLista");
        reproducir.addActionListener(c);

        borrar.setActionCommand("Borrar");
        borrar.addActionListener(c);

        mostrar.setActionCommand("Mostrar");
        mostrar.addActionListener(c);
    }

    public JTable getTabla() {
        return tabla;
    }

    public void limpiarTabla() {
        int i;
        int rows = modeloDatos.getRowCount();
        for(i = 0; i < rows; i++) {
            modeloReproducibles.removeRow(0);
        }
        modeloReproducibles.fireTableDataChanged();
    }


    public Lista[] getResultados() {
        return resultados;
    }

    public DefaultTableModel getModeloDatos() {
        return modeloDatos;
    }

    public DefaultTableModel getModeloReproducibles(){ return modeloReproducibles; }


    public void guardarResultados(Lista[] listas) {
        resultados = listas;
    }
}
