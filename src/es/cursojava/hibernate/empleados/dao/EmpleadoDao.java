package es.cursojava.hibernate.empleados.dao;

import es.cursojava.hibernate.empleados.entity.Empleado;

import java.util.List;

public interface EmpleadoDao {

    void guardar(Empleado empleado);  // crear o actualizar

    Empleado buscarPorId(Long id);

    Empleado buscarPorNif(String nif);

    List<Empleado> buscarPorDepartamento(String departamento);

    List<Empleado> buscarTodos();
}
