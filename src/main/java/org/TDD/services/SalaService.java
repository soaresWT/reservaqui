package org.TDD.services;

import org.TDD.interfaces.iSala;

public class SalaService  implements  iSalaService {
    @Override
    public void cadastrar(iSala sala) {
        validarSala(sala);

    }

    @Override
    public void excluir(iSala sala) {
        validarSala(sala);
    }

    @Override
    public void editar(iSala sala) {
        validarSala(sala);

    }

    private void validarSala(iSala sala) {
        if (sala.getBloco() == null || sala.getBloco().isEmpty()) {
            throw new IllegalArgumentException("Bloco é obrigatório");
        }
        if (sala.getAndar() == null || sala.getAndar().isEmpty()) {
            throw new IllegalArgumentException("Andar é obrigatório");
        }
        if (sala.getNumero() == 0) {
            throw new IllegalArgumentException("Número é obrigatório");
        }
        if (sala.getCapacidade() <= 0) {
            throw new IllegalArgumentException("Capacidade é obrigatória");
        }
    }
}
