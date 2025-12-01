package es.cursojava.hibernate.empleados.presentacion;

import es.cursojava.hibernate.empleados.dao.EmpleadoDao;
import es.cursojava.hibernate.empleados.dao.EmpleadoDaoImpl;
import es.cursojava.hibernate.empleados.dto.EmpleadoDto;
import es.cursojava.hibernate.empleados.exception.BusinessException;
import es.cursojava.hibernate.empleados.interfaces.EmpleadoService;
import es.cursojava.hibernate.empleados.service.EmpleadoServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class AppEmpleados {

    public static void main(String[] args) {

        EmpleadoDao empleadoDao = new EmpleadoDaoImpl();
        EmpleadoService empleadoService = new EmpleadoServiceImpl(empleadoDao);

        try {
            System.out.println("=== Alta de empleados ===");

            EmpleadoDto emp1 = new EmpleadoDto("11111111A", "Ana", "IT", new BigDecimal("1800.00"));
            EmpleadoDto empCreado1 = empleadoService.altaEmpleado(emp1);
            System.out.println("Creado: " + empCreado1);

            EmpleadoDto emp2 = new EmpleadoDto("22222222B", "Luis", "VENTAS", new BigDecimal("2000.00"));
            EmpleadoDto empCreado2 = empleadoService.altaEmpleado(emp2);
            System.out.println("Creado: " + empCreado2);

            System.out.println("\n=== Buscar por NIF ===");
            EmpleadoDto buscado = empleadoService.buscarPorNif("11111111A");
            System.out.println("Encontrado: " + buscado);

            System.out.println("\n=== Listar por departamento (IT) ===");
            List<EmpleadoDto> listaIt = empleadoService.listarPorDepartamento("IT");
            listaIt.forEach(System.out::println);

            System.out.println("\n=== Actualizar salario ===");
            EmpleadoDto actualizado = empleadoService.actualizarSalario("11111111A", new BigDecimal("1900.00"));
            System.out.println("Actualizado: " + actualizado);

            System.out.println("\n=== Listar todos ===");
            List<EmpleadoDto> todos = empleadoService.listarTodos();
            todos.forEach(System.out::println);

        } catch (BusinessException e) {
            System.err.println("Error de negocio: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado:");
            e.printStackTrace();
        }
    }
}
