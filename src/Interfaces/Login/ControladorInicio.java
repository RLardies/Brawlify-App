package Interfaces.Login;

import Aplicacion.Aplicacion;
import Exceptions.ContrasenaIncorrecta;
import Exceptions.UsuarioBloqueado;
import Exceptions.UsuarioNoExistente;
import Exceptions.UsuarioYaExistente;
import Interfaces.GuiBrawlify;
import Interfaces.MenuPrincipal.PanelMenuPrincipal;
import Interfaces.MenuPrincipal.Reportes;
import Notificacion.Notificacion;
import Reporte.Reporte;
import Reproducible.Reproducible;
import Reproducible.Cancion;
import Reproducible.Lista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorInicio implements ActionListener {

    private GuiBrawlify ventana;
    private Aplicacion app;
    private PanelInicio panelInicio;
    private PanelMenuPrincipal panelMenuPrincipal;

    public ControladorInicio(GuiBrawlify ventana, Aplicacion app, PanelInicio panelInicio, PanelMenuPrincipal panelMenuPrincipal) {
        this.ventana = ventana;
        this.app = app;
        this.panelInicio = panelInicio;
        this.panelMenuPrincipal = panelMenuPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getActionCommand().equals("Loguearse")) {    //LOGIN
            Login login = panelInicio.getLogin();
            try {
                app.login(login.getUserText(), login.getPasswordText());
            } catch (UsuarioNoExistente e) {
                JOptionPane.showMessageDialog(panelInicio, "Usuario no existente", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (ContrasenaIncorrecta e) {
                JOptionPane.showMessageDialog(panelInicio, "El nombre de usuario o la contraseña son incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (UsuarioBloqueado e) {
                JOptionPane.showMessageDialog(panelInicio, "Tu cuenta ha sido bloqueada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(app.getUsuarioLogueado().esAdmin()) {
                panelMenuPrincipal.getTabbedPane().addTab("Buscar Canciones", panelMenuPrincipal.getBuscarCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Canciones", panelMenuPrincipal.getMisCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Listas", panelMenuPrincipal.getMisListas());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Notificaciones", panelMenuPrincipal.getMisNotificaciones());
                panelMenuPrincipal.getTabbedPane().addTab("Subir Cancion", panelMenuPrincipal.getSubirCancion());
                panelMenuPrincipal.getTabbedPane().addTab("Reportes", panelMenuPrincipal.getReportes());
                panelMenuPrincipal.getTabbedPane().addTab("Ajustes", panelMenuPrincipal.getAjustes());
                panelMenuPrincipal.getTabbedPane().addTab("Validaciones", panelMenuPrincipal.getValidaciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Suscripciones", panelMenuPrincipal.getMisSuscripciones());

            } else if(app.getUsuarioLogueado().esPremium()) {
                panelMenuPrincipal.getTabbedPane().addTab("Buscar Canciones", panelMenuPrincipal.getBuscarCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Canciones", panelMenuPrincipal.getMisCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Listas", panelMenuPrincipal.getMisListas());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Notificaciones", panelMenuPrincipal.getMisNotificaciones());
                panelMenuPrincipal.getTabbedPane().addTab("Subir Cancion", panelMenuPrincipal.getSubirCancion());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Suscripciones", panelMenuPrincipal.getMisSuscripciones());
            } else {
                panelMenuPrincipal.getTabbedPane().addTab("Buscar Canciones", panelMenuPrincipal.getBuscarCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Canciones", panelMenuPrincipal.getMisCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Listas", panelMenuPrincipal.getMisListas());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Notificaciones", panelMenuPrincipal.getMisNotificaciones());
                panelMenuPrincipal.getTabbedPane().addTab("Subir Cancion", panelMenuPrincipal.getSubirCancion());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Suscripciones", panelMenuPrincipal.getMisSuscripciones());
            }

            panelMenuPrincipal.getTabbedPane().setSelectedIndex(0);
            ventana.mostrarPanel(GuiBrawlify.PANEL_PRINCIPAL);
            if(app.getUsuarioLogueado().getNotificaciones().size() > 0) {
                panelMenuPrincipal.getTabbedPane().setSelectedIndex(3);
                for(Notificacion n : app.getUsuarioLogueado().getNotificaciones()) {
                    panelMenuPrincipal.getMisNotificaciones().getModeloDatos().addRow(new Object[]{n.toString()});
                }
                JOptionPane.showMessageDialog(ventana, "Tiene nuevas notificaciones. Desapareceran cuando cierre sesion", "Notificaciones", JOptionPane.INFORMATION_MESSAGE);
            }

            if(app.getUsuarioLogueado().getReproducibles().size() > 0){

                panelMenuPrincipal.getMisCanciones().limpiarTabla();

                ArrayList<Cancion> canciones = new ArrayList<Cancion>();

                for(Reproducible r : app.getUsuarioLogueado().getReproducibles()){
                    if(r.esCancion() && r.getEstado() != Cancion.Estado.BORRADO){
                        canciones.add((Cancion)r);
                        panelMenuPrincipal.getMisCanciones().getModeloDatos().addRow(new Object[]{r.getTitulo(), r.getEstado(), r.getDuracion()});
                    }
                }

                Cancion[] resultados = new Cancion[canciones.size()];
                int i;
                for(i = 0; i < canciones.size(); i++){
                    resultados[i] = canciones.get(i);
                }

                panelMenuPrincipal.getMisCanciones().guardarResultados(resultados);
            }

            if(app.getUsuarioLogueado().esPremium()){
                panelMenuPrincipal.getMisListas().limpiarTabla();

                ArrayList<Lista> listas = new ArrayList<Lista>();

                for(Reproducible r : app.getUsuarioLogueado().getReproducibles()){
                    if(r.esLista() && r.getEstado() != Cancion.Estado.BLOQUEADO){
                        listas.add((Lista) r);
                        panelMenuPrincipal.getMisListas().getModeloDatos().addRow(new Object[]{r.getTitulo(),r.getNumeroCanciones(),r.getDuracion()});
                    }
                }

                Lista[] l = new Lista[listas.size()];
                int j;
                for(j=0; j < listas.size(); j++){
                    l[j] = listas.get(j);
                }

                panelMenuPrincipal.getMisListas().guardarResultados(l);

            }

            if(app.getUsuarioLogueado().esAdmin()){
                panelMenuPrincipal.getValidaciones().limpiarTabla();

                ArrayList<Cancion> canciones = app.mostrarCancionesAValidar();

                for(Cancion c : canciones){
                    panelMenuPrincipal.getValidaciones().getModeloDatos().addRow(new Object[]{c.getTitulo(),c.getAutor()});

                }

                Cancion[] l = new Cancion[canciones.size()];
                int j;
                for(j=0; j < canciones.size(); j++){
                    l[j] = canciones.get(j);
                }
                panelMenuPrincipal.getValidaciones().guardarResultados(l);



                panelMenuPrincipal.getReportes().limpiarTabla();
                ArrayList<Reporte> reportes = app.mostrarReportes();

                for (Reporte r : reportes){
                    panelMenuPrincipal.getReportes().getModeloDatos().addRow(new Object[]{r.getCancionReportada().getTitulo(), r.getUsuario().getUsername(), r.getComentario()});
                }

                Reporte[] r = new Reporte[reportes.size()];
                int k;
                for(k=0; k < reportes.size(); k++){
                    r[k] = reportes.get(k);
                }
                panelMenuPrincipal.getReportes().guardarResultados(r);

                panelMenuPrincipal.getAjustes().getT1().setText("Reproducciones mensuales para obtener el premium: " + app.getRepToPremium());
                panelMenuPrincipal.getAjustes().getT2().setText("Reproducciones mensuales maximas para usuarios no premium: " + app.getMaxRepNoPremium());


            }


        } else if(actionEvent.getActionCommand().equals("ContinuarSinRegistrarse")) {

            ventana.mostrarPanel(GuiBrawlify.PANEL_PRINCIPAL);
            panelMenuPrincipal.getTabbedPane().addTab("Buscar Canciones", panelMenuPrincipal.getBuscarCanciones());

        }else if(actionEvent.getActionCommand().equals("Registrarse")){
            Registro registro = panelInicio.getRegistro();
            try{
                //Comprobar que los campos no sean null
                //Comprobar que la fecha este en formato correcto (Da error si no: java.time.format.DateTimeParseException)
                //Si se ha registrado correcctamente poner el mensaje, pero no loguear directamente
                app.registrarUsuario(registro.getUserText(),registro.getPasswordText(), LocalDate.parse(registro.getFechaText()),registro.getNombretext());
            } catch (UsuarioYaExistente e) {
                JOptionPane.showMessageDialog(panelInicio,"Este usuario ya existe","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

    }
}
