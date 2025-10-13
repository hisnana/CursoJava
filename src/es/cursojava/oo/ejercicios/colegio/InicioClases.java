package es.cursojava.oo.ejercicios.colegio;

public class InicioClases {

	public static void main(String[] args) {
		// Crear 3 alumnos y ponerlos a estudiar
		Alumno[] alumnos = new Alumno[3];
		String[] nombres = {"Ana","Isabel","Juan"};
		String[] dnis = {"111A","222A","333B"};
		double[] notasMedias = {9.1,5.5,3.3};
		String[] asignaturas = {"Química","Filosofia","Matemáticas","Lengua", "Sociales","Historia","Tecnología","Educacion Fisica"};
		
		
		for (int i = 0; i < nombres.length ; i++) {
			Alumno alumno = new Alumno(nombres[i],dnis[i]);
//			alumno.setNombre(nombres[i]);
//			alumno.setDni(dnis[i]);
			alumno.setNotaMedia(notasMedias[i]);
			int num = (int)(Math.random() * 7+1);
			
			String[] asignaturasAlumno = new String[num];
			
			for (int j = 0 ; j < num ; j++) {
				
				asignaturasAlumno[j] = asignaturas[j];
				
			}
			
			alumno.setAsignaturas(asignaturasAlumno);
			
			alumno.estudiar();
			alumnos[i] = alumno ;
		}

	}

}
