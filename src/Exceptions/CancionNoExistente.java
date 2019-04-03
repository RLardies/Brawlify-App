package Exceptions;

public class CancionNoExistente extends Exception {
    @Override
    public String toString() {
        return "Cancion no existente en la aplicacion";
    }
}
