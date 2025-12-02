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
            log.info("=== Alta de empleados ===".toUpperCase());
            // Eliminar empleados previos si existen (para pruebas repetibles)
            empleadoService.eliminarPorNif("11111111A");

            EmpleadoDto emp1 = new EmpleadoDto("11111111A", "Ana", "IT", new BigDecimal("1800.00"));
            EmpleadoDto empCreado1 = empleadoService.altaEmpleado(emp1);
            log.info("Creado empleado: {}", empCreado1);

            EmpleadoDto emp2 = new EmpleadoDto("22222222B", "Luis", "VENTAS", new BigDecimal("2000.00"));
            EmpleadoDto empCreado2 = empleadoService.altaEmpleado(emp2);
            log.info("Creado empleado: {}".toUpperCase(), empCreado2);

            log.info("=== Buscar por NIF ===".toUpperCase());
            EmpleadoDto buscado = empleadoService.buscarPorNif("11111111A");
            log.info("Empleado encontrado por NIF 11111111A: {}".toUpperCase(), buscado);

            log.info("=== Listar por departamento (IT) ===".toUpperCase());
            List<EmpleadoDto> listaIt = empleadoService.listarPorDepartamento("IT");
            listaIt.forEach(e -> log.info("Empleado IT: {}".toUpperCase(), e));

            log.info("=== Actualizar salario ===".toUpperCase());
            EmpleadoDto actualizado = empleadoService.actualizarSalario("11111111A", new BigDecimal("1900.00"));
            log.info("Empleado actualizado: {}".toUpperCase(), actualizado);

            log.info("=== Listar todos ===".toUpperCase());
            List<EmpleadoDto> todos = empleadoService.listarTodos();
            todos.forEach(e -> log.info("Empleado en BBDD: {}".toUpperCase(), e));

        } catch (BusinessException e) {
            // Error de negocio esperado → WARN/ERROR con mensaje
            log.warn("Error de negocio: {}", e.getMessage());
        } catch (Exception e) {
            // Error inesperado → ERROR con stacktrace
            log.error("Error inesperado en la aplicación", e);
        }
    }
}
