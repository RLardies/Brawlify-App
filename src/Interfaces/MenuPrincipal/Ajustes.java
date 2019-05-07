package Interfaces.MenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Ajustes extends JPanel {
    private JTextField repToPremium;
    private JTextField maxRep;
    private JButton cambiar1;
    private JButton cambiar2;
    private JLabel t1;
    private JLabel t2;


    public void setControlador(ActionListener c) {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        repToPremium = new JTextField(5);
        maxRep = new JTextField(5);
        cambiar1 = new JButton("Cambiar");
        cambiar2 = new JButton("Cambiar" );


    }
}
