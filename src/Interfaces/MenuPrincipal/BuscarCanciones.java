package Interfaces.MenuPrincipal;
/**
 * Ventana para buscar canciones. Tambien se pueden reportar canciones si eres un usuario registrado
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */
import Reproducible.Cancion;
import Reproducible.Lista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BuscarCanciones extends JPanel {

    private JTextField textoABuscar;
    private JComboBox filtro;
    private JButton iniciarBusqueda;
    private JTable tabla;
    private JTable tablaListas;
    private JScrollPane scroll;
    private JScrollPane scroll2;
    private SpringLayout layout;
    private JButton reproducir;
    private JButton añadirLista;
    private Cancion[] resultados;
    private Lista[] listas;
    private Lista listaSelec;
    private JButton reportarCancion;
    private JButton suscribirseAAutor;
    private JLabel comentarioLabel;
    private JTextField comentario;
    DefaultTableModel modeloDatos;
    DefaultTableModel modeloListas;

    public BuscarCanciones() {
        layout = new SpringLayout();
        this.setLayout(layout);

        textoABuscar = new JTextField(30);
        iniciarBusqueda = new JButton("Buscar");
        String[] tipo = {"Por Título", "Por Autor"};
        filtro = new JComboBox(tipo);
        filtro.setSelectedIndex(0);

        String[] titulos = {"Titulo", "Autor", "Duracion"};
        Object[][] filas = new Object[0][3];
        modeloDatos = new DefaultTableModel(filas, titulos);
        tabla = new JTable(modeloDatos);
        JScrollPane scroll = new JScrollPane(tabla);

        String[] titulos2 = {"Nombre"};
        Object[][] filas2 = new Object[0][1];
        modeloListas = new DefaultTableModel(filas2, titulos2);
        tablaListas = new JTable(modeloListas);
        JScrollPane scroll2 = new JScrollPane(tablaListas);

        scroll2.setPreferredSize(new Dimension(300, 230));

        reportarCancion = new JButton("Reportar Cancion");
        suscribirseAAutor = new JButton("Suscribirse A Autor");
        añadirLista = new JButton("Añadir a Lista");
        reproducir = new JButton("Reproducir");

        comentarioLabel = new JLabel("Comentario del reporte:");
        comentario = new JTextField(20);


        layout.putConstraint(SpringLayout.WEST, textoABuscar, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textoABuscar, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, filtro, 20, SpringLayout.EAST, textoABuscar);
        layout.putConstraint(SpringLayout.NORTH, filtro, -2, SpringLayout.NORTH, textoABuscar);

        layout.putConstraint(SpringLayout.WEST, iniciarBusqueda, 20, SpringLayout.EAST, filtro);
        layout.putConstraint(SpringLayout.NORTH, iniciarBusqueda, 0, SpringLayout.NORTH, filtro);

        layout.putConstraint(SpringLayout.WEST, reproducir, 20, SpringLayout.EAST, iniciarBusqueda);
        layout.putConstraint(SpringLayout.NORTH, reproducir, 0, SpringLayout.NORTH, iniciarBusqueda);

        layout.putConstraint(SpringLayout.WEST, scroll, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.SOUTH, textoABuscar);

        layout.putConstraint(SpringLayout.WEST, reportarCancion, 20, SpringLayout.EAST, reproducir);
        layout.putConstraint(SpringLayout.NORTH, reportarCancion, 0, SpringLayout.NORTH, reproducir);

        layout.putConstraint(SpringLayout.WEST, comentarioLabel, 0, SpringLayout.WEST, reproducir);
        layout.putConstraint(SpringLayout.NORTH, comentarioLabel, 0, SpringLayout.NORTH, scroll);

        layout.putConstraint(SpringLayout.WEST, comentario, 0, SpringLayout.WEST, comentarioLabel);
        layout.putConstraint(SpringLayout.NORTH, comentario, 10, SpringLayout.SOUTH, comentarioLabel);

        layout.putConstraint(SpringLayout.WEST, suscribirseAAutor, 0, SpringLayout.WEST, comentarioLabel);
        layout.putConstraint(SpringLayout.NORTH, suscribirseAAutor, 40, SpringLayout.SOUTH, comentario);

        layout.putConstraint(SpringLayout.WEST, añadirLista, 0, SpringLayout.WEST, suscribirseAAutor);
        layout.putConstraint(SpringLayout.NORTH, añadirLista, 40, SpringLayout.SOUTH, suscribirseAAutor);

        layout.putConstraint(SpringLayout.WEST, scroll2, 0, SpringLayout.WEST, añadirLista);
        layout.putConstraint(SpringLayout.NORTH, scroll2, 20, SpringLayout.SOUTH, añadirLista);

        this.add(textoABuscar);
        this.add(iniciarBusqueda);
        this.add(filtro);
        this.add(reproducir);
        this.add(scroll);
        this.add(reportarCancion);
        this.add(comentario);
        this.add(comentarioLabel);
        this.add(suscribirseAAutor);
        this.add(scroll2);
        this.add(añadirLista);
    }


    public void setControlador(ActionListener c) {
        iniciarBusqueda.setActionCommand("Buscar");
        iniciarBusqueda.addActionListener(c);
        reproducir.setActionCommand("ReproducirBuscar");
        reproducir.addActionListener(c);
        reportarCancion.setActionCommand("Reportar");
        reportarCancion.addActionListener(c);
        suscribirseAAutor.setActionCommand("Suscribirse");
        suscribirseAAutor.addActionListener(c);
        añadirLista.setActionCommand("AñadirLista");
        añadirLista.addActionListener(c);
    }

    public String getTextoABuscar() {
        return textoABuscar.getText();
    }

    public JTable getTabla() {
        return tabla;
    }

    public Lista[] getListas(){
        return listas;
    }

    public JTable getTablaListas(){ return tablaListas; }


    public JComboBox getFiltro() {
        return filtro;
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

    public void guardarListas(Lista[] l) {
        listas = l;
    }

    public void limpiarTabla() {
        int i;
        int rows = modeloDatos.getRowCount();
        for(i = 0; i < rows; i++) {
            modeloDatos.removeRow(0);
        }
        modeloDatos.fireTableDataChanged();
    }

    public void limpiarListas() {
        int i;
        int rows = modeloListas.getRowCount();
        for(i = 0; i < rows; i++) {
            modeloListas.removeRow(0);
        }
        modeloListas.fireTableDataChanged();
    }

    public JTextField getComentario() {
        return comentario;
    }

    public DefaultTableModel getModeloListas() {
        return modeloListas;
    }

    public JButton getAñadirLista() {
        return añadirLista;
    }

    public Lista getListaSelec(){
        return listaSelec;
    }

    public void setListaSelec(Lista l){
        listaSelec = l;
    }


}
