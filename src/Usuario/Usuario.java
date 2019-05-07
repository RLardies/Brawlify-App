/**
 * Clase de usuario
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */
package Usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import Aplicacion.Aplicacion;
import Reproducible.Reproducible;
import Notificacion.Notificacion;

public class Usuario  implements Serializable {

    /**
     * Nombre de usuario
     */
    private String username;

    /**
     * Contraseña del usuario
     */
    private String contrasena;

    /**
     * Fecha de nacimiento del usuario
     */
    private LocalDate fechaNac;

    /**
     * Nombre real del usuario
     */
    private String nombre;

    /**
     * Numero de reproducciones que el usuario lleva este mes(si no es premium)
     */
    private Integer reproducciones;

    /**
     * Numero de veces que han sido reproducidas las canciones que ha subido el usuario
     */
    private Integer repToPremium;

    /**
     * Fecha en la que acaba el bloqueo del usuario
     */
    private LocalDate fechaBloqueo;

    /**
     * Array de las notificaciones pendientes para el usuario
     */
    private ArrayList<Notificacion> notificaciones;

    /**
     * Array que contiene las canciones que ha subido el autor, asi como los albumes y las listas que ha creado
     */
    private ArrayList<Reproducible> reproducibles;

    /**
     * Array de usuarios a los que sigue el autor
     */
    private ArrayList<Usuario> usuariosSeguidos;

    /**
     * Fecha en la que acaba el periodo premium del usuario
     */
    private LocalDate fechaPremium;


    /**
     * Constructor de usuario
     * @param username Nombre de usuario
     * @param contrasena Cotntraseña del usuario
     * @param fechaNac Fecha de nacimiento
     * @param nombre Nombre real
     */
    public Usuario(String username, String contrasena, LocalDate fechaNac, String nombre) {
        this.username = username;
        this.contrasena = contrasena;
        this.fechaNac = fechaNac;
        this.nombre = nombre;
        reproducciones = 0;
        repToPremium = 0;
        fechaBloqueo = LocalDate.of(2000,1,1);
        notificaciones = new ArrayList<Notificacion>();
        reproducibles = new ArrayList<Reproducible>();
        usuariosSeguidos = new ArrayList<Usuario>();
        fechaPremium = LocalDate.of(2000,1,1);

    }

    /**
     * Metodo toString de usuario (lo convierte a una cadena de caracteres)
     * @return cadena
     */
    public String toString() {
        return username + ", " + nombre;
    }

    /**
     * Devuelve el nombre de usuario
     * @return Nombre de usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Devuelve la contraseña del usuario
     * @return Contraseña del usuario
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Devuelve la fecha de nacimiento del usuario
     * @return Fecha de nacimiento del usuario
     */
    public LocalDate getFechaNac() {
        return fechaNac;
    }

    /**
     * Devuelve el nombre real del usuario
     * @return Nombre real del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el numero de reproducciones que el usuario lleva este mes(si no es premium)
     * @return Numero de reproducciones que el usuario lleva este mes(si no es premium)
     */
    public Integer getReproducciones() {
        return reproducciones;
    }

    /**
     * Devuelve el numero de veces que han sido reproducidas las canciones que ha subido el usuario
     * @return Numero de veces que han sido reproducidas las canciones que ha subido el usuario
     */
    public Integer getRepToPremium() {
        return repToPremium;
    }

    /**
     * Devuelve la fecha en la que acaba el bloqueo del usuario
     * @return Fecha en la que acaba el bloqueo del usuario
     */
    public LocalDate getFechaBloqueo() {
        return fechaBloqueo;
    }

    /**
     * Devuelve el array de las notificaciones pendientes para el usuario
     * @return Array de las notificaciones pendientes para el usuario
     */
    public ArrayList<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    /**
     * Devuelve el array que contiene las canciones que ha subido el autor, asi como los albumes y las listas que ha creado
     * @return Array que contiene las canciones que ha subido el autor, asi como los albumes y las listas que ha creado
     */
    public ArrayList<Reproducible> getReproducibles() {
        return reproducibles;
    }

    /**
     * Devuelve el array de usuarios a los que sigue el autor
     * @return Array de usuarios a los que sigue el autor
     */
    public ArrayList<Usuario> getUsuariosSeguidos() {
        return usuariosSeguidos;
    }

    /**
     * Devuelve la fecha en la que acaba el periodo premium del usuario
     * @return Fecha en la que acaba el periodo premium del usuario
     */
    public LocalDate getFechaPremium() {
        return fechaPremium;
    }

    /**
     * Modifica el nombre de usuario
     * @param username Nombre de usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Modifica la contraseña del usuario
     * @param contrasena Contraseña del usuario
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Modifica la fecha de nacimiento del usuario
     * @param fechaNac Fecha de nacimiento del usuario
     */
    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    /**
     * Modifica el nombre real del usuario
     * @param nombre Nombre real del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Modifica el numero de reproducciones que el usuario lleva este mes(si no es premium)
     * @param reproducciones Numero de reproducciones que el usuario lleva este mes(si no es premium)
     */
    public void setReproducciones(Integer reproducciones) {
        this.reproducciones = reproducciones;
    }

