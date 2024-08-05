package org.TDD.dao;

import org.TDD.entity.Usuario;

public interface UsuarioDAO {
    void save(Usuario usuario);

    void list();

    void delete();

    void findById(int id);
}
