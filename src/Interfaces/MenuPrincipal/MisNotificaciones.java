package Interfaces.MenuPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class MisNotificaciones extends JPanel {

    private JTable tabla;
    private SpringLayout layout;
    DefaultTableModel modeloDatos;


    public MisNotificaciones() {
        layout = new SpringLayout();
        this.setLayout(layout);

        String[] titulos = {"Notificaciones"};
        Object[][] filas = new Object[0][1];
        modeloDatos = new DefaultTableModel(filas, titulos);
        tabla = new JTable(modeloDatos);
        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setPreferredSize(new Dimension(900, 530));

        this.add(scroll);
        layout.putConstraint(SpringLayout.WEST, scroll, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.NORTH, this);

    }

    public void setControlador(ActionListener c) {

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
