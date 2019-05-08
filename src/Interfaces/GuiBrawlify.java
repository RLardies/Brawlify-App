package Interfaces;

import Aplicacion.Aplicacion;
import Interfaces.Login.ControladorInicio;
import Interfaces.Login.PanelInicio;
import Interfaces.MenuPrincipal.ControladorMenuPrincipal;
import Interfaces.MenuPrincipal.PanelMenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GuiBrawlify extends JFrame {

    private CardLayout layout;
    public static final String PANEL_LOGIN     = "LOGIN";
    public static final String PANEL_PRINCIPAL = "MENU_PRINCIPAL";

    public GuiBrawlify() {
        super("Brawlify");

        Aplicacion app = null;

        Path path = Paths.get("ficheroobj");

        if (Files.exists(path)) {
            app = Aplicacion.cargarDatos();
        } else if (Files.notExists(path)) {
            app = new Aplicacion();
        }

        PanelInicio pLogin = new PanelInicio();
        PanelMenuPrincipal panelMenuPrincipal = new PanelMenuPrincipal();


        pLogin.setControlador(new ControladorInicio(this, app, pLogin, panelMenuPrincipal));
        panelMenuPrincipal.setControlador(new ControladorMenuPrincipal(this, app, panelMenuPrincipal));

        layout = new CardLayout();
        setLayout(layout);
        this.add(pLogin, PANEL_LOGIN);
        this.add(panelMenuPrincipal, PANEL_PRINCIPAL);


        this.mostrarPanel(PANEL_LOGIN);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);


        Aplicacion finalApp = app;
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                finalApp.cerrarSesion();
                finalApp.guardarDatos();
                System.exit(0);
            }
        });

    }

    public void mostrarPanel(String nombrePanel) {
        layout.show(this.getContentPane(), nombrePanel);
    }

}
