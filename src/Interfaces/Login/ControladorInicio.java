package Interfaces.Login;

import Aplicacion.Aplicacion;
import Exceptions.ContrasenaIncorrecta;
import Exceptions.UsuarioNoExistente;
import Exceptions.UsuarioYaExistente;
import Interfaces.GuiBrawlify;
import Interfaces.MenuPrincipal.PanelMenuPrincipal;
import Interfaces.MenuPrincipal.Reportes;
import Notificacion.Notificacion;
import Reproducible.Reproducible;
import Reproducible.Cancion;

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
            }

            if(app.getUsuarioLogueado().esAdmin()) {
                panelMenuPrincipal.getTabbedPane().addTab("Buscar Canciones", panelMenuPrincipal.getBuscarCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Canciones", panelMenuPrincipal.getMisCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Listas", panelMenuPrincipal.getMisListas());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Notificaciones", panelMenuPrincipal.getMisNotificaciones());
                panelMenuPrincipal.getTabbedPane().addTab("Reportes", panelMenuPrincipal.getReportes());
            } else if(app.getUsuarioLogueado().esPremium()) {
                panelMenuPrincipal.getTabbedPane().addTab("Buscar Canciones", panelMenuPrincipal.getBuscarCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Canciones", panelMenuPrincipal.getMisCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Listas", panelMenuPrincipal.getMisListas());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Notificaciones", panelMenuPrincipal.getMisNotificaciones());
            } else {
                panelMenuPrincipal.getTabbedPane().addTab("Buscar Canciones", panelMenuPrincipal.getBuscarCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Canciones", panelMenuPrincipal.getMisCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Listas", panelMenuPrincipal.getMisListas());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Notificaciones", panelMenuPrincipal.getMisNotificaciones());
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
