package ar.com.ada.creditos.services;

import java.util.List;

import ar.com.ada.creditos.entities.reportes.ReporteDePrestamos;
import ar.com.ada.creditos.entities.reportes.ReportePrestamoPorCliente;
import ar.com.ada.creditos.managers.PrestamoManager;

public class PrestamoService {

    public PrestamoManager repo = new PrestamoManager();

    public PrestamoService(PrestamoManager repo) {
        this.repo = repo;
        repo.setup();
    }

	public void reportePrestamoPorCliente(int id) {

        List<ReportePrestamoPorCliente> clientes = repo.generarReportePrestamoCliente(id);
        for (ReportePrestamoPorCliente cliente : clientes) {
            mostrarReportePrestamoPorCliente(cliente);
        }
    }

    public void mostrarReportePrestamoPorCliente(ReportePrestamoPorCliente reporte) {

        System.out.print("Prestamos realizado por: " +
         reporte.getNombre() + "\nCon Id de: " + reporte.getClienteId() + "\nCon una cantidad de prestamos: " + reporte.getCantidadPrestamos() +
          " \nImporte Maximo: " + reporte.getImporteMaximo() +
          " \nTotal de Prestamo: " + reporte.getTotalPrestamo());
        System.out.println();
    }

    public void reporteDePrestamo(int id) {
        List<ReporteDePrestamos> prestamos = repo.generarReportePrestamos(id);
        for (ReporteDePrestamos prestamo : prestamos) {
            mostrarReportePrestamo(prestamo);
        }
    }
    public void mostrarReportePrestamo(ReporteDePrestamos reporte) {
        System.out.println(
            "Estado de prestamo: " +
            "\nId de prestamo: " + reporte.getPrestamoId() +
            "\nCantidad de prestamo: " + reporte.getCantidadDePrestamos() +
            "\nTotal de importe: " + reporte.getTotalDeCash()
        );
    }

    
    
}