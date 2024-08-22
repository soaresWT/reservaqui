package org.TDD.services;

public class UsuarioService {
    private final iEmailService emailService;

    public UsuarioService(iEmailService emailService) {
        this.emailService = emailService;
    }

    public void logar(String email, String senha) {
        System.out.println("Logando usuário...");
    }

    public void recuperarSenha(String email) {
        System.out.println("Recuperando senha para o usuário...");
        String assunto = "Recuperação de Senha";
        String corpo = "Instruções para recuperação de senha...";
        enviarEmail(email, assunto, corpo);
    }

    public void cadastrar(String nome, String email, String senha) {
        System.out.println("Cadastrando usuário: " + nome);
    }

    public void enviarEmail(String emailDest, String assunto, String corpo) {
        emailService.enviarEmail(emailDest, assunto, corpo);
        System.out.println("Email enviado para: " + emailDest);
    }
}
