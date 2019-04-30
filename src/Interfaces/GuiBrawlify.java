package Interfaces;

import Aplicacion.Aplicacion;
import Interfaces.Login.ControladorInicio;
import Interfaces.Login.PanelInicio;
import Interfaces.MenuPrincipal.PanelMenuPrincipal;

import javax.swing.*;
import java.awt.*;

public class GuiBrawlify extends JFrame {

    private CardLayout layout;
    public static final String PANEL_LOGIN     = "LOGIN";
    public static final String PANEL_PRINCIPAL = "MENU_PRINCIPAL";

    public GuiBrawlify() {
        super("Brawlify");


        Aplicacion app = new Aplicacion();


        PanelInicio pLogin = new PanelInicio();
        PanelMenuPrincipal panelMenuPrincipal = new PanelMenuPrincipal();


        pLogin.setControlador(new ControladorInicio(this, app, pLogin));

        layout = new CardLayout();
        setLayout(layout);
        this.add(pLogin, PANEL_LOGIN);
        this.add(panelMenuPrincipal, PANEL_PRINCIPAL);


        this.mostrarPanel(PANEL_LOGIN);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void mostrarPanel(String nombrePanel) {
        layout.show(this.getContentPane(), nombrePanel);
    }
}
