package Interfaces.MenuPrincipal;

import Interfaces.Login.Ayuda;
import Interfaces.Login.Inicio;
import Interfaces.Login.Login;
import Interfaces.Login.Registro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelMenuPrincipal extends JPanel {

    private BorderLayout layout;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private BuscarCanciones buscarCanciones = new BuscarCanciones();
    private MisCanciones misCanciones = new MisCanciones();
    private MisListas misListas = new MisListas();

    private JButton unlogin = new JButton();

    public PanelMenuPrincipal(){

        layout = new BorderLayout();

        tabbedPane.addTab("Buscar Canciones", buscarCanciones);
        tabbedPane.addTab("Mis Canciones", misCanciones);
        tabbedPane.addTab("Mis Listas", misListas);
        tabbedPane.setPreferredSize(new Dimension(950,600));

        JPanel botonera = new JPanel();
        unlogin = new JButton("Cerrar Sesion");
        botonera.add(unlogin);

        this.add(tabbedPane, BorderLayout.CENTER);
        this.add(botonera,BorderLayout.EAST);
    }

    public void setControlador(ActionListener c) {
        buscarCanciones.setControlador(c);
        misCanciones.setControlador(c);
        misListas.setControlador(c);
        unlogin.setActionCommand("Unlogin");
        unlogin.addActionListener(c);
    }

    public BuscarCanciones getBuscarCanciones() {
        return buscarCanciones;
    }

    public MisCanciones getMisCanciones() {
        return misCanciones;
    }

    public MisListas getMisListas() {
        return misListas;
    }
}
