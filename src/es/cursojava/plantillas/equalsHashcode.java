package es.cursojava.plantillas;

import java.util.Objects;

/**
 * Igualdad por NIF (clave natural): dos Clientes son "el mismo" si tienen el mismo NIF.
 * Útil antes de persistir (no dependes de un ID generado).
 */
public class equalsHashcode {
    private Long   id;     // puede ser null antes de persistir
    private String nif;    // clave natural
    private String nombre;

    // getters/setters/constructores...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;              // 1) reflexivo/rápido
        if (!(o instanceof equalsHashcode)) return false; // 2) compatible por jerarquía (instanceof)
        equalsHashcode other = (equalsHashcode) o;
        return Objects.equals(this.nif, other.nif); // 3) identidad lógica
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif);                 // 4) usa mismos campos que equals
    }
}
