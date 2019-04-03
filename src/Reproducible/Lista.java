package Reproducible;
/**
 * Clase de Lista
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */

import Usuario.Usuario;
import Reproducible.Album;
import Reproducible.Cancion;
import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

public class Lista extends Reproducible  implements Serializable {

    /**
     * Array de reproducibles de la lista
     */
    private ArrayList<Reproducible> elementos = new ArrayList<Reproducible>();

    /**
     * Constructor de lista
     * @param titulo Titulo de la lista
     * @param autor Autor de la lista
     */
    public Lista(String titulo, Usuario autor) {
        super(titulo,0,0,autor);
    }

    /**
     * Devuelve el array de reproducibles
     * @return Array de los reproducibles
     */
    public ArrayList<Reproducible> getElementos() {
        return elementos;
    }

    /**
     * Elimina un reproducible de la lista
     * @param reproducible Reproducible a eliminar
     * @return True si se ha eliminado, false si no
     */
    public boolean removeReproducible(Reproducible reproducible) {
        if(reproducible == null || !elementos.contains(reproducible)) {
            return false;
        } else {
            elementos.remove(reproducible);
            setNumeroCanciones(getNumeroCanciones() - reproducible.getNumeroCanciones());
            setDuracion(getDuracion() - reproducible.getDuracion());
            return true;
        }
    }

    /**
     * Añade un reproducible a la lista
     * @param reproducible Reproducible que queremos añadir
     * @return True si se ha añadido, false si no
     */
    public boolean addReproducible(Reproducible reproducible) {
        if(reproducible == null || containsReproducible(reproducible)) {
            return false;
        } else {
            elementos.add(reproducible);
            setNumeroCanciones(reproducible.getNumeroCanciones() + getNumeroCanciones());
            setDuracion(reproducible.getDuracion() + getDuracion());
            return true;
        }
    }


    /**
     * Devuelve true si algun elemento del reproducible que se pasa como argumento esta contenido en la lista
     * @param reproducible Reproducible que queremos buscar
     * @return true si esta contenido, false si no
     */
    public boolean containsReproducible(Reproducible reproducible) {

        if(!reproducible.esContenedor()) {
            return containsCancion((Cancion) reproducible);

        } else if(reproducible.esAlbum()) {
            return containsAlbum((Album) reproducible);

        } else if(reproducible.esLista()) {
            return containsLista((Lista) reproducible);
        }

        return false;
    }

    /**
     * Devuelve true si la lista contiene una lista, un album o una cancion de la lista
     * @param lista Lista que queremos buscar
     * @return true si esta contenida, false si no
     */
    public boolean containsLista(Lista lista) {
        if(elementos.contains(lista)) {
            return true;
        }

        for(Reproducible r : elementos) {
            if(r.esLista()) {
                if(r.containsLista(lista)) {
                    return true;
                }
            }
        }

        for(Reproducible r : lista.getElementos()) {
            if(!r.esContenedor()) {
                if(containsCancion((Cancion) r)) {
                    return true;
                }
            } else if (r.esAlbum()) {
                if(containsAlbum((Album) r)) {
                    return true;
                }
            } else if (r.esLista()) {
                if(containsLista((Lista) r)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Devuelve true si el album o alguna cancion del album esta contenido en el reproducible
     * @param album Album que queremos buscar
     * @return true si esta contenido, false si no
     */
    public boolean containsAlbum(Album album) {
        if(elementos.contains(album)) {
            return true;
        }
        for(Reproducible r : elementos) {
            if(r.esLista()) {
                if(r.containsAlbum(album)) {
                    return true;
                }
            }
        }

        for(Cancion c : album.getCanciones()) {
            if(containsCancion(c)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Devuelve true si la cancion esta contenida en la lista
     * @param cancion Cancion que queremos buscar
     * @return true si esta contenida, false si no
     */
    public boolean containsCancion(Cancion cancion) {
        if(elementos.contains(cancion)) {
            return true;
        }
        for(Reproducible r : elementos) {
            if(r.esContenedor()) {
                if(r.containsCancion(cancion)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Devuelve true porque el reproducible es una lista
     * @return true
     */
    public boolean esLista() {
        return true;
    }

    /**
     * Devuelve la informacion de la lista en forma de cadena de caracteres
     * @return Cadena de caracteres
     */
    public String toString() {
        return super.toString() + " - (" + getNumeroCanciones() + " canciones)";
    }

    /**
     * Devuelve true si el reproducible es una lista o un album
     * @return
     */
    public boolean esContenedor() {
        return true;
    }
}
