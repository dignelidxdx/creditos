package ar.com.ada.creditos.managers;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ar.com.ada.creditos.entities.Cancelacion;

public class CancelacionManager {

    protected SessionFactory sessionFactory;

    

    public void setup() {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures settings
                                                                                                  // from
                                                                                                  // hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw ex;
        }


    }

    public void exit() {
        sessionFactory.close();
    }

    public void create(Cancelacion cancelacion) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(cancelacion);

        session.getTransaction().commit();
        session.close();
    }

    public Cancelacion read(int cancelacionId) {
        Session session = sessionFactory.openSession();

        Cancelacion cancelacion = session.get(Cancelacion.class, cancelacionId);

        session.close();

        return cancelacion;
    }

    public void update(Cancelacion cancelacion) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(cancelacion);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Cancelacion cancelacion) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(cancelacion);

        session.getTransaction().commit();
        session.close();
    }

    public Cancelacion buscarPorIdDCancelacion(int idDeCancelacion) {
        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("SELECT * FROM cancelacion where cancelacion_id = ?", Cancelacion.class);
        query.setParameter(1, idDeCancelacion);
        Cancelacion cancelacionId = (Cancelacion) query.getSingleResult();
        return cancelacionId;
    }

    public Boolean eliminacionLogica(int idDeCancelacion) {
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery("UPDATE cancelacion SET eliminado = 1 WHERE cancelacion_id = ?", Cancelacion.class);
        query.setParameter(1, idDeCancelacion);
        session.beginTransaction();

        int cambio = query.executeUpdate();
        if(cambio == 1) {
            session.getTransaction().commit();
            session.close();
            return true;
        } else {
            session.close();
            return false;
        }
    }

}