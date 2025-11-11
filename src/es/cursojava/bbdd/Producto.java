package es.cursojava.bbdd;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data //Genera getters, setters, toString(), equals() y hashCode(), y un constructor requerido para campos marcados con @NonNull o final.
@Builder 
//Genera el patrón builder (fluent): Producto.builder().nombre("...").build().
//Con @NonNull en un campo, el builder lanza NullPointerException si no lo proporcionas.
//Si necesitas valores por defecto con builder: añade @Builder.Default al campo.
@NoArgsConstructor
//Crea el constructor vacío. Útil para frameworks (Jackson, JPA, etc.).
//(Existe @NoArgsConstructor(force = true) si tuvieras final y quisieras inicializarlos a valores por defecto.)
@AllArgsConstructor //Crea el constructor con todos los campos en orden.
@EqualsAndHashCode(of = "id") //Hace que solo id cuente en equals() y hashCode().
public class Producto {

    // ID            NUMBER (PK)
    private Long id;
    // NOMBRE        VARCHAR2(120) NOT NULL
    @NonNull
    //Lombok mete checks de nulos en setters/constructores/builder.
    //Si pasas null, lanza NullPointerException con un mensaje claro.
    private String nombre;
    // CATEGORIA     VARCHAR2(50)  NOT NULL
    @NonNull
    private String categoria;
    // PRECIO        NUMBER(8,2)
    private BigDecimal precio;
    // STOCK         NUMBER(6)     NOT NULL
    @NonNull
    private Integer stock;
    // FECHA_ALTA    DATE
    private LocalDate fechaAlta;
    // ESTADO        VARCHAR2(10)
    private String estado;
    // CODIGO_SKU    VARCHAR2(30)
    private String codigoSku;
    // CREADO_POR    VARCHAR2(30)
    private String creadoPor;
    // IVA           NUMBER(3)     NOT NULL
    @NonNull
    private Integer iva;
    
    
    
    
    
}
