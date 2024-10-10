package com.tecsup.demo.services;

import com.tecsup.demo.modelo.entidades.Usuario;

import java.util.List;

public interface UsuarioService {

    public List<Usuario> listar();

    public void grabar(Usuario usuario);

    public Usuario buscar(Integer id);

    public void eliminar(Integer id);

}

