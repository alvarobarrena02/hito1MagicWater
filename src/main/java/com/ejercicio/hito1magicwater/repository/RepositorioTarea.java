package com.ejercicio.hito1magicwater.repository;

import com.ejercicio.hito1magicwater.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RepositorioTarea extends JpaRepository<Tarea, Integer> {
    List<Tarea> findByUsuarioNif(String nif);

    @Query("SELECT COUNT(t) FROM Tarea t")
    Long contarTodasLasTareas();

    @Query("SELECT COUNT(t) FROM Tarea t WHERE t.estado = 'Completada'")
    Long contarTareasCompletadas();

    @Query("SELECT COUNT(t) FROM Tarea t WHERE t.estado = 'En Progreso'")
    Long contarTareasEnProgreso();
}
