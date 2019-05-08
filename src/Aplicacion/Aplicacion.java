package Aplicacion;
/**
 * Clase de Aplicacion
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */

import Exceptions.*;
import Reporte.Reporte;
import Reproducible.Reproducible;
import Reproducible.Cancion;
import Reproducible.Album;
import Reproducible.Lista;
import Usuario.Usuario;
import Notificacion.Notificacion;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import fechasimulada.FechaSimulada;
import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Aplicacion implements Serializable {

    /**
     * Numero de reproducciones que necesita un usuario sobre sus canciones para obtener gratuitamente el premium
     * para el siguiente mes. Se restablecen mensualmente.
     */
    private Integer repToPremium = 100;

    /**
     * Numero maximo de reproducciones que puede hacer un usuario no premium en un mes. Se restablecen mensualmente.
     */
    private Integer maxRepNoPremium = 5;

    /**
     * Array con todos los usuarios registrados en la aplicacion
     */
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    /**
     * Array con todos los reproducibles que se han subido a la aplicacion
     */
    private ArrayList<Reproducible> reproducibles = new ArrayList<Reproducible>();

    /**
     * Array con todos los reportes pendientes que el admin aun no ha procesado
     */
    private ArrayList<Reporte> reportes = new ArrayList<Reporte>();

    /**
     * Usuario que esta logueado actualmente en la aplicacion. Si es null significa que el usuario no esta registrado
     */
    private Usuario usuarioLogueado;

    /**
     * Reproductor MP3
     */
    private Mp3Player reproductor;

    /**
     * Sistema de pago
     */
    private TeleChargeAndPaySystem sistemaPago;

    /**
     * Fecha del siguiente dia en el que se deben acutalizar los datos de reproducciones de los usuarios. Es el primer dia de cada mes
     */
    private static LocalDate fechaCambio;


    public Aplicacion() {

        Usuario admin = new Usuario("admin", "1234", getFechaActual(), "Admin");
        usuarios.add(admin);
    }

    /**
     * Devuelve el numero de reproducciones para convertirse en premium
     * @return Numero de reproducciones para convertirse en premium
     */
    public Integer getRepToPremium() {
        return repToPremium;
    }

    /**
     * Devuelve el maximo numero maximo de reproducciones mensuales de un usuario no premium
     * @return Numero maximo de reproducciones mensuales de un usuario no premium
     */
    public Integer getMaxRepNoPremium() {
        return maxRepNoPremium;
    }

    /**
     * Modifica el numero de reproducciones para convertirse en premium. Solo puede hacerlo el admin
     * @param repToPremium Numero de reproducciones para convertirse en premium
     */
    public void setRepToPremium(Integer repToPremium) {
        if(usuarioLogueado.esAdmin()){
            this.repToPremium = repToPremium;
        }

    }

    /**
     * Devuelve el reproductor
     * @param reproductor
     */
    public void setReproductor(Mp3Player reproductor) {
        this.reproductor = reproductor;
    }

    /**
     * Devuelve el maximo numero maximo de reproducciones mensuales de un usuario no premium. Solo puede hacerlo el admin
     * @param maxRepNoPremium Maximo numero maximo de reproducciones mensuales de un usuario no premium
     */
    public void setMaxRepNoPremium(Integer maxRepNoPremium) {
        if(usuarioLogueado.esAdmin()){
            this.maxRepNoPremium = maxRepNoPremium;
        }

    }

    /**
     * Devuelve el array de usuarios registrados
     * @return Array de usuarios registrados
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Modifica el array de usuarios registrados
     * @param usuarios Array de usuarios registrados
     */
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * Devuelve el array de reproducibles
     * @return Array de reproducibles
     */
    public ArrayList<Reproducible> getReproducibles() {
        return reproducibles;
    }

    /**
     * Modifica el array de reproducibles
     * @param reproducibles Modifica el array de reproducibles
     */
    public void setReproducibles(ArrayList<Reproducible> reproducibles) {
        this.reproducibles = reproducibles;
    }

    /**
     *
     * @return fechaCambio
     */
    public LocalDate getFechaCambio() {
        return fechaCambio;
    }

    /**
     * Modifica fechaCambio
     * @param fechaCambio
     */
    public void setFechaCambio(LocalDate fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    /**
     * Devuelve el array de reportes que aun no han sido procesados por el admin
     * @return Array de reportes
     */
    public ArrayList<Reporte> getReportes() {
        if(usuarioLogueado.esAdmin()) {
            return reportes;
        }
        return null;
    }

    /**
     * Modifica el array de reportes que aun no han sido procesados por el admin
     * @param reportes Array de reportes
     */
    public void setReportes(ArrayList<Reporte> reportes) {
        this.reportes = reportes;
    }

    /**
     * Devuelve el usuario logueado en la aplicacion. Es null si el usuario es no registrado
     * @return Usuario logueado, null si no hay ninguno
     */
    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    /**
     * Devuelve el array de canciones que se corresponden a la busqueda realizada por el usuario
     * @param busqueda Titulo de la cancion que buscamos
     * @return Array de resultados
     */
    public ArrayList<Cancion> buscarCancionPorTitulo(String busqueda) {

        ArrayList<Cancion> resultados = new ArrayList<Cancion>();

        for(Reproducible r: reproducibles){
            if ((r.getTitulo().startsWith(busqueda)) && (r.esCancion()) && (sePuedeEscuchar(r))) {
                resultados.add((Cancion) r);
            }
        }

        return resultados;
    }

    /**
     * Devuelve el array de canciones que se corresponden a la busqueda realizada por el usuario
     * @param busqueda Autor de la cancion que buscamos
     * @return Array de resultados
     */
    public ArrayList<Cancion> buscarCancionPorAutor(String busqueda) {

        ArrayList<Cancion> resultados = new ArrayList<Cancion>();

        for(Reproducible r: reproducibles){
            if ((r.getAutor().getUsername().startsWith(busqueda)) && (r.esCancion()) && (sePuedeEscuchar(r))) {
                resultados.add((Cancion) r);
            }
        }

        return resultados;
    }

    /**
     * Devuelve el array de albums que se corresponden a la busqueda realizada por el usuario
     * @param busqueda Titulo del album que buscamos
     * @return Array de resultados
     */
    public ArrayList<Album> buscarAlbumPorTitulo(String busqueda) {

        ArrayList<Album> resultados = new ArrayList<Album>();

        for(Reproducible r: reproducibles){
            if ((r.getTitulo().startsWith(busqueda)) && (r.esAlbum()) && (sePuedeEscuchar(r))) {
                resultados.add((Album) r);
            }
        }

        return resultados;
    }

    /**
     * Devuelve el array de albums que se corresponden a la busqueda realizada por el usuario
     * @param busqueda Titulo del album que buscamos
     * @return Array de resultados
     */
    public ArrayList<Album> buscarAlbumPorAutor(String busqueda) {

        ArrayList<Album> resultados = new ArrayList<Album>();

        for(Reproducible r: reproducibles){
            if ((r.getAutor().getUsername().startsWith(busqueda)) && (r.esAlbum()) && (sePuedeEscuchar(r))) {
                resultados.add((Album) r);
            }
        }

        return resultados;
    }

    /**
     * Funcion para registrar a un usuario
     * @param username Nombre de usuario
     * @param contrasena Contraseña
     * @param fechaNac Fecha de nacimiento del usuario
     * @param nombre Nombre real
     * @return true si el usuario se ha registrado, false si ya hay un usuario con ese nombre o si el usuario esta loguead en la aplicacion
     */
    public boolean registrarUsuario(String username, String contrasena, LocalDate fechaNac, String nombre) throws UsuarioYaExistente {

        boolean flag = false;

        if(usuarioLogueado != null) {
            return false;
        }

        if(username.equals("admin")) {
            return false;
        }

        for(Usuario u : usuarios) {
            if(username.equals(u.getUsername())) {
                throw new UsuarioYaExistente();
            }
        }

        Usuario usuario = new Usuario(username, contrasena, fechaNac, nombre);
        usuarios.add(usuario);

        return true;
    }

    /**
     * Metodo para iniciar sesion. Ademas al inicar sesion, si la ultima vez que un usuario utilizo la aplicacion
     * fue en un mes pasado, se deben poner los contadores de reproducciones a 0 y comprobar que usuarios pasan a ser premium
     * por las reproducciones(Esto solo ocurres si llegaron a las reproducciones el mes pasado, si han pasado mas meses ya
     * han perdido su mes de premium). Tambien comprobamos si hay algun usuario al que se le ha terminado el bloqueo temporal.
     * Si hay alguno ponemos sus canciones otra vez al estado VALIDADO
     * @param username Nombre de usuario
     * @param contrasena Coontraseña
     * @throws UsuarioNoExistente
     * @throws ContrasenaIncorrecta
     */
    public void login(String username,String contrasena) throws UsuarioNoExistente, ContrasenaIncorrecta, UsuarioBloqueado {

        if(usuarioLogueado != null) {
            return;
        }

        if(username == null || contrasena == null){
            return;
        }

        if(fechaCambio == null){
            fechaCambio = getFechaActual().withDayOfMonth(1).plusMonths(1);
        }

        for(Usuario  u: usuarios){
            if(u.getUsername().equals(username) ){


                if(u.getContrasena().equals(contrasena)){

                    if(u.esBloqueado()) {
                        throw new UsuarioBloqueado();
                    }

                    usuarioLogueado = u;


                    LocalDate nuevaFecha = getFechaActual().withDayOfMonth(1).plusMonths(1);

                    for (Usuario us : usuarios) {
                        if(getFechaActual().isAfter(fechaCambio) || getFechaActual().isEqual(fechaCambio)) {


                            /**
                             * Solo pasan a ser premium los usuarios si estamos en el mes siguiente al que han
                             * conseguido llegar al minimo de reproducciones en sus canciones
                             */
                            if (getFechaActual().getMonth() == fechaCambio.getMonth()) {
                                if (us.getRepToPremium() >= repToPremium) {
                                    us.setFechaPremium(nuevaFecha);
                                }

                            }

                            us.setReproducciones(0);
                            us.setRepToPremium(0);
                        }

                        for(Reproducible r: us.getReproducibles()) {
                            if(getFechaActual().isAfter(us.getFechaBloqueo())) {
                                if (r.esCancion() && r.getContenido() != Cancion.Contenido.NOVALIDO && r.getEstado() == Cancion.Estado.BLOQUEADO) {
                                    r.setEstado(Cancion.Estado.VALIDADO);
                                }
                            }
                            if(r.esCancion() && r.getContenido() == Cancion.Contenido.NOVALIDO  && ((Cancion)r).getFecha().isBefore(getFechaActual()) ){
                                r.setEstado(Cancion.Estado.BORRADO);
                            }
                        }

                    }
                    fechaCambio = getFechaActual().plusMonths(1).withDayOfMonth(1);




                    /*System.out.println(usuarioLogueado.getNotificaciones());
                    usuarioLogueado.emptyNotificacion();*/
                    return;
                }
                else{
                    throw new ContrasenaIncorrecta();
                }


            }


        }


        throw new UsuarioNoExistente();
    }

    /**
     * Finaliza la sesion del usuario logueado
     */
    public void cerrarSesion() {
        if(usuarioLogueado != null) {
            usuarioLogueado.emptyNotificacion();
            usuarioLogueado = null;
        }
    }

    /**
     * Crea un reporte sobre una cancion
     * @param c Cancion reportada
     * @param comentario Explicacion del reporte
     * @return True si se ha realizado el reporte, false si el usario es no registrado o si no existe la cancion
     * @throws CancionNoExistente
     */
    public boolean reportarCancion(Cancion c, String comentario) throws CancionNoExistente{
        if(usuarioLogueado == null) {
            return false;
        }

        if(reproducibles.contains(c) == false){
            throw new CancionNoExistente();
        }

        c.setEstado(Cancion.Estado.REPORTADO);

        Reporte reporte = new Reporte(comentario, usuarioLogueado, c);

        reportes.add(reporte);
        c.getAutor().addNotificacion(new Notificacion("La cancion " + c.getTitulo() + " ha sido reportada por plagio. Permanecera bloqueada hasta que nuestro equipo juridico determine si lo és o no"));
        return true;
    }

    /**
     * Devuelve el array de reportes que aun no han sido procesados por el admin
     * @return Array de reportes
     */
    public ArrayList<Reporte> mostrarReportes() {

        if(usuarioLogueado.esAdmin() != true) {
            return null;
        }

        return reportes;
    }

    /**
     * Metodo para subir una cancion. Solo pueden hacerlo los usuarios registrados. Ademas el Mp3Player calcula la duracion. Esta
     * no puede ser mayor que media hora.
     * @param titulo Titulo de la cancion
     * @param rutaFichero Ruta del fichero que contiene la cancion
     * @return true si se ha subido, false si ha habido algun problema
     */
    public Cancion subirCancion(String titulo, String rutaFichero) throws CancionInvalida, IOException {
        Integer duracion;

        if(usuarioLogueado == null) {
            return null;
        }

        if(Mp3Player.isValidMp3File(rutaFichero)) {
            try {
                duracion = (int) Mp3Player.getDuration(rutaFichero);
            } catch (FileNotFoundException e) {
                throw new CancionInvalida();
            }
        } else {
            throw new CancionInvalida();
        }


        if(duracion > 1800) {
            throw new CancionInvalida();
        }

        File archivo = new File(rutaFichero);

        String ruta = new String();
        ruta = System.getProperty("user.dir") + "/canciones/" + LocalDateTime.now() + " - " + this.getUsuarioLogueado().getUsername() + " - " + titulo + " - " + archivo.getName();

        File archivo2 = new File(ruta);
        archivo2.getParentFile().mkdirs();

        copyFile(rutaFichero, ruta);

        Cancion c = new Cancion(titulo, duracion, usuarioLogueado, ruta);

        usuarioLogueado.addReproducible(c);
        reproducibles.add(c);

        return c;
    }

    public void copyFile(String pathOrg, String pathDest) throws IOException {
        InputStream f1 = new FileInputStream(pathOrg);
        OutputStream f2 = new FileOutputStream(pathDest);
        byte[] buffer = new byte[1024];
        int length;

        while ((length = f1.read(buffer)) > 0)
            f2.write(buffer, 0, length);

        return;
    }

    /**
     * Modifica una cancion. Solo lo puede hacer el dueño de la cancion y si el contenido se encuentra como NOVALIDO.
     * Ademas solo hay 3 dias para hacerlo desde que el admin la declara como NOVALIDO
     * @param cancion Cancion a modificar
     * @param titulo Nuevo titulo
     * @return true si se ha modificado, false si no
     * @throws CancionNoExistente
     */
    public boolean modificarCancion(Cancion cancion, String titulo) throws  CancionNoExistente{
        if(usuarioLogueado == null) {
            return false;
        }

        if(reproducibles.contains(cancion) == false){
            throw new CancionNoExistente();
        }

        if(cancion.getAutor() != usuarioLogueado) {
            return false;
        }

        if(cancion.getFecha().isBefore(getFechaActual())) {
            return false;
        }

        if(cancion.getEstado() != Cancion.Estado.BLOQUEADO || cancion.getContenido() != Cancion.Contenido.NOVALIDO) {
            return false;
        }

        cancion.setTitulo(titulo);
        cancion.setEstado(Cancion.Estado.NUEVO);

        return true;

    }

    /** Modifica una cancion. Solo lo puede hacer el dueño de la cancion y si el contenido se encuentra como NOVALIDO.
     * Ademas solo hay 3 dias para hacerlo desde que el admin la declara como NOVALIDO. El estado vuelve a ser nuevo,
     * para que el admin sepa que se ha modificado
     * @param cancion Cancion a modificar
     * @param titulo Nuevo titulo
     * @param rutaFichero Nueva ruta del fichero
     * @return true si se ha modificado, false si no
     * @throws CancionNoExistente
     */
    public boolean modificarCancion(Cancion cancion, String titulo, String rutaFichero) throws CancionNoExistente {
        Integer duracion;

        if(reproducibles.contains(cancion) == false){
            throw new CancionNoExistente();
        }

        if(modificarCancion(cancion, titulo) == false) {
            return false;
        }

        if(Mp3Player.isValidMp3File(rutaFichero)) {
            try{
                duracion = (int) Mp3Player.getDuration(rutaFichero);
            } catch (FileNotFoundException e) {
                return false;
            }

        } else {
            return false;
        }


        if(duracion > 1800) {
            return false; //Cancion dura demasiado
        }

        cancion.setDuracion(duracion);
        cancion.setFichero(rutaFichero);
        cancion.setFecha(LocalDate.of(2000,1,1));
        return true;
    }

    /**
     * Permite borrar una cancion a su autor
     * @param c Cancion a borrar
     * @return true si se ha borrado, false si no
     * @throws CancionNoExistente
     */
    public boolean borrarCancion(Cancion c) throws CancionNoExistente {
        if(usuarioLogueado == null) {
            return false;
        }

        if(reproducibles.contains(c) == false){
            throw new CancionNoExistente();
        }

        if(c.getAutor() != usuarioLogueado) {
            return false;
        }

        c.setEstado(Cancion.Estado.BORRADO);
        File archivo = new File(c.getFichero());
        archivo.delete();
        return true;
    }


    /**
     * Metodo para que el admin pueda evaluar las nuevas canciones que se han subido a la aplicacion.
     * Su contenido puede ser:
     * -AUTORIZADO si es apto para todos los publicos
     * -EXPLICITO  si solo es apto para mayores de edad
     * -NOVALIDO la cancion no es valida. El autor tiene 3 dias para modificarla o borrarla
     * @param c Cancion a validar
     * @param contenido Contenido de la cancion
     * @return true si se ha validado a cualquier contenido, false si ha habido errores
     * @throws CancionNoExistente
     */
    public boolean validarCancion(Cancion c, Cancion.Contenido contenido) throws CancionNoExistente{

        if(reproducibles.contains(c) == false){
            throw new CancionNoExistente();
        }

        if(usuarioLogueado.esAdmin() == false){
            return false;
        }

        if(c.getEstado() != Cancion.Estado.NUEVO) {
            return false;
        }

        c.setContenido(contenido);

        if(contenido == Cancion.Contenido.NOVALIDO) {
            c.setEstado(Cancion.Estado.BLOQUEADO);
            c.setFecha(getFechaActual().plusDays(3));
            c.getAutor().addNotificacion(new Notificacion("Su cancion " + c.getTitulo() + " no ha sido validada" +
                    ". Tiene 3 dias para modificarla o borrarla. Pasados esos tres dias la cancion se borrara del sistema."));
        } else if(contenido == Cancion.Contenido.EXPLICITO) {
            c.setEstado(Cancion.Estado.VALIDADO);
            for(Usuario u: usuarios){
                if(u.getUsuariosSeguidos().contains(c.getAutor()) && u.esApto()){
                    u.addNotificacion(new Notificacion(c.getAutor().getUsername() + " ha subido una cancion: " + c.getTitulo()));
                }
            }
        } else if(contenido == Cancion.Contenido.AUTORIZADO) {
            c.setEstado(Cancion.Estado.VALIDADO);
            for(Usuario u: usuarios){
                if(u.getUsuariosSeguidos().contains(c.getAutor())){
                    u.addNotificacion(new Notificacion(c.getAutor().getUsername() + " ha subido una cancion: " + c.getTitulo()));
                }
            }
        }

        return true;

    }

    /**
     * Muestra al administrador las nuevas canciones que aun no han sido validadas
     * @return Array con las canciones a validar
     */
    public ArrayList<Cancion> mostrarCancionesAValidar(){
        if(usuarioLogueado.esAdmin() == true){
            ArrayList<Cancion> cancionesAValidar = new ArrayList<>();

            for(Reproducible r: reproducibles){
                if(r.esCancion() == true){
                    if(r.getEstado() == Cancion.Estado.NUEVO){
                        cancionesAValidar.add((Cancion) r);

                    }
                }
            }

            return cancionesAValidar;
        }
        return null;
    }

    public ArrayList<Cancion> mostrarCancionesAModificar() {
        if(usuarioLogueado != null) {
            ArrayList<Cancion> cancionesAModificar = new ArrayList<>();

            for(Reproducible r : usuarioLogueado.getReproducibles()) {
                if(r.esCancion()) {
                    if(r.getEstado() == Cancion.Estado.BLOQUEADO && r.getContenido() == Cancion.Contenido.NOVALIDO) {
                        cancionesAModificar.add((Cancion)r);
                    }
                }
            }

            return cancionesAModificar;
        }

        return null;
    }

    /**
     * Permite solo a los usuarios premium, crear una lista de reproduccion. La lista puede contener canciones, albums y
     * otras listas. La lista no puede tener canciones duplicadas aunque estas se encuentren dentro de otras listas o albumes.
     * @param nombre Nombre de la lista
     * @return Lista de reproduccion creada
     */
    public Lista crearLista(String nombre){
        //if((usuarioLogueado == null) || usuarioLogueado.esPremium() == false){
        //    return null;
        //}

        Lista l = new Lista(nombre, usuarioLogueado);

        usuarioLogueado.addReproducible(l);
        reproducibles.add(l);

        return l;

    }

    /**
     * Permite a lis usuarios registrados crear un album. Un album solo esta formado por canciones, que ademas no pueden
     * estar duplicadas o bloqueadas
     * @param nombre Nombre del album
     * @param canciones Array de canciones a introducir en el album
     * @return Album creado, null si el usuario no esta logueado o si hay canciones bloqueadas
     * @throws CancionNoExistente
     */
    public Album crearAlbum(String nombre, ArrayList<Cancion> canciones) throws CancionNoExistente{
        if(usuarioLogueado == null){
            return null;
        }

        for(Cancion c : canciones) {
            if(reproducibles.contains(c) == false){
                throw new CancionNoExistente();
            }
            if(c.getAutor() != usuarioLogueado || c.getEstado() != Cancion.Estado.VALIDADO) {
                return null;
            }
        }

        Album a = new Album(nombre,usuarioLogueado,getFechaActual().getYear(),canciones);

        usuarioLogueado.addReproducible(a);
        reproducibles.add(a);

        return a;

    }

    /**
     * Metodo para que el admin procese un reporte. Puede confirmarlo o desmentirlo. Quien decide es el equipo juridico
     * pero el admin es el que lo plasma en la aplicacion.
     * Si lo confirma se bloquea al autor del plagio permanentemente, asi como todas sus canciones.
     * Si se desmiente el plagio, se bloquea al autor del reporte durante 30 dias, asi como sus canciones
     * @param reporte Reporte a procesar
     * @param plagio true si confirma el plagio, false si lo desmiente
     * @return
     */
    public boolean procesarPlagio(Reporte reporte, Boolean plagio) {
        String nombre;

        if(usuarioLogueado == null) {
            return false;
        }

        if(usuarioLogueado.esAdmin() == false) {
            return false;
        }
        nombre = reporte.getCancionReportada().getTitulo();
        if(plagio == true) {
            reporte.getCancionReportada().getAutor().setFechaBloqueo(LocalDate.of(9999,1,1));
            for(Reproducible r : reporte.getCancionReportada().getAutor().getReproducibles()) {
                if(r.esCancion()) {
                    r.setEstado(Cancion.Estado.BLOQUEADO);
                }
            }

            reporte.getUsuario().addNotificacion(new Notificacion("Nuestro equipo juridico ha confirmado el plagio" +
                    "que usted reporto, acerca de la cancion " + nombre + ". Muchas gracias por su colaboracion."));

        } else if(plagio == false) {
            reporte.getUsuario().setFechaBloqueo(getFechaActual().plusMonths(1));
            reporte.getCancionReportada().setEstado(Cancion.Estado.VALIDADO);

            reporte.getCancionReportada().getAutor().addNotificacion(new Notificacion("Nuestro equipo juridico ha determinado" +
                    " que su cancion " + nombre + " no es un plagio. Ya vuelve a estar disponible para su reproduccion."));
            for(Reproducible r : reporte.getUsuario().getReproducibles()) {
                if(r.esCancion()) {
                    r.setEstado(Cancion.Estado.BLOQUEADO);
                }
            }
        }

        reportes.remove(reporte);
        return true;
    }


    public void stopReproductor() {
        if(reproductor != null) {
            reproductor.stop();
        }
    }

    /**
     * Reproduce una cancion o canciones. Se incrementan el numero de reproducciones del usuario no premium y el repToPremium del autor de la cancion.
     * @param cancion Cancion o canciones a reproducir
     * @throws FileNotFoundException
     * @throws Mp3PlayerException
     */
    public void reproducir(Cancion ... cancion) throws FileNotFoundException, Mp3PlayerException, NoRepLeft {
        if(reproductor != null) {
            reproductor.stop();
        }

        Integer rep_left;

        reproductor = new Mp3Player();
        ArrayList<String> mp3 = new ArrayList<>();
        Integer i;

        for(Cancion c : cancion) {
            mp3.add(c.getFichero());
        }

        if(usuarioLogueado == null) {
            reproductor.add(mp3.get(0));
        } else if (usuarioLogueado.esPremium() || usuarioLogueado.esAdmin()) {
            for(i = 0; i < mp3.size(); i++) {
                reproductor.add(mp3.get(i));
            }

            for(Cancion c : cancion) {
                if(c.getAutor() != usuarioLogueado) {
                    c.getAutor().setRepToPremium(c.getAutor().getRepToPremium() + 1);
                }
            }

        } else {
            rep_left = maxRepNoPremium - usuarioLogueado.getReproducciones();

            if(rep_left < 1) {
                throw new NoRepLeft();
            }

            for(i = 0; i < mp3.size() && i < rep_left; i++) {
                reproductor.add(mp3.get(i));
                usuarioLogueado.setReproducciones(usuarioLogueado.getReproducciones() + 1);
            }

            i = 0;
            for(Cancion c : cancion) {
                if(i == rep_left) {
                    break;
                }
                if(c.getAutor() != usuarioLogueado) {
                    c.getAutor().setRepToPremium(c.getAutor().getRepToPremium() + 1);
                }

                i++;
            }
        }

        reproductor.play();
    }

    /**
     * Reproduce un album.
     * @param album Album a reproducir.
     * @throws FileNotFoundException
     * @throws Mp3PlayerException
     */
    public void reproducir(Album album) throws FileNotFoundException, Mp3PlayerException {
        if(reproductor != null) {
            reproductor.stop();
        }

        Integer rep_left;

        reproductor = new Mp3Player();
        ArrayList<String> mp3 = new ArrayList<>();
        Integer i;

        for(Cancion c : album.getCanciones()) {
            mp3.add(c.getFichero());
        }

        for(i = 0; i < mp3.size(); i++) {
            reproductor.add(mp3.get(i));
        }

        if(usuarioLogueado == null) {
            reproductor.add(mp3.get(0));
        } else if (usuarioLogueado.esPremium() || usuarioLogueado.esAdmin()) {
            for(i = 0; i < mp3.size(); i++) {
                reproductor.add(mp3.get(i));
            }

            for(Cancion c : album.getCanciones()) {
                if(c.getAutor() != usuarioLogueado) {
                    c.getAutor().setRepToPremium(c.getAutor().getRepToPremium() + 1);
                }

            }

        } else {
            rep_left = maxRepNoPremium - usuarioLogueado.getReproducciones();

            for(i = 0; i < mp3.size() && i < rep_left; i++) {
                reproductor.add(mp3.get(i));
                usuarioLogueado.setReproducciones(usuarioLogueado.getReproducciones() + 1);
            }

            i = 0;
            for(Cancion c : album.getCanciones()) {
                if(i == rep_left) {
                    break;
                }
                if(c.getAutor() != usuarioLogueado) {
                    c.getAutor().setRepToPremium(c.getAutor().getRepToPremium() + 1);
                }

                i++;
            }
        }

        reproductor.play();

    }

    /**
     * Reproduce una lista
     * @param lista Lista a reproducir
     * @throws FileNotFoundException
     * @throws Mp3PlayerException
     */
    public void reproducir(Lista lista) throws FileNotFoundException, Mp3PlayerException {
        if(reproductor != null) {
            reproductor.stop();
        }

        Integer rep_left;

        reproductor = new Mp3Player();
        ArrayList<String> mp3 = new ArrayList<>();
        ArrayList<Cancion> canciones = new ArrayList<>();
        Integer i;

        extraerCanciones(lista, canciones);

        for(Cancion c : canciones) {
            mp3.add(c.getFichero());
        }

        if(usuarioLogueado == null) {
            reproductor.add(mp3.get(0));
        } else if (usuarioLogueado.esPremium() || usuarioLogueado.esAdmin()) {
            for(i = 0; i < mp3.size(); i++) {
                reproductor.add(mp3.get(i));
            }

            for(Cancion c : canciones) {
                if(c.getAutor() != usuarioLogueado) {
                    c.getAutor().setRepToPremium(c.getAutor().getRepToPremium() + 1);
                }

            }

        } else {
            rep_left = maxRepNoPremium - usuarioLogueado.getReproducciones();

            for(i = 0; i < mp3.size() && i < rep_left; i++) {
                reproductor.add(mp3.get(i));
                usuarioLogueado.setReproducciones(usuarioLogueado.getReproducciones() + 1);
            }

            i = 0;
            for(Cancion c : canciones) {
                if(i == rep_left) {
                    break;
                }
                if(c.getAutor() != usuarioLogueado) {
                    c.getAutor().setRepToPremium(c.getAutor().getRepToPremium() + 1);
                }

                i++;
            }
        }

        reproductor.play();

    }

    /**
     *
     * @param elemento
     * @param canciones
     */
    public void extraerCanciones(Reproducible elemento, ArrayList<Cancion> canciones) {
        if(elemento.esCancion()) {
            canciones.add(((Cancion)elemento));
        } else if(elemento.esAlbum()) {
            for(Cancion c : ((Album)elemento).getCanciones()) {
                canciones.add(c);
            }
        } else if(elemento.esLista()) {
            for(Reproducible r : ((Lista)elemento).getElementos()) {
                extraerCanciones(r, canciones);
            }
        }
    }

    /**
     * Devuelve true si el usuario puede escuchar el reproducible. Tiene en cuenta el estado y contenido de la cancion,
     * si el usuario es myor de edad o no...
     * En el caso del album si hay una cancion bloqueada no se puede reproducir
     * @param reproducible
     * @return
     */
    public boolean sePuedeEscuchar(Reproducible reproducible) {
        if(reproducible == null) {
            return false;
        }

        if(reproducible.esCancion()){

            if(reproducible.getEstado() == Cancion.Estado.VALIDADO) {
                if(reproducible.getContenido() == Cancion.Contenido.AUTORIZADO) {
                    return true;
                } else if(reproducible.getContenido() == Cancion.Contenido.EXPLICITO) {
                    if(usuarioLogueado != null) {

                        if(usuarioLogueado.esApto() == true) {
                            return true;
                        }
                    }
                }
            }

        } else if(reproducible.esAlbum()) {

            for(Cancion c : reproducible.getCanciones()) {
                if(c.getEstado() != Cancion.Estado.VALIDADO) {
                    return false;
                }
            }

            return true;

        } else if(reproducible.esLista()) {

            return true;

        }
        return false;
    }

    /**
     * Permite guardar los datos de la aplicacion en un fichero
     */
    public void guardarDatos() {
        reproductor = null;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("ficheroobj"));
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permite cargar los datos de la aplicacion desde un fichero
     * @return  Devuelve la aplicacion
     */
    public static Aplicacion cargarDatos() {
        Aplicacion app = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ficheroobj"));
            app = (Aplicacion) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return app;
    }

    /**
     * Metodo para que los usuarios registrados puedan hacer el pago para convertise en premium
     * @param tarjeta numero de la tarjeta(16 digitos)
     * @return true si el pago se ha aceptado, false si no
     * @throws InvalidCardNumberException
     * @throws FailedInternetConnectionException
     * @throws OrderRejectedException
     */
    public boolean pagarPremium(String tarjeta) throws InvalidCardNumberException, FailedInternetConnectionException, OrderRejectedException {
        if(usuarioLogueado == null) {
            return false;
        }

        if(usuarioLogueado.esPremium()) {
            return false;
        }

        if (sistemaPago.isValidCardNumber(tarjeta)) {
            try {
                sistemaPago.charge(tarjeta, "Suscripción a premium.",  -10, true);
                usuarioLogueado.setFechaPremium(getFechaActual().plusDays(30));
                return true;
            } catch (InvalidCardNumberException c) {
                c.printStackTrace();
            } catch (FailedInternetConnectionException i) {
                i.printStackTrace();
            } catch (OrderRejectedException o) {
                o.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Devuelve la fecha de la aplicacion
     * @return Fecha
     */
    public static LocalDate getFechaActual() {
        return FechaSimulada.getHoy();
    }

    @Override
    public String toString() {
        return "Aplicacion{" +
                "repToPremium=" + repToPremium +
                ", maxRepNoPremium=" + maxRepNoPremium +
                ", usuarios=" + usuarios +
                ", reproducibles=" + reproducibles +
                ", reportes=" + reportes +
                ", usuarioLogueado=" + usuarioLogueado +
                ", reproductor=" + reproductor +
                ", sistemaPago=" + sistemaPago +
                ", fechaCambio=" + fechaCambio +
                '}';
    }


}