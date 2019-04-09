package Interfaces;

import javax.swing.*;
import java.awt.*;

public class panelLogin<main> extends JPanel {

    private JLabel userLabel, passwordLabel;
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton loginButton;

    public panelLogin(){
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        userLabel = new JLabel("Usuario:");
        passwordLabel = new JLabel("Contraseña:");
        userText = new JTextField(15);
        passwordText = new JPasswordField(15);

        layout.putConstraint(SpringLayout.WEST, userLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, userLabel, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, userText, 5, SpringLayout.EAST, userLabel);
        layout.putConstraint(SpringLayout.NORTH, userLabel, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, passwordLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, passwordLabel, 5, SpringLayout.SOUTH, userLabel);

        layout.putConstraint(SpringLayout.WEST, passwordText, 5, SpringLayout.EAST, passwordLabel);
        layout.putConstraint(SpringLayout.NORTH, passwordText, 5, SpringLayout.SOUTH, userText);

        this.add(userLabel);
        this.add(userText);
        this.add(passwordLabel);
        this.add(passwordText);

        //SpringUtilities.makeCompactGrid(this, 2, 2, 5 ,5 ,5 ,5);

        this.setPreferredSize(new Dimension(250, 50));
    }


    public static void main(String ... arg) {
        JFrame ventana = new JFrame("TUpU");
        ventana.setLayout(new SpringLayout());

        ventana.add(new panelLogin());
        ventana.setSize(500, 500);

        ventana.setVisible(true);
        System.out.println("hola");
    }
}
