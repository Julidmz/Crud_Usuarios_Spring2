package com.app.web.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.web.entidad.Usuario;
import com.app.web.repositorio.UsuarioRepositorio;

public interface UsuarioServicio {

    public List<Usuario> listarTodosLosUsuarios();
    
    public Usuario guardarUsuario(Usuario usuario);
    
    public Usuario obtenerUsuarioPorId(Long id);
    
    public Usuario actualizarUsuario(Usuario usuario);
    
    public void eliminarUsuario(Long id);
    
    List<Usuario> listarUsuariosPorEstado(String estado); // Método para obtener sin paginación

    // Métodos con paginación
    Page<Usuario> listarTodosLosUsuarios(Pageable pageable);
    
    Page<Usuario> listarUsuariosPorEstado(boolean activo, Pageable pageable); // Método para paginación
}

