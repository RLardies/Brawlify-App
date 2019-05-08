package Aplicacion;

import Exceptions.*;
import Reproducible.Album;
import Reproducible.Cancion;
import Reproducible.Lista;
import Reproducible.Reproducible;
import Usuario.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static Aplicacion.Aplicacion.getFechaActual;
import static org.junit.Assert.*;

public class AplicacionTest {

    private Aplicacion app;
    private Cancion c,c2,c3,c4,c5,c6;
    private Lista l,l2,l3;
    private Album a,a2;
    private Usuario u,u2,u3;
    private ArrayList<Cancion> canciones = new ArrayList<Cancion>();
    private ArrayList<Cancion> canciones2 = new ArrayList<Cancion>();

    @Before
    public void iniciarAplicacion() throws UsuarioNoExistente, ContrasenaIncorrecta, UsuarioBloqueado {
        app = new Aplicacion();
        u = new Usuario("rodri88", "12345", LocalDate.of(1997, 6, 13), "Rodrigo");
        u2 = new Usuario("calongenio", "12345", LocalDate.of(2011, 6, 13), "Rodrigo");
        c = new Cancion("Prueba", 1000, u,"hive.mp3");
        c2 = new Cancion("prueba2",1000,u,"chicle.mp3");
        c3 = new Cancion("prueba3",1000,u,"np.mp3");
        c4 = new Cancion("prueba4",1000,u,"nop.mp3");
        c5 = new Cancion("prueba5",1000,u,"nwop.mp3");
        c6 = new Cancion("prueba6",1000,u,"nwoasdp.mp3");

        try {
            app.registrarUsuario("fidel", "12345", LocalDate.of(1997, 6, 13), "Fidel");
        }catch (UsuarioYaExistente e){
            System.out.println(e);
        }

        app.login("fidel","12345");
        l = new Lista("Chill",u);
        l2 = new Lista("MenosChill",u);
        l3 = new Lista("Fiesta",u);

    }


    @Test
    public void registrarUsuario() {

        try {
            app.registrarUsuario("Ciórraga", "12345", LocalDate.of(2011, 4, 24), "Rodrigo");
        }catch (UsuarioYaExistente e){
            System.out.println(e);
        }

        ArrayList<Usuario> usuarios = app.getUsuarios();
        for(Usuario u: usuarios){
            if(u.getUsername().equals("Ciórraga")){
                assertTrue(u.getContrasena().equals("12345"));
                assertTrue(u.getNombre().equals("Rodrigo"));
                assertTrue(u.getFechaNac().equals(LocalDate.of(2011,4,24)));
            }
        }
    }

    @Test
    public void login() throws UsuarioNoExistente, ContrasenaIncorrecta, UsuarioBloqueado{
        app.cerrarSesion();
        assertTrue(app.getUsuarioLogueado() == null);
        ArrayList<Usuario> usuarios = app.getUsuarios();
        app.login("fidel","12345");

        for(Usuario u: usuarios) {
            if (u.getUsername().equals("fidel") && u.getContrasena().equals("12345")) {
                assertTrue(u.equals(app.getUsuarioLogueado()));
            }
        }

    }

    @Test
    public void cerrarSesion()  throws UsuarioNoExistente, ContrasenaIncorrecta, UsuarioBloqueado{
        app.login("fidel","12345");
        app.cerrarSesion();
        assertTrue(app.getUsuarioLogueado() == null);
    }



    @Test
    public void subirCancion() throws CancionInvalida, IOException {
        app.subirCancion("Prueba2","hive.mp3");
        for(Reproducible r : app.getReproducibles()){
            if(r.esCancion() && r.getAutor().equals(app.getUsuarioLogueado()) && r.getTitulo().equals("Prueba2")){
                assertTrue(r.getEstado().equals(Cancion.Estado.NUEVO));
            }
        }
    }

    @Test
    public void modificarCancion() throws CancionNoExistente, UsuarioNoExistente, ContrasenaIncorrecta, UsuarioBloqueado, CancionInvalida, IOException {
        app.login("fidel","12345");
        app.subirCancion("Prueba2","hive.mp3");
        for(Reproducible r : app.getReproducibles()){
            if(r.esCancion() && r.getAutor().equals(app.getUsuarioLogueado()) && r.getTitulo().equals("Prueba2")){
                assertTrue(r.getEstado().equals(Cancion.Estado.NUEVO));

                assertTrue(app.modificarCancion((Cancion)r,"Prueba3"));

                System.out.println(r);
                assertTrue(r.getTitulo().equals("Prueba3"));
                assertFalse(r.getTitulo().equals("Prueba2"));

                app.modificarCancion((Cancion)r,"Prueba2","chicle.mp3");

                assertTrue(r.getTitulo().equals("Prueba2"));
                assertFalse(r.getTitulo().equals("Prueba3"));

                assertFalse(((Cancion) r).getFichero().equals("hive.mp3"));
                assertTrue(((Cancion) r).getFichero().equals("chicle.mp3"));

            }
        }

    }

    @Test
    public void borrarCancion() throws CancionNoExistente, CancionInvalida, IOException {
        app.subirCancion("Prueba2", "hive.mp3");
        for (Reproducible r : app.getReproducibles()) {
            if (r.esCancion() && r.getAutor().equals(app.getUsuarioLogueado()) && r.getTitulo().equals("Prueba2")) {
                assertTrue(r.getEstado().equals(Cancion.Estado.NUEVO));

                app.borrarCancion((Cancion) r);
                assertTrue(r.getEstado().equals(Cancion.Estado.BORRADO));

            }
        }
    }

    @Test
    public void validarCancion() throws CancionNoExistente, CancionInvalida, IOException {
        app.subirCancion("Prueba2", "hive.mp3");
        for (Reproducible r : app.getReproducibles()) {
            if (r.esCancion() && r.getAutor().equals(app.getUsuarioLogueado()) && r.getTitulo().equals("Prueba2")) {
                assertTrue(r.getEstado().equals(Cancion.Estado.NUEVO));

                app.validarCancion((Cancion)r, Cancion.Contenido.AUTORIZADO);
                assertTrue(r.getEstado().equals(Cancion.Estado.VALIDADO));
                app.borrarCancion((Cancion) r);
            }
        }
        app.subirCancion("Prueba2", "hive.mp3");
        for (Reproducible r : app.getReproducibles()) {
            if (r.esCancion() && r.getAutor().equals(app.getUsuarioLogueado()) && r.getTitulo().equals("Prueba2")) {
                assertTrue(r.getEstado().equals(Cancion.Estado.NUEVO));

                app.validarCancion((Cancion)r, Cancion.Contenido.EXPLICITO);
                assertTrue(r.getEstado().equals(Cancion.Estado.VALIDADO));
                app.borrarCancion((Cancion) r);
            }
        }

        app.subirCancion("Prueba2", "hive.mp3");
        for (Reproducible r : app.getReproducibles()) {
            if (r.esCancion() && r.getAutor().equals(app.getUsuarioLogueado()) && r.getTitulo().equals("Prueba2")) {
                assertTrue(r.getEstado().equals(Cancion.Estado.NUEVO));

                app.validarCancion((Cancion)r, Cancion.Contenido.NOVALIDO);
                assertTrue(r.getEstado().equals(Cancion.Estado.BLOQUEADO));
                app.borrarCancion((Cancion) r);
            }
        }
    }


}