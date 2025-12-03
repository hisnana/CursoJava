package es.cursojava.hibernate.cursos;

import es.cursojava.hibernate.cursos.dto.AulaDTO;
import es.cursojava.hibernate.cursos.dto.CursoDTO;
import es.cursojava.hibernate.cursos.service.CursoService;
import es.cursojava.utils.UtilidadesHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AppCursosAulas {

    public static void main(String[] args) {
        Session session = null;
        Transaction tx = null;

        try {
            session = UtilidadesHibernate.abrirSesion();
            tx = session.beginTransaction();

            CursoService cursoService = new CursoService(session);

            // 1) Crear DTOs de curso y aula
            CursoDTO cursoDTO = new CursoDTO();
            cursoDTO.setCodigo("JAVA-AULA-1");
            cursoDTO.setNombre("Java con Aula");
            cursoDTO.setDescripcion("Curso de Java con aula asignada");
            cursoDTO.setHorasTotales(80);
            cursoDTO.setActivo(true);
            cursoDTO.setNivel("Básico");
            cursoDTO.setCategoria("Programación");
            cursoDTO.setPrecio(new BigDecimal("250.00"));
            cursoDTO.setFechaInicio(LocalDate.of(2025, 1, 10));
            cursoDTO.setFechaFin(LocalDate.of(2025, 3, 10));

            AulaDTO aulaDTO = new AulaDTO();
            aulaDTO.setCodigoAula("AULA-101");
            aulaDTO.setCapacidad(20);
            aulaDTO.setUbicacion("Planta 1");

            // 2) Crear curso + aula a la vez
            CursoDTO cursoGuardado = cursoService.crearCursoConAula(cursoDTO, aulaDTO);
            System.out.println("Curso creado con ID: " + cursoGuardado.getId());

            // 3) Confirmamos la transacción
            tx.commit();

            // 4) Nueva sesión para simular otra petición que lee
            session = UtilidadesHibernate.abrirSesion();
            CursoService servicioLectura = new CursoService(session);

            CursoDTO cursoLeido = servicioLectura.obtenerCursoConAula(cursoGuardado.getId());

            System.out.println("Curso leído: " + cursoLeido.getNombre());
            if (cursoLeido.getAula() != null) {
                System.out.println("Aula: " + cursoLeido.getAula().getCodigoAula()
                        + " (" + cursoLeido.getAula().getUbicacion() + "), capacidad "
                        + cursoLeido.getAula().getCapacidad());
            } else {
                System.out.println("El curso no tiene aula asignada.");
            }

        } catch (Exception e) {
            if (tx != null) {
                try { tx.rollback(); } catch (Exception ignored) {}
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
