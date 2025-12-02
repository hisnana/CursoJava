package es.cursojava.hibernate.empleados.interfaces;

import es.cursojava.hibernate.empleados.dto.EmpleadoDto;
import es.cursojava.hibernate.empleados.exception.BusinessException;

import java.math.BigDecimal;
import java.util.List;

public interface EmpleadoService {

    EmpleadoDto altaEmpleado(EmpleadoDto empleadoDto) throws BusinessException;

    EmpleadoDto actualizarSalario(String nif, BigDecimal nuevoSalario) throws BusinessException;

    EmpleadoDto buscarPorNif(String nif) throws BusinessException;

    List<EmpleadoDto> listarPorDepartamento(String departamento) throws BusinessException;

    List<EmpleadoDto> listarTodos() throws BusinessException; // opcional pero útil
    
    void eliminarPorNif(String nif) throws BusinessException;
    
}
