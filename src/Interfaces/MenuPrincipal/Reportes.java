package Interfaces.MenuPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class Reportes extends JPanel {

    private JTable tabla;
    private SpringLayout layout;
    DefaultTableModel modeloDatos;
    private JButton confirmar;
    private JButton desmentir;


    public Reportes() {
        layout = new SpringLayout();
        this.setLayout(layout);

        String[] titulos = {"Titulo", "Autor", "Comentario"};
        Object[][] filas = new Object[0][3];
        modeloDatos = new DefaultTableModel(filas, titulos);
        tabla = new JTable(modeloDatos);
        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setPreferredSize(new Dimension(700, 530));

        confirmar = new JButton("Confirmar");
        desmentir = new JButton("Desmentir");

        this.add(scroll);
        layout.putConstraint(SpringLayout.WEST, scroll, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, confirmar, 30, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, confirmar, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, desmentir, 30, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, desmentir, 30, SpringLayout.NORTH, confirmar);

        this.add(confirmar);
        this.add(desmentir);

    }

    public void setControlador(ActionListener c) {
        confirmar.setActionCommand("Confirmar");
        confirmar.addActionListener(c);
        desmentir.setActionCommand("Desmentir");
        desmentir.addActionListener(c);
    }

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
