package Reproducible;
/**
 * Clase de album
 * @author
 */

import Usuario.Usuario;
import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

public class Album extends Reproducible  implements Serializable {

    /**
     * Año de publicacion
     */
    private Integer anioPublic;

    /**
     * Array de canciones del album
     */
    private ArrayList<Cancion> canciones;

    /**
     * Constructor de Album
     * @param titulo Titulo del reproducible
     * @param autor Autor del album
     * @param anioPublic Año de publicacion
     * @param canciones Canciones del album
     */
    public Album(String titulo, Usuario autor , Integer anioPublic, ArrayList<Cancion> canciones) {
        super(titulo, 0, canciones.size(), autor);
        this.anioPublic = anioPublic;
        this.canciones = canciones;

        Integer duracion = 0;

        for(Cancion c : canciones) {
            duracion += c.getDuracion();
        }

        setDuracion(duracion);

    }

    /**
     * Devuelve el año de publicacion del album
     * @return Año de publicacion
     */
    public Integer getAnioPublic() {
        return anioPublic;
    }

    /**
     * Devuelve el array de canciones
     * @return Array de canciones
     */
    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    /**
     * Devuele true porque es un album(contiene canciones)
     * @return true
     */
    public boolean esContenedor() {
        return true;
    }

    /**
     * Devuelve true si la cancion esta contenida en el album
     * @param cancion Cancion que queremos buscar
     * @return true si esta contenida, false si no
     */
    public boolean containsCancion(Cancion cancion) {
        return canciones.contains(cancion);
    }

    /**
     * Devuelve true si es album
     * @return true
     */
    public boolean esAlbum() {
        return true;
    }

    /**
     *
     * @param reproductor
     * @throws FileNotFoundException
     * @throws Mp3PlayerException
     * @throws InterruptedException
     */
    public void reproducir(Mp3Player reproductor) throws FileNotFoundException, Mp3PlayerException, InterruptedException {

    }
}