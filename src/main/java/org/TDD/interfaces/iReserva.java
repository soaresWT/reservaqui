package org.TDD.interfaces;

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
}
