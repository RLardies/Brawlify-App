package Interfaces.MenuPrincipal;
/**
 * Ventana para subir una cancion, disponible para cualquier usuario registrado
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;

public class SubirCancion extends JPanel {

    private SpringLayout layout = new SpringLayout();
    private JLabel titulo = new JLabel("Titulo de la Cancion:");
    private JTextField tituloTexto = new JTextField(20);
    private JLabel archivo = new JLabel("Archivo:");
    private JTextField archivoRuta = new JTextField(20);

    private JButton examinar = new JButton("Examinar");

    private JButton subir = new JButton("Subir Cancion");

    JFileChooser fileChooser = new JFileChooser();

    public SubirCancion() {

        this.setLayout(layout);

        layout.putConstraint(SpringLayout.WEST, titulo, 200, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, titulo, 200, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, tituloTexto, 30, SpringLayout.EAST, titulo);
        layout.putConstraint(SpringLayout.NORTH, tituloTexto, -2, SpringLayout.NORTH, titulo);

        layout.putConstraint(SpringLayout.WEST, archivo, 0, SpringLayout.WEST, titulo);
        layout.putConstraint(SpringLayout.NORTH, archivo, 40, SpringLayout.SOUTH, titulo);

        layout.putConstraint(SpringLayout.WEST, archivoRuta, 0, SpringLayout.WEST, tituloTexto);
        layout.putConstraint(SpringLayout.NORTH, archivoRuta, -2, SpringLayout.NORTH, archivo);

        layout.putConstraint(SpringLayout.WEST, examinar, 30, SpringLayout.EAST, archivoRuta);
        layout.putConstraint(SpringLayout.NORTH, examinar, -2, SpringLayout.NORTH, archivoRuta);

        layout.putConstraint(SpringLayout.WEST, subir, 0, SpringLayout.WEST, archivoRuta);
        layout.putConstraint(SpringLayout.NORTH, subir, 50, SpringLayout.SOUTH, archivoRuta);

        fileChooser.setFileFilter(new FileNameExtensionFilter("MP3", "mp3"));

        this.add(titulo);
        this.add(tituloTexto);
        this.add(archivo);
        this.add(archivoRuta);
        this.add(examinar);
        this.add(subir);
    }

    public void setControlador(ActionListener c) {
        examinar.addActionListener(c);
        examinar.setActionCommand("Examinar");
        subir.addActionListener(c);
        subir.setActionCommand("Subir");
    }

    public JTextField getArchivoRuta() {
        return archivoRuta;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public JTextField getTituloTexto() {
        return tituloTexto;
    }
}
