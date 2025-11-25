package es.cursojava.hibernate;

import java.util.List;

import org.hibernate.Session;

public class CursoDao {
	
	private Session session;
	
	public void guardarCurso(Curso curso) {
		// Implementación para guardar un curso en la base de datos
		session.persist(curso);
	}
	public Curso obtenerCursoPorId(Long id) {
		// Implementación para obtener un curso por su ID
		return null;
	}
	public void actualizarCurso(Curso curso) {
		// Implementación para actualizar un curso existente
	}
	public void eliminarCurso(Curso curso) {
		// Implementación para eliminar un curso
	}
	public List<Curso> obtenerTodosLosCursos() {
		// Implementación para obtener todos los cursos
		
		
		return session.createQuery("FROM Curso", Curso.class).list();
	}
	

}
