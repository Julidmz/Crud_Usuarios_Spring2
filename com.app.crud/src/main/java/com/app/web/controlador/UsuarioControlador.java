package com.app.web.controlador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.web.entidad.Usuario;
import com.app.web.servicio.UsuarioServicio;

@Controller
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio servicio;

    @GetMapping("/usuarios")
    public String listarUsuarios(
        @RequestParam(value = "estado", required = false, defaultValue = "Activo") String estado, // Por defecto muestra los activos
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        Model model) {
    
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Usuario> usuariosPage;
    
        // Manejo del filtrado basado en el estado
        if ("Activo".equals(estado)) {
            usuariosPage = servicio.listarUsuariosPorEstado(true, pageable); // Filtra usuarios activos
        } else if ("Inactivo".equals(estado)) {
            usuariosPage = servicio.listarUsuariosPorEstado(false, pageable); // Filtra usuarios inactivos
        } else {
            usuariosPage = servicio.listarUsuariosPorEstado(true, pageable); // Por defecto, muestra activos
        }
    
        model.addAttribute("usuariosPage", usuariosPage);
        model.addAttribute("estados", new String[]{"Activo", "Inactivo"});
        return "usuarios";
    }
    

    @GetMapping("/usuarios/nuevo")
    public String crearUsuarioFormulario(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "crear_usuario";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "crear_usuario";
        }
    
        // No hace falta establecer un valor predeterminado, ya que el checkbox maneja esto
        servicio.guardarUsuario(usuario);
    
        return "redirect:/usuarios";
    }
    

    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", servicio.obtenerUsuarioPorId(id));
        return "editar_usuario";
    }

    @PostMapping("/usuarios/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute("usuario") Usuario usuario, Model model) {
        Usuario usuarioExistente = servicio.obtenerUsuarioPorId(id);
        usuarioExistente.setId(id);
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setDni(usuario.getDni());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setRol(usuario.getRol());
        usuarioExistente.setContrasena(usuario.getContrasena());
        usuarioExistente.setActivo(usuario.isActivo());

        servicio.actualizarUsuario(usuarioExistente);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        servicio.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

    // Nuevo método para mostrar el menú principal
    @GetMapping("/menu-principal")
    public String mostrarMenuPrincipal() {
        return "menu-principal"; // Retorna el archivo menu-principal.html
    }
}

