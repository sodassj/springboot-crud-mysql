package com.tecsup.demo.services;

import com.tecsup.demo.modelo.daos.CochesemiRepository;
import com.tecsup.demo.modelo.entidades.Cochesemi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CochesemiServiceImpl implements CochesemiService {
    @Autowired
    private CochesemiRepository dao;

    @Override
    @Transactional(readOnly = false)
    public void grabar(Cochesemi cochesemi) {
        dao.save(cochesemi);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(Integer id) {
        dao.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Cochesemi buscar(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cochesemi> listar() {
        return (List<Cochesemi>)dao.findAll();
    }
}
