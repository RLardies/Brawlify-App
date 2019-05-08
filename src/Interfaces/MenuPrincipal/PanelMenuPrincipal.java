package Interfaces.MenuPrincipal;
/**
 * Panel del menu principal
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */
import Interfaces.Login.Ayuda;
import Interfaces.Login.Inicio;
import Interfaces.Login.Login;
import Interfaces.Login.Registro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelMenuPrincipal extends JPanel {

    private BorderLayout layout;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private BuscarCanciones buscarCanciones = new BuscarCanciones();
    private MisCanciones misCanciones = new MisCanciones();
    private MisListas misListas = new MisListas();
    private MisNotificaciones misNotificaciones = new MisNotificaciones();
    private Reportes reportes = new Reportes();
    private Validaciones validaciones = new Validaciones();
    private Ajustes ajustes = new Ajustes();
    private SubirCancion subirCancion = new SubirCancion();
    private MisSuscripciones misSuscripciones = new MisSuscripciones();
    private MisAlbums misAlbums = new MisAlbums();

    private JButton unlogin = new JButton("Cerrar Sesion");
    private JButton stop = new JButton("Stop Music");
    private InfoUser infoUser = new InfoUser();

    private JLabel tarjeta = new JLabel("Numero de tarjeta: ");
    private JTextField numeroTarjeta = new JTextField(20);
    private JButton pagar = new JButton("Pagar Premium");

    JPanel panelPagar = new JPanel();
    public PanelMenuPrincipal(){

        layout = new BorderLayout();

        tabbedPane.setPreferredSize(new Dimension(950,600));

        JPanel botonera = new JPanel();
        GridLayout layout = new GridLayout();
        layout.setColumns(1);
        layout.setRows(2);
        botonera.setLayout(layout);
        botonera.add(unlogin);
        botonera.add(stop);

        JPanel barraLateral = new JPanel();

        SpringLayout layoutPagar = new SpringLayout();
        SpringLayout layoutBarraLateral = new SpringLayout();
        barraLateral.setLayout(layoutBarraLateral);
        panelPagar.setLayout(layoutPagar);

        panelPagar.setPreferredSize(new Dimension(300, 100));
        layoutPagar.putConstraint(SpringLayout.WEST, tarjeta, 10, SpringLayout.WEST, panelPagar);
        layoutPagar.putConstraint(SpringLayout.NORTH, tarjeta, 10, SpringLayout.NORTH, panelPagar);

        layoutPagar.putConstraint(SpringLayout.WEST, numeroTarjeta, 0, SpringLayout.WEST, tarjeta);
        layoutPagar.putConstraint(SpringLayout.NORTH, numeroTarjeta, 10, SpringLayout.SOUTH, tarjeta);

        layoutPagar.putConstraint(SpringLayout.WEST, pagar, 0, SpringLayout.WEST, tarjeta);
        layoutPagar.putConstraint(SpringLayout.NORTH, pagar, 10, SpringLayout.SOUTH, numeroTarjeta);

        panelPagar.add(tarjeta);
        panelPagar.add(numeroTarjeta);
        panelPagar.add(pagar);

        layoutBarraLateral.putConstraint(SpringLayout.WEST, infoUser, 10, SpringLayout.WEST, barraLateral);
        layoutBarraLateral.putConstraint(SpringLayout.NORTH, infoUser, 10, SpringLayout.NORTH, barraLateral);

        layoutBarraLateral.putConstraint(SpringLayout.WEST, botonera, 0, SpringLayout.WEST, infoUser);
        layoutBarraLateral.putConstraint(SpringLayout.NORTH, botonera, 10, SpringLayout.SOUTH, infoUser);

        layoutBarraLateral.putConstraint(SpringLayout.WEST, panelPagar, 0, SpringLayout.WEST, infoUser);
        layoutBarraLateral.putConstraint(SpringLayout.NORTH, panelPagar, 100, SpringLayout.SOUTH, botonera);


        barraLateral.setPreferredSize(new Dimension(300 ,500));
        barraLateral.add(infoUser);
        barraLateral.add(botonera);
        barraLateral.add(panelPagar);


        this.add(tabbedPane, BorderLayout.CENTER);
        this.add(barraLateral,BorderLayout.EAST);
    }

    public void setControlador(ActionListener c) {
        buscarCanciones.setControlador(c);
        misCanciones.setControlador(c);
        misListas.setControlador(c);
        misNotificaciones.setControlador(c);
        misAlbums.setControlador(c);
        subirCancion.setControlador(c);
        reportes.setControlador(c);
        validaciones.setControlador(c);
        ajustes.setControlador(c);
        misSuscripciones.setControlador(c);

        unlogin.setActionCommand("Unlogin");
        unlogin.addActionListener(c);
        stop.setActionCommand("Stop");
        stop.addActionListener(c);
        pagar.setActionCommand("Pagar");
        pagar.addActionListener(c);
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

    public MisNotificaciones getMisNotificaciones() {
        return misNotificaciones;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public Reportes getReportes() {
        return reportes;
    }

    public Validaciones getValidaciones() {
        return validaciones;
    }

    public Ajustes getAjustes() {
        return ajustes;
    }

    public SubirCancion getSubirCancion() {
        return subirCancion;
    }

    public MisSuscripciones getMisSuscripciones() {
        return misSuscripciones;
    }

    public MisAlbums getMisAlbums(){ return  misAlbums; }

    public InfoUser getInfoUser() {
        return infoUser;
    }

    public void esconderPagar(){panelPagar.setVisible(false);}

    public void mostrarPagar(){panelPagar.setVisible(true);}

    public String getNumeroTarjeta() {
        return numeroTarjeta.getText();
    }
}
