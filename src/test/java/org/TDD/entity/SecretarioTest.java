package org.TDD.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.TDD.services.iEmailService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecretarioTest {

    private Secretario secretario;
    private Reserva reserva;
    private iEmailService emailService;

    @BeforeEach
    public void setUp() {
        // Mock do serviço de e-mail
        emailService = mock(iEmailService.class);

        // Criação do objeto Secretario com o mock do emailService
        secretario = new Secretario(12345, "João Silva", "joao.silva@example.com", "senha123", emailService);

        // Criação de uma reserva com status inicial como "Processando"
        reserva = new Reserva();
        reserva.setStatus(StatusReserva.PROCESSANDO);
        reserva.setResponsavel(new Usuario("Aluno", "aluno@example.com", "senha123")); // Ajuste conforme necessário
    }

    @Test
    public void deveAprovarReservaEEnviarEmail() {
        // Executar a ação
        secretario.aprovarReserva(reserva);

        // Verificar o resultado
        assertEquals(StatusReserva.APROVADA, reserva.getStatus(), "A reserva deve ser aprovada pelo secretário");

        // Verificar se o e-mail foi enviado
        verify(emailService).enviarEmail(eq("aluno@example.com"), eq("Status da Reserva"), eq("Sua reserva foi Aprovada."));
    }

    @Test
    public void deveRejeitarReservaEEnviarEmail() {
        // Executar a ação
        secretario.rejeitarReserva(reserva);

        // Verificar o resultado
        assertEquals(StatusReserva.REJEITADA, reserva.getStatus(), "A reserva deve ser rejeitada pelo secretário");

        // Verificar se o e-mail foi enviado
        verify(emailService).enviarEmail(eq("aluno@example.com"), eq("Status da Reserva"), eq("Sua reserva foi Rejeitada."));
    }
}