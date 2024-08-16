package org.TDD.entity;

import org.TDD.interfaces.iReserva;
import org.TDD.interfaces.iSala;
import org.TDD.interfaces.iUsuario;
import org.TDD.services.ReservaService;
import org.TDD.services.iEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaTest {

    private Reserva reserva;
    private iUsuario mockUsuario;
    private iSala mockSala;
    private Date dataInicio;
    private Date dataFim;
    private ReservaService reservaService;
    private iEmailService emailServiceMock;

    @BeforeEach
    void setUp() {
        reserva = new Reserva();
        mockUsuario = mock(iUsuario.class);
        mockSala = mock(iSala.class);
        dataInicio = new Date();
        dataFim = new Date();
        emailServiceMock = mock(iEmailService.class); // Corrigido para o mesmo nome
        reservaService = new ReservaService(emailServiceMock);
        reset(emailServiceMock); // Limpa chamadas anteriores
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
        iSala sala = mock(iSala.class);
        when(sala.toString()).thenReturn("Bloco A, Andar 1, 101");

        iUsuario usuario = mock(iUsuario.class);
        when(usuario.getEmail()).thenReturn("test@example.com");

        iReserva reserva1 = new Reserva();
        reserva1.setSala(sala);
        reserva1.setResponsavel(usuario);
        reserva1.setDataInicio(new Date(System.currentTimeMillis()));
        reserva1.setDataFim(new Date(System.currentTimeMillis() + 100000));

        iReserva reserva2 = new Reserva();
        reserva2.setSala(sala);
        reserva2.setResponsavel(usuario);
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
    void deveCancelarReserva() {
        iSala sala = mock(iSala.class);
        when(sala.toString()).thenReturn("Bloco A, Andar 1, 101");

        iUsuario usuario = mock(iUsuario.class);
        when(usuario.getEmail()).thenReturn("test@example.com");

        iReserva reserva = new Reserva();
        reserva.setSala(sala);
        reserva.setResponsavel(usuario);
        reserva.setDataInicio(new Date(System.currentTimeMillis()));
        reserva.setDataFim(new Date(System.currentTimeMillis() + 100000));

        reservaService.cadastrar(reserva);

        reservaService.cancelar(reserva);

        assertFalse(reservaService.getReservas().contains(reserva));

        ArgumentCaptor<String> destinatarioCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> assuntoCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> corpoCaptor = ArgumentCaptor.forClass(String.class);

        verify(emailServiceMock, times(2)).enviarEmail(destinatarioCaptor.capture(), assuntoCaptor.capture(), corpoCaptor.capture());

        assertEquals("test@example.com", destinatarioCaptor.getValue());
        assertEquals("Cancelamento de Reserva", assuntoCaptor.getValue());
        assertTrue(corpoCaptor.getValue().contains("Sua reserva para a sala Bloco A, Andar 1, 101 foi cancelada."));
    }

    @Test
    void deveEnviarEmailAoCadastrarReserva() {
        iUsuario usuario = mock(iUsuario.class);
        when(usuario.getEmail()).thenReturn("test@example.com");

        iSala sala = mock(iSala.class);
        when(sala.toString()).thenReturn("Bloco A, Andar 1, 101");

        iReserva reserva = new Reserva();
        reserva.setResponsavel(usuario);
        reserva.setSala(sala);
        reserva.setDataInicio(new Date(System.currentTimeMillis()));
        reserva.setDataFim(new Date(System.currentTimeMillis() + 100000));

        reservaService.cadastrar(reserva);

        ArgumentCaptor<String> destinatarioCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> assuntoCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> corpoCaptor = ArgumentCaptor.forClass(String.class);

        verify(emailServiceMock).enviarEmail(destinatarioCaptor.capture(), assuntoCaptor.capture(), corpoCaptor.capture());

        assertEquals("test@example.com", destinatarioCaptor.getValue());
        assertEquals("Confirmação de Reserva", assuntoCaptor.getValue());
        assertTrue(corpoCaptor.getValue().contains("Sua reserva para a sala Bloco A, Andar 1, 101 foi confirmada."));
    }

    @Test
    void naoDeveLancarExcecaoSeNaoHouverConflitoDeHorario() {
        iSala sala = mock(iSala.class);
        when(sala.toString()).thenReturn("Bloco A, Andar 1, 101");

        iUsuario usuario = mock(iUsuario.class);
        when(usuario.getEmail()).thenReturn("test@example.com");

        iReserva reserva1 = new Reserva();
        reserva1.setSala(sala);
        reserva1.setResponsavel(usuario);
        reserva1.setDataInicio(new Date(System.currentTimeMillis()));
        reserva1.setDataFim(new Date(System.currentTimeMillis() + 100000));

        iReserva reserva2 = new Reserva();
        reserva2.setSala(sala);
        reserva2.setResponsavel(usuario);
        reserva2.setDataInicio(new Date(System.currentTimeMillis() + 200000));
        reserva2.setDataFim(new Date(System.currentTimeMillis() + 300000));

        reservaService.cadastrar(reserva1);

        assertDoesNotThrow(() -> reservaService.cadastrar(reserva2));
    }

    @Test
    void deveBuscarReservasPorIntervalo() {
        // Configura as datas
        Date agora = new Date();
        Date inicio = new Date(agora.getTime() - 3600000); // 1 hora atrás
        Date fim = new Date(agora.getTime() + 3600000); // 1 hora à frente

        // Cria e adiciona reservas
        iUsuario usuario = mock(iUsuario.class);
        when(usuario.getEmail()).thenReturn("test@example.com");

        iReserva reserva1 = new Reserva();
        reserva1.setResponsavel(usuario);
        reserva1.setDataInicio(new Date(agora.getTime() - 1800000)); // 30 minutos atrás
        reserva1.setDataFim(new Date(agora.getTime() + 1800000)); // 30 minutos à frente
        reservaService.cadastrar(reserva1);

        iReserva reserva2 = new Reserva();
        reserva2.setResponsavel(usuario);
        reserva2.setDataInicio(new Date(agora.getTime() + 1800000)); // 30 minutos à frente
        reserva2.setDataFim(new Date(agora.getTime() + 5400000)); // 90 minutos à frente
        reservaService.cadastrar(reserva2);

        // Chama o método de busca
        List<iReserva> reservas = reservaService.buscarReservasPorIntervalo(inicio, fim);

        // Verifica o resultado
        assertTrue(reservas.contains(reserva1));
        assertTrue(reservas.contains(reserva2));
        assertEquals(2, reservas.size());
    }

}
