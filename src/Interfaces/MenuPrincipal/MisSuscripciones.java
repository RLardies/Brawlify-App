package Interfaces.MenuPrincipal;

import Usuario.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class MisSuscripciones extends JPanel{
    private JTable tabla;
    private SpringLayout layout;
    private Usuario[] resultados;
    private JButton removeAutor;
    DefaultTableModel modeloDatos;


    public MisSuscripciones(){
        layout = new SpringLayout();
        this.setLayout(layout);

        String[] titulos = {"Autores"};
        Object[][] filas = new Object[0][1];
        modeloDatos = new DefaultTableModel(filas, titulos);
        tabla = new JTable(modeloDatos);
        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setPreferredSize(new Dimension(600, 530));

        removeAutor = new JButton("Remover Autor");

        layout.putConstraint(SpringLayout.WEST, scroll, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.EAST, removeAutor, 210, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.NORTH, removeAutor, 30, SpringLayout.NORTH, scroll);

        this.add(scroll);
        this.add(removeAutor);
    }


    public void setControlador(ActionListener c) {
        removeAutor.setActionCommand("RemoveAutor");
        removeAutor.addActionListener(c);

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


    public Usuario[] getResultados() {
        return resultados;
    }

    public DefaultTableModel getModeloDatos() {
        return modeloDatos;
    }


    public void guardarResultados(Usuario[] canciones) {
        resultados = canciones;
    }
}
