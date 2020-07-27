package com.renzo.dawii.service;

import com.renzo.dawii.bean.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> findAll();
    Usuario findbyName(String nombre);
    Usuario getUsuario(int id);
    String adminUsuario(Usuario u);
    Usuario saveUser(Usuario usuario);
}
