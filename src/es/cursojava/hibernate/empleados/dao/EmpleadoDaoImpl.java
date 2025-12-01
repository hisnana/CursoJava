package es.cursojava.hibernate.empleados.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import es.cursojava.hibernate.empleados.entity.Empleado;
import es.cursojava.utils.UtilidadesHibernate; // AJUSTA ESTE IMPORT SI TU UtilidadesHibernate ESTÁ EN OTRA RUTA

public class EmpleadoDaoImpl implements EmpleadoDao {

    @Override
    public void guardar(Empleado empleado) {
        Transaction tx = null;
        try (Session session = UtilidadesHibernate.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(empleado);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public Empleado buscarPorId(Long id) {
        try (Session session = UtilidadesHibernate.getSessionFactory().openSession()) {
            return session.get(Empleado.class, id);
        }
    }

    @Override
    public Empleado buscarPorNif(String nif) {
        try (Session session = UtilidadesHibernate.getSessionFactory().openSession()) {
            String hql = "FROM Empleado e WHERE e.nif = :nif";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            query.setParameter("nif", nif);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Empleado> buscarPorDepartamento(String departamento) {
        try (Session session = UtilidadesHibernate.getSessionFactory().openSession()) {
            String hql = "FROM Empleado e WHERE e.departamento = :dep";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            query.setParameter("dep", departamento);
            return query.list();
        }
    }

    @Override
    public List<Empleado> buscarTodos() {
        try (Session session = UtilidadesHibernate.getSessionFactory().openSession()) {
            String hql = "FROM Empleado";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            return query.list();
        }
    }
}
	