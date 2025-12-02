package es.cursojava.hibernate.empleados.service;

import es.cursojava.hibernate.empleados.dao.EmpleadoDao;
import es.cursojava.hibernate.empleados.dto.EmpleadoDto;
import es.cursojava.hibernate.empleados.entity.Empleado;
import es.cursojava.hibernate.empleados.exception.BusinessException;
import es.cursojava.hibernate.empleados.interfaces.EmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;   // 👈 añade esto

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmpleadoServiceImpl implements EmpleadoService {
	
	private static final Logger log = LoggerFactory.getLogger(EmpleadoServiceImpl.class); // 👈 logger

    private final EmpleadoDao empleadoDao;

    // Departamentos válidos opcionales (del enunciado)
    private static final Set<String> DEPARTAMENTOS_VALIDOS =
            new HashSet<>(Arrays.asList("IT", "RRHH", "VENTAS", "FINANZAS"));

    public EmpleadoServiceImpl(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }

    @Override
    public EmpleadoDto altaEmpleado(EmpleadoDto empleadoDto) throws BusinessException {
        validarEmpleadoParaAlta(empleadoDto);

        // Comprobar NIF duplicado
        Empleado existente = empleadoDao.buscarPorNif(empleadoDto.getNif());
        if (existente != null) {
            // ⚠️ Ya existe → no creamos uno nuevo, solo avisamos y devolvemos el existente
            log.warn("No se crea empleado; ya existe uno con NIF {}. Se devuelve el existente.", 
                     empleadoDto.getNif());
            return toDto(existente);
        }

        // Si no existe, lo creamos normalmente
        Empleado empleado = toEntity(empleadoDto);
        empleadoDao.guardar(empleado);

        log.info("Empleado creado correctamente con NIF {}", empleado.getNif());
        return toDto(empleado);
    }

    @Override
    public EmpleadoDto actualizarSalario(String nif, BigDecimal nuevoSalario) throws BusinessException {
        if (nif == null || nif.isBlank()) {
            throw new BusinessException("El NIF es obligatorio para actualizar el salario");
        }
        if (nuevoSalario == null || nuevoSalario.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("El salario no puede ser nulo ni negativo");
        }

        // Salario mínimo razonable (opcional del enunciado)
        BigDecimal SALARIO_MINIMO = new BigDecimal("1000.00");
        if (nuevoSalario.compareTo(SALARIO_MINIMO) < 0) {
            throw new BusinessException("El salario debe ser igual o superior a " + SALARIO_MINIMO);
        }

        Empleado empleado = empleadoDao.buscarPorNif(nif);
        if (empleado == null) {
            throw new BusinessException("No existe empleado con NIF " + nif);
        }

        empleado.setSalario(nuevoSalario);
        empleadoDao.guardar(empleado);

        return toDto(empleado);
    }

    @Override
    public EmpleadoDto buscarPorNif(String nif) throws BusinessException {
        if (nif == null || nif.isBlank()) {
            throw new BusinessException("El NIF es obligatorio para la búsqueda");
        }

        Empleado empleado = empleadoDao.buscarPorNif(nif);
        if (empleado == null) {
            throw new BusinessException("No se encontró empleado con NIF " + nif);
        }

        return toDto(empleado);
    }

    @Override
    public List<EmpleadoDto> listarPorDepartamento(String departamento) throws BusinessException {
        if (departamento == null || departamento.isBlank()) {
            throw new BusinessException("El departamento es obligatorio");
        }

        if (!DEPARTAMENTOS_VALIDOS.contains(departamento)) {
            throw new BusinessException("Departamento inválido: " + departamento);
        }

        List<Empleado> lista = empleadoDao.buscarPorDepartamento(departamento);
        return lista.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<EmpleadoDto> listarTodos() throws BusinessException {
        List<Empleado> lista = empleadoDao.buscarTodos();
        return lista.stream().map(this::toDto).collect(Collectors.toList());
    }

    // =====================
    //  Métodos privados
    // =====================

    private void validarEmpleadoParaAlta(EmpleadoDto dto) throws BusinessException {
        if (dto == null) {
            throw new BusinessException("El empleado no puede ser null");
        }

        // 1. NIF obligatorio
        if (dto.getNif() == null || dto.getNif().isBlank()) {
            throw new BusinessException("El NIF es obligatorio");
        }

        // 2. Nombre obligatorio
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new BusinessException("El nombre es obligatorio");
        }

        // 3. Departamento válido
        if (dto.getDepartamento() == null || dto.getDepartamento().isBlank()) {
            throw new BusinessException("El departamento es obligatorio");
        }
        if (!DEPARTAMENTOS_VALIDOS.contains(dto.getDepartamento())) {
            throw new BusinessException("Departamento inválido: " + dto.getDepartamento());
        }

        // 4. Salario válido
        if (dto.getSalario() == null) {
            throw new BusinessException("El salario no puede ser null");
        }
        if (dto.getSalario().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("El salario no puede ser negativo");
        }

        BigDecimal SALARIO_MINIMO = new BigDecimal("1000.00");
        if (dto.getSalario().compareTo(SALARIO_MINIMO) < 0) {
            throw new BusinessException("El salario debe ser al menos " + SALARIO_MINIMO);
        }
    }

    private Empleado toEntity(EmpleadoDto dto) {
        Empleado e = new Empleado();
        e.setId(dto.getId());
        e.setNif(dto.getNif());
        e.setNombre(dto.getNombre());
        e.setDepartamento(dto.getDepartamento());
        e.setSalario(dto.getSalario());
        return e;
    }

    private EmpleadoDto toDto(Empleado e) {
        if (e == null) return null;
        return new EmpleadoDto(
                e.getId(),
                e.getNif(),
                e.getNombre(),
                e.getDepartamento(),
                e.getSalario()
        );
    }
    
    @Override
    public void eliminarPorNif(String nif) throws BusinessException {
        if (nif == null || nif.isBlank()) {
            throw new BusinessException("El NIF es obligatorio para eliminar un empleado");
        }

        Empleado empleado = empleadoDao.buscarPorNif(nif);
        if (empleado == null) {
            throw new BusinessException("No existe empleado con NIF " + nif);
        }

        empleadoDao.eliminar(empleado);
        log.info("Empleado eliminado correctamente con NIF {}", nif);
    }
}
