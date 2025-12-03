package es.cursojava.hibernate.cursos.dao;

import java.util.List;

import org.hibernate.Session;

import es.cursojava.hibernate.cursos.entity.Aula;
import es.cursojava.hibernate.cursos.entity.Curso;

/**
 * DAO de la entidad Curso.
 * Encapsula las operaciones de acceso a datos usando Hibernate.
 *
 * OJO: Este DAO NO abre ni cierra la sesión ni la transacción.
 * Recibe la Session desde fuera (por constructor).
 */
public class CursoDAO {
	
	private final Session session;

    // Constructor: obligas a pasarle una Session válida al crear el DAO
	public CursoDAO(Session session) {
		this.session = session;
	}
	
    /**
     * Guarda un curso nuevo en la base de datos (INSERT).
     */
	public void guardarCurso(Curso curso) {
		session.persist(curso);
	}

    /**
     * Busca un curso por su ID (PK).
     */
	public Curso obtenerCursoPorId(Long id) {
		return session.get(Curso.class, id);
	}

    /**
     * Actualiza un curso existente.
     * Si el objeto viene de la misma sesión, con cambiar los campos basta.
     * Si viene "despegado", puedes usar merge.
     */
	public void actualizarCurso(Curso curso) {
		session.merge(curso);  // o session.update(curso) según cómo lo estéis viendo en clase
	}

    /**
     * Elimina un curso (objeto ya cargado).
     */
	public void eliminarCurso(Curso curso) {
		session.remove(curso);
	}

    /**
     * Elimina un curso a partir de su id.
     */
    public void eliminarCursoPorId(Long id) {
        Curso c = obtenerCursoPorId(id);
        if (c != null) {
            session.remove(c);
        }
    }

    /**
     * Devuelve todos los cursos de la tabla.
     */
	public List<Curso> obtenerTodosLosCursos() {
		return session.createQuery("FROM Curso", Curso.class).list();
	}

    /**
     * Devuelve solo los cursos activos (activo = true).
     */
    public List<Curso> obtenerCursosActivos() {
        return session.createQuery(
                "FROM Curso c WHERE c.activo = true",
                Curso.class
        ).list();
    }
    
    /**
     * Asigna un aula a un curso dado su id.
     * No hace validaciones de negocio (eso es tarea del servicio).
     */
    public void asignarAula(Long cursoId, Aula aula) {
        Curso curso = obtenerCursoPorId(cursoId);
        if (curso != null) {
            curso.setAula(aula);
            session.merge(curso); // sincroniza cambios
        }
    }

    /**
     * Obtiene un curso junto con su aula (fetch join para evitar lazy en otra sesión).
     */
    public Curso obtenerCursoConAula(Long id) {
        return session.createQuery(
                        "SELECT c FROM Curso c " +
                        "LEFT JOIN FETCH c.aula " +
                        "WHERE c.id = :id",
                        Curso.class
                )
                .setParameter("id", id)
                .uniqueResult();
    }

}
