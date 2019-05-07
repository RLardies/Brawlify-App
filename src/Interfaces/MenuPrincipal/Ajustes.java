package Interfaces.MenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Ajustes extends JPanel {
    private JTextField newRepToPremium;
    private JTextField newMaxRepNoPremium;
    private JButton cambiar1;
    private JButton cambiar2;
    private JLabel t1;
    private JLabel t2;
    private int maxRepNoPremium;
    private int repToPremium;


    public Ajustes() {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        newRepToPremium = new JTextField(5);
        newMaxRepNoPremium = new JTextField(5);
        cambiar1 = new JButton("Cambiar repToPremium");
        cambiar2 = new JButton("Cambiar maxRepNoPremium");
        t1 = new JLabel("Reproducciones mensuales para obtener el premium: " + repToPremium);
        t2 = new JLabel("Reproducciones mensuales maximas para usuarios no premium: "+ maxRepNoPremium);

        layout.putConstraint(SpringLayout.WEST, t1, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, t1, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, newRepToPremium, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, newRepToPremium, 30, SpringLayout.SOUTH, t1);

        layout.putConstraint(SpringLayout.WEST, cambiar1, 30, SpringLayout.EAST, newRepToPremium);
        layout.putConstraint(SpringLayout.NORTH, cambiar1, 30, SpringLayout.SOUTH, t1);

        layout.putConstraint(SpringLayout.WEST, t2, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, t2, 30, SpringLayout.SOUTH, newRepToPremium);

        layout.putConstraint(SpringLayout.WEST, newMaxRepNoPremium, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, newMaxRepNoPremium, 30, SpringLayout.SOUTH, t2);

        layout.putConstraint(SpringLayout.WEST, cambiar2, 30, SpringLayout.EAST, newMaxRepNoPremium);
        layout.putConstraint(SpringLayout.NORTH, cambiar2, 30, SpringLayout.SOUTH, t2);

        this.add(t1);
        this.add(t2);
        this.add(newMaxRepNoPremium);
        this.add(newRepToPremium);
        this.add(cambiar1);
        this.add(cambiar2);



    }

    public String getNewRepToPremium() {
        return newRepToPremium.getText();
    }

    public String getNewMaxRepNoPremium() {
        return newMaxRepNoPremium.getText();
    }

    public void setControlador(ActionListener c) {
        cambiar1.setActionCommand("CambiarRepToPremium");
        cambiar1.addActionListener(c);
        cambiar1.setActionCommand("CambiarMaxRepNoPremium");
        cambiar1.addActionListener(c);

    }
    public int getMaxRepNoPremium() {
        return maxRepNoPremium;
    }

    public void setMaxRepNoPremium(int maxRepNoPremium) {
        this.maxRepNoPremium = maxRepNoPremium;
    }

    public int getRepToPremium() {
        return repToPremium;
    }

    public void setRepToPremium(int repToPremium) {
        this.repToPremium = repToPremium;
    }
}
