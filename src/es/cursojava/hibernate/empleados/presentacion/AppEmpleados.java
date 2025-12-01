package es.cursojava.hibernate.empleados.presentacion;

import es.cursojava.hibernate.empleados.dao.EmpleadoDao;
import es.cursojava.hibernate.empleados.dao.EmpleadoDaoImpl;
import es.cursojava.hibernate.empleados.dto.EmpleadoDto;
import es.cursojava.hibernate.empleados.exception.BusinessException;
import es.cursojava.hibernate.empleados.interfaces.EmpleadoService;
import es.cursojava.hibernate.empleados.service.EmpleadoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class AppEmpleados {

    // ✅ Logger SLF4J
    private static final Logger log = LoggerFactory.getLogger(AppEmpleados.class);

    public static void main(String[] args) {

        EmpleadoDao empleadoDao = new EmpleadoDaoImpl();
        EmpleadoService empleadoService = new EmpleadoServiceImpl(empleadoDao);

        try {
            log.info("=== Alta de empleados ===");

            EmpleadoDto emp1 = new EmpleadoDto("11111111A", "Ana", "IT", new BigDecimal("1800.00"));
            EmpleadoDto empCreado1 = empleadoService.altaEmpleado(emp1);
            log.info("Creado empleado: {}", empCreado1);

            EmpleadoDto emp2 = new EmpleadoDto("22222222B", "Luis", "VENTAS", new BigDecimal("2000.00"));
            EmpleadoDto empCreado2 = empleadoService.altaEmpleado(emp2);
            log.info("Creado empleado: {}", empCreado2);

            log.info("=== Buscar por NIF ===");
            EmpleadoDto buscado = empleadoService.buscarPorNif("11111111A");
            log.info("Empleado encontrado por NIF 11111111A: {}", buscado);

            log.info("=== Listar por departamento (IT) ===");
            List<EmpleadoDto> listaIt = empleadoService.listarPorDepartamento("IT");
            listaIt.forEach(e -> log.info("Empleado IT: {}", e));

            log.info("=== Actualizar salario ===");
            EmpleadoDto actualizado = empleadoService.actualizarSalario("11111111A", new BigDecimal("1900.00"));
            log.info("Empleado actualizado: {}", actualizado);

            log.info("=== Listar todos ===");
            List<EmpleadoDto> todos = empleadoService.listarTodos();
            todos.forEach(e -> log.info("Empleado en BBDD: {}", e));

        } catch (BusinessException e) {
            // Error de negocio esperado → WARN/ERROR con mensaje
            log.warn("Error de negocio: {}", e.getMessage());
        } catch (Exception e) {
            // Error inesperado → ERROR con stacktrace
            log.error("Error inesperado en la aplicación", e);
        }
    }
}
