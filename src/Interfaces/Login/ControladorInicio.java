package Interfaces.Login;
/**
 * Controlador del panel de inicio
 * @author Jesus Blanco, Rodrigo Lardies, Daniel Calonge
 */
import Aplicacion.Aplicacion;
import Exceptions.ContrasenaIncorrecta;
import Exceptions.UsuarioBloqueado;
import Exceptions.UsuarioNoExistente;
import Exceptions.UsuarioYaExistente;
import Interfaces.GuiBrawlify;
import Interfaces.MenuPrincipal.PanelMenuPrincipal;
import Interfaces.MenuPrincipal.Reportes;
import Notificacion.Notificacion;
import Reporte.Reporte;
import Reproducible.Reproducible;
import Reproducible.*;
import Reproducible.Lista;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorInicio implements ActionListener {

    private GuiBrawlify ventana;
    private Aplicacion app;
    private PanelInicio panelInicio;
    private PanelMenuPrincipal panelMenuPrincipal;

    public ControladorInicio(GuiBrawlify ventana, Aplicacion app, PanelInicio panelInicio, PanelMenuPrincipal panelMenuPrincipal) {
        this.ventana = ventana;
        this.app = app;
        this.panelInicio = panelInicio;
        this.panelMenuPrincipal = panelMenuPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getActionCommand().equals("Loguearse")) {    //LOGIN
            Login login = panelInicio.getLogin();
            try {
                if(login.getUserText().getText().isEmpty() || login.getPasswordText().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(panelInicio, "Rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                app.login(login.getUserText().getText(), login.getPasswordText().getText());
            } catch (UsuarioNoExistente e) {
                JOptionPane.showMessageDialog(panelInicio, "Usuario no existente", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (ContrasenaIncorrecta e) {
                JOptionPane.showMessageDialog(panelInicio, "El nombre de usuario o la contrase??a son incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (UsuarioBloqueado e) {
                JOptionPane.showMessageDialog(panelInicio, "Tu cuenta ha sido bloqueada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(app.getUsuarioLogueado().esAdmin()) {
                panelMenuPrincipal.esconderPagar();
                panelMenuPrincipal.getTabbedPane().addTab("Buscar Canciones", panelMenuPrincipal.getBuscarCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Canciones", panelMenuPrincipal.getMisCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Listas", panelMenuPrincipal.getMisListas());
                panelMenuPrincipal.getTabbedPane().addTab("Mis ??lbums",panelMenuPrincipal.getMisAlbums());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Notificaciones", panelMenuPrincipal.getMisNotificaciones());
                panelMenuPrincipal.getTabbedPane().addTab("Subir Cancion", panelMenuPrincipal.getSubirCancion());
                panelMenuPrincipal.getTabbedPane().addTab("Reportes", panelMenuPrincipal.getReportes());
                panelMenuPrincipal.getTabbedPane().addTab("Ajustes", panelMenuPrincipal.getAjustes());
                panelMenuPrincipal.getTabbedPane().addTab("Validaciones", panelMenuPrincipal.getValidaciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Suscripciones", panelMenuPrincipal.getMisSuscripciones());

            } else if(app.getUsuarioLogueado().esPremium()) {
                panelMenuPrincipal.esconderPagar();
                panelMenuPrincipal.getTabbedPane().addTab("Buscar Canciones", panelMenuPrincipal.getBuscarCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Canciones", panelMenuPrincipal.getMisCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Listas", panelMenuPrincipal.getMisListas());
                panelMenuPrincipal.getTabbedPane().addTab("Mis ??lbums",panelMenuPrincipal.getMisAlbums());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Notificaciones", panelMenuPrincipal.getMisNotificaciones());
                panelMenuPrincipal.getTabbedPane().addTab("Subir Cancion", panelMenuPrincipal.getSubirCancion());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Suscripciones", panelMenuPrincipal.getMisSuscripciones());
            } else {
                panelMenuPrincipal.mostrarPagar();
                panelMenuPrincipal.getTabbedPane().addTab("Buscar Canciones", panelMenuPrincipal.getBuscarCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Canciones", panelMenuPrincipal.getMisCanciones());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Listas", panelMenuPrincipal.getMisListas());
                panelMenuPrincipal.getTabbedPane().addTab("Mis ??lbums",panelMenuPrincipal.getMisAlbums());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Notificaciones", panelMenuPrincipal.getMisNotificaciones());
                panelMenuPrincipal.getTabbedPane().addTab("Subir Cancion", panelMenuPrincipal.getSubirCancion());
                panelMenuPrincipal.getTabbedPane().addTab("Mis Suscripciones", panelMenuPrincipal.getMisSuscripciones());
            }

            panelMenuPrincipal.getInfoUser().getNombre().setText(app.getUsuarioLogueado().getNombre());
            panelMenuPrincipal.getInfoUser().getUsername().setText(app.getUsuarioLogueado().getUsername());
            if(app.getUsuarioLogueado().esPremium() || app.getUsuarioLogueado().esAdmin()) {
                panelMenuPrincipal.getInfoUser().getPremium().setText("Servicio Premium: Activado");
                panelMenuPrincipal.getInfoUser().getReproduccionesRestantes().setText("Reproducciones Restantes: Ilimitadas");
            } else {
                panelMenuPrincipal.getInfoUser().getPremium().setText("Servicio Premium: Desactivado");
                panelMenuPrincipal.getInfoUser().getReproduccionesRestantes().setText("Reproducciones Restantes: " + (app.getMaxRepNoPremium() - app.getUsuarioLogueado().getReproducciones()));
            }


            panelMenuPrincipal.getTabbedPane().setSelectedIndex(0);
            ventana.mostrarPanel(GuiBrawlify.PANEL_PRINCIPAL);
            if(app.getUsuarioLogueado().getNotificaciones().size() > 0) {
                panelMenuPrincipal.getTabbedPane().setSelectedIndex(3);
                for(Notificacion n : app.getUsuarioLogueado().getNotificaciones()) {
                    panelMenuPrincipal.getMisNotificaciones().getModeloDatos().addRow(new Object[]{n.toString()});
                }
                JOptionPane.showMessageDialog(ventana, "Tiene nuevas notificaciones. Desapareceran cuando cierre sesion", "Notificaciones", JOptionPane.INFORMATION_MESSAGE);
            }

            if(app.getUsuarioLogueado().getReproducibles().size() > 0){

                panelMenuPrincipal.getMisCanciones().limpiarTabla();
                panelMenuPrincipal.getMisAlbums().limpiarTabla();

                ArrayList<Cancion> canciones = new ArrayList<Cancion>();
                ArrayList<Album> albums = new ArrayList<Album>();

                for(Reproducible r : app.getUsuarioLogueado().getReproducibles()){
                    if(r.esCancion() && r.getEstado() != Cancion.Estado.BORRADO){
                        canciones.add((Cancion)r);
                        panelMenuPrincipal.getMisCanciones().getModeloDatos().addRow(new Object[]{r.getTitulo(), r.getEstado(), r.getDuracion()});
                    }else if(r.esAlbum()){
                        albums.add((Album)r);
                        panelMenuPrincipal.getMisAlbums().getModeloDatos().addRow(new Object[]{r.getTitulo(),r.getNumeroCanciones(),((Album) r).getAnioPublic()});
                    }
                }

                Cancion[] resultados = new Cancion[canciones.size()];
                Album[] a = new Album[albums.size()];
                int i;
                for(i = 0; i < canciones.size(); i++){
                    resultados[i] = canciones.get(i);
                }
                for(i=0; i < albums.size(); i++){
                    a[i] = albums.get(i);
                }

                panelMenuPrincipal.getMisCanciones().guardarResultados(resultados);
                panelMenuPrincipal.getMisAlbums().guardarResultados(a);
            }

            if(app.getUsuarioLogueado().getUsuariosSeguidos().size() > 0){

                panelMenuPrincipal.getMisSuscripciones().limpiarTabla();

                ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

                for(Usuario u : app.getUsuarioLogueado().getUsuariosSeguidos()){
                    usuarios.add(u);
                    panelMenuPrincipal.getMisSuscripciones().getModeloDatos().addRow(new Object[]{u.getUsername()});

                }

                Usuario[] resultados = new Usuario[usuarios.size()];
                int i;
                for(i = 0; i < usuarios.size(); i++){
                    resultados[i] = usuarios.get(i);
                }

                panelMenuPrincipal.getMisSuscripciones().guardarResultados(resultados);
            }

            if(app.getUsuarioLogueado().esPremium()){
                panelMenuPrincipal.getMisListas().limpiarTabla();
                panelMenuPrincipal.getBuscarCanciones().limpiarListas();
                panelMenuPrincipal.getMisAlbums().limpiarListas();

                ArrayList<Lista> listas = new ArrayList<Lista>();

                for(Reproducible r : app.getUsuarioLogueado().getReproducibles()){
                    if(r.esLista() && r.getEstado() != Cancion.Estado.BLOQUEADO){
                        listas.add((Lista) r);
                        panelMenuPrincipal.getBuscarCanciones().getModeloListas().addRow(new Object[]{r.getTitulo()});
                        panelMenuPrincipal.getMisListas().getModeloDatos().addRow(new Object[]{r.getTitulo(),r.getNumeroCanciones(),r.getDuracion()});
                        panelMenuPrincipal.getMisAlbums().getModeloListas().addRow(new Object[]{r.getTitulo()});
                    }
                }

                Lista[] l = new Lista[listas.size()];
                int j;
                for(j=0; j < listas.size(); j++){
                    l[j] = listas.get(j);
                }

                panelMenuPrincipal.getMisListas().guardarResultados(l);
                panelMenuPrincipal.getBuscarCanciones().guardarListas(l);
                panelMenuPrincipal.getMisAlbums().guardarListas(l);

            }

            if(app.getUsuarioLogueado().esAdmin()){
                panelMenuPrincipal.getValidaciones().limpiarTabla();

                ArrayList<Cancion> canciones = app.mostrarCancionesAValidar();

                for(Cancion c : canciones){
                    panelMenuPrincipal.getValidaciones().getModeloDatos().addRow(new Object[]{c.getTitulo(),c.getAutor()});

                }

                Cancion[] l = new Cancion[canciones.size()];
                int j;
                for(j=0; j < canciones.size(); j++){
                    l[j] = canciones.get(j);
                }
                panelMenuPrincipal.getValidaciones().guardarResultados(l);



                panelMenuPrincipal.getReportes().limpiarTabla();
                ArrayList<Reporte> reportes = app.mostrarReportes();

                for (Reporte r : reportes){
                    panelMenuPrincipal.getReportes().getModeloDatos().addRow(new Object[]{r.getCancionReportada().getTitulo(), r.getUsuario().getUsername(), r.getComentario()});
                }

                Reporte[] r = new Reporte[reportes.size()];
                int k;
                for(k=0; k < reportes.size(); k++){
                    r[k] = reportes.get(k);
                }
                panelMenuPrincipal.getReportes().guardarResultados(r);

                panelMenuPrincipal.getAjustes().getT1().setText("Reproducciones mensuales para obtener el premium: " + app.getRepToPremium());
                panelMenuPrincipal.getAjustes().getT2().setText("Reproducciones mensuales maximas para usuarios no premium: " + app.getMaxRepNoPremium());


            }


        } else if(actionEvent.getActionCommand().equals("ContinuarSinRegistrarse")) {

            ventana.mostrarPanel(GuiBrawlify.PANEL_PRINCIPAL);
            panelMenuPrincipal.esconderPagar();

            panelMenuPrincipal.getTabbedPane().addTab("Buscar Canciones", panelMenuPrincipal.getBuscarCanciones());

        }else if(actionEvent.getActionCommand().equals("Registrarse")){
            Registro registro = panelInicio.getRegistro();
            try{
                //Comprobar que los campos no sean null
                //Comprobar que la fecha este en formato correcto (Da error si no: java.time.format.DateTimeParseException)
                //Si se ha registrado correcctamente poner el mensaje, pero no loguear directamente

                if(registro.getUserText().getText().isEmpty() || registro.getPasswordText().getText().isEmpty() || registro.getFechaText().getText().isEmpty() || registro.getNombretext().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(panelInicio,"Debe rellenar todos los campos","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                app.registrarUsuario(registro.getUserText().getText(),registro.getPasswordText().getText(), LocalDate.parse(registro.getFechaText().getText()) ,registro.getNombretext().getText());
            } catch (UsuarioYaExistente e) {
                JOptionPane.showMessageDialog(panelInicio,"Este usuario ya existe","Error",JOptionPane.ERROR_MESSAGE);
                return;
            } catch (DateTimeException e) {
                JOptionPane.showMessageDialog(panelInicio,"Formato de fecha incorrecto","Error",JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(panelInicio,"Usuario Registrado Correctamente","Ok",JOptionPane.INFORMATION_MESSAGE);
            panelInicio.setSelectedIndex(2);
            registro.getUserText().setText("");
            registro.getNombretext().setText("");
            registro.getFechaText().setText("");
            registro.getPasswordText().setText("");

        }

    }
}
