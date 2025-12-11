package es.cursojava.hibernate.cursos;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

public class AppCargaFicheros {

    private static final Logger log = LoggerFactory.getLogger(AppCargaFicheros.class);

    public static void main(String[] args) {

        // 1) Cargar datos en BD
        Session session = null;
        Transaction tx = null;

        try {
            session = UtilidadesHibernate.abrirSesion();
            tx = session.beginTransaction();

            CursoDAO cursoDAO = new CursoDAO(session);
            AlumnoDAO alumnoDAO = new AlumnoDAO(session);

            cargarCursosYAulas(cursoDAO,
                               Path.of("recursos/cursos_aula.txt"),
                               Path.of("recursos/aulas.txt"));

            cargarAlumnos(alumnoDAO,
                          cursoDAO,
                          Path.of("recursos/alumnos_curso.txt"));

            tx.commit();
            log.info("Carga de ficheros completada correctamente.");

        } catch (Exception e) {
            if (tx != null) {
                try { tx.rollback(); } catch (Exception ignored) {}
            }
            log.error("Error cargando datos desde ficheros", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        
        
        try (Session sesConsulta = UtilidadesHibernate.abrirSesion()) {
            AlumnoDAO alumnoDAOConsulta = new AlumnoDAO(sesConsulta);

            Alumno alumno = alumnoDAOConsulta
                    .obtenerAlumnoConCursoYAulaPorNombre("alumno100");

            if (alumno == null) {
                log.warn("No se ha encontrado al alumno alumno100");
            } else {
                Curso curso = alumno.getCurso();
                Aula aula = curso.getAula();

                log.info("El alumno {} está matriculado en el curso {} ({}) y su aula es {} (ubicación: {})",
                        alumno.getNombre(),
                        curso.getCodigo(),
                        curso.getNombre(),
                        aula.getCodigoAula(),
                        aula.getUbicacion());
            }
        }
        
        // Practica de acceso a datos mediante un objeto con relaciones bidireccionales
        
        try (Session ses = UtilidadesHibernate.abrirSesion()) {
            AlumnoDAO alumnoDAO = new AlumnoDAO(ses);

            Alumno alumno = alumnoDAO.obtenerAlumnoPorNombre("alumno100");

            if (alumno == null) {
                log.warn("No se ha encontrado al alumno alumno100");
            } else {
                Curso curso = alumno.getCurso();   // aquí Hibernate puede hacer otra SELECT
                Aula aula   = curso.getAula();     // y aquí otra, si la relación es LAZY

                log.info("El alumno {} está en el curso {} ({}) y en el aula {} ({})",
                        alumno.getNombre(),
                        curso.getCodigo(),
                        curso.getNombre(),
                        aula.getCodigoAula(),
                        aula.getUbicacion());
            }
        }

        
        
    }

    // -------------------------------------------------
    //  CARGAR AULAS (aulas.txt)
    // -------------------------------------------------
    private static void cargarAulas(AulaDAO aulaDAO, Path ruta) throws Exception {
        log.info("Cargando aulas desde {}", ruta);

        try (BufferedReader br = Files.newBufferedReader(ruta)) {
            String linea = br.readLine(); // cabecera
            // formato: codigoAula;capacidad;ubicacion

            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] partes = linea.split(";");
                Integer codigoAula = Integer.parseInt(partes[0].trim());
                Integer capacidad = Integer.parseInt(partes[1].trim());
                String ubicacion = partes[2].trim();

                // Comprobar si ya existe por código (por si ejecutas varias veces)
                Aula existente = aulaDAO.buscarPorCodigo(codigoAula);
                if (existente != null) {
                    log.debug("Aula {} ya existe, se omite creación", codigoAula);
                    continue;
                }

                Aula aula = new Aula();
                aula.setCodigoAula(codigoAula);
                aula.setCapacidad(capacidad);
                aula.setUbicacion(ubicacion);

                aulaDAO.guardar(aula);
            }
        }
    }

    // -------------------------------------------------
    //  CARGAR CURSOS + ASIGNAR AULA (cursos_aula.txt)
    // -------------------------------------------------
    private static void cargarCursosConAula(CursoDAO cursoDAO,
                                            AulaDAO aulaDAO,
                                            Path ruta) throws Exception {
        log.info("Cargando cursos (con aula) desde {}", ruta);

        try (BufferedReader br = Files.newBufferedReader(ruta)) {
            String linea = br.readLine(); // cabecera
            // formato: código;nombre;descripcion;horasTotales;activo;nivel;categoria;
            //          precio;fechaInicio;fechaFin;fechaCreacion;codigo_aula

            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] partes = linea.split(";");
                String codigoCurso = partes[0].trim();       // "1001"
                String nombre = partes[1].trim();
                String descripcion = partes[2].trim();
                int horasTotales = Integer.parseInt(partes[3].trim());
                boolean activo = Boolean.parseBoolean(partes[4].trim());
                String nivel = partes[5].trim();
                String categoria = partes[6].trim();
                BigDecimal precio = new BigDecimal(partes[7].trim());
                LocalDate fechaInicio = LocalDate.parse(partes[8].trim());
                LocalDate fechaFin = LocalDate.parse(partes[9].trim());
                LocalDateTime fechaCreacion = LocalDate.parse(partes[10].trim()).atStartOfDay();
                Integer codigoAula = Integer.parseInt(partes[11].trim());       // p.ej. "1"

                // ¿Ya existe el curso por código?
                Curso existente = cursoDAO.obtenerPorCodigo(codigoCurso);
                if (existente != null) {
                    log.debug("Curso {} ya existe, se omite creación", codigoCurso);
                    continue;
                }

                // Buscar aula por su código
                Aula aula = aulaDAO.buscarPorCodigo(codigoAula);
                if (aula == null) {
                    log.warn("No se encontró aula con código {} para el curso {}, se omite curso",
                            codigoAula, codigoCurso);
                    continue;
                }

                Curso curso = new Curso();
                curso.setCodigo(codigoCurso);
                curso.setNombre(nombre);
                curso.setDescripcion(descripcion);
                curso.setHorasTotales(horasTotales);
                curso.setActivo(activo);
                curso.setNivel(nivel);
                curso.setCategoria(categoria);
                curso.setPrecio(precio);
                curso.setFechaInicio(fechaInicio);
                curso.setFechaFin(fechaFin);
                curso.setFechaCreacion(fechaCreacion);
                curso.setAula(aula);

                cursoDAO.guardarCurso(curso);
                log.info("Curso {} creado y asignado al aula {}", codigoCurso, codigoAula);
            }
        }
    }

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
