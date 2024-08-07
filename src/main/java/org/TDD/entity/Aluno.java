package org.TDD.entity;

import org.TDD.interfaces.iUsuario;

public class Aluno extends Usuario {
    private int matricula;
    private String curso;


    public Aluno(int matricula, String curso, String nome, String email, String senha) {
        super(nome, email, senha);
        this.matricula = matricula;
        this.curso = curso;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return this.curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
