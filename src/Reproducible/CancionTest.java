package Reproducible;

import Usuario.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static Aplicacion.Aplicacion.getFechaActual;
import static org.junit.Assert.*;

public class CancionTest {

    private Cancion c;
    private Lista l;
    private Album a;
    private ArrayList<Cancion> canciones = new ArrayList<Cancion>();

    @Before
    public void iniciarCancion(){
        Usuario u = new Usuario("rodri88", "12345", LocalDate.of(1999, 6, 13), "Rodrigo");
        c = new Cancion("Prueba", 1000, u,"hive.mp3");
        l = new Lista("Chill",u);
        canciones.add(c);
        a = new Album("prueba",u,getFechaActual().getYear(),canciones);
    }

    @Test
    public void esCancion() {
        assertFalse(l.esCancion());
        assertFalse(a.esCancion());
        assertTrue(c.esCancion());
    }

    @Test
    public void containsReproducible() {
        assertFalse(c.containsReproducible(l));
        assertFalse(c.containsReproducible(a));
        assertFalse(c.containsReproducible(c));
    }
}