package es.cursojava.hibernate.empleados.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_EMPLEADO")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "ID")
    private Long id;

    @Column(name = "NIF", nullable = false, unique = true, length = 20)
    private String nif;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DEPARTAMENTO", nullable = false, length = 50)
    private String departamento;

    @Column(name = "SALARIO", nullable = false, precision = 10, scale = 2)
    private BigDecimal salario;

    public Empleado() {
    }

    public Empleado(String nif, String nombre, String departamento, BigDecimal salario) {
        this.nif = nif;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}
