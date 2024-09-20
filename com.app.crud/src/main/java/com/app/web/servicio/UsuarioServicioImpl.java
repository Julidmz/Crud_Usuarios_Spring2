package com.app.web.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.web.entidad.Usuario;
import com.app.web.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio repositorio;

    @Override
    public List<Usuario> listarTodosLosUsuarios() {
        return repositorio.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        repositorio.deleteById(id);
    }

    // Método para paginación
    @Override
    public Page<Usuario> listarTodosLosUsuarios(Pageable pageable) {
        return repositorio.findAll(pageable);
    }

    // Método para filtrar por estado con paginación
    @Override
    public Page<Usuario> listarUsuariosPorEstado(boolean activo, Pageable pageable) {
        return repositorio.findByActivo(activo, pageable);
    }

    // Método para filtrar sin paginación
    @Override
    public List<Usuario> listarUsuariosPorEstado(String estado) {
        if (estado.equals("Activo")) {
            return repositorio.findByActivo(true);
        } else if (estado.equals("Inactivo")) {
            return repositorio.findByActivo(false);
        }
        return repositorio.findAll(); // Para "todos"
    }

}


