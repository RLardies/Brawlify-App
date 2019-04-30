package Interfaces.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

    public class PanelInicio<main> extends JTabbedPane {

    private Login login = new Login();
    private Registro registro = new Registro();
    private Inicio inicio = new Inicio();
    private Ayuda ayuda = new Ayuda();

    public PanelInicio(){
        this.addTab("Inicio", inicio);
        this.addTab("Registrarse", registro);
        this.addTab("Inicio Sesion", login);
        this.addTab("Ayuda", ayuda);
    }

    public void setControlador(ActionListener c) {
        login.setControlador(c);
    }



    public Login getLogin() {
        return login;
    }

    public Registro getRegistro() {
        return registro;
    }

    public Inicio getInicio() {
        return inicio;
    }
    }
