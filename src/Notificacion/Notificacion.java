package Notificacion;
/**
 * Clase de Notificacion
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */

import fechasimulada.FechaSimulada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import static Aplicacion.Aplicacion.getFechaActual;

public class Notificacion  implements Serializable {

    /**
     * Cadena en la que se guarda la notificacion
     */
    private String comentario;

    /**
     * Constructor de notificacion
     * @param comentario Comentario de la notificacion
     */
    public Notificacion(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Devuelve la cadena con el mensaje de la notificacion
     * @return Cadena con el mensaje de la notificacion
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Modifica la cadena con el mensaje de la notificacion
     * @param comentario Cadena con el mensaje de la notificacion
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return getFechaActual() + " " + comentario;
    }
}