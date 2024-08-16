package org.TDD.interfaces;

import org.TDD.entity.StatusReserva;

import java.util.Date;

public interface iReserva {
    iSala getSala();
    void setSala(iSala sala);

    iUsuario getResponsavel();
    void setResponsavel(iUsuario responsavel);

    Date getDataInicio();
    void setDataInicio(Date dataInicio);

    Date getDataFim();
    void setDataFim(Date dataFim);

    StatusReserva getStatus();
    void setStatus(StatusReserva status);
}
