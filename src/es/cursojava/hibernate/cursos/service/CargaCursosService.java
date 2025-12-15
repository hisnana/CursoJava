package es.cursojava.hibernate.cursos.service;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.cursojava.hibernate.cursos.dao.AlumnoDAO;
import es.cursojava.hibernate.cursos.dao.AulaDAO;
import es.cursojava.hibernate.cursos.dao.CursoDAO;
import es.cursojava.hibernate.cursos.entity.Alumno;
import es.cursojava.hibernate.cursos.entity.Aula;
import es.cursojava.hibernate.cursos.entity.Curso;
import es.cursojava.utils.UtilidadesHibernate;
import java.nio.file.Path;


public class CargaCursosService {

    private static final Logger log = LoggerFactory.getLogger(CargaCursosService.class);

    public void cargarDatosIniciales(Path rutaAulas,
                                     Path rutaCursosAula,
                                     Path rutaAlumnos) {

        Session session = null;
        Transaction tx = null;

        try {
            session = UtilidadesHibernate.abrirSesion();
            tx = session.beginTransaction();

            AulaDAO aulaDAO = new AulaDAO(session);
            CursoDAO cursoDAO = new CursoDAO(session);
            AlumnoDAO alumnoDAO = new AlumnoDAO(session);

            // aquí llamas a tus métodos de carga:
            cargarCursosYAulas(cursoDAO, rutaCursosAula, rutaAulas);
            cargarAlumnos(alumnoDAO, cursoDAO, rutaAlumnos);

            tx.commit();
            log.info("Carga de datos iniciales completada");

        } catch (Exception e) {
            if (tx != null) {
                try { tx.rollback(); } catch (Exception ignore) {}
            }
            log.error("Error en la carga de datos iniciales", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void mostrarCursoYAulaDeAlumno(String nombreAlumno) {

        try (Session session = UtilidadesHibernate.abrirSesion()) {
            AlumnoDAO alumnoDAO = new AlumnoDAO(session);

            Alumno alumno = alumnoDAO.obtenerAlumnoConCursoYAulaPorNombre(nombreAlumno);
            if (alumno == null) {
                log.warn("No se encontró al alumno {}", nombreAlumno);
                return;
            }

            Curso curso = alumno.getCurso();
            Aula aula = curso.getAula();

            log.info("El alumno {} está en el curso {} ({}) y en el aula {} ({})",
                    alumno.getNombre(),
                    curso.getCodigo(),
                    curso.getNombre(),
                    aula.getCodigoAula(),
                    aula.getUbicacion());
        }
    }

    // Aquí dentro puedes dejar tus métodos privados:
    // cargarCursosYAulas(...), cargarAlumnos(...), cargarAulasEnMemoria(...), etc.
    
    // -------------------------------------------------
    //  CARGAR ALUMNOS + ASIGNAR CURSO (alumnos_curso.txt)
    // -------------------------------------------------
    private static void cargarAlumnos(AlumnoDAO alumnoDAO,
                                      CursoDAO cursoDAO,
                                      Path ruta) throws Exception {
        log.info("Cargando alumnos desde {}", ruta);

        try (BufferedReader br = Files.newBufferedReader(ruta)) {
            String linea = br.readLine(); // cabecera
            // asumimos formato: nombre;email;edad;codigo_curso

            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] partes = linea.split(";");
                String nombre       = partes[0].trim();
                String email        = partes[1].trim();
                Integer edad        = Integer.parseInt(partes[2].trim());
                String codigoCurso  = partes[3].trim();

                // ¿existe ya un alumno con ese email? (email es único)
                Alumno existente = alumnoDAO.obtenerAlumnoPorEmail(email);
                if (existente != null) {
                    log.debug("Alumno con email {} ya existe, se omite", email);
                    continue;
                }

                Curso curso = cursoDAO.obtenerPorCodigo(codigoCurso);
                if (curso == null) {
                    log.warn("No se encontró curso con código {} para el alumno {}, se omite", codigoCurso, nombre);
                    continue;
                }

                Alumno alumno = new Alumno();
                alumno.setNombre(nombre);
                alumno.setEmail(email);
                alumno.setEdad(edad);
                alumno.setCurso(curso);

                alumnoDAO.guardarAlumno(alumno);
                log.info("Alumno {} asignado al curso {}", nombre, codigoCurso);
            }
        }
    }
    
    private static Map<Integer, Aula> cargarAulasEnMemoria(Path rutaAulas) throws Exception {
        Map<Integer, Aula> mapa = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(rutaAulas)) {
            String linea = br.readLine(); // cabecera

            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] partes = linea.split(";");
                int codigoAula = Integer.parseInt(partes[0].trim());
                int capacidad   = Integer.parseInt(partes[1].trim());
                String ubicacion = partes[2].trim();

                Aula aula = new Aula();
                aula.setCodigoAula(codigoAula);
                aula.setCapacidad(capacidad);
                aula.setUbicacion(ubicacion);

                // NO se persiste aún, solo se guarda en memoria
                mapa.put(codigoAula, aula);
            }
        }
        return mapa;
    }
    
    private static void cargarCursosYAulas(CursoDAO cursoDAO,
    		Path rutaCursosAula,
    		Path rutaAulas) throws Exception {

    	Map<Integer, Aula> mapaAulas = cargarAulasEnMemoria(rutaAulas);

    	try (BufferedReader br = Files.newBufferedReader(rutaCursosAula)) {
    		String linea = br.readLine(); // cabecera

    		while ((linea = br.readLine()) != null) {
    			if (linea.isBlank()) continue;

    			String[] partes = linea.split(";");
    			String codigoCurso = partes[0].trim();
    			// ... resto de campos del curso ...
    			int codigoAula = Integer.parseInt(partes[11].trim());

    			// ¿ya existe el curso?
    			Curso existente = cursoDAO.obtenerPorCodigo(codigoCurso);
    			if (existente != null) {
    				log.info("Curso {} ya existe, no se vuelve a crear", codigoCurso);
    				continue;
    			}

    			Curso curso = new Curso();
    			curso.setCodigo(codigoCurso);
    			// set resto de campos...

    			Aula aula = mapaAulas.get(codigoAula);
    			if (aula == null) {
    				log.warn("No hay datos de aula {} en aulas.txt para el curso {}", codigoAula, codigoCurso);
    				continue;
    			}

    			curso.setAula(aula);      // relación 1–1
    			cursoDAO.guardarCurso(curso); // CascadeType.ALL → persiste curso + aula

    			log.info("Curso {} creado con aula {}", codigoCurso, codigoAula);
    		}
    	}
    }
}
