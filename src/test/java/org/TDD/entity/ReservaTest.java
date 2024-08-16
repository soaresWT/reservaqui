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
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);

        reserva = new Reserva();
        mockUsuario = mock(iUsuario.class);
        mockSala = mock(iSala.class);
        dataInicio = new Date();
        dataFim = new Date();

        // Cria um mock do serviço de e-mail e inicializa o ReservaService com ele
        emailServiceMock = mock(iEmailService.class);
        reservaService = new ReservaService(emailServiceMock);
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
        // Cria uma instância fictícia de iSala
        iSala sala = mock(iSala.class);
        when(sala.toString()).thenReturn("Bloco A, Andar 1, 101");

        // Cria uma instância fictícia de iUsuario com um e-mail
        iUsuario usuario = mock(iUsuario.class);
        when(usuario.getEmail()).thenReturn("test@example.com");

        // Cria a primeira reserva com conflito
        iReserva reserva1 = new Reserva();
        reserva1.setSala(sala);
        reserva1.setResponsavel(usuario); // Garante que o responsável está configurado
        reserva1.setDataInicio(new Date(System.currentTimeMillis()));
        reserva1.setDataFim(new Date(System.currentTimeMillis() + 100000)); // +100 segundos

        // Cria a segunda reserva que vai gerar um conflito
        iReserva reserva2 = new Reserva();
        reserva2.setSala(sala);
        reserva2.setResponsavel(usuario); // Garante que o responsável está configurado
        reserva2.setDataInicio(new Date(System.currentTimeMillis() + 50000));
        reserva2.setDataFim(new Date(System.currentTimeMillis() + 150000)); // +150 segundos

        // Cadastra a primeira reserva
        reservaService.cadastrar(reserva1);

        // Verifica se uma exceção é lançada ao tentar cadastrar a segunda reserva
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.cadastrar(reserva2);
        });

        String expectedMessage = "Conflito de horário detectado para a sala";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deveEnviarEmailAoCadastrarReserva() {
        // Cria uma instância fictícia de iUsuario com um e-mail
        iUsuario usuario = mock(iUsuario.class);
        when(usuario.getEmail()).thenReturn("test@example.com");

        // Cria uma instância fictícia de iSala
        iSala sala = mock(iSala.class);
        when(sala.toString()).thenReturn("Bloco A, Andar 1, 101");

        // Cria uma reserva fictícia
        iReserva reserva = new Reserva();
        reserva.setResponsavel(usuario);
        reserva.setSala(sala);
        reserva.setDataInicio(new Date(System.currentTimeMillis()));
        reserva.setDataFim(new Date(System.currentTimeMillis() + 100000)); // +100 segundos

        // Cadastra a reserva
        reservaService.cadastrar(reserva);

        // Verifica se o serviço de e-mail foi chamado
        ArgumentCaptor<String> destinatarioCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> assuntoCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> corpoCaptor = ArgumentCaptor.forClass(String.class);

        verify(emailServiceMock).enviarEmail(destinatarioCaptor.capture(), assuntoCaptor.capture(), corpoCaptor.capture());

        // Verifica os valores capturados
        assertEquals("test@example.com", destinatarioCaptor.getValue());
        assertEquals("Confirmação de Reserva", assuntoCaptor.getValue());
        assertTrue(corpoCaptor.getValue().contains("Sua reserva para a sala Bloco A, Andar 1, 101 foi confirmada."));
    }

    @Test
    void naoDeveLancarExcecaoSeNaoHouverConflitoDeHorario() {
        // Cria uma instância fictícia de iSala
        iSala sala = mock(iSala.class);
        when(sala.toString()).thenReturn("Bloco A, Andar 1, 101");

        // Cria uma instância fictícia de iUsuario com um e-mail
        iUsuario usuario = mock(iUsuario.class);
        when(usuario.getEmail()).thenReturn("test@example.com");

        // Cria a primeira reserva sem conflito
        iReserva reserva1 = new Reserva();
        reserva1.setSala(sala);
        reserva1.setResponsavel(usuario); // Garante que o responsável está configurado
        reserva1.setDataInicio(new Date(System.currentTimeMillis()));
        reserva1.setDataFim(new Date(System.currentTimeMillis() + 100000)); // +100 segundos

        // Cria a segunda reserva sem conflito
        iReserva reserva2 = new Reserva();
        reserva2.setSala(sala);
        reserva2.setResponsavel(usuario); // Garante que o responsável está configurado
        reserva2.setDataInicio(new Date(System.currentTimeMillis() + 200000));
        reserva2.setDataFim(new Date(System.currentTimeMillis() + 300000)); // +300 segundos

        // Cadastra a primeira reserva
        reservaService.cadastrar(reserva1);

        // Tenta cadastrar a segunda reserva e verifica que não há exceção
        assertDoesNotThrow(() -> reservaService.cadastrar(reserva2));
    }
}
