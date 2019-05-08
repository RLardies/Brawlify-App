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
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.add(nombre, BorderLayout.CENTER);


    }
}
