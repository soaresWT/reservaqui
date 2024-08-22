package org.TDD.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UsuarioServiceTest {

    @Mock
    private iEmailService emailService;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogar() {
        String email = "test@example.com";
        String senha = "password";
        String expectedToken = "ifiusbfubsiufsfsuifsnfffnsof";

        String token = usuarioService.logar(email, senha);
        assertEquals(expectedToken, token, "O token gerado não corresponde ao esperado.");
    }

    @Test
    public void testRecuperarSenha() {
        String email = "test@example.com";

        usuarioService.recuperarSenha(email);

        verify(emailService).enviarEmail(
                eq(email),
                eq("Recuperação de Senha"),
                eq("Instruções para recuperação de senha...")
        );
    }

    @Test
    public void testCadastrarSenhaValida() {
        String nome = "Nome do Usuário";
        String email = "test@example.com";
        String senha = "Senha@123";

        assertDoesNotThrow(() -> usuarioService.cadastrar(nome, email, senha));
    }

    @Test
    public void testCadastrarSenhaInvalida() {
        String nome = "Nome do Usuário";
        String email = "test@example.com";
        String senha = "senha";

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.cadastrar(nome, email, senha),
                "A senha não atende aos requisitos de segurança."
        );
        assertEquals("A senha não atende aos requisitos de segurança.", thrown.getMessage());
    }

    @Test
    public void testEnviarEmail() {
        String emailDest = "test@example.com";
        String assunto = "Assunto do Email";
        String corpo = "Corpo do Email";

        usuarioService.enviarEmail(emailDest, assunto, corpo);

        verify(emailService).enviarEmail(emailDest, assunto, corpo);
    }
}
