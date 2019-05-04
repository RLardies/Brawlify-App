package Interfaces.Login;

import Aplicacion.Aplicacion;
import Exceptions.ContrasenaIncorrecta;
import Exceptions.UsuarioNoExistente;
import Interfaces.GuiBrawlify;
import Interfaces.MenuPrincipal.PanelMenuPrincipal;
import Notificacion.Notificacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            panelMenuPrincipal.getTabbedPane().setSelectedIndex(0);
            ventana.mostrarPanel(GuiBrawlify.PANEL_PRINCIPAL);
            if(app.getUsuarioLogueado().getNotificaciones().size() > 0) {
                panelMenuPrincipal.getTabbedPane().setSelectedIndex(3);
                for(Notificacion n : app.getUsuarioLogueado().getNotificaciones()) {
                    panelMenuPrincipal.getMisNotificaciones().getModeloDatos().addRow(new Object[]{n.toString()});
                }
                JOptionPane.showMessageDialog(ventana, "Tiene nuevas notificaciones. Desapareceran cuando cierre sesion", "Notificaciones", JOptionPane.INFORMATION_MESSAGE);
            }


        } else if(actionEvent.getActionCommand().equals("ContinuarSinRegistrarse")) {

            ventana.mostrarPanel(GuiBrawlify.PANEL_PRINCIPAL);

        }
    }
}
