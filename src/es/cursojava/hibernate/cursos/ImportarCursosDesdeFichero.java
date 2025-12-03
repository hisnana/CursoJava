package es.cursojava.hibernate.cursos;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.cursojava.hibernate.cursos.dao.CursoDAO;
import es.cursojava.hibernate.cursos.entity.Curso;
import es.cursojava.utils.UtilidadesHibernate;

public class ImportarCursosDesdeFichero {

    public static void main(String[] args) {
        String rutaFichero = "./recursos/cursos.txt"; // ajusta la ruta si está en otra carpeta
        importarCursos(rutaFichero);
    }

    public static void importarCursos(String rutaFichero) {

        Session session = null;
        Transaction tx = null;

        try (BufferedReader br = Files.newBufferedReader(Path.of(rutaFichero))) {

            // 1) Abrimos sesión y transacción
            session = UtilidadesHibernate.abrirSesion();
            tx = session.beginTransaction();

            // 2) Creamos el DAO usando esta sesión
            CursoDAO cursoDAO = new CursoDAO(session);

            String linea;

            // 3) Leemos el fichero línea a línea
            while ((linea = br.readLine()) != null) {

                if (linea.isBlank()) {
                    continue;
                }

                String[] campos = linea.split("\\|");

                if (campos.length != 10) {
                    System.err.println("Línea con formato incorrecto: " + linea);
                    continue;
                }

                // 4) Creamos el objeto Curso a partir de la línea
                Curso curso = new Curso();

                curso.setCodigo(campos[0].trim());
                curso.setNombre(campos[1].trim());
                curso.setDescripcion(campos[2].trim());

                curso.setHorasTotales(Integer.parseInt(campos[3].trim()));
                curso.setActivo(Boolean.parseBoolean(campos[4].trim()));

                curso.setNivel(campos[5].trim());
                curso.setCategoria(campos[6].trim());

                curso.setPrecio(new BigDecimal(campos[7].trim()));

                curso.setFechaInicio(LocalDate.parse(campos[8].trim()));
                curso.setFechaFin(LocalDate.parse(campos[9].trim()));
                // fechaCreacion se pondrá sola en @PrePersist

                // 5) Guardamos el curso usando el DAO
                cursoDAO.guardarCurso(curso);
            }

            // 6) Commit de la transacción
            tx.commit();
            System.out.println("Importación de cursos completada.");

        } catch (IOException e) {
            if (tx != null) tx.rollback();
            System.err.println("Error leyendo el fichero: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error importando cursos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
