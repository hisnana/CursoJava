package es.cursojava.plantillas.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.cursojava.utils.UtilidadesHibernate;

/**
 * Chuleta de patrones de uso de Hibernate:
 * - Esqueleto estándar para ESCRITURAS (INSERT/UPDATE/DELETE)
 * - Esqueleto estándar para LECTURAS (SELECT)
 *
 * Ajusta el package y el nombre de UtilidadesHibernate según tu proyecto.
 */

public class TrasnsaccionesHibernate {

    /**
     * Ejemplo de ESCRITURA con Hibernate:
     * INSERT / UPDATE / DELETE dentro de una transacción.
     */
    public static void ejemploEscritura() {
        Session session = null;
        Transaction tx = null;

        try {
            // 1) Abrir sesión
            session = UtilidadesHibernate.abrirSesion();

            // 2) Iniciar transacción (IMPORTANTE incluso si el autocommit existe por debajo)
            tx = session.beginTransaction();

            // 3) Operaciones de escritura
            // ----------------------------------------
            // Aquí irían cosas como:
            //
            //   Curso curso = new Curso();
            //   curso.setNombre("Java Básico");
            //   ...
            //   session.persist(curso);      // INSERT
            //
            //   Curso cursoBD = session.get(Curso.class, 1L);
            //   cursoBD.setNombre("Nuevo nombre");
            //   // session.merge(cursoBD);   // UPDATE (según el caso)
            //
            //   session.remove(cursoBD);     // DELETE
            // ----------------------------------------

            // 4) Confirmar la transacción (se ejecutan los INSERT/UPDATE/DELETE reales)
            tx.commit();

        } catch (Exception e) {
            // Si algo falla, deshacemos la transacción (rollback)
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception exRollback) {
                    System.err.println("Error al hacer rollback: " + exRollback.getMessage());
                }
            }
            e.printStackTrace();
        } finally {
            // 5) Cerrar la sesión para liberar recursos
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Ejemplo de LECTURA con Hibernate:
     * SELECT dentro de una transacción de solo lectura.
     */
    public static void ejemploLectura() {
        Session session = null;
        Transaction tx = null;

        try {
            // 1) Abrir sesión
            session = UtilidadesHibernate.abrirSesion();

            // 2) Iniciar transacción también en lecturas (buena práctica)
            tx = session.beginTransaction();

            // 3) Operaciones de lectura
            // ----------------------------------------
            // Ejemplos:
            //
            //   // Obtener un objeto por id:
            //   Curso curso = session.get(Curso.class, 1L);
            //
            //   // HQL para listar todos:
            //   List<Curso> cursos = session
            //           .createQuery("FROM Curso", Curso.class)
            //           .getResultList();
            //
            //   // HQL para listar solo activos:
            //   List<Curso> activos = session
            //           .createQuery("FROM Curso c WHERE c.activo = true", Curso.class)
            //           .getResultList();
            //
            //   cursos.forEach(System.out::println);
            // ----------------------------------------

            // 4) Confirmar la transacción de lectura
            tx.commit();

        } catch (Exception e) {
            // Si algo falla, deshacemos la transacción
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception exRollback) {
                    System.err.println("Error al hacer rollback: " + exRollback.getMessage());
                }
            }
            e.printStackTrace();
        } finally {
            // 5) Cerrar la sesión
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
