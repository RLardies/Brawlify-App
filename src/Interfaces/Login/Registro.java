package Interfaces.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Registro extends JPanel {


    private JLabel nombreLabel, userLabel, passwordLabel,fechaLabel;
    private JTextField nombreText, userText,fechaText;
    private JPasswordField passwordText;
    private JButton registroButton;

    public Registro() {

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);


        nombreLabel = new JLabel("Nombre:");
        userLabel = new JLabel("Usuario:");
        passwordLabel = new JLabel("Contraseña:");
        fechaLabel = new JLabel("Fecha de Nacimiento (yyyy-mm-dd):");
        nombreText = new JTextField(15);
        userText = new JTextField(15);
        passwordText = new JPasswordField(15);
        fechaText = new JTextField(15);
        registroButton = new JButton("Registrarse");


        layout.putConstraint(SpringLayout.WEST, nombreLabel, 120, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nombreLabel, 155, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, nombreText, 190, SpringLayout.EAST, nombreLabel);
        layout.putConstraint(SpringLayout.NORTH, nombreText, -2, SpringLayout.NORTH, nombreLabel);

        layout.putConstraint(SpringLayout.WEST, userLabel, 0, SpringLayout.WEST, nombreLabel);
        layout.putConstraint(SpringLayout.NORTH, userLabel, 30, SpringLayout.SOUTH, nombreLabel);

        layout.putConstraint(SpringLayout.WEST, userText, 0, SpringLayout.WEST, nombreText);
        layout.putConstraint(SpringLayout.NORTH, userText, -2, SpringLayout.NORTH, userLabel);

        layout.putConstraint(SpringLayout.WEST, passwordLabel, 0, SpringLayout.WEST, userLabel);
        layout.putConstraint(SpringLayout.NORTH, passwordLabel, 30, SpringLayout.SOUTH, userLabel);

        layout.putConstraint(SpringLayout.WEST, passwordText, 0, SpringLayout.WEST, userText);
        layout.putConstraint(SpringLayout.NORTH, passwordText, -2, SpringLayout.NORTH, passwordLabel);

        layout.putConstraint(SpringLayout.WEST, fechaLabel, 0, SpringLayout.WEST, userLabel);
        layout.putConstraint(SpringLayout.NORTH, fechaLabel, 30, SpringLayout.SOUTH, passwordLabel);

        layout.putConstraint(SpringLayout.WEST, fechaText, 0, SpringLayout.WEST, userText);
        layout.putConstraint(SpringLayout.NORTH, fechaText, -2, SpringLayout.NORTH, fechaLabel);

        layout.putConstraint(SpringLayout.WEST, registroButton, 0, SpringLayout.WEST, userLabel);
        layout.putConstraint(SpringLayout.NORTH, registroButton, 30, SpringLayout.SOUTH, fechaLabel);


        this.add(nombreLabel);
        this.add(nombreText);
        this.add(userLabel);
        this.add(userText);
        this.add(passwordLabel);
        this.add(passwordText);
        this.add(fechaLabel);
        this.add(fechaText);
        this.add(registroButton);

        this.setPreferredSize(new Dimension(950, 600));
    }

    public void setControlador(ActionListener c) {
        registroButton.setActionCommand("Registrarse");
        registroButton.addActionListener(c);
    }

    public JTextField getUserText() {
        return userText;
    }

    public JTextField getPasswordText() {
        return passwordText;
    }

    public JTextField getNombretext(){ return nombreText; }

    public JTextField getFechaText(){ return fechaText; }
}
