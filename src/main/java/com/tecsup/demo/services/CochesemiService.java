package com.tecsup.demo.services;

import com.tecsup.demo.modelo.entidades.Cochesemi;

import java.util.List;

public interface CochesemiService {

    public List<Cochesemi> listar();

    public void grabar(Cochesemi cochesemi);

    public Cochesemi buscar(Integer id);

    public void eliminar(Integer id);

}
