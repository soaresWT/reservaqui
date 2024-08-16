package org.TDD.entity;

import org.TDD.interfaces.iSala;

public class Sala implements iSala {
    private String bloco;
    private String andar;
    private int numero;
    private Boolean projetor;
    private Boolean arcondicionado;
    private int capacidade;
    public Sala() {
    }
    public Sala(String bloco, String andar, int numero, Boolean projetor, Boolean arcondicionado, int capacidade) {
        this.bloco = bloco;
        this.andar = andar;
        this.numero = numero;
        this.projetor = projetor;
        this.arcondicionado = arcondicionado;
        this.capacidade = capacidade;
    }

    @Override
    public String getBloco() {
        return this.bloco;
    }

    @Override
    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    @Override
    public String getAndar() {
        return this.andar;
    }

    @Override
    public void setAndar(String andar) {
        this.andar = andar;
    }

    @Override
    public int getNumero() {
        return this.numero;
    }

    @Override
    public void setNumero(int numero) { this.numero = numero; }

    @Override
    public Boolean getProjetor() {
        return this.projetor;
    }

    @Override
    public void setProjetor(Boolean projetor) {
        this.projetor = projetor;
    }

    @Override
    public Boolean getArCondicionado() {
        return this.arcondicionado;
    }

    @Override
    public void setArCondicionado(Boolean arCondicionado) {
        this.arcondicionado = arCondicionado;
    }

    @Override
    public int getCapacidade() {
        return capacidade;
    }

    @Override
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
