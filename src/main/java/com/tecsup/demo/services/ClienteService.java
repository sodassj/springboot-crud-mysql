package com.tecsup.demo.services;

import com.tecsup.demo.modelo.entidades.Cliente;

import java.util.List;

public interface ClienteService {

    public List<Cliente> listar();

    public void grabar(Cliente cliente);

    public Cliente buscar(Integer id);

    public void eliminar(Integer id);

}
