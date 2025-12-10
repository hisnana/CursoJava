package es.cursojava.hibernate.cursos;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

            AulaDAO aulaDAO = new AulaDAO(session);
            CursoDAO cursoDAO = new CursoDAO(session);
            AlumnoDAO alumnoDAO = new AlumnoDAO(session);

            cargarAulas(aulaDAO, Path.of("recursos/aulas.txt"));
            cargarCursosConAula(cursoDAO, aulaDAO, Path.of("recursos/cursos_aula.txt"));
            cargarAlumnos(alumnoDAO, cursoDAO, Path.of("recursos/alumnos_curso.txt"));

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

        // 2) Consulta: en qué aula está alumno100
        try (Session sesConsulta = UtilidadesHibernate.abrirSesion()) {
            mostrarAulaDeAlumnoPorNombre(sesConsulta, "alumno100");
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
                String codigoAula = partes[0].trim();
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
                String codigoAula = partes[11].trim();       // p.ej. "1"

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
                String nombre = partes[0].trim();
                String email = partes[1].trim();
                Integer edad = Integer.parseInt(partes[2].trim());
                String codigoCurso = partes[3].trim();

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
            }
        }
    }

    // -------------------------------------------------
    //  CONSULTA: ¿EN QUÉ AULA ESTÁ 'alumno100'?
    // -------------------------------------------------
    private static void mostrarAulaDeAlumnoPorNombre(Session session, String nombreAlumno) {
        log.info("Buscando aula del alumno {}", nombreAlumno);

        Aula aula = session.createQuery(
                        "SELECT al.curso.aula FROM Alumno al " +
                        "WHERE al.nombre = :nombre",
                        Aula.class
                )
                .setParameter("nombre", nombreAlumno)
                .uniqueResult();

        if (aula == null) {
            log.warn("No se encontró aula para el alumno {}", nombreAlumno);
        } else {
            log.info("El alumno {} está en el aula {} ({})",
                    nombreAlumno,
                    aula.getCodigoAula(),
                    aula.getUbicacion());
        }
    }
}
