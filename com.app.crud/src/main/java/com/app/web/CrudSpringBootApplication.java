package com.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.web.entidad.Usuario;
import com.app.web.repositorio.UsuarioRepositorio;

@SpringBootApplication
public class CrudSpringBootApplication /*implements CommandLineRunner*/ {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringBootApplication.class, args);
    }

    /*@Autowired
    private UsuarioRepositorio repositorio;
    
    @Override
    public void run(String... args) throws Exception {
        Usuario usuario1 = new Usuario("Julian", "Dominguez", "julidmz46@gmail.com", "empleado", "activo", "Septiembre 13, 2024");
        repositorio.save(usuario1);
        
        Usuario usuario2 = new Usuario("Martin", "Lopez", "Mlopez.1406@gmail.com", "empleado", "activo", "Septiembre 13, 2024");
        repositorio.save(usuario2);
    }*/

}

