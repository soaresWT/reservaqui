package org.TDD.dao;

import org.TDD.entity.Sala;

public interface SalaDAO {

    void save(Sala sala);

    void delete(int id);

    void list();

    void findById(int id);
}
