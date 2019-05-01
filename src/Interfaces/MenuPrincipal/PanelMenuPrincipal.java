package Interfaces.MenuPrincipal;

import Interfaces.Login.Ayuda;
import Interfaces.Login.Inicio;
import Interfaces.Login.Login;
import Interfaces.Login.Registro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelMenuPrincipal extends JTabbedPane {

    private BuscarCanciones buscarCanciones = new BuscarCanciones();
    private MisCanciones misCanciones = new MisCanciones();
    private MisListas misListas = new MisListas();

    public PanelMenuPrincipal(){
        this.addTab("Buscar Canciones", buscarCanciones);
        this.addTab("Mis Canciones", misCanciones);
        this.addTab("Mis Listas", misListas);
    }

    public void setControlador(ActionListener c) {
        buscarCanciones.setControlador(c);
        misCanciones.setControlador(c);
        misListas.setControlador(c);
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
