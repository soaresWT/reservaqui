package org.TDD.entity;

import org.TDD.interfaces.iSala;
import org.TDD.interfaces.iUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    private Reserva reserva;
    private iUsuario mockUsuario;
    private iSala mockSala;
    private Date dataInicio;
    private Date dataFim;

    @BeforeEach
    void setUp() {
        reserva = new Reserva();
        mockUsuario = mock(iUsuario.class);
        mockSala = mock(iSala.class);
        dataInicio = new Date();
        dataFim = new Date();
    }

    @Test
    void testSetAndGetResponsavel() {
        reserva.setResponsavel(mockUsuario);
        assertEquals(mockUsuario, reserva.getResponsavel());
    }

    @Test
    void testSetAndGetSala() {
        reserva.setSala(mockSala);
        assertEquals(mockSala, reserva.getSala());
    }

    @Test
    void testSetAndGetDataInicio() {
        reserva.setDataInicio(dataInicio);
        assertEquals(dataInicio, reserva.getDataInicio());
    }

    @Test
    void testSetAndGetDataFim() {
        reserva.setDataFim(dataFim);
        assertEquals(dataFim, reserva.getDataFim());
    }
}