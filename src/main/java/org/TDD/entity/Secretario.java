package org.TDD.entity;

import org.TDD.interfaces.iUsuario;

public class Secretario extends Usuario {
    private int siape;

    public Secretario(int siape, String nome, String email, String senha) {
        super(nome, email, senha);
        this.siape = siape;
    }

    public int getSiape() {
        return siape;
    }

    public void setSiape(int siape) {
        this.siape = siape;
        Secretario sec = new Secretario(1, "h", "h", "h");
    }
}
