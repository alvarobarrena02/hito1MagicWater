package com.ejercicio.hito1magicwater.controller;

import com.ejercicio.hito1magicwater.model.Proyecto;
import com.ejercicio.hito1magicwater.model.Tarea;
import com.ejercicio.hito1magicwater.repository.RepositorioProyecto;
import com.ejercicio.hito1magicwater.repository.RepositorioTarea;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class Controlador {

    @Autowired
    private RepositorioTarea repositorioTarea;

    @Autowired
    private RepositorioProyecto repositorioProyecto;

    @GetMapping("/")
    public String inicio(Model model) {
        List<Proyecto> proyectosLimitados = repositorioProyecto.findAll();
        if (proyectosLimitados.size() > 3) {
            proyectosLimitados = proyectosLimitados.subList(0, 3);
        }
        model.addAttribute("proyectos", proyectosLimitados);
        return "index";
    }

    @GetMapping("/proyectos")
    public String proyectos(Model model, Authentication authentication) {
        List<Proyecto> proyectos = repositorioProyecto.findAll();
        model.addAttribute("proyectos", proyectos);
        boolean esSupervisor = authentication.getAuthorities().contains(new SimpleGrantedAuthority("SUPERVISOR"));
        model.addAttribute("esSupervisor", esSupervisor);
        return "proyectos";
    }

    @GetMapping("/proyecto/nuevo")
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public String mostrarFormularioNuevoProyecto(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "crearProyecto";
    }

    @PostMapping("/proyecto/guardar")
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public String guardarProyecto(@ModelAttribute("proyecto") Proyecto proyecto) {
        repositorioProyecto.save(proyecto);
        return "redirect:/proyectos";
    }

    @GetMapping("/tarea/nueva")
    public String mostrarFormularioNuevaTarea(Model model) {
        model.addAttribute("tarea", new Tarea());
        model.addAttribute("proyectos", repositorioProyecto.findAll());
        return "crearTarea";
    }

    @PostMapping("/tarea/guardar")
    public String guardarTarea(@ModelAttribute("tarea") Tarea tarea) {
        repositorioTarea.save(tarea);
        return "redirect:/tareas";
    }

    @GetMapping("/tarea/editar/{id}")
    public String mostrarFormularioEditarTarea(@PathVariable("id") Integer id, Model model) {
        Tarea tarea = repositorioTarea.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de tarea inválido:" + id));
        model.addAttribute("tarea", tarea);
        model.addAttribute("proyectos", repositorioProyecto.findAll());
        return "editarTarea";
    }

    @PostMapping("/tarea/actualizar")
    public String actualizarTarea(@ModelAttribute("tarea") Tarea tarea) {
        repositorioTarea.save(tarea);
        return "redirect:/tareas";
    }

    @GetMapping("/tarea/eliminar/{id}")
    public String eliminarTarea(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            repositorioTarea.deleteById(id);
            redirectAttributes.addFlashAttribute("mensajeExito", "Tarea eliminada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al eliminar la tarea.");
        }
        return "redirect:/tareas";
    }

    @GetMapping("/tareas")
    public String listarTareas(Model model, Authentication authentication) {
        boolean esSupervisor = authentication.getAuthorities().contains(new SimpleGrantedAuthority("SUPERVISOR"));

        // Línea de depuración para confirmar el valor de esSupervisor
        System.out.println("Es supervisor: " + esSupervisor);

        List<Tarea> tareas;
        if (esSupervisor) {
            // Cargar todas las tareas para el supervisor
            tareas = repositorioTarea.findAll();
        } else {
            // Cargar solo las tareas asignadas al usuario (trabajador)
            String nifUsuarioActual = authentication.getName();
            tareas = repositorioTarea.findByUsuarioNif(nifUsuarioActual);
        }

        model.addAttribute("esSupervisor", esSupervisor);
        model.addAttribute("tareas", tareas);
        return "tareas";
    }

    @GetMapping("/tareas/estadisticas")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public String verEstadisticas(Model model) {
        model.addAttribute("totalTareas", repositorioTarea.contarTodasLasTareas());
        model.addAttribute("tareasCompletadas", repositorioTarea.contarTareasCompletadas());
        model.addAttribute("tareasEnProgreso", repositorioTarea.contarTareasEnProgreso());

        // Añade más estadísticas según sea necesario
        return "estadisticasTareas";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
}
