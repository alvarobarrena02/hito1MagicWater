package com.ejercicio.hito1magicwater.repository;

import com.ejercicio.hito1magicwater.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RepositorioUsuario extends JpaRepository<Usuario, String> {

}
