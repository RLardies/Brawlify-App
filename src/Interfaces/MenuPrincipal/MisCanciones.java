package Interfaces.MenuPrincipal;

import Reproducible.Cancion;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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

    private JLabel titulo = new JLabel("Titulo de la Cancion:");
    private JTextField tituloTexto = new JTextField(15);
    private JLabel archivo = new JLabel("Archivo:");
    private JTextField archivoRuta = new JTextField(15);

    JFileChooser fileChooser = new JFileChooser();
    private JButton examinar = new JButton("Examinar");
    private JButton modificarCancion = new JButton("Modificar Cancion");

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
        crear = new JButton("Crear Album");
        nombreLabel = new JLabel("Nombre del Album:");
        nombre = new JTextField(15);

        this.add(scroll);
        this.add(reproducir);
        this.add(borrar);
        this.add(crear);
        this.add(nombre);
        this.add(nombreLabel);
        this.add(titulo);
        this.add(tituloTexto);
        this.add(archivo);
        this.add(archivoRuta);
        this.add(examinar);
        this.add(modificarCancion);
        fileChooser.setFileFilter(new FileNameExtensionFilter("MP3", "mp3"));

        layout.putConstraint(SpringLayout.WEST, scroll, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.EAST, reproducir, 210, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, reproducir, 30, SpringLayout.NORTH, scroll);

        layout.putConstraint(SpringLayout.WEST, borrar, 0, SpringLayout.WEST, reproducir);
        layout.putConstraint(SpringLayout.NORTH, borrar, 50, SpringLayout.NORTH, reproducir);

        layout.putConstraint(SpringLayout.WEST, nombreLabel, 0, SpringLayout.WEST, borrar);
        layout.putConstraint(SpringLayout.NORTH, nombreLabel, 100, SpringLayout.NORTH, borrar);

        layout.putConstraint(SpringLayout.WEST, nombre, 0, SpringLayout.WEST, nombreLabel);
        layout.putConstraint(SpringLayout.NORTH, nombre, 10, SpringLayout.SOUTH, nombreLabel);

        layout.putConstraint(SpringLayout.WEST, crear, 0, SpringLayout.WEST, nombre);
        layout.putConstraint(SpringLayout.NORTH, crear, 30, SpringLayout.NORTH, nombre);

        layout.putConstraint(SpringLayout.WEST, titulo, 0, SpringLayout.WEST, nombre);
        layout.putConstraint(SpringLayout.NORTH, titulo, 100, SpringLayout.NORTH, crear);

        layout.putConstraint(SpringLayout.WEST, tituloTexto, 0, SpringLayout.WEST, titulo);
        layout.putConstraint(SpringLayout.NORTH, tituloTexto, 20, SpringLayout.NORTH, titulo);

        layout.putConstraint(SpringLayout.WEST, archivo, 0, SpringLayout.WEST, titulo);
        layout.putConstraint(SpringLayout.NORTH, archivo, 20, SpringLayout.NORTH, tituloTexto);

        layout.putConstraint(SpringLayout.WEST, archivoRuta, 0, SpringLayout.WEST, titulo);
        layout.putConstraint(SpringLayout.NORTH, archivoRuta, 20, SpringLayout.NORTH, archivo);

        layout.putConstraint(SpringLayout.WEST, examinar, 0, SpringLayout.WEST, titulo);
        layout.putConstraint(SpringLayout.NORTH, examinar, 30, SpringLayout.NORTH, archivoRuta);

        layout.putConstraint(SpringLayout.WEST, modificarCancion, 0, SpringLayout.WEST, titulo);
        layout.putConstraint(SpringLayout.NORTH, modificarCancion, 30, SpringLayout.NORTH, examinar);



    }
    public void setControlador(ActionListener c) {
        reproducir.setActionCommand("ReproducirMis");
        reproducir.addActionListener(c);

        borrar.setActionCommand("Borrar");
        borrar.addActionListener(c);

        crear.setActionCommand("CrearAlbum");
        crear.addActionListener(c);

        examinar.addActionListener(c);
        examinar.setActionCommand("ExaminarMisCanciones");

        modificarCancion.addActionListener(c);
        modificarCancion.setActionCommand("ModificarCancion");
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

    public JTextField getArchivoRuta() {
        return archivoRuta;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public JTextField getTituloTexto() {
        return tituloTexto;
    }

}
