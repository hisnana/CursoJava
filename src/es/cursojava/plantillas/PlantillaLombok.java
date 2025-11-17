package es.cursojava.plantillas;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import java.util.*;

@Data                              // getter+setter+equals+hashCode+toString
@NoArgsConstructor                 // ctor vacío
@AllArgsConstructor                // ctor con todos los campos
@Builder                          // patrón builder
@FieldNameConstants               // genera Clase.Fields.NOMBRE, etc. (opcional)
public class PlantillaLombok {

    @NonNull                      // null → NullPointerException en ctor/builder
    private String nombre;

    private String email;

    @Builder.Default              // mantiene el valor por defecto en el builder
    private List<String> etiquetas = new ArrayList<>();

    @ToString.Exclude             // no lo muestres en toString (p.ej. password)
    @EqualsAndHashCode.Exclude    // no lo uses en equals/hashCode
    private String secreto;
}


//PlantillaLombok a = PlantillaLombok.builder()
//.nombre("Ana")
//.email("ana@acme.es")
//.etiquetas(new ArrayList<>()) // o usa @Singular abajo
//.build();


//@Builder.Default imprescindible si quieres conservar valores por defecto al construir con builder.
//Evita meter en equals/hashCode campos mutables (listas, estados).
//@NonNull en parámetro/campo: valida automáticamente y lanza NPE con mensaje claro.
//@SneakyThrows existe, pero no abuses (oculta checked); mejor declarar throws.
//Si usas JPA: no pongas @Data en entidades → mejor @Getter/@Setter y define equals/hashCode con cuidado (por ID).