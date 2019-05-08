package Interfaces.Login;
/**
 * Ventana de ayuda
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */
import javax.swing.*;

public class Ayuda extends JPanel {

    private JLabel ayuda;
    private JLabel correo;
    private JLabel telefono;
    private JLabel gracias;

    public Ayuda() {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        ayuda = new JLabel("Si tiene cualquier problema contacte con nosotros:");
        correo = new JLabel("Correo: Brawlify@gmail.com");
        telefono = new JLabel("Telefono: 608851935");
        gracias = new JLabel("Gracias por usar Brawlify");

        layout.putConstraint(SpringLayout.WEST, ayuda, 150, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, ayuda, 200, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, correo, 0, SpringLayout.WEST, ayuda);
        layout.putConstraint(SpringLayout.NORTH, correo, 40, SpringLayout.NORTH, ayuda);

        layout.putConstraint(SpringLayout.WEST, telefono, 0, SpringLayout.WEST, correo);
        layout.putConstraint(SpringLayout.NORTH, telefono, 40, SpringLayout.NORTH, correo);

        layout.putConstraint(SpringLayout.WEST, gracias, 0, SpringLayout.WEST, telefono);
        layout.putConstraint(SpringLayout.NORTH, gracias, 40, SpringLayout.NORTH, telefono);

        this.add(ayuda);
        this.add(correo);
        this.add(telefono);
        this.add(gracias);


    }
}
