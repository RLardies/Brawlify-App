package Reproducible;
/**
 * Clase de Cancion
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */

import Usuario.Usuario;
import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class Cancion extends Reproducible  implements Serializable {

    /**
     * Id de la siguiente cancion que se suba a la aplicacion
     */
    private static Integer nextId = 1;

    /**
     * Id de la cancion
     */
    private Integer id;

    /**
     * Ruta del fichero de la cancion
     */
    private String fichero;

    /**
     * Fecha en la que se acaba el tiempo de modificacion de la cancion( si no esta en fecha de modificacion es una fecha muy grande
     */
    private LocalDate fecha;


    /**
     * Enumeracion con los posibles estados de una cancion que son:
     * - BORRADO si la cancion esta borrada
     * - BLOQUEDO si la cancion esta bloqueada o su autor lo esta
     * - NUEVO si la cancion se acaba de subir y aun no ha sido validada por el admin
     * - VALIDADO si la cancion ha sido validada por el Admin
     */
    public enum Estado{
        BORRADO, BLOQUEADO, NUEVO, VALIDADO
    }

    /**
     * Enumeracion con los diferentes valores que puede tener el contenido de una cancion. Pueden ser:
     * - NOVALIDO si al validar la cancion el admin la rechaza. El usuario tiene 3 dias para modificarla o borrarla
     * - EXPLICITO si la cancion solo puede ser escuchada por los usuarios mayores de 18 años
     * - AUTORIZADO si la cancion es apta para todos los usuarios
     */
    public enum Contenido{
        NOVALIDO, EXPLICITO, AUTORIZADO
    }

    /**
     * Campo estado de una cancion
     */
    private Estado estado;

    /**
     * Campo contenido de una cancion
     */
    private Contenido contenido;

    /**
     * Constructor de cancion
     * @param titulo Titulo de la cancion
     * @param duracion Duracion de la cancion
     * @param autor Autor de la cancion
     * @param fichero Ruta del fichero de la cancion
     */
    public Cancion(String titulo, Integer duracion, Usuario autor, String fichero) {

        super(titulo, duracion, 1, autor);
        this.id = nextId;
        nextId = nextId + 1;
        this.fichero = fichero;


        estado = Estado.NUEVO;
        contenido = Contenido.NOVALIDO;
        fecha = LocalDate.of(9999,1,1);
    }

    /**
     * Devuelve la fecha en la que se acaba el tiempo de modificacion de la cancion
     * @return fecha en la que se acaba el tiempo de modificacion de la cancion
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Modifica la fecha en la que se acaba el tiempo de modificacion de la cancion
     * @param fecha Fecha en la que se acaba el tiempo de modificacion de la cancion
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Modifca el valor del campo estado de la cancion
     * @param estado Estado de la cancion
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Devuelve el valor del campo estado de la cancion
     * @return Estado de la cancion
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Modifica el valor del campo contenido de la cancion
     * @param contenido Campo contenido de la cancion
     */
    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    /**
     * Devuelve el valor del campo contenido de la cancion
     * @return Valor del campo contenido
     */
    public Contenido getContenido() {
        return contenido;
    }

    /**
     * Modifica el campo id de una cancion
     * @param id Id de la cancion
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Devuelve el id de la cancion
     * @return Id de la cancion
     */
    public Integer getId() {
        return id;
    }

    /**
     * Modifica el fichero de la cancion
     * @param fichero Fichero de la cancion
     */
    public void setFichero(String fichero) {
        this.fichero = fichero;
    }

    /**
     * Devuelve el fichero de la cancion
     * @return Fichero de la cancion
     */
    public String getFichero() {
        return fichero;
    }

    /**
     * Devuelve true si el reproducible es una cancion
     * @return True si es una cancion, false si no
     */
    public boolean esCancion() {
        return true;
    }

    /**
     * Devuelve true si el reproducible que se pasa como argumento esta contenido en el que ejecutamos el metodo
     * @param reproducible Reproducible que queremos buscar
     * @return true si esta contenido, false si no
     */
    public boolean containsReproducible(Reproducible reproducible) {
        return false;
    }


}