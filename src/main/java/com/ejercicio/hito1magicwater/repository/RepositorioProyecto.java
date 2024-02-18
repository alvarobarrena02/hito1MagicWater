package com.ejercicio.hito1magicwater.repository;

import com.ejercicio.hito1magicwater.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProyecto extends JpaRepository<Proyecto, Integer> {

}
