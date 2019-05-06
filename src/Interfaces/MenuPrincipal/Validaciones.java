package Interfaces.MenuPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class Validaciones extends JPanel {
    private JTable tabla;
    private SpringLayout layout;
    DefaultTableModel modeloDatos;
    private JButton validar;
    private JButton rechazar;
    private JButton reproducir;


    public Validaciones() {
        layout = new SpringLayout();
        this.setLayout(layout);

        String[] titulos = {"Titulo", "Autor"};
        Object[][] filas = new Object[0][2];
        modeloDatos = new DefaultTableModel(filas, titulos);
        tabla = new JTable(modeloDatos);
        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setPreferredSize(new Dimension(700, 530));

        validar = new JButton("Validar");
        rechazar = new JButton("Rechazar");
        reproducir = new JButton("Reproducir");

        this.add(scroll);
        layout.putConstraint(SpringLayout.WEST, scroll, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, reproducir, 30, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, reproducir, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, validar, 30, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, validar, 30, SpringLayout.NORTH, reproducir);

        layout.putConstraint(SpringLayout.WEST, rechazar, 30, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, rechazar, 30, SpringLayout.NORTH, validar);

        this.add(validar);
        this.add(rechazar);
        this.add(reproducir);

    }

    public void setControlador(ActionListener c) {
        validar.setActionCommand("Validar");
        validar.addActionListener(c);
        rechazar.setActionCommand("Rechazar");
        rechazar.addActionListener(c);
        reproducir.setActionCommand("Reproducir");
        reproducir.addActionListener(c);    }

    public void limpiarTabla() {
        int i;
        int rows = modeloDatos.getRowCount();
        for(i = 0; i < rows; i++) {
            modeloDatos.removeRow(0);
        }
        modeloDatos.fireTableDataChanged();
    }

    public DefaultTableModel getModeloDatos() {
        return modeloDatos;
    }
}
