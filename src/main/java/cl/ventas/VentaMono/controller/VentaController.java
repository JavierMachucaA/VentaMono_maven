package cl.ventas.VentaMono.controller;

import cl.ventas.VentaMono.entity.Venta;
import cl.ventas.VentaMono.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public String listarVentas(Model model){
        List<Venta> listaVentas= ventaService.listarVentas();
        model.addAttribute("listaVentas", listaVentas);
        return "ventas";
    }

    // Mostrar formulario de nueva venta
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaVenta(Model modelo) {
        modelo.addAttribute("venta", new Venta());
        return "venta_form";  // vista del formulario de venta (para alta)
    }

    // Guardar venta (nueva o editada)
    @PostMapping
    public String guardarVenta(@ModelAttribute("venta") Venta venta) {
        ventaService.guardarVenta(venta);
        return "redirect:/ventas";
    }

    // Mostrar formulario de edición de venta existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarVenta(@PathVariable("id") Long id, Model modelo) {
        Venta venta = ventaService.obtenerVentaPorId(id);
        if (venta != null) {
            modelo.addAttribute("venta", venta);
            return "venta_form";  // reutiliza la misma plantilla del formulario
        } else {
            // Si no se encuentra la venta, redirige al listado
            return "redirect:/ventas";
        }
    }

    // Eliminar una venta por id
    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable("id") Long id) {
        ventaService.eliminarVenta(id);
        return "redirect:/ventas";
    }

}
