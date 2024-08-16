package org.TDD.entity;

import org.TDD.interfaces.iReserva;
import org.TDD.interfaces.iSala;
import org.TDD.interfaces.iUsuario;
import org.TDD.services.ReservaValidator;

import java.util.Date;

public class Reserva implements iReserva {
    private iUsuario responsavel;
    private iSala sala;
    private Date dataInicio;
    private Date dataFim;
    private StatusReserva status;

    public Reserva() {
        this.status = StatusReserva.PROCESSANDO;
    }
    @Override
    public iSala getSala() {
        return sala;
    }

    @Override
    public void setSala(iSala sala) {
        this.sala = sala;
    }

    @Override
    public iUsuario getResponsavel() {
        return responsavel;
    }

    @Override
    public void setResponsavel(iUsuario responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public Date getDataInicio() {
        return dataInicio;
    }

    @Override
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public Date getDataFim() {
        return dataFim;
    }

    @Override
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public StatusReserva getStatus() {
        return status;
    }

    @Override
    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public void validarReserva() {
        if (ReservaValidator.isDataFimMenorQueDataInicio(this.dataInicio, this.dataFim)) {
            throw new IllegalArgumentException("A data de fim não pode ser menor que a data de início.");
        }
    }
}

