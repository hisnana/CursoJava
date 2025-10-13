package es.cursojava.oo.ejercicios.colecciones.colegio;

import java.util.List;
import java.util.Map;

import es.cursojava.oo.ejercicios.colegio.Alumno;

public class Colegio {
	
    private final String nombre;
    private final String direccion;
    // Usamos LinkedHashMap para mantener el orden de inserción al imprimir
    private final Map<String, List<Alumno>> aulas;

    public Colegio(String nombre, String direccion, Map<String, List<Alumno>> aulas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.aulas = aulas;
    }

    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public Map<String, List<Alumno>> getAulas() { return aulas; }

}
