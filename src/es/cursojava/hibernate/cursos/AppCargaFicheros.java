package es.cursojava.hibernate.cursos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.cursojava.hibernate.cursos.service.CargaCursosService;
import java.nio.file.Path;

public class AppCargaFicheros {

    private static final Logger log = LoggerFactory.getLogger(AppCargaFicheros.class);

    public static void main(String[] args) {

        CargaCursosService service = new CargaCursosService();

        Path rutaAulas       = Path.of("recursos/aulas.txt");
        Path rutaCursosAula  = Path.of("recursos/cursos_aula.txt");
        Path rutaAlumnos     = Path.of("recursos/alumnos_curso.txt");

        // 1) Cargar datos
        service.cargarDatosIniciales(rutaAulas, rutaCursosAula, rutaAlumnos);

        // 2) Consulta final del enunciado
        service.mostrarCursoYAulaDeAlumno("alumno50");

        log.info("Fin de la aplicación");
    }
}
