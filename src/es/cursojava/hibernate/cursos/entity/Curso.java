package es.cursojava.hibernate.cursos.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import es.cursojava.hibernate.cursos.entity.Aula;

/**
 * Entidad Curso -> tabla TB_CURSO
 */
@Entity
@Table(
        name = "TB_CURSO",
        uniqueConstraints = {
                // CODIGO debe ser único
                @UniqueConstraint(name = "UK_CURSO_CODIGO", columnNames = "CODIGO")
        }
)
public class Curso {

    // ID: clave primaria, autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    // CODIGO: obligatorio, único, máx 20 caracteres
    @Column(name = "CODIGO", nullable = false, length = 20, unique = true)
    private String codigo;

    // NOMBRE: obligatorio, máx 100 caracteres
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    // DESCRIPCION: opcional, máx 1000 caracteres
    @Column(name = "DESCRIPCION", length = 1000)
    private String descripcion;

    // HORAS_TOTALES: obligatorio, entero positivo (>0)
    // Nota: aquí solo marcamos nullable=false; la positividad habría que validarla en código
    @Column(name = "HORAS_TOTALES", nullable = false)
    private int horasTotales;

    // ACTIVO: obligatorio, true/false, por defecto true
    @Column(name = "ACTIVO", nullable = false)
    private boolean activo = true;

    // NIVEL: opcional, máx 20 caracteres (Básico, Intermedio, Avanzado...)
    @Column(name = "NIVEL", length = 20)
    private String nivel;

    // CATEGORIA: opcional, máx 50 caracteres (Programación, Big Data...)
    @Column(name = "CATEGORIA", length = 50)
    private String categoria;

    // PRECIO: opcional, si se informa debería ser >= 0
    // En BD lo guardamos con 2 decimales
    @Column(name = "PRECIO", precision = 10, scale = 2)
    private BigDecimal precio;

    // FECHA_INICIO: opcional
    @Column(name = "FECHA_INICIO")
    private LocalDate fechaInicio;

    // FECHA_FIN: opcional, debería ser >= FECHA_INICIO si existe
    @Column(name = "FECHA_FIN")
    private LocalDate fechaFin;

    // FECHA_CREACION: obligatoria; se pondrá automáticamente al crear el curso
    @Column(name = "FECHA_CREACION", nullable = false)
    private LocalDateTime fechaCreacion;

    // --------- CONSTRUCTOR VACÍO (obligatorio para Hibernate) ---------

    public Curso() {
    }

    // --------- CALLBACK @PrePersist ---------
    // Se ejecuta justo antes del INSERT
    @PrePersist
    //Se ejecuta antes de insertar un dato
    public void prePersist() {
        // Si no hemos seteado fechaCreacion, la ponemos a "ahora"
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }

        // Aquí podrías hacer pequeñas validaciones manuales si quisieras,
        // por ejemplo asegurar que horasTotales > 0, precio >= 0, etc.
        // pero para el ejercicio las dejamos solo documentadas.
    }

    // --------- GETTERS / SETTERS ---------

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getHorasTotales() { return horasTotales; }

    public void setHorasTotales(int horasTotales) { this.horasTotales = horasTotales; }

    public boolean isActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }

    public String getNivel() { return nivel; }

    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getCategoria() { return categoria; }

    public void setCategoria(String categoria) { this.categoria = categoria; }

    public BigDecimal getPrecio() { return precio; }

    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public LocalDate getFechaInicio() { return fechaInicio; }

    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }

    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    // --------- toString para mostrar los datos por consola ---------

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", horasTotales=" + horasTotales +
                ", activo=" + activo +
                ", nivel='" + nivel + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio=" + precio +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
    
    // ----------------- RELACIÓN ONE-TO-ONE CON AULA -----------------

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "AULA_ID",
            foreignKey = @ForeignKey(name = "FK_CURSO_AULA"),
            unique = true // un aula no puede estar en dos cursos a la vez
    )
    private Aula aula;

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }
}
