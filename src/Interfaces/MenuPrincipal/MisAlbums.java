package Interfaces.MenuPrincipal;

import Reproducible.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class MisAlbums extends JPanel {

    private JTable tabla;
    private JTable tabla2;
    private JTable tabla3;
    private SpringLayout layout;
    private JButton reproducir;
    private JButton borrar;
    private JButton añadirLista;
    private Album[] resultados;
    private Album albumSelec;
    private Lista[] listas;
    private Lista listaSelec;
    private Cancion[] canciones;
    private JButton mostrar;
    DefaultTableModel modeloDatos;
    DefaultTableModel modeloReproducibles;
    DefaultTableModel modeloListas;


    public MisAlbums() {
        layout = new SpringLayout();
        this.setLayout(layout);

        String[] titulos = {"Albums", "Nº de Canciones", "Año"};
        Object[][] filas = new Object[0][3];
        modeloDatos = new DefaultTableModel(filas, titulos);

        String[] titulos2 = {"Titulo"};
        Object[][] filas2 = new Object[0][1];
        modeloReproducibles = new DefaultTableModel(filas2,titulos2);

        String[] titulos3 = {"Listas"};
        Object[][] filas3 = new Object[0][1];
        modeloListas = new DefaultTableModel(filas3, titulos3);

        tabla = new JTable(modeloDatos);
        tabla2 = new JTable(modeloReproducibles);
        tabla3 = new JTable(modeloListas);

        JScrollPane scroll = new JScrollPane(tabla);
        JScrollPane scroll2 = new JScrollPane(tabla2);
        JScrollPane scroll3 = new JScrollPane(tabla3);

        scroll.setPreferredSize(new Dimension(600, 230));
        scroll2.setPreferredSize(new Dimension(300,250));
        scroll3.setPreferredSize(new Dimension(250, 230));




        reproducir = new JButton("Reproducir");
        borrar = new JButton("Borrar");
        mostrar = new JButton("Mostrar");
        añadirLista = new JButton("Añadir a Lista");


        this.add(scroll);
        this.add(scroll2);
        this.add(scroll3);
        this.add(añadirLista);
        this.add(reproducir);
        this.add(borrar);
        this.add(mostrar);

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

        layout.putConstraint(SpringLayout.WEST, añadirLista, 0, SpringLayout.WEST, borrar);
        layout.putConstraint(SpringLayout.NORTH, añadirLista, 100, SpringLayout.NORTH, borrar);

        layout.putConstraint(SpringLayout.WEST, scroll3, -60, SpringLayout.WEST, añadirLista);
        layout.putConstraint(SpringLayout.NORTH, scroll3, 50, SpringLayout.NORTH, añadirLista);

    }

    public void setControlador(ActionListener c) {
        reproducir.setActionCommand("ReproducirAlbum");
        reproducir.addActionListener(c);

        borrar.setActionCommand("BorrarAlbum");
        borrar.addActionListener(c);

        mostrar.setActionCommand("MostrarAlbum");
        mostrar.addActionListener(c);

        añadirLista.setActionCommand("AñadirAlbumLista");
        añadirLista.addActionListener(c);

    }

    public JTable getTabla() {
        return tabla;
    }

    public JTable getTabla2(){
        return tabla2;
    }

    public JButton getAñadirLista() {
        return añadirLista;
    }

    public JTable getTabla3() {
        return tabla3;
    }

    public Album getAlbumSelec() {
        return albumSelec;
    }

    public Lista[] getListas() {
        return listas;
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

    public void limpiarListas() {
        int i;
        int rows = modeloListas.getRowCount();
        for(i = 0; i < rows; i++) {
            modeloListas.removeRow(0);
        }
        modeloListas.fireTableDataChanged();
    }


    public Album[] getResultados() {
        return resultados;
    }

    public DefaultTableModel getModeloDatos() {
        return modeloDatos;
    }

    public DefaultTableModel getModeloListas(){
        return modeloListas;
    }

    public DefaultTableModel getModeloReproducibles(){ return modeloReproducibles; }


    public void guardarResultados(Album[] albums) {
        resultados = albums;
    }

    public void guardarListas(Lista[] l) {
        listas = l;
    }

    public void setAlbumSelec(Album albumSelec) {
        this.albumSelec = albumSelec;
    }

    public Cancion[] getReps(){ return canciones; }

    public void guardarCanciones(Cancion[] c){
        canciones = c;
    }

    public void setListaSelec(Lista l){
        listaSelec = l;
    }

    public Lista getListaSelec(){
        return listaSelec;
    }

}
