package Interfaces.Login;

import Aplicacion.Aplicacion;
import Exceptions.ContrasenaIncorrecta;
import Exceptions.UsuarioNoExistente;
import Interfaces.GuiBrawlify;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorInicio implements ActionListener {

    private GuiBrawlify ventana;
    private Aplicacion app;
    private PanelInicio panelInicio;

    public ControladorInicio(GuiBrawlify ventana, Aplicacion app, PanelInicio panelInicio) {
        this.ventana = ventana;
        this.app = app;
        this.panelInicio = panelInicio;
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

            ventana.mostrarPanel(GuiBrawlify.PANEL_PRINCIPAL);

        } else if(actionEvent.getActionCommand().equals("ContinuarSinRegistrarse")) {

            ventana.mostrarPanel(GuiBrawlify.PANEL_PRINCIPAL);

        }
    }
}
