package es.cursojava.hibernate.cursos.dao;

import es.cursojava.hibernate.cursos.entity.Aula;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AulaDAO {

    private static final Logger log = LoggerFactory.getLogger(AulaDAO.class);

    private final Session session;

    public AulaDAO(Session session) {
        this.session = session;
    }

    public void guardar(Aula aula) {
        log.debug("Guardando aula {}", aula.getCodigoAula());
        session.persist(aula);
    }

    public Aula buscarPorId(Long id) {
        return session.get(Aula.class, id);
    }

    public Aula buscarPorCodigo(String codigoAula) {
        return session.createQuery(
                        "FROM Aula a WHERE a.codigoAula = :codigo",
                        Aula.class
                )
                .setParameter("codigo", codigoAula)
                .uniqueResult();
    }
}
