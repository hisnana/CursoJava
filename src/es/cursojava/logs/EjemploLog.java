package es.cursojava.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EjemploLog {
    private static final Logger log = LoggerFactory.getLogger(EjemploLog.class);

    public static void main(String[] args) {
        log.error("Error gordo");
        log.warn("Aviso importante");
        log.info("Info normalita");
        log.debug("Detalle para depurar");
    }
}