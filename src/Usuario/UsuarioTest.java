package Usuario;

import Notificacion.Notificacion;
import Reproducible.Cancion;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDate;

import static Aplicacion.Aplicacion.getFechaActual;
import static org.junit.Assert.*;

public class UsuarioTest {

    private Usuario u;

    @Before
    public void iniciarUsuario(){
        u = new Usuario("rodri88", "12345",LocalDate.of(1997, 6, 13), "Rodrigo");
    }

    @Test
    public void getUsername() {
        String name = u.getUsername();
        assertEquals("rodri88", name);
    }

    @Test
    public void suscribirseAAutor() {
        Usuario u2 = new Usuario("calongenio","1234",LocalDate.of(1999, 6, 13),"Daniel");
        u.suscribirseAAutor(u2);
        assertTrue(u.getUsuariosSeguidos().contains(u2));
    }

    @Test
    public void removeAutor() {
        Usuario u2 = new Usuario("calongenio","1234",LocalDate.of(1999, 6, 13),"Daniel");
        u.suscribirseAAutor(u2);
        u.removeAutor(u2);
        assertFalse(u.getUsuariosSeguidos().contains(u2));
    }


    @Test
    public void addReproducible() {
        Cancion c = new Cancion("prueba",80,u,"prueba.mp3");
        u.addReproducible(c);
        assertTrue(u.getReproducibles().contains(c));
    }

    @Test
    public void removeReproducible() {
        Cancion c = new Cancion("prueba",80,u,"prueba.mp3");
        u.addReproducible(c);
        u.removeReproducible(c);
        assertFalse(u.getReproducibles().contains(c));
    }

    @Test
    public void addNotificacion() {
        Notificacion n = new Notificacion("Notificacion de prueba");
        u.addNotificacion(n);
        assertTrue(u.getNotificaciones().contains(n));
    }

    @org.junit.Test
    public void removeNotificacion() {
        Notificacion n = new Notificacion("Notificacion de prueba");
        u.addNotificacion(n);
        u.removeNotificacion(n);
        assertFalse(u.getNotificaciones().contains(n));
    }

    @Test
    public void esApto() {
        Usuario u2 = new Usuario("calongenio","1234",LocalDate.of(2011, 6, 13),"Daniel");
        assertFalse(u2.esApto());
        assertTrue(u.esApto());
    }

    @Test
    public void esBloqueado() {
        Usuario u2 = new Usuario("calongenio","1234",LocalDate.of(1999, 6, 13),"Daniel");
        u2.setFechaBloqueo(getFechaActual().plusDays(30)); //SUmarle un mes a la fecha actual
        assertFalse(u.esBloqueado());
        assertTrue(u2.esBloqueado());
    }

    @Test
    public void esPremium() {
        Usuario u2 = new Usuario("calongenio","1234",LocalDate.of(1999, 6, 13),"Daniel");
        u2.setFechaPremium(getFechaActual().plusDays(30));// Sumarle un mes a la fecha actual
        assertTrue(u2.esPremium());
        assertFalse(u.esPremium());
    }
}
