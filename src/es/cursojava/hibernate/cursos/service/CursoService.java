package es.cursojava.hibernate.cursos.service;

import es.cursojava.hibernate.cursos.dao.CursoDAO;
import es.cursojava.hibernate.cursos.dto.AulaDTO;
import es.cursojava.hibernate.cursos.dto.CursoDTO;
import es.cursojava.hibernate.cursos.entity.Aula;
import es.cursojava.hibernate.cursos.entity.Curso;
import org.hibernate.Session;

public class CursoService {

    private final Session session;
    private final CursoDAO cursoDAO;

    public CursoService(Session session) {
        this.session = session;
        this.cursoDAO = new CursoDAO(session);
    }

    // --------- MAPEOS DTO <-> ENTITY ---------

    private Aula mapAulaDTOToEntity(AulaDTO dto) {
        if (dto == null) return null;
        Aula aula = new Aula();
        aula.setId(dto.getId());
        aula.setCodigoAula(dto.getCodigoAula());
        aula.setCapacidad(dto.getCapacidad());
        aula.setUbicacion(dto.getUbicacion());
        return aula;
    }

    private AulaDTO mapAulaToDTO(Aula aula) {
        if (aula == null) return null;
        AulaDTO dto = new AulaDTO();
        dto.setId(aula.getId());
        dto.setCodigoAula(aula.getCodigoAula());
        dto.setCapacidad(aula.getCapacidad());
        dto.setUbicacion(aula.getUbicacion());
        return dto;
    }

    private Curso mapCursoDTOToEntity(CursoDTO dto) {
        if (dto == null) return null;
        Curso curso = new Curso();
        curso.setId(dto.getId());
        curso.setCodigo(dto.getCodigo());
        curso.setNombre(dto.getNombre());
        curso.setDescripcion(dto.getDescripcion());
        curso.setHorasTotales(dto.getHorasTotales());
        curso.setActivo(dto.isActivo());
        curso.setNivel(dto.getNivel());
        curso.setCategoria(dto.getCategoria());
        curso.setPrecio(dto.getPrecio());
        curso.setFechaInicio(dto.getFechaInicio());
        curso.setFechaFin(dto.getFechaFin());
        // fechaCreacion la pone @PrePersist
        return curso;
    }

    private CursoDTO mapCursoToDTO(Curso curso) {
        if (curso == null) return null;
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setCodigo(curso.getCodigo());
        dto.setNombre(curso.getNombre());
        dto.setDescripcion(curso.getDescripcion());
        dto.setHorasTotales(curso.getHorasTotales());
        dto.setActivo(curso.isActivo());
        dto.setNivel(curso.getNivel());
        dto.setCategoria(curso.getCategoria());
        dto.setPrecio(curso.getPrecio());
        dto.setFechaInicio(curso.getFechaInicio());
        dto.setFechaFin(curso.getFechaFin());
        dto.setAula(mapAulaToDTO(curso.getAula()));
        return dto;
    }

    // --------- VALIDACIONES ---------

    private void validarAula(Aula aula) {
        if (aula.getCapacidad() == null || aula.getCapacidad() <= 0) {
            throw new IllegalArgumentException("La capacidad del aula debe ser mayor que 0");
        }
    }

    private void validarAulaNoAsignada(Long aulaId) {
        Long count = session.createQuery(
                        "SELECT COUNT(c) FROM Curso c WHERE c.aula.id = :aulaId",
                        Long.class
                )
                .setParameter("aulaId", aulaId)
                .uniqueResult();
        if (count != null && count > 0) {
            throw new IllegalStateException("El aula ya está asignada a otro curso");
        }
    }

    // --------- MÉTODOS DEL ENUNCIADO ---------

    /**
     * Crea un curso y un aula a la vez, y los guarda en BD.
     */
    public CursoDTO crearCursoConAula(CursoDTO cursoDTO, AulaDTO aulaDTO) {
        Aula aula = mapAulaDTOToEntity(aulaDTO);
        validarAula(aula);

        Curso curso = mapCursoDTOToEntity(cursoDTO);
        curso.setAula(aula); // asocio el aula

        // Como cascade = ALL, al guardar el curso se guarda también el aula
        cursoDAO.guardarCurso(curso);

        return mapCursoToDTO(curso);
    }

    /**
     * Asigna un aula ya existente a un curso ya existente.
     */
    public void asignarAula(Long cursoId, Long aulaId) {
        Curso curso = cursoDAO.obtenerCursoPorId(cursoId);
        if (curso == null) {
            throw new IllegalArgumentException("No existe curso con id " + cursoId);
        }

        Aula aula = session.get(Aula.class, aulaId);
        if (aula == null) {
            throw new IllegalArgumentException("No existe aula con id " + aulaId);
        }

        validarAula(aula);
        validarAulaNoAsignada(aulaId);

        curso.setAula(aula);
        cursoDAO.actualizarCurso(curso);
    }

    public CursoDTO obtenerCursoConAula(Long cursoId) {
        Curso curso = cursoDAO.obtenerCursoConAula(cursoId);
        return mapCursoToDTO(curso);
    }
}
