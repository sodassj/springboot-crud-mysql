package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

}
