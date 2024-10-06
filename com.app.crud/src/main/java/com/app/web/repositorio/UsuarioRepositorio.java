package com.app.web.repositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.web.entidad.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Page<Usuario> findByLegajoContaining(String legajo, Pageable pageable);
    long countByLegajoContaining(String legajo);
}

    



