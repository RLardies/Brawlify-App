package Interfaces.MenuPrincipal;

import Aplicacion.Aplicacion;
import Interfaces.GuiBrawlify;
import Interfaces.Login.Login;
import Interfaces.Login.PanelInicio;
import Reproducible.Cancion;
import pads.musicPlayer.exceptions.Mp3PlayerException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ControladorMenuPrincipal implements ActionListener {

    private GuiBrawlify ventana;
    private Aplicacion app;
    private PanelMenuPrincipal panelMenuPrincipal;

    public ControladorMenuPrincipal(GuiBrawlify ventana, Aplicacion app, PanelMenuPrincipal panelMenuPrincipal) {
        this.ventana = ventana;
        this.app = app;
        this.panelMenuPrincipal = panelMenuPrincipal;
    }

    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getActionCommand().equals("Buscar")) {
            String filtro = (String) panelMenuPrincipal.getBuscarCanciones().getFiltro().getSelectedItem();

            ArrayList<Cancion> resultados = null;
            if(filtro.equals("Por Título")) {
                resultados = app.buscarCancionPorTitulo(panelMenuPrincipal.getBuscarCanciones().getTextoABuscar());
            } else if(filtro.equals("Por Autor")) {
                resultados = app.buscarCancionPorAutor(panelMenuPrincipal.getBuscarCanciones().getTextoABuscar());
            }

            String[] titulos = {"Titulo", "Autor", "Duracion"};

            Object [][] filas = new Object[resultados.size()][3];
            Cancion[] canciones = new Cancion[resultados.size()];

            int i;
            for(i = 0; i < resultados.size(); i++) {
                filas[i][0] = resultados.get(i).getTitulo();
                filas[i][1] = resultados.get(i).getAutor().getUsername();
                filas[i][2] = resultados.get(i).getDuracion();
                canciones[i] = resultados.get(i);
            }

            panelMenuPrincipal.getBuscarCanciones().setTabla(filas, titulos, canciones);

        } else if(actionEvent.getActionCommand().equals("Reproducir")) {

            int[] selected = panelMenuPrincipal.getBuscarCanciones().getTabla().getSelectedRows();
            Cancion[] cancionesSeleccionadas = new Cancion[selected.length];

            int i;
            for(i = 0; i < selected.length; i++) {
                cancionesSeleccionadas[i] = panelMenuPrincipal.getBuscarCanciones().getResultados()[selected[i]];
            }

            try {
                app.reproducir(cancionesSeleccionadas);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Mp3PlayerException e) {
                e.printStackTrace();
            }

        }
    }

}
