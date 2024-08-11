package org.TDD.services;

import java.util.Date;

public class ReservaValidator {
    public static boolean isDataFimMenorQueDataInicio(Date dataInicio, Date dataFim) {
        if (dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("As datas não podem ser nulas");
        }
        return dataFim.before(dataInicio);
    }
}
