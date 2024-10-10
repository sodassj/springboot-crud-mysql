package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
