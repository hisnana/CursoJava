package es.cursojava.hibernate.app;


import es.cursojava.hibernate.Curso;
import es.cursojava.utils.UtilidadesHibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
// Si usas Java 11+
import java.nio.file.Files;
import java.nio.file.Path;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Lee un fichero de texto con cursos separados por '|'
 * y los inserta en la BD usando Hibernate.
 */
public class ImportarCursosDesdeFichero {

    public static void main(String[] args) {
        // Ruta del fichero:
        // - Puedes dejarla fija
        // - O leerla de args[0] si quieres pasarlo por parámetros
        String rutaFichero = "./recursos/cursos.txt";

        importarCursos(rutaFichero);
    }

    /**
     * Lee el fichero línea a línea, crea objetos Curso
     * y los inserta en la base de datos.
     */
    public static void importarCursos(String rutaFichero) {

        Session session = null;
        Transaction tx = null;

        // try-with-resources para que el BufferedReader se cierre solo
        try (BufferedReader br = Files.newBufferedReader(Path.of(rutaFichero))) {

            // 1) Abrimos sesión y transacción de Hibernate
            session = UtilidadesHibernate.abrirSesion();
            tx = session.beginTransaction();

            String linea;

            // 2) Leemos cada línea del fichero
            while ((linea = br.readLine()) != null) {

                // Saltamos líneas vacías por si las hubiera
                if (linea.isBlank()) {
                    continue;
                }

                // 3) Separamos la línea por el carácter '|'
                // OJO: en regex '|' es especial, por eso usamos "\\|"
                String[] campos = linea.split("\\|");

                // Comprobación rápida del número de campos
                // Esperamos:
                // 0: codigo
                // 1: nombre
                // 2: descripcion
                // 3: horas_totales
                // 4: activo
                // 5: nivel
                // 6: categoria
                // 7: precio
                // 8: fecha_inicio
                // 9: fecha_fin
                if (campos.length != 10) {
                    System.err.println("Línea con formato incorrecto: " + linea);
                    continue; // saltamos esta línea
                }

                // 4) Creamos el objeto Curso y seteamos los campos
                Curso curso = new Curso();

                curso.setCodigo(campos[0].trim());
                curso.setNombre(campos[1].trim());
                curso.setDescripcion(campos[2].trim());

                // horas_totales -> int
                curso.setHorasTotales(Integer.parseInt(campos[3].trim()));

                // activo -> boolean ("true"/"false")
                curso.setActivo(Boolean.parseBoolean(campos[4].trim()));

                curso.setNivel(campos[5].trim());
                curso.setCategoria(campos[6].trim());

                // precio -> BigDecimal (usa punto como separador decimal)
                curso.setPrecio(new BigDecimal(campos[7].trim()));

                // fechas -> LocalDate (van en formato ISO: yyyy-MM-dd, así que LocalDate.parse vale directo)
                curso.setFechaInicio(LocalDate.parse(campos[8].trim()));
                curso.setFechaFin(LocalDate.parse(campos[9].trim()));

                // fecha_creacion NO viene en el fichero:
                // se rellenará sola en @PrePersist dentro de la entidad Curso

                // 5) Persistimos el curso
                session.persist(curso);
            }

            // 6) Confirmamos la transacción: se hacen los INSERTs de verdad
            tx.commit();

            System.out.println("Importación de cursos completada correctamente.");

        } catch (IOException e) {
            // Errores de lectura del fichero
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error leyendo el fichero: " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            // Cualquier otro error (Hibernate, parseos, etc.)
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error importando cursos: " + e.getMessage());
            e.printStackTrace();

        } finally {
            // Cerrar sesión si está abierta
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
