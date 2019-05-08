package Interfaces.Login;
/**
 * Ventana de inicio
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */
import javax.swing.*;
import java.awt.*;

public class Inicio extends JPanel {
    private JLabel nombre = new JLabel("Brawlify");

    public Inicio() {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        Font auxFont=nombre.getFont();


        nombre.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 100));

        this.add(nombre);

        layout.putConstraint(SpringLayout.WEST, nombre, 400, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nombre, 200, SpringLayout.NORTH, this);

    }
}
