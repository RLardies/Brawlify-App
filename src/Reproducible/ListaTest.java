package Reproducible;

import Usuario.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static Aplicacion.Aplicacion.getFechaActual;
import static org.junit.Assert.*;

public class ListaTest {

    private Cancion c,c2,c3,c4,c5,c6;
    private Lista l,l2,l3;
    private Album a,a2;
    private ArrayList<Cancion> canciones = new ArrayList<Cancion>();
    private ArrayList<Cancion> canciones2 = new ArrayList<Cancion>();

    @Before
    public void iniciarlista(){
        Usuario u = new Usuario("rodri88", "12345", LocalDate.of(1999, 6, 13), "Rodrigo");
        c = new Cancion("Prueba", 1000, u,"hive.mp3");
        c2 = new Cancion("prueba2",1000,u,"chicle.mp3");
        c3 = new Cancion("prueba3",1000,u,"np.mp3");
        c4 = new Cancion("prueba4",1000,u,"nop.mp3");
        c5 = new Cancion("prueba5",1000,u,"nwop.mp3");
        c6 = new Cancion("prueba6",1000,u,"nwoasdp.mp3");
        l = new Lista("Chill",u);
        l2 = new Lista("MenosChill",u);
        l3 = new Lista("Fiesta",u);
        canciones.add(c2);
        a = new Album("prueba",u,getFechaActual().getYear(),canciones);
        canciones2.add(c4);
        a2 = new Album("prueba2",u,getFechaActual().getYear(),canciones2);
        l.addReproducible(c);
        l2.addReproducible(c3);
        l.addReproducible(a);
        l3.addReproducible(c5);
        l.addReproducible(l3);
    }

    @Test
    public void removeReproducible() {
        assertTrue(l.getElementos().contains(c));
        l.removeReproducible(c);
        assertFalse(l.getElementos().contains(c));

        assertTrue(l.getElementos().contains(a));
        l.removeReproducible(a);
        assertFalse(l.getElementos().contains(a));

        assertTrue(l.getElementos().contains(l3));
        l.removeReproducible(l3);
        assertFalse(l.getElementos().contains(l3));


    }

    @Test
    public void addReproducible() {
        assertFalse(l.getElementos().contains(c6));
        l.addReproducible(c6);
        assertTrue(l.getElementos().contains(c6));
        assertFalse(l.getElementos().contains(l2));
        l.addReproducible(l2);
        assertTrue(l.getElementos().contains(l2));
        assertFalse(l.getElementos().contains(a2));
        l.addReproducible(a2);
        assertTrue(l.getElementos().contains(a2));

    }

    @Test
    public void containsReproducible() {
        assertFalse(l.containsReproducible(l2));
        assertFalse(l.containsReproducible(a2));
        assertFalse(l.containsReproducible(c4));
        assertFalse(l.containsReproducible(c3));
        assertTrue(l.containsReproducible(c));
        assertTrue(l.containsReproducible(l3));
        assertTrue(l.containsReproducible(a));
        assertTrue(l.containsReproducible(c2));
        assertTrue(l.containsReproducible(c5));
    }

    @Test
    public void containsLista() {
        assertTrue(l.containsLista(l));
        assertFalse(l.containsLista(l2));
        assertTrue(l.containsLista(l3));
    }

    @Test
    public void containsAlbum() {
        assertTrue(l.containsAlbum(a));
        assertFalse(l.containsAlbum(a2));
    }

    @Test
    public void containsCancion() {
        assertTrue(l.containsCancion(c));
        assertFalse(l.containsCancion(c3));
        assertTrue(l.containsCancion(c2));
        assertFalse(l.containsCancion(c4));
        assertTrue(l.containsCancion(c5));
        assertFalse(l.containsCancion(c6));
    }

    @Test
    public void esLista() {
        assertFalse(c.esLista());
        assertTrue(l.esLista());
        assertFalse(a.esLista());
    }

    @Test
    public void esContenedor() {
        assertFalse(c.esContenedor());
        assertTrue(l.esContenedor());
        assertTrue(a.esContenedor());
    }
}