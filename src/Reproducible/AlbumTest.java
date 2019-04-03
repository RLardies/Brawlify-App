package Reproducible;

import Usuario.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static Aplicacion.Aplicacion.getFechaActual;
import static org.junit.Assert.*;

public class AlbumTest {

    private Cancion c,c2;
    private Lista l;
    private Album a;
    private ArrayList<Cancion> canciones = new ArrayList<Cancion>();

    @Before
    public void iniciarAlbum(){
        Usuario u = new Usuario("rodri88", "12345", LocalDate.of(1999, 6, 13), "Rodrigo");
        c = new Cancion("Prueba", 1000, u,"hive.mp3");
        c2 = new Cancion("prueba2",1000,u,"chicle.mp3");
        l = new Lista("Chill",u);
        canciones.add(c);
        a = new Album("prueba",u,getFechaActual().getYear(),canciones);
    }

    @Test
    public void esContenedor() {
        assertTrue(a.esContenedor());
        assertFalse(c.esContenedor());
        assertTrue(l.esContenedor());
    }

    @Test
    public void containsCancion() {
        assertFalse(a.containsCancion(c2));
        assertTrue(a.containsCancion(c));
    }

    @Test
    public void esAlbum() {
        assertFalse(l.esAlbum());
        assertFalse(c.esAlbum());
        assertTrue(a.esAlbum());
    }

}