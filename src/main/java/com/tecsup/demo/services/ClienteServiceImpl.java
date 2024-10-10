package com.tecsup.demo.services;

import com.tecsup.demo.modelo.daos.ClienteRepository;
import com.tecsup.demo.modelo.entidades.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository dao;

    @Override
    @Transactional(readOnly = false)
    public void grabar(Cliente cliente) {
        dao.save(cliente);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(Integer id) {
        dao.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Cliente buscar(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return (List<Cliente>)dao.findAll();
    }
}
