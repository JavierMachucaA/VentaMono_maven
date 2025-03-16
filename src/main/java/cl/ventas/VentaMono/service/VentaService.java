package cl.ventas.VentaMono.service;

import cl.ventas.VentaMono.entity.Venta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VentaService {
    List<Venta> listarVentas();
    Venta obtenerVentaPorId(Long id);
    Venta guardarVenta(Venta venta);
    void eliminarVenta(Long id);
}
