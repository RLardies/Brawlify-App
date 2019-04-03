package Reporte;

/**
 * Clase de reporte
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */

import Reproducible.Cancion;
import Usuario.Usuario;

import java.io.Serializable;

public class Reporte  implements Serializable {

    /**
     * Comentario que acompaña al reporte
     */
    private String comentario;

    /**
     * Usuario que realiza el reporte
     */
    private Usuario usuario;

    /**
     * Cancion reportada
     */
    private Cancion cancionReportada;

    /**
     * Constructor de reporte
     * @param comentario Comentario que acompaña al reporte
     * @param usuario Usuario que realiza el reporte
     * @param cancionReportada Cancion reportada
     */
    public Reporte(String comentario, Usuario usuario, Cancion cancionReportada) {
        this.comentario = comentario;
        this.usuario = usuario;
        this.cancionReportada = cancionReportada;
    }


    /**
     * Devuelve el comentario que acompaña al reporte
     * @return Comentario que acompaña al reporte
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Modifica el comentario que acompaña al reporte
     * @param comentario Comentario que acompaña al reporte
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Devuelve el usuario que realiza el reporte
     * @return Usuario que realiza el reporte
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Modifica el usuario que realiza el reporte
     * @param usuario Usuario que realiza el reporte
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Devuelve la cancion reportada
     * @return Cancion reportada
     */
    public Cancion getCancionReportada() {
        return cancionReportada;
    }

    /**
     * Devuelve la cancion reportada
     * @param cancionReportada Cancion reportada
     */
    public void setCancionReportada(Cancion cancionReportada) {
        this.cancionReportada = cancionReportada;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "comentario='" + comentario + '\'' +
                ", usuario=" + usuario.getUsername() +
                ", cancionReportada=" + cancionReportada +
                '}';
    }
}