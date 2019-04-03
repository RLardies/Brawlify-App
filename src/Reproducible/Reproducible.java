package Reproducible;
/**
 * Clase de Reproducible
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */

import Usuario.Usuario;
import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Reproducible  implements Serializable {

    /**
     * Titulo del reproducible
     */
    private String titulo;

    /**
     * Duracion del reproducible(Si es un conjunto, la suma de todos ellos)
     */
    private Integer duracion;

    /**
     * Numero de canciones del conjunto de reproducibles, es 1 si el reproducible es una cancion
     */
    private Integer numeroCanciones;

    /**
     * Usuario que ha subido o creado el reproducible
     */
    private Usuario autor;


    /**
     * Constructor de reproducible
     * @param titulo Titulo del reproducible
     * @param duracion Duracion del reproducible(Si es un conjunto, la suma de todos ellos)
     * @param numeroCanciones Numero de canciones del conjunto de reproducibles, es 1 si el reproducible es una cancion
     * @param autor Usuario que ha subido o creado el reproducible
     */
    public Reproducible(String titulo, Integer duracion, Integer numeroCanciones, Usuario autor) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.numeroCanciones = numeroCanciones;
        this.autor = autor;
    }


    /**
     * Modifica el titulo del reproducible
     * @param titulo Titulo del reproducible
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Devuelve el titulo del reproducible
     * @return Titulo del reproducible
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Modifica la duracion del reproducible
     * @param duracion Duracion del reproducible
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    /**
     * Devuelve la duracion del reproducible
     * @return Duracion del reproducible
     */
    public Integer getDuracion() {
        return duracion;
    }

    /**
     * Modifica el numero de canciones
     * @param numeroCanciones Numero de canciones
     */
    public void setNumeroCanciones(Integer numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    /**
     * Devuelve el numero de canciones
     * @return Numero de canciones
     */
    public Integer getNumeroCanciones() {
        return numeroCanciones;
    }

    /**
     * Modifica el autor del reproducible
     * @param autor Autor del reproducible
     */
    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    /**
     * Devuelve el autor del reproducible
     * @return Autor del reproducible
     */
    public Usuario getAutor() {
        return autor;
    }


    /**
     * Metodo que convierte la clase Reproducible en una cadena de caracteres
     * @return Cadena de caracteres
     */
    public String toString() {
        return titulo + " - " + duracion/60 + ":" + String.format("%02d",duracion%60);
    }

    /**
     * Devuelve true si el reproducible es una lista o un album
     * @return
     */
    public boolean esContenedor() {
        return false;
    }

    /**
     * Devuelve true si algun elemento del reproducible que se pasa como argumento esta contenido en el que ejecutamos el metodo
     * @param reproducible Reproducible que queremos buscar
     * @return true si esta contenido, false si no
     */
    public boolean containsReproducible(Reproducible reproducible) {
        return false;
    }

    /**
     * Devuelve true si la cancion esta contenida en el reproducible
     * @param cancion Cancion que queremos buscar
     * @return true si esta contenida, false si no
     */
    public boolean containsCancion(Cancion cancion) {
        return false;
    }

    /**
     * Devuelve true si el album o alguna cancion del album esta contenido en el reproducible
     * @param album Album que queremos buscar
     * @return true si esta contenido, false si no
     */
    public boolean containsAlbum(Album album) {
        return false;
    }

    /**
     * Devuelve true si el reproducible contiene una lista, un album o una cancion del reproducible
     * @param lista Lista que queremos buscar
     * @return true si esta contenida, false si no
     */
    public boolean containsLista(Lista lista) {
        return false;
    }

    /**
     * Devuelve true si el reproducible es una cancion
     * @return true si es una cancion, false si no lo es
     */
    public boolean esCancion() {
        return false;
    }

    /**
     * Devuelve true si el reproducible es un album
     * @return true si es una album, false si no lo es
     */
    public boolean esAlbum() {
        return false;
    }

    /**
     * Devuelve true si el reproducible es una lista
     * @return
     */
    public boolean esLista() {
        return false;
    }

    /**
     * Devuelve el estado del reproducible(Puede ser nuevo, valido, bloqueado o borrado
     * @return  Estado del reproducible
     */
    public Cancion.Estado getEstado() {
        return null;
    }

    /**
     * Devuelve el contenido del reproducible que puede ser novalido, explicito si solo es para mayores de 18 años, o autorizado si
     * apto para todos los publicos
     * @return Campo "contenido" del reproducible
     */
    public Cancion.Contenido getContenido() {
        return null;
    }

    /**
     * Modifica el estado del reproducible
     * @param estado Estado del reproducible
     */
    public void setEstado(Cancion.Estado estado) {
    }

    /**
     * Mosidica el campo contenido del reproducible
     * @param contenido Campo contenido del reproducible
     */
    public void setContenido(Cancion.Contenido contenido) {
    }

    /**
     * Devuelve el array de canciones que hay en el reproducible
     * @return Array de canciones
     */
    public ArrayList<Cancion> getCanciones() {
        return null;
    }


}
