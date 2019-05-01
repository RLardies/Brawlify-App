package Interfaces.MenuPrincipal;

import Reproducible.Cancion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BuscarCanciones extends JPanel {

    private JTextField textoABuscar;
    private JComboBox filtro;
    private JButton iniciarBusqueda;
    private JTable tabla;
    private JScrollPane scroll;
    private SpringLayout layout;
    private JButton reproducir;
    private Cancion[] resultados;

    public BuscarCanciones() {
        layout = new SpringLayout();
        this.setLayout(layout);

        textoABuscar = new JTextField(30);
        iniciarBusqueda = new JButton("Buscar");
        String[] tipo = {"Por Título", "Por Autor"};
        filtro = new JComboBox(tipo);
        filtro.setSelectedIndex(0);

        String[] titulos = {"Titulo", "Autor", "Duracion"};

        reproducir = new JButton("Reproducir");

        layout.putConstraint(SpringLayout.WEST, textoABuscar, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textoABuscar, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, filtro, 20, SpringLayout.EAST, textoABuscar);
        layout.putConstraint(SpringLayout.NORTH, filtro, -2, SpringLayout.NORTH, textoABuscar);

        layout.putConstraint(SpringLayout.WEST, iniciarBusqueda, 20, SpringLayout.EAST, filtro);
        layout.putConstraint(SpringLayout.NORTH, iniciarBusqueda, 0, SpringLayout.NORTH, filtro);

        layout.putConstraint(SpringLayout.WEST, reproducir, 20, SpringLayout.EAST, iniciarBusqueda);
        layout.putConstraint(SpringLayout.NORTH, reproducir, 0, SpringLayout.NORTH, iniciarBusqueda);

        this.add(textoABuscar);
        this.add(iniciarBusqueda);
        this.add(filtro);
        this.add(reproducir);
    }


    public void setControlador(ActionListener c) {
        iniciarBusqueda.setActionCommand("Buscar");
        iniciarBusqueda.addActionListener(c);
        reproducir.setActionCommand("Reproducir");
        reproducir.addActionListener(c);
    }

    public String getTextoABuscar() {
        return textoABuscar.getText();
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(Object[][] filas, String[] titulos, Cancion[] resultados) {
        if(tabla != null) {
            this.remove(tabla);
        }
        if(scroll!= null) {
            this.remove(scroll);
        }

        this.resultados = resultados;
        JTable tabla = new JTable(filas, titulos);
        //tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
        JScrollPane scroll = new JScrollPane(tabla);
        this.add(scroll);
        layout.putConstraint(SpringLayout.WEST, scroll, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.SOUTH, textoABuscar);
        scroll.setVisible(true);
        this.tabla = tabla;
        this.scroll = scroll;
        this.validate();
    }

    public JComboBox getFiltro() {
        return filtro;
    }

    public Cancion[] getResultados() {
        return resultados;
    }
}
