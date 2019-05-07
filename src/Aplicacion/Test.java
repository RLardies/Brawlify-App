package Aplicacion;

import Exceptions.*;
import Reporte.Reporte;
import Reproducible.Cancion;
import Reproducible.Lista;
import Reproducible.Album;
import Reproducible.Reproducible;
import Usuario.Usuario;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import fechasimulada.FechaSimulada;
import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;

import java.io.CharArrayReader;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Test {

    public static void main(String[] args) throws FileNotFoundException, Mp3PlayerException, InterruptedException, UsuarioNoExistente, ContrasenaIncorrecta , CancionNoExistente, UsuarioBloqueado {
        Aplicacion app = new Aplicacion();

        ArrayList<Reproducible> reproducibles;
        ArrayList<Cancion> canciones;
        ArrayList<Album> albumes;

        ArrayList<Usuario> usuarios = app.getUsuarios();

        Album album;


        Lista l;
        Lista l2;

        try {
            app.registrarUsuario("user1", "holaquetal", LocalDate.of(1950, 1, 1), "User1");
            app.registrarUsuario("user2", "holaquetal", LocalDate.of(1980, 2, 2), "User2");
            app.registrarUsuario("user3", "holaquetal", LocalDate.of(1987, 3, 3), "User3");
            app.registrarUsuario("user4", "holaquetal", LocalDate.of(1990, 4, 4), "User4");
            app.registrarUsuario("user5", "holaquetal", LocalDate.of(2004, 5, 5), "User5");
            app.registrarUsuario("user6", "holaquetal", LocalDate.of(2005, 6, 6), "User6");
        }catch (UsuarioYaExistente e){
            System.out.println(e);
        }

        for(Usuario u : usuarios) {
            System.out.println(u);
        }

        try {
            app.login("user7", "holaquetal");
        } catch (UsuarioNoExistente e) {
            System.out.println("Usuario no existente");
        } catch (ContrasenaIncorrecta e) {
            System.out.println("Contraseña incorrecta");
        }

        try {
            app.login("user6", "adios");
        } catch (UsuarioNoExistente e) {
            System.out.println("Usuario no existente");
        } catch (ContrasenaIncorrecta e) {
            System.out.println("Contraseña incorrecta");
        }

        System.out.println("1---------------------------------");

        app.login("user1", "holaquetal"); //Muestra el array de notificaciones vacio

        System.out.println(app.getUsuarioLogueado());

        System.out.println(app.subirCancion("Chicle","chicle3.mp3")); //true la cancion se ha subido correctamente
        System.out.println(app.subirCancion("Hive","hive.mp3"));    //true la cancion se ha subido correctamente
        System.out.println(app.subirCancion("Invalid","invalid.mp3"));  //false la cancion no se ha subido
        System.out.println(app.subirCancion("Np","np.mp3"));    //true la cancion se ha subido correctamente

        System.out.println(app.buscarCancionPorTitulo("Chicle")); //No encuentra cancion (la cancion no esta validada)

        app.cerrarSesion();

        System.out.println("2---------------------------------");

        app.login("admin","1234");

        app.setMaxRepNoPremium(2);

        app.setRepToPremium(2);


        System.out.println(canciones = app.mostrarCancionesAValidar());

        app.validarCancion(canciones.get(0), Cancion.Contenido.AUTORIZADO);

        System.out.println(app.mostrarCancionesAValidar());

        app.validarCancion(canciones.get(1), Cancion.Contenido.EXPLICITO);

        System.out.println(app.mostrarCancionesAValidar());

        app.validarCancion(canciones.get(2), Cancion.Contenido.NOVALIDO);

        System.out.println(app.mostrarCancionesAValidar());

        app.cerrarSesion();

        System.out.println("3---------------------------------");

        System.out.println(app.buscarCancionPorTitulo("Chicle"));
        System.out.println(app.buscarCancionPorTitulo("Hive"));     //No encuentra la cancion porque los no regitrados no pueden acceder a contenido explicito
        System.out.println(app.buscarCancionPorTitulo("Np"));   //La cancion es no valida

        System.out.println(app.buscarCancionPorAutor("user1"));

        System.out.println("4---------------------------------");



        app.login("user2","holaquetal");

        System.out.println(app.buscarCancionPorAutor("user1")); //El user2 encuentra todas las canciones de user1 porque es mayor de edad

        app.cerrarSesion();



        System.out.println("5---------------------------------");

        app.login("user1","holaquetal");

        System.out.println(canciones = app.mostrarCancionesAModificar());

        app.modificarCancion(canciones.get(0), "Np2");

        System.out.println(app.mostrarCancionesAModificar());

        app.borrarCancion(app.buscarCancionPorTitulo("Hive").get(0));

        System.out.println(app.buscarCancionPorTitulo("Hive")); //No aparece porque acabamos de borrar la cancion

        app.subirCancion("Hive2","hive.mp3");

        app.cerrarSesion();

        System.out.println("6---------------------------------");

        app.login("admin","1234");

        System.out.println(canciones = app.mostrarCancionesAValidar());

        app.validarCancion(canciones.get(0), Cancion.Contenido.NOVALIDO);

        app.validarCancion(canciones.get(1), Cancion.Contenido.AUTORIZADO);

        app.cerrarSesion();

        System.out.println("7---------------------------------");

        app.login("user1","holaquetal");

        System.out.println(canciones = app.mostrarCancionesAModificar());

        app.cerrarSesion();

        System.out.println("8---------------------------------");

        FechaSimulada.avanzar(5);

        app.login("user1","holaquetal");

        System.out.println(canciones = app.mostrarCancionesAModificar()); //Ya no puede modificar la cancion porque han pasado mas de 3 dias

        app.subirCancion("Np4","np.mp3");

        app.cerrarSesion();

        System.out.println("9---------------------------------");

        app.login("user2", "holaquetal");

        app.getUsuarioLogueado().suscribirseAAutor(app.buscarCancionPorTitulo("Hive2").get(0).getAutor());

        app.cerrarSesion();


        System.out.println("10---------------------------------");

        app.login("admin","1234");

        System.out.println(canciones = app.mostrarCancionesAValidar());

        app.validarCancion(canciones.get(0), Cancion.Contenido.AUTORIZADO);

        app.cerrarSesion();


        System.out.println("11---------------------------------");

        app.login("user2", "holaquetal"); //Muestra la notificacion de que el usuario ha subido una cancion

        app.cerrarSesion();


        System.out.println("12---------------------------------");

        app.login("user1","holaquetal");

        canciones = app.buscarCancionPorAutor("user1");

        canciones.remove(canciones.get(0));

        System.out.println(canciones);

        System.out.println(album = app.crearAlbum("ALBUM 1", canciones));

        System.out.println(app.buscarAlbumPorAutor("user1"));

        System.out.println(app.buscarAlbumPorTitulo("ALBUM 1"));




        l = app.crearLista("LISTA 1");
        l2 = app.crearLista("LISTA 2");


        l.addReproducible(app.buscarCancionPorTitulo("Chicle").get(0));

        System.out.println(l);

        l.addReproducible(album);// No añade el album porque se repetiria el contenido

        System.out.println(l);

        l2.addReproducible(l);

        System.out.println(l2);

        app.reproducir(l); //Solo reproduce las 2 primeras canciones porque ha alcanzado el numero maximo de reproducciones en ese mes


        try {
            app.pagarPremium("1234123412341234");
        } catch (InvalidCardNumberException e) {

        } catch (FailedInternetConnectionException e) {

        } catch (OrderRejectedException e) {

        }

        sleep(40000);

        app.reproducir(l);  //Reproduce la lista entera porque ha pagado el premium

        sleep(40000);

        System.out.println(app.getUsuarioLogueado().esPremium());
        FechaSimulada.avanzar(31);
        System.out.println(app.getUsuarioLogueado().esPremium());

        app.cerrarSesion();


        System.out.println("13---------------------------------");

        app.guardarDatos();

        Aplicacion app2 = Aplicacion.cargarDatos();

        System.out.println(app2);


        System.out.println("14---------------------------------");


        app2.login("user3","holaquetal");

        System.out.println(app2.buscarCancionPorTitulo("Chicle"));
        app2.reportarCancion(app2.buscarCancionPorTitulo("Chicle").get(0), "Es un plagio");
        System.out.println(app2.buscarCancionPorTitulo("Chicle"));

        app2.subirCancion("ChicleUser3","chicle3.mp3");


        app2.cerrarSesion();

        System.out.println("15---------------------------------");

        app2.login("admin","1234");

        app2.validarCancion(app2.mostrarCancionesAValidar().get(0), Cancion.Contenido.AUTORIZADO);

        System.out.println(app2.mostrarReportes());

        System.out.println(app2.buscarCancionPorTitulo("Chicle"));

        Cancion canc = app2.mostrarReportes().get(0).getCancionReportada();

        System.out.println(canc.getEstado());

        app2.procesarPlagio(app2.mostrarReportes().get(0),false);

        System.out.println(canc.getEstado());

        app2.cerrarSesion();

        System.out.println("16---------------------------------");

        app2.login("user3","holaquetal"); //No hace login, el usuario esta bloqueado

        app2.cerrarSesion();

        System.out.println("17---------------------------------");

        app2.login("user1","holaquetal");

        System.out.println(app2.buscarCancionPorTitulo("Chicle"));

        app2.cerrarSesion();

        System.out.println("18---------------------------------");

        FechaSimulada.avanzar(35);

        app2.login("user1","holaquetal");

        System.out.println(app2.buscarCancionPorTitulo("ChicleUser3"));

        app2.reproducir(app2.buscarCancionPorTitulo("ChicleUser3").get(0), app2.buscarCancionPorTitulo("ChicleUser3").get(0));

        sleep(50000);

        app2.cerrarSesion();

        System.out.println("19---------------------------------");

        FechaSimulada.avanzar(35);

        app2.login("user3","holaquetal");

        System.out.println(app2.getUsuarioLogueado().esPremium()); //Deberia ser premium porque user1 ha reproducido sus canciones y ha superado el numero minimo para pasarse a premium

        return;
    }
}
