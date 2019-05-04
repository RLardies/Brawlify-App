package Interfaces.Login;

import javax.swing.*;

public class Registro extends JPanel {
    private JLabel userLabel, passwordLabel, nameLabel;
    private JTextField userText, nameText;
    private JPasswordField passwordText;
    private JButton registerButton;
    private JList dia, mes, anio;

    public Registro() {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        userLabel = new JLabel("Usuario:");
        passwordLabel = new JLabel("Contraseña:");
        nameLabel = new JLabel("Nombre:");
        userText = new JTextField(15);
        nameText = new JTextField(15);
        passwordText = new JPasswordField(15);
        registerButton = new JButton("Registrarse");



        dia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        anio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollDia = new JScrollPane(dia);
        JScrollPane scrollMes = new JScrollPane(mes);
        JScrollPane scrollAnio = new JScrollPane(anio);

    }
}
