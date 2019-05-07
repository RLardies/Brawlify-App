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
    private JButton eliminar;
    private JButton crear;
    private Lista[] resultados;
    private Lista listaSelec;
    private Reproducible[] reps;
    private JButton mostrar;
    private JLabel nombreLabel;
    private JTextField nombre;
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
        eliminar = new JButton("Eliminar");
        crear = new JButton("Crear");

        nombreLabel = new JLabel("Nombre de la Lista:");
        nombre = new JTextField(15);

        this.add(scroll);
        this.add(scroll2);
        this.add(reproducir);
        this.add(borrar);
        this.add(mostrar);
        this.add(eliminar);
        this.add(crear);
        this.add(nombre);
        this.add(nombreLabel);

        layout.putConstraint(SpringLayout.WEST, scroll, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, scroll2, 0, SpringLayout.WEST, scroll);
        layout.putConstraint(SpringLayout.NORTH, scroll2, 40, SpringLayout.SOUTH, scroll);

        layout.putConstraint(SpringLayout.EAST, mostrar, 170, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, mostrar, 30, SpringLayout.NORTH, scroll);

        layout.putConstraint(SpringLayout.WEST, reproducir, 0, SpringLayout.WEST, mostrar);
        layout.putConstraint(SpringLayout.NORTH, reproducir, 50, SpringLayout.NORTH, mostrar);

        layout.putConstraint(SpringLayout.WEST, borrar, 0, SpringLayout.WEST, reproducir);
        layout.putConstraint(SpringLayout.NORTH, borrar, 50, SpringLayout.NORTH, reproducir);

        layout.putConstraint(SpringLayout.WEST, crear, 0, SpringLayout.WEST, borrar);
        layout.putConstraint(SpringLayout.NORTH, crear, 50, SpringLayout.NORTH, borrar);

        layout.putConstraint(SpringLayout.WEST, nombreLabel, 0, SpringLayout.WEST, crear);
        layout.putConstraint(SpringLayout.NORTH, nombreLabel, 40, SpringLayout.NORTH, crear);

        layout.putConstraint(SpringLayout.WEST, nombre, 0, SpringLayout.WEST, nombreLabel);
        layout.putConstraint(SpringLayout.NORTH, nombre, 10, SpringLayout.SOUTH, nombreLabel);

        layout.putConstraint(SpringLayout.WEST, eliminar, 0, SpringLayout.WEST, borrar);
        layout.putConstraint(SpringLayout.NORTH, eliminar, 230, SpringLayout.NORTH, borrar);
    }

    public void setControlador(ActionListener c) {
        reproducir.setActionCommand("ReproducirLista");
        reproducir.addActionListener(c);

        borrar.setActionCommand("BorrarLista");
        borrar.addActionListener(c);

        mostrar.setActionCommand("Mostrar");
        mostrar.addActionListener(c);

        crear.setActionCommand("Crear");
        crear.addActionListener(c);

        eliminar.setActionCommand("Eliminar");
        eliminar.addActionListener(c);
    }

    public JTable getTabla() {
        return tabla;
    }

    public JTable getTabla2(){
        return tabla2;
    }

    public void limpiarTabla() {
        int i;
        int rows = modeloDatos.getRowCount();
        for(i = 0; i < rows; i++) {
            modeloDatos.removeRow(0);
        }
        modeloDatos.fireTableDataChanged();
    }

    public void limpiarTablaReproducibles() {
        int i;
        int rows = modeloReproducibles.getRowCount();
        for(i = 0; i < rows; i++) {
            modeloReproducibles.removeRow(0);
        }
        modeloReproducibles.fireTableDataChanged();
    }

    public Lista getListaSelec(){ return listaSelec; }

    public void setListaSelec(Lista l){
        listaSelec = l;
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

    public Reproducible[] getReps(){ return reps; }

    public void guardarReps(Reproducible[] reproducibles){
        reps = reproducibles;
    }

    public JTextField getNombre(){ return nombre; }
}
