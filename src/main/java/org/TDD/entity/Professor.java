package org.TDD.entity;

public class Professor extends Usuario{
    private int id;

    public Professor(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public int getId() {
        return this.id;
    }
}
