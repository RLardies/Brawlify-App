package Interfaces.MenuPrincipal;

import Aplicacion.Aplicacion;
import Exceptions.CancionNoExistente;
import Interfaces.GuiBrawlify;
import Interfaces.Login.Login;
import Interfaces.Login.PanelInicio;
import Notificacion.Notificacion;
import Reporte.Reporte;
import Reproducible.Cancion;
import Reproducible.*;
import Usuario.Usuario;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import pads.musicPlayer.exceptions.Mp3PlayerException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import Exceptions.*;

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

            Cancion[] canciones = new Cancion[resultados.size()];

            panelMenuPrincipal.getBuscarCanciones().limpiarTabla();

            int i;
            for(i = 0; i < resultados.size(); i++) {
                canciones[i] = resultados.get(i);
                panelMenuPrincipal.getBuscarCanciones().getModeloDatos().addRow(new Object[]{resultados.get(i).getTitulo(), resultados.get(i).getAutor().getUsername(), resultados.get(i).getDuracion()});
            }

            panelMenuPrincipal.getBuscarCanciones().guardarResultados(canciones);

        } else if(actionEvent.getActionCommand().equals("ReproducirBuscar")) {

            int[] selected = panelMenuPrincipal.getBuscarCanciones().getTabla().getSelectedRows();
            Cancion[] cancionesSeleccionadas = new Cancion[selected.length];

            int i;
            if(cancionesSeleccionadas.length > 0) {
                for(i = 0; i < selected.length; i++) {
                    cancionesSeleccionadas[i] = panelMenuPrincipal.getBuscarCanciones().getResultados()[selected[i]];
                }

                try {
                    app.reproducir(cancionesSeleccionadas);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Mp3PlayerException e) {
                    e.printStackTrace();
                } catch (NoRepLeft noRepLeft) {
                    JOptionPane.showMessageDialog(panelMenuPrincipal, "Se te han acabado las reproducciones por este mes. Pasate a premium para seguir escuchando música", "Ups", JOptionPane.INFORMATION_MESSAGE);
                }

                actualizarInfoUser();
            }


        } else if(actionEvent.getActionCommand().equals("Unlogin")) {
            app.cerrarSesion();
            panelMenuPrincipal.getBuscarCanciones().limpiarTabla();
            panelMenuPrincipal.getMisNotificaciones().limpiarTabla();
            panelMenuPrincipal.getMisCanciones().limpiarTabla();
            panelMenuPrincipal.getMisListas().limpiarTabla();
            panelMenuPrincipal.getInfoUser().limpiarInfo();
            app.stopReproductor();

            panelMenuPrincipal.getTabbedPane().remove(panelMenuPrincipal.getBuscarCanciones());
            panelMenuPrincipal.getTabbedPane().remove(panelMenuPrincipal.getMisCanciones());
            panelMenuPrincipal.getTabbedPane().remove(panelMenuPrincipal.getMisListas());
            panelMenuPrincipal.getTabbedPane().remove(panelMenuPrincipal.getMisNotificaciones());
            panelMenuPrincipal.getTabbedPane().remove(panelMenuPrincipal.getReportes());
            panelMenuPrincipal.getTabbedPane().remove(panelMenuPrincipal.getValidaciones());
            panelMenuPrincipal.getTabbedPane().remove(panelMenuPrincipal.getAjustes());
            panelMenuPrincipal.getTabbedPane().remove(panelMenuPrincipal.getSubirCancion());
            ventana.mostrarPanel(GuiBrawlify.PANEL_LOGIN);

        }else if(actionEvent.getActionCommand().equals("Borrar")){
            int[] selected = panelMenuPrincipal.getMisCanciones().getTabla().getSelectedRows();
            Cancion[] cancionesSeleccionadas = new Cancion[selected.length];

            int i;
            if(cancionesSeleccionadas.length > 0) {
                for (i = 0; i < selected.length; i++) {
                    cancionesSeleccionadas[i] = panelMenuPrincipal.getMisCanciones().getResultados()[selected[i]];
                    panelMenuPrincipal.getMisCanciones().getModeloDatos().removeRow(selected[i]-i);
                }


                for(Cancion c : cancionesSeleccionadas) {
                    try {
                        app.borrarCancion(c);
                    }catch (CancionNoExistente e){
                        System.out.println(e);
                    }
                }
                panelMenuPrincipal.getBuscarCanciones().limpiarTabla();
            }

        } else if(actionEvent.getActionCommand().equals("ReproducirMis")) {

            int[] selected = panelMenuPrincipal.getMisCanciones().getTabla().getSelectedRows();
            Cancion[] cancionesSeleccionadas = new Cancion[selected.length];

            int i;
            if (cancionesSeleccionadas.length > 0) {
                for (i = 0; i < selected.length; i++) {
                    cancionesSeleccionadas[i] = panelMenuPrincipal.getMisCanciones().getResultados()[selected[i]];
                }

                try {
                    app.reproducir(cancionesSeleccionadas);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Mp3PlayerException e) {
                    e.printStackTrace();
                } catch (NoRepLeft noRepLeft) {
                    JOptionPane.showMessageDialog(panelMenuPrincipal, "Se te han acabado las reproducciones por este mes. Pasate a premium para seguir escuchando música", "Ups", JOptionPane.INFORMATION_MESSAGE);
                }

                actualizarInfoUser();
            }

        } else if(actionEvent.getActionCommand().equals("Mostrar")){

            if (app.getUsuarioLogueado().esPremium() == false) {
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Funcion para usuarios Premium", "Funcion Premium", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int[] selected = panelMenuPrincipal.getMisListas().getTabla().getSelectedRows();
                Lista listaSeleccionada;

                if (selected.length == 1) {
                    listaSeleccionada = panelMenuPrincipal.getMisListas().getResultados()[selected[0]];
                    panelMenuPrincipal.getMisListas().limpiarTablaReproducibles();

                    panelMenuPrincipal.getMisListas().setListaSelec(listaSeleccionada);

                    Reproducible[] reps = new Reproducible[listaSeleccionada.getElementos().size()];

                    int k = 0;
                    for (Reproducible r : listaSeleccionada.getElementos()) {
                        String tipo;
                        if (r.esLista()) {
                            tipo = "Lista";
                        } else if (r.esAlbum()) {
                            tipo = "Álbum";
                        } else
                            tipo = "Canción";

                        panelMenuPrincipal.getMisListas().getModeloReproducibles().addRow(new Object[]{r.getTitulo(), tipo, r.getNumeroCanciones()});

                        reps[k] = r;
                        k++;
                    }
                    panelMenuPrincipal.getMisListas().guardarReps(reps);
                }
            }

        } else if(actionEvent.getActionCommand().equals("Reportar")) {
            if (app.getUsuarioLogueado() == null) {
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Inicia sesión para reportar una cancion", "Inicia Sesion", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String comentario = new String(panelMenuPrincipal.getBuscarCanciones().getComentario().getText());

                int[] selected = panelMenuPrincipal.getBuscarCanciones().getTabla().getSelectedRows();
                Cancion[] cancionesSeleccionadas = new Cancion[selected.length];

                int i;
                if (cancionesSeleccionadas.length > 0) {
                    for (i = 0; i < selected.length; i++) {
                        cancionesSeleccionadas[i] = panelMenuPrincipal.getBuscarCanciones().getResultados()[selected[i]];
                        panelMenuPrincipal.getBuscarCanciones().getModeloDatos().removeRow(selected[i] - i);
                        try {
                            app.reportarCancion(cancionesSeleccionadas[i], comentario);
                        } catch (CancionNoExistente cancionNoExistente) {
                            cancionNoExistente.printStackTrace();
                        }
                    }
                    JOptionPane.showMessageDialog(panelMenuPrincipal, "Canciones reportadas correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);

                }
            }

        } else if(actionEvent.getActionCommand().equals("Eliminar")){
            if (app.getUsuarioLogueado().esPremium() == false) {
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Funcion para usuarios Premium", "Funcion Premium", JOptionPane.INFORMATION_MESSAGE);
            } else {

                int[] selected = panelMenuPrincipal.getMisListas().getTabla2().getSelectedRows();
                Reproducible[] reproduciblesSeleccionados = new Reproducible[selected.length];

                int i;
                if (reproduciblesSeleccionados.length > 0) {
                    for (i = 0; i < selected.length; i++) {
                        reproduciblesSeleccionados[i] = panelMenuPrincipal.getMisListas().getReps()[selected[i]];
                        panelMenuPrincipal.getMisListas().getModeloReproducibles().removeRow(selected[i] - i);
                    }


                    for (Reproducible r : reproduciblesSeleccionados) {
                        panelMenuPrincipal.getMisListas().getListaSelec().removeReproducible(r);
                    }
                }
            }

        }else if(actionEvent.getActionCommand().equals("BorrarLista")){

            if (app.getUsuarioLogueado().esPremium() == false) {
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Funcion para usuarios Premium", "Funcion Premium", JOptionPane.INFORMATION_MESSAGE);
            } else {

                int[] selected = panelMenuPrincipal.getMisListas().getTabla().getSelectedRows();
                Lista[] listasSeleccionadas = new Lista[selected.length];

                int i;
                if (listasSeleccionadas.length > 0) {
                    for (i = 0; i < selected.length; i++) {
                        listasSeleccionadas[i] = panelMenuPrincipal.getMisListas().getResultados()[selected[i]];
                        panelMenuPrincipal.getMisListas().getModeloDatos().removeRow(selected[i] - i);
                    }

                    for (Lista l : listasSeleccionadas) {
                        app.getReproducibles().remove(l);
                        app.getUsuarioLogueado().getReproducibles().remove(l);
                    }
                }
            }
        }else if(actionEvent.getActionCommand().equals("Confirmar")){
            int[] selected = panelMenuPrincipal.getReportes().getTabla().getSelectedRows();
            Reporte[] reportesSeleccionados = new Reporte[selected.length];

            int i;
            if (reportesSeleccionados.length > 0) {
                for (i = 0; i < selected.length; i++) {
                    reportesSeleccionados[i] = panelMenuPrincipal.getReportes().getResultados()[selected[i]];
                    panelMenuPrincipal.getReportes().getModeloDatos().removeRow(selected[i] - i);

                    app.procesarPlagio(reportesSeleccionados[i], true);
                }
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Plagios confirmados correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(actionEvent.getActionCommand().equals("Desmentir")){
            int[] selected = panelMenuPrincipal.getReportes().getTabla().getSelectedRows();
            Reporte[] reportesSeleccionados = new Reporte[selected.length];

            int i;
            if (reportesSeleccionados.length > 0) {
                for (i = 0; i < selected.length; i++) {
                    reportesSeleccionados[i] = panelMenuPrincipal.getReportes().getResultados()[selected[i]];
                    panelMenuPrincipal.getReportes().getModeloDatos().removeRow(selected[i] - i);

                    app.procesarPlagio(reportesSeleccionados[i], false);
                }
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Plagios desmentidos correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(actionEvent.getActionCommand().equals("ReproducirReportes")) {

            int[] selected = panelMenuPrincipal.getReportes().getTabla().getSelectedRows();
            Cancion[] cancionesSeleccionadas = new Cancion[selected.length];

            int i;
            if (cancionesSeleccionadas.length > 0) {
                for (i = 0; i < selected.length; i++) {
                    cancionesSeleccionadas[i] = panelMenuPrincipal.getReportes().getResultados()[selected[i]].getCancionReportada();
                }

                try {
                    app.reproducir(cancionesSeleccionadas);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Mp3PlayerException e) {
                    e.printStackTrace();
                } catch (NoRepLeft noRepLeft) {
                    noRepLeft.printStackTrace();
                }
            }
        }else if(actionEvent.getActionCommand().equals("ReproducirValidaciones")) {

            int[] selected = panelMenuPrincipal.getValidaciones().getTabla().getSelectedRows();
            Cancion[] cancionesSeleccionadas = new Cancion[selected.length];

            int i;
            if (cancionesSeleccionadas.length > 0) {
                for (i = 0; i < selected.length; i++) {
                    cancionesSeleccionadas[i] = panelMenuPrincipal.getValidaciones().getResultados()[selected[i]];
                }

                try {
                    app.reproducir(cancionesSeleccionadas);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Mp3PlayerException e) {
                    e.printStackTrace();
                } catch (NoRepLeft noRepLeft) {
                    JOptionPane.showMessageDialog(panelMenuPrincipal, "Se te han acabado las reproducciones por este mes. Pasate a premium para seguir escuchando música", "Ups", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }else if(actionEvent.getActionCommand().equals("ValidarAutorizado")){
            int[] selected = panelMenuPrincipal.getValidaciones().getTabla().getSelectedRows();
            Cancion[] cancionesSeleccionadas = new Cancion[selected.length];

            int i;
            if (cancionesSeleccionadas.length > 0) {
                for (i = 0; i < selected.length; i++) {
                    cancionesSeleccionadas[i] = panelMenuPrincipal.getValidaciones().getResultados()[selected[i]];
                    panelMenuPrincipal.getValidaciones().getModeloDatos().removeRow(selected[i] - i);

                    try{
                        app.validarCancion(cancionesSeleccionadas[i], Cancion.Contenido.AUTORIZADO);
                    }catch (CancionNoExistente e){
                        e.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Canciones validadas correctamente con contenido autorizado", "Ok", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(actionEvent.getActionCommand().equals("ValidarExplicito")){
            int[] selected = panelMenuPrincipal.getValidaciones().getTabla().getSelectedRows();
            Cancion[] cancionesSeleccionadas = new Cancion[selected.length];

            int i;
            if (cancionesSeleccionadas.length > 0) {
                for (i = 0; i < selected.length; i++) {
                    cancionesSeleccionadas[i] = panelMenuPrincipal.getValidaciones().getResultados()[selected[i]];
                    panelMenuPrincipal.getValidaciones().getModeloDatos().removeRow(selected[i] - i);

                    try{
                        app.validarCancion(cancionesSeleccionadas[i], Cancion.Contenido.EXPLICITO);
                    }catch (CancionNoExistente e){
                        e.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Canciones validadas correctamente con contenido explicito", "Ok", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(actionEvent.getActionCommand().equals("Rechazar")){
            int[] selected = panelMenuPrincipal.getValidaciones().getTabla().getSelectedRows();
            Cancion[] cancionesSeleccionadas = new Cancion[selected.length];

            int i;
            if (cancionesSeleccionadas.length > 0) {
                for (i = 0; i < selected.length; i++) {
                    cancionesSeleccionadas[i] = panelMenuPrincipal.getValidaciones().getResultados()[selected[i]];
                    panelMenuPrincipal.getValidaciones().getModeloDatos().removeRow(selected[i] - i);

                    try{
                        app.validarCancion(cancionesSeleccionadas[i], Cancion.Contenido.NOVALIDO);
                    }catch (CancionNoExistente e){
                        e.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Canciones no validadas ", "Ok", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(actionEvent.getActionCommand().equals("CambiarRepToPremium")){
            if(Integer.parseInt(panelMenuPrincipal.getAjustes().getNewRepToPremium()) > 0){
                app.setRepToPremium(Integer.parseInt(panelMenuPrincipal.getAjustes().getNewRepToPremium()));
                panelMenuPrincipal.getAjustes().getT1().setText("Reproducciones mensuales para obtener el premium: " + app.getRepToPremium());
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Los cambios se han realizado correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);

            }else {
                JOptionPane.showMessageDialog(panelMenuPrincipal, "El numero de reproducciones debe ser mayor que 0 ", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }

        }else if(actionEvent.getActionCommand().equals("CambiarMaxRepNoPremium")){
            if(Integer.parseInt(panelMenuPrincipal.getAjustes().getNewMaxRepNoPremium()) > 0){
                app.setMaxRepNoPremium(Integer.parseInt(panelMenuPrincipal.getAjustes().getNewMaxRepNoPremium()));
                panelMenuPrincipal.getAjustes().getT2().setText("Reproducciones mensuales maximas para usuarios no premium: " + app.getMaxRepNoPremium());
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Los cambios se han realizado correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);

            }else {
                JOptionPane.showMessageDialog(panelMenuPrincipal, "El numero de reproducciones debe ser mayor que 0 ", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if(actionEvent.getActionCommand().equals("ReproducirLista")){
            if (app.getUsuarioLogueado().esPremium() == false) {
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Funcion para usuarios Premium", "Funcion Premium", JOptionPane.INFORMATION_MESSAGE);
            } else {

                int[] selected = panelMenuPrincipal.getMisListas().getTabla().getSelectedRows();
                Lista listaSeleccionada;

                if (selected.length == 1) {
                    listaSeleccionada = panelMenuPrincipal.getMisListas().getResultados()[selected[0]];

                    panelMenuPrincipal.getMisListas().setListaSelec(listaSeleccionada);

                    try {
                        app.reproducir(listaSeleccionada);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (Mp3PlayerException e) {
                        e.printStackTrace();
                    }
                }
            }

        }else if (actionEvent.getActionCommand().equals("Crear")){

            if (app.getUsuarioLogueado().esPremium() == false) {
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Funcion para usuarios Premium", "Funcion Premium", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String nombre = panelMenuPrincipal.getMisListas().getNombre().getText();

                if(nombre.length() == 0){
                    JOptionPane.showMessageDialog(panelMenuPrincipal,"Escriba un nombre para la lista","Nombre de Lista",JOptionPane.ERROR_MESSAGE);
                }else {
                    app.crearLista(nombre);
                    panelMenuPrincipal.getMisListas().getModeloDatos().addRow(new Object[]{nombre,0,0});
                }
            }

        } else if(actionEvent.getActionCommand().equals("Examinar")) {
            int seleccion = panelMenuPrincipal.getSubirCancion().getFileChooser().showOpenDialog(panelMenuPrincipal.getSubirCancion());
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File fichero = panelMenuPrincipal.getSubirCancion().getFileChooser().getSelectedFile();
                panelMenuPrincipal.getSubirCancion().getArchivoRuta().setText(fichero.getAbsolutePath());
            }

        } else if(actionEvent.getActionCommand().equals("Subir")) {

            try {
                Cancion c = app.subirCancion(panelMenuPrincipal.getSubirCancion().getTituloTexto().getText(), panelMenuPrincipal.getSubirCancion().getArchivoRuta().getText());
            } catch (CancionInvalida e) {
                JOptionPane.showMessageDialog(panelMenuPrincipal,"La cancion no es valida", "Error",JOptionPane.ERROR_MESSAGE);
                return;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(panelMenuPrincipal,"Error Subiendo la canción", "Error",JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(panelMenuPrincipal,"Cancion subida correctamente", "Ok",JOptionPane.INFORMATION_MESSAGE);

        } else if(actionEvent.getActionCommand().equals("Stop")) {
            app.stopReproductor();
        }else if(actionEvent.getActionCommand().equals("Suscribirse")) {
            if (app.getUsuarioLogueado() == null) {
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Inicia sesión para suscribirse a un autor", "Inicia Sesion", JOptionPane.INFORMATION_MESSAGE);
            } else {


                int[] selected = panelMenuPrincipal.getBuscarCanciones().getTabla().getSelectedRows();
                Cancion[] cancionesSeleccionadas = new Cancion[selected.length];

                int i;
                if (cancionesSeleccionadas.length > 0) {
                    for (i = 0; i < selected.length; i++) {
                        cancionesSeleccionadas[i] = panelMenuPrincipal.getBuscarCanciones().getResultados()[selected[i]];

                        app.getUsuarioLogueado().suscribirseAAutor(cancionesSeleccionadas[i].getAutor());

                    }
                    JOptionPane.showMessageDialog(panelMenuPrincipal, "Te has suscrito a los autores de las canciones", "Ok", JOptionPane.INFORMATION_MESSAGE);

                }
            }

        }else if(actionEvent.getActionCommand().equals("RemoveAutor")) {


            int[] selected = panelMenuPrincipal.getMisSuscripciones().getTabla().getSelectedRows();
            Usuario[] autoresSeleccionados = new Usuario[selected.length];

            int i;
            if (autoresSeleccionados.length > 0) {
                for (i = 0; i < selected.length; i++) {

                    autoresSeleccionados[i] = panelMenuPrincipal.getMisSuscripciones().getResultados()[selected[i]];
                    panelMenuPrincipal.getMisSuscripciones().getModeloDatos().removeRow(selected[i] - i);
                    app.getUsuarioLogueado().removeAutor(autoresSeleccionados[i]);

                }
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Has eliminado tu suscripcion a los autores", "Ok", JOptionPane.INFORMATION_MESSAGE);

            }
        } else if(actionEvent.getActionCommand().equals("AñadirLista")){
            if (app.getUsuarioLogueado().esPremium() == false) {
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Funcion para usuarios Premium", "Funcion Premium", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int[] selected = panelMenuPrincipal.getBuscarCanciones().getTabla().getSelectedRows();
                Cancion[] cancionesSeleccionadas = new Cancion[selected.length];

                int[] selected2 = panelMenuPrincipal.getBuscarCanciones().getTablaListas().getSelectedRows();
                Lista listaSeleccionada;

                if (selected2.length == 1) {
                    listaSeleccionada = panelMenuPrincipal.getBuscarCanciones().getListas()[selected2[0]];
                    panelMenuPrincipal.getBuscarCanciones().setListaSelec(listaSeleccionada);


                    int i;
                    boolean flag = false;
                    if (cancionesSeleccionadas.length > 0) {
                        for (i = 0; i < selected.length; i++) {
                            cancionesSeleccionadas[i] = panelMenuPrincipal.getBuscarCanciones().getResultados()[selected[i]];
                        }

                        for (Cancion c : cancionesSeleccionadas) {
                            if (listaSeleccionada.containsCancion(c)) {
                                flag = true;
                            }
                            else{
                                listaSeleccionada.addReproducible(c);
                            }
                        }

                        if(flag){
                            JOptionPane.showMessageDialog(panelMenuPrincipal, "Alguna cancion seleccionada ya pertenece a la Lista", "Cancion ya introducida", JOptionPane.ERROR_MESSAGE);
                        }else
                            JOptionPane.showMessageDialog(panelMenuPrincipal, "Canciones Introducidas con Éxito", "Canciones Introducidas", JOptionPane.INFORMATION_MESSAGE);
                    }else
                        JOptionPane.showMessageDialog(panelMenuPrincipal, "Seleccione alguna cancion", "Seleccion no valida", JOptionPane.ERROR_MESSAGE);
                }else
                    JOptionPane.showMessageDialog(panelMenuPrincipal, "Seleccione una única lista", "Selección no válida", JOptionPane.ERROR_MESSAGE);


            }
        } else if(actionEvent.getActionCommand().equals("MostrarAlbum")){

            int[] selected = panelMenuPrincipal.getMisAlbums().getTabla().getSelectedRows();
            Album  albumSeleccionado;

            if (selected.length == 1) {
                albumSeleccionado = panelMenuPrincipal.getMisAlbums().getResultados()[selected[0]];
                panelMenuPrincipal.getMisAlbums().limpiarTablaReproducibles();

                panelMenuPrincipal.getMisAlbums().setAlbumSelec(albumSeleccionado);

                Cancion[] canciones = new Cancion[albumSeleccionado.getCanciones().size()];

                int k = 0;
                for (Cancion c : albumSeleccionado.getCanciones()) {

                    panelMenuPrincipal.getMisAlbums().getModeloReproducibles().addRow(new Object[]{c.getTitulo()});

                    canciones[k] = c;
                    k++;
                }
                panelMenuPrincipal.getMisAlbums().guardarCanciones(canciones);

            }
            else
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Seleccione un único Album", "Selección no válida", JOptionPane.ERROR_MESSAGE);

        }else if(actionEvent.getActionCommand().equals("ReproducirAlbum")){
            int[] selected = panelMenuPrincipal.getMisAlbums().getTabla().getSelectedRows();
            Album album;

            if (selected.length == 1) {
                album = panelMenuPrincipal.getMisAlbums().getResultados()[selected[0]];

                panelMenuPrincipal.getMisAlbums().setAlbumSelec(album);

                try {
                    app.reproducir(album);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Mp3PlayerException e) {
                    e.printStackTrace();
                }
            }else
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Seleccione un único Album", "Selección no válida", JOptionPane.ERROR_MESSAGE);

        }else if(actionEvent.getActionCommand().equals("BorrarAlbum")){
            int[] selected = panelMenuPrincipal.getMisAlbums().getTabla().getSelectedRows();
            Album[] albums = new Album[selected.length];

            int i;
            if (albums.length > 0) {
                for (i = 0; i < selected.length; i++) {
                    albums[i] = panelMenuPrincipal.getMisAlbums().getResultados()[selected[i]];
                    panelMenuPrincipal.getMisAlbums().getModeloDatos().removeRow(selected[i] - i);
                }
                panelMenuPrincipal.getMisAlbums().limpiarTablaReproducibles();

                for (Album a : albums) {
                    app.getReproducibles().remove(a);
                    app.getUsuarioLogueado().getReproducibles().remove(a);
                }
            }
        }else if(actionEvent.getActionCommand().equals("AñadirAlbumLista")){
            int[] selected = panelMenuPrincipal.getMisAlbums().getTabla().getSelectedRows();
            Album[] albums = new Album[selected.length];

            int[] selected2 = panelMenuPrincipal.getMisAlbums().getTabla3().getSelectedRows();
            Lista listaSeleccionada;

            if (selected2.length == 1) {
                listaSeleccionada = panelMenuPrincipal.getMisAlbums().getListas()[selected2[0]];
                panelMenuPrincipal.getMisAlbums().setListaSelec(listaSeleccionada);


                int i;
                boolean flag = false;
                if (albums.length > 0) {
                    for (i = 0; i < selected.length; i++) {
                        albums[i] = panelMenuPrincipal.getMisAlbums().getResultados()[selected[i]];
                    }

                    for (Album a : albums) {
                        if (listaSeleccionada.containsAlbum(a)) {
                            flag = true;
                        }
                        else{
                            listaSeleccionada.addReproducible(a);
                        }
                    }

                    if(flag){
                        JOptionPane.showMessageDialog(panelMenuPrincipal, "Algun album seleccionada ya pertenece a la Lista", "Album ya introducido", JOptionPane.ERROR_MESSAGE);
                    }else
                        JOptionPane.showMessageDialog(panelMenuPrincipal, "Albums Introducidos con Éxito", "Albums Introducidos", JOptionPane.INFORMATION_MESSAGE);
                }else
                    JOptionPane.showMessageDialog(panelMenuPrincipal, "Seleccione algun album", "Seleccion no valida", JOptionPane.ERROR_MESSAGE);
            }else
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Seleccione una única lista", "Selección no válida", JOptionPane.ERROR_MESSAGE);

        }else if(actionEvent.getActionCommand().equals("Pagar")){
            if(!(panelMenuPrincipal.getNumeroTarjeta().equals(""))){

                /*if(app.getSistemaPago().isValidCardNumber(panelMenuPrincipal.getNumeroTarjeta())){
                    try {
                        app.pagarPremium(panelMenuPrincipal.getNumeroTarjeta());
                    } catch (InvalidCardNumberException c) {
                        c.printStackTrace();
                    } catch (FailedInternetConnectionException i) {
                        i.printStackTrace();
                    } catch (OrderRejectedException o) {
                        o.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(panelMenuPrincipal, "El pago se ha realizado correctamente. Vuelva a iniciar sesion para aplicar los cambios", "Ok", JOptionPane.INFORMATION_MESSAGE);

                }else{
                    JOptionPane.showMessageDialog(panelMenuPrincipal, "Introduzca un numero de tarjeta valido", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
*/

            }else {
                JOptionPane.showMessageDialog(panelMenuPrincipal, "Introduzca un numero de tarjeta", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    public void actualizarInfoUser() {
        if(app.getUsuarioLogueado() == null) {
            return;
        }
        panelMenuPrincipal.getInfoUser().getNombre().setText(app.getUsuarioLogueado().getNombre());
        panelMenuPrincipal.getInfoUser().getUsername().setText(app.getUsuarioLogueado().getUsername());
        if(app.getUsuarioLogueado().esPremium() || app.getUsuarioLogueado().esAdmin()) {
            panelMenuPrincipal.getInfoUser().getPremium().setText("Servicio Premium: Activado");
            panelMenuPrincipal.getInfoUser().getReproduccionesRestantes().setText("Reproducciones Restantes: Ilimitadas");
        } else {
            panelMenuPrincipal.getInfoUser().getPremium().setText("Servicio Premium: Desactivado");
            panelMenuPrincipal.getInfoUser().getReproduccionesRestantes().setText("Reproducciones Restantes: " + (app.getMaxRepNoPremium() - app.getUsuarioLogueado().getReproducciones()));
        }
    }

}
