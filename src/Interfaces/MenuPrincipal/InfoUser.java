package Interfaces.MenuPrincipal;
/**
 * Panel con informacion del usuario
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InfoUser extends JPanel {

    private JLabel nombre = new JLabel("");
    private JLabel username = new JLabel("");
    private JLabel premium = new JLabel("");
    private JLabel reproduccionesRestantes = new JLabel("");


    public InfoUser() {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        layout.putConstraint(SpringLayout.WEST, nombre, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nombre, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, username, 0, SpringLayout.WEST, nombre);
        layout.putConstraint(SpringLayout.NORTH, username, 10, SpringLayout.SOUTH, nombre);

        layout.putConstraint(SpringLayout.WEST, premium, 0, SpringLayout.WEST, username);
        layout.putConstraint(SpringLayout.NORTH, premium, 10, SpringLayout.SOUTH, username);

        layout.putConstraint(SpringLayout.WEST, reproduccionesRestantes, 0, SpringLayout.WEST, premium);
        layout.putConstraint(SpringLayout.NORTH, reproduccionesRestantes, 10, SpringLayout.SOUTH, premium);

        this.setPreferredSize(new Dimension(300,100));

        this.add(nombre);
        this.add(username);
        this.add(premium);
        this.add(reproduccionesRestantes);
    }

    public JLabel getNombre() {
        return nombre;
    }

    public JLabel getUsername() {
        return username;
    }

    public JLabel getPremium() {
        return premium;
    }

    public JLabel getReproduccionesRestantes() {
        return reproduccionesRestantes;
    }

    public void limpiarInfo() {
        nombre.setText("");
        username.setText("No registrado");
        premium.setText("");
        reproduccionesRestantes.setText("");
    }
}