    /**
     * Modifica el numero de veces que han sido reproducidas las canciones que ha subido el usuario
     * @param repToPremium Numero de veces que han sido reproducidas las canciones que ha subido el usuario
     */
    public void setRepToPremium(Integer repToPremium) {
        this.repToPremium = repToPremium;
    }

    /**
     * Modifica la fecha en la que acaba el bloqueo del usuario
     * @param fechaBloqueo Fecha en la que acaba el bloqueo del usuario
     */
    public void setFechaBloqueo(LocalDate fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
    }

    /**
     * Modifica el array de las notificaciones pendientes para el usuario
     * @param notificaciones Array de las notificaciones pendientes para el usuario
     */
    public void setNotificaciones(ArrayList<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    /**
     * Modifica el array que contiene las canciones que ha subido el autor, asi como los albumes y las listas que ha creado
     * @param reproducibles Array que contiene las canciones que ha subido el autor, asi como los albumes y las listas que ha creado
     */
    public void setReproducibles(ArrayList<Reproducible> reproducibles) {
        this.reproducibles = reproducibles;
    }

    /**
     * Modifica el array de usuarios a los que sigue el autor
     * @param usuariosSeguidos Array de usuarios a los que sigue el autor
     */
    public void setUsuariosSeguidos(ArrayList<Usuario> usuariosSeguidos) {
        this.usuariosSeguidos = usuariosSeguidos;
    }

    /**
     * Modifica la fecha en la que acaba el periodo premium del usuario
     * @param fechaPremium Fecha en la que acaba el periodo premium del usuario
     */
    public void setFechaPremium(LocalDate fechaPremium) {
        this.fechaPremium = fechaPremium;
    }

    /**
     * Permite al usuario suscribirse a un autor
     * @param u Usuario al que queremos empezar a seguir
     * @return true si se ha suscrito al autor, false si ya estaba suscrito o si es el propio usuario
     */
    public boolean suscribirseAAutor(Usuario u) {
        if(username.equals(u.getUsername())){
            return false;
        }
        if(usuariosSeguidos.contains(u)){
            return false; /*Ya esta suscrito al autor*/
        }else{
            usuariosSeguidos.add(u);
            return true;
        }
    }

    /**
     * Elimina un autor de los seguidores del usuario
     * @param u Usuario al que queremos dejar de a seguir
     * @return true
     */
    public boolean removeAutor(Usuario u) {

        if(usuariosSeguidos.contains(u)){
            usuariosSeguidos.remove(u);
            return true;
        }else{

            return true; /*El usuario no estaba suscrito a este autor*/

        }
    }

    /**
     * Añade un reproducible al array de reproducibles del usuario
     * @param r Reproducible que queremos añadir
     * @return true si se ha añadido, false si ya estaba añadido
     */
    public boolean addReproducible(Reproducible r){
        if(reproducibles.contains(r)){
            return false; /*Ya existe ese reproducible*/
        }else{
            reproducibles.add(r);
            return true;

        }
    }

    /**
     * Elimina un reproducible del array de reproducibles del usuario
     * @param r Reproducible que queremos eliminar
     * @return true
     */
    public boolean removeReproducible(Reproducible r){
        if(reproducibles.contains(r)){
            reproducibles.remove(r);
            return true;
        }else{

            return true;   /*Este reproducible no estaba*/

        }
    }

    /**
     * Añade una notificacion al usuario
     * @param n Notificacion a añadir
     * @return True si se ha añadido, false si ya estaba añadida
     */
    public boolean addNotificacion(Notificacion n){
        if(notificaciones.contains(n)){
            return false; /*Ya existe esa notificacion*/
        }else{
            notificaciones.add(n);
            return true;
        }
    }

    /**
     * Elimina una notificacion del usuario
     * @param n Notificacion a eliminar
     * @return True si se ha añadido, false si ya estaba añadida
     */
    public boolean removeNotificacion(Notificacion n){
        if(notificaciones.contains(n)){
            notificaciones.remove(n);
            return true;
        }else{

            return true;   /*Esta notificacion no estaba*/

        }
    }
    /**
     * Vacia el campo de notificaciones del usuario
     */
    public void emptyNotificacion(){
        notificaciones.clear();
    }
    /**
     * Funcion que comprueba si el usuario es mayor de edad
     * @return true si el usuario es mayor de edad, false si no lo es
     */
    public boolean esApto(){
        if(fechaNac.plusYears(18).isBefore(Aplicacion.getFechaActual())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Funcion que comprueba si el usuario esta bloqueado
     * @return true si el usuario esta bloqueado, false si no lo esta
     */
    public boolean esBloqueado(){
        if(fechaBloqueo.isAfter(Aplicacion.getFechaActual())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Funcion que comprueba si el usuario es premium
     * @return true si el usuario es premium, false si no
     */
    public boolean esPremium(){
        if(fechaPremium.isAfter(Aplicacion.getFechaActual())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Funcion que comprueba si el usuario es admin
     * @return true si el usuario es admin, false si no lo es
     */
    public boolean esAdmin() {

        if(username.equals("admin")) {
            return true;
        } else {
            return false;
        }
    }

}
