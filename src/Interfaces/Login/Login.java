package Interfaces.Login;
/**
 * Ventana para iniciar sesion
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Login extends JPanel {

    private JLabel userLabel, passwordLabel;
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JButton continuar;

    public Login(){
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        userLabel = new JLabel("Usuario:");
        passwordLabel = new JLabel("Contraseña:");
        userText = new JTextField(15);
        passwordText = new JPasswordField(15);
        loginButton = new JButton("Login");
        continuar = new JButton("Continuar sin registrarse");

        layout.putConstraint(SpringLayout.WEST, userLabel, 150, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, userLabel, 200, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, userText, 60, SpringLayout.EAST, userLabel);
        layout.putConstraint(SpringLayout.NORTH, userText, -2, SpringLayout.NORTH, userLabel);

        layout.putConstraint(SpringLayout.WEST, passwordLabel, 0, SpringLayout.WEST, userLabel);
        layout.putConstraint(SpringLayout.NORTH, passwordLabel, 30, SpringLayout.SOUTH, userLabel);

        layout.putConstraint(SpringLayout.WEST, passwordText, 0, SpringLayout.WEST, userText);
        layout.putConstraint(SpringLayout.NORTH, passwordText, -2, SpringLayout.NORTH, passwordLabel);

        layout.putConstraint(SpringLayout.WEST, loginButton, 0, SpringLayout.WEST, userLabel);
        layout.putConstraint(SpringLayout.NORTH, loginButton, 30, SpringLayout.SOUTH, passwordLabel);

        layout.putConstraint(SpringLayout.WEST, continuar, 0, SpringLayout.WEST, userLabel);
        layout.putConstraint(SpringLayout.NORTH, continuar, 30, SpringLayout.SOUTH, loginButton);

        this.add(userLabel);
        this.add(userText);
        this.add(passwordLabel);
        this.add(passwordText);

        this.add(loginButton);
        this.add(continuar);

        this.setPreferredSize(new Dimension(950, 600));
    }

    public void setControlador(ActionListener c) {
        loginButton.setActionCommand("Loguearse");
        loginButton.addActionListener(c);
        continuar.setActionCommand("ContinuarSinRegistrarse");
        continuar.addActionListener(c);
    }

    public JTextField getUserText() {
        return userText;
    }

    public JTextField getPasswordText() {
        return passwordText;
    }


}
