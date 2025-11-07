package es.cursojava.ini.excepciones;

public class NumeroNoPositivoException extends Exception {
    public NumeroNoPositivoException(String mensaje) {
        super(mensaje);
    }
}