package ar.com.ada.creditos.managers;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

import ar.com.ada.creditos.entities.Cliente;
import ar.com.ada.creditos.entities.Prestamo;
import ar.com.ada.creditos.entities.reportes.ReportePrestamoPorCliente;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class PrestamoManager {

        protected SessionFactory sessionFactory;

        public void setup() {
                java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures
                                                                                                          // settings
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

        public void create(Prestamo prestamo) {

                Session session = sessionFactory.openSession();
                session.beginTransaction();

                session.save(prestamo);

                session.getTransaction().commit();
                session.close();
        }

        public List<Prestamo> buscarTodos() {

                Session session = sessionFactory.openSession();

                /// NUNCA HARCODEAR SQLs nativos en la aplicacion.
                // ESTO es solo para nivel educativo
                Query query = session.createNativeQuery("SELECT * FROM prestamo", Prestamo.class);

                List<Prestamo> todos = query.getResultList();

                return todos;

        }

        public Prestamo buscarPorIdPrestamo(int idDelPrestamo) {

                Session session = sessionFactory.openSession();
                Query query = session.createNativeQuery("SELECT * FROM prestamo where prestamo_id = '" + idDelPrestamo + "'", Prestamo.class);

                Prestamo prestamoPorId = (Prestamo) query.getSingleResult();

                return prestamoPorId;
        }

        public List<ReportePrestamoPorCliente>  generarReportePrestamoCliente(int idCliente) {

                Session session = sessionFactory.openSession();
                // Query queryReportesPorCliente = session.createNativeQuery(
                //         "SELECT c.idcliente, c.nombre, count(p) cantidad, sum(p.importe) totalDePrestamo, max(p.importe) maximoDeImportePrestamo FROM cliente c inner join prestamo p on c.idcliente = p.idcliente WHERE c.idcliente = ? GROUP BY c.idcliente, c.nombre",
                //             ReportePrestamoPorCliente.class);                
                // queryReportesPorCliente.setParameter(1, idCliente);

                Query queryJPQLConParametros = session.createQuery("SELECT r.cliente.nombre, r.prestamo.importe, r.totalPrestamo, r.importeMaximo, r.cantidadPrestamo FROM ReportePrestamoPorCliente r where r.cliente.clienteId = :nombreFiltro", ReportePrestamoPorCliente.class);
                queryJPQLConParametros.setParameter("nombreFiltro", idCliente);

                List<ReportePrestamoPorCliente> reportePrestamoPorCliente = queryJPQLConParametros.getResultList();

                return reportePrestamoPorCliente;
        }
}