package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.web.entidad.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    // Método para encontrar usuarios por estado activo sin paginación
    List<Usuario> findByActivo(Boolean activo); // Para listar sin paginación

    Page<Usuario> findByActivo(boolean activo, Pageable pageable); // Para listar con paginación

}
    



