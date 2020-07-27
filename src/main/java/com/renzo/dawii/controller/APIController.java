package com.renzo.dawii.controller;

import com.renzo.dawii.bean.Usuario;
import com.renzo.dawii.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class APIController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/api/user/list")
    public List<Usuario> getUsuarios(){
        return usuarioService.findAll();
    }

    @PostMapping
    public ResponseEntity saveUser(@Validated Usuario usuario){

        System.out.println(usuario.getNombre());

        return ResponseEntity.ok().build();
    }

}
