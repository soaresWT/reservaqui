package org.TDD.services;

import org.TDD.services.iEmailService;

import java.util.regex.Pattern;

public class UsuarioService {
    private final iEmailService emailService;

    public UsuarioService(iEmailService emailService) {
        this.emailService = emailService;
    }

    public String logar(String email, String senha) {
        return "ifiusbfubsiufsfsuifsnfffnsof";
    }

    public void recuperarSenha(String email) {
        String assunto = "Recuperação de Senha";
        String corpo = "Instruções para recuperação de senha...";
        enviarEmail(email, assunto, corpo);
    }

    public void cadastrar(String nome, String email, String senha) {
        if (!validarSenha(senha)) {
            throw new IllegalArgumentException("A senha não atende aos requisitos de segurança.");
        }
    }

    public void enviarEmail(String emailDest, String assunto, String corpo) {
        emailService.enviarEmail(emailDest, assunto, corpo);
    }

    private boolean validarSenha(String senha) {
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@!#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(senha).matches();
    }
}
