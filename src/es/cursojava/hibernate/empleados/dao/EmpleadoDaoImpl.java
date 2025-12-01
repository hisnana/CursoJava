package es.cursojava.hibernate.empleados.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.cursojava.hibernate.empleados.entity.Empleado;
import es.cursojava.utils.UtilidadesHibernate; // AJUSTA ESTE IMPORT SI TU UtilidadesHibernate ESTÁ EN OTRA RUTA

public class EmpleadoDaoImpl implements EmpleadoDao {

    private static final Logger log = LoggerFactory.getLogger(EmpleadoDaoImpl.class);

    @Override
    public void guardar(Empleado empleado) {
        Transaction tx = null;
        try (Session session = UtilidadesHibernate.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(empleado);
            tx.commit();
            log.debug("Empleado guardado/actualizado en BBDD: {}", empleado);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.error("Error guardando/actualizando empleado en BBDD", e);
            throw e;
        }
    }

    @Override
    public Empleado buscarPorId(Long id) {
        try (Session session = UtilidadesHibernate.getSessionFactory().openSession()) {
            Empleado emp = session.get(Empleado.class, id);
            log.debug("Buscar por ID {} → {}", id, emp);
            return emp;
        }
    }

    @Override
    public Empleado buscarPorNif(String nif) {
        try (Session session = UtilidadesHibernate.getSessionFactory().openSession()) {
            String hql = "FROM Empleado e WHERE e.nif = :nif";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            query.setParameter("nif", nif);
            Empleado emp = query.uniqueResult();
            log.debug("Buscar por NIF {} → {}", nif, emp);
            return emp;
        }
    }

    @Override
    public List<Empleado> buscarPorDepartamento(String departamento) {
        try (Session session = UtilidadesHibernate.getSessionFactory().openSession()) {
            String hql = "FROM Empleado e WHERE e.departamento = :dep";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            query.setParameter("dep", departamento);
            List<Empleado> lista = query.list();
            log.debug("Buscar por departamento {} → {} resultados", departamento, lista.size());
            return lista;
        }
    }

    @Override
    public List<Empleado> buscarTodos() {
        try (Session session = UtilidadesHibernate.getSessionFactory().openSession()) {
            String hql = "FROM Empleado";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            List<Empleado> lista = query.list();
            log.debug("Buscar todos los empleados → {} resultados", lista.size());
            return lista;
        }
    }
}