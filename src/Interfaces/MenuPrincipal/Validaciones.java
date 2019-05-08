package Interfaces.MenuPrincipal;

/**
 * Panel en el que se muestran las canciones a validar(Solo visible por el admin)
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */

import Reporte.Reporte;
import Reproducible.Cancion;
import Reproducible.Lista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class Validaciones extends JPanel {
    private JTable tabla;
    private SpringLayout layout;
    DefaultTableModel modeloDatos;
    private JButton validarAutorizado;
    private JButton validarExplicito;
    private JButton rechazar;
    private JButton reproducir;
    private Cancion[] resultados;


    public Validaciones() {
        layout = new SpringLayout();
        this.setLayout(layout);

        String[] titulos = {"Titulo", "Autor"};
        Object[][] filas = new Object[0][2];
        modeloDatos = new DefaultTableModel(filas, titulos);
        tabla = new JTable(modeloDatos);
        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setPreferredSize(new Dimension(700, 530));

        validarAutorizado = new JButton("Validar como Autorizado");
        validarExplicito = new JButton("Validar como Explicito");
        rechazar = new JButton("Rechazar");
        reproducir = new JButton("Reproducir");

        this.add(scroll);
        layout.putConstraint(SpringLayout.WEST, scroll, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, reproducir, 15, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, reproducir, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, validarAutorizado, 15, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, validarAutorizado, 30, SpringLayout.NORTH, reproducir);

        layout.putConstraint(SpringLayout.WEST, validarExplicito, 15, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, validarExplicito, 30, SpringLayout.NORTH, validarAutorizado);

        layout.putConstraint(SpringLayout.WEST, rechazar, 15, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, rechazar, 30, SpringLayout.NORTH, validarExplicito);

        this.add(validarAutorizado);
        this.add(validarExplicito);
        this.add(rechazar);
        this.add(reproducir);

    }

    public void setControlador(ActionListener c) {
        validarAutorizado.setActionCommand("ValidarAutorizado");
        validarAutorizado.addActionListener(c);
        validarExplicito.setActionCommand("ValidarExplicito");
        validarExplicito.addActionListener(c);
        rechazar.setActionCommand("Rechazar");
        rechazar.addActionListener(c);
        reproducir.setActionCommand("ReproducirValidaciones");
        reproducir.addActionListener(c);
    }

    public void limpiarTabla() {
        int i;
        int rows = modeloDatos.getRowCount();
        for(i = 0; i < rows; i++) {
            modeloDatos.removeRow(0);
        }
        modeloDatos.fireTableDataChanged();
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModeloDatos() {
        return modeloDatos;
    }
    public void guardarResultados(Cancion[] canciones) {
        resultados = canciones;
    }
    public Cancion[] getResultados() {
        return resultados;
    }
}
