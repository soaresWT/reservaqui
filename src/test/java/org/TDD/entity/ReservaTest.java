package org.TDD.entity;

import org.TDD.interfaces.iReserva;
import org.TDD.interfaces.iSala;
import org.TDD.interfaces.iUsuario;
import org.TDD.services.ReservaService;
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
    private ReservaService reservaService;

    @BeforeEach
    void setUp() {
        reserva = new Reserva();
        mockUsuario = mock(iUsuario.class);
        mockSala = mock(iSala.class);
        dataInicio = new Date();
        dataFim = new Date();
        reservaService = new ReservaService();
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

    @Test
    void deveLancarExcecaoSeConflitoDeHorarioForDetectado() {
        iSala sala = new Sala();

        iReserva reserva1 = new Reserva();
        reserva1.setSala(sala);
        reserva1.setDataInicio(new Date(System.currentTimeMillis()));
        reserva1.setDataFim(new Date(System.currentTimeMillis() + 100000));

        iReserva reserva2 = new Reserva();
        reserva2.setSala(sala);
        reserva2.setDataInicio(new Date(System.currentTimeMillis() + 50000));
        reserva2.setDataFim(new Date(System.currentTimeMillis() + 150000));

        reservaService.cadastrar(reserva1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.cadastrar(reserva2);
        });

        String expectedMessage = "Conflito de horário detectado para a sala";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void naoDeveLancarExcecaoSeNaoHouverConflitoDeHorario() {
        Reserva reserva1 = new Reserva();
        reserva1.setDataInicio(new Date(System.currentTimeMillis()));
        reserva1.setDataFim(new Date(System.currentTimeMillis() + 100000));

        Reserva reserva2 = new Reserva();
        reserva2.setDataInicio(new Date(System.currentTimeMillis() + 200000));
        reserva2.setDataFim(new Date(System.currentTimeMillis() + 300000));

        reservaService.cadastrar(reserva1);
        assertDoesNotThrow(() -> reservaService.cadastrar(reserva2));
    }
}