package org.TDD.services;

public class EmailService implements iEmailService {
    @Override
    public void enviarEmail(String destinatario, String assunto, String corpo) {
        // Lógica simulada para envio de email
        System.out.println("Enviando email para " + destinatario);
        System.out.println("Assunto: " + assunto);
        System.out.println("Corpo: " + corpo);
    }
}
