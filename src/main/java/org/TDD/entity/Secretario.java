package org.TDD.entity;

import org.TDD.interfaces.iReserva;
import org.TDD.interfaces.iUsuario;
import org.TDD.services.iEmailService;

public class Secretario extends Usuario {
    private int siape;
    private final iEmailService emailService;


    public Secretario(int siape, String nome, String email, String senha,  iEmailService emailService) {
            super(nome, email, senha);
            this.siape = siape;
            this.emailService = emailService;
        }


    public int getSiape() {
        return siape;
    }

    public void setSiape(int siape) {
        this.siape = siape;
    }


    public void aprovarReserva(iReserva reserva) {
        if (reserva instanceof Reserva) {
            Reserva reservaConcreta = (Reserva) reserva;
            reservaConcreta.setStatus(StatusReserva.APROVADA);
            enviarEmailDeConfirmacao(reservaConcreta, "Aprovada");
        }
    }


    public void rejeitarReserva(iReserva reserva) {
        if (reserva instanceof Reserva) {
            Reserva reservaConcreta = (Reserva) reserva;
            reservaConcreta.setStatus(StatusReserva.REJEITADA);
            enviarEmailDeConfirmacao(reservaConcreta, "Rejeitada");
        }
    }

    private void enviarEmailDeConfirmacao(Reserva reserva, String status) {
        String emailDestinatario = reserva.getResponsavel().getEmail();
        String assunto = "Status da Reserva";
        String corpo = "Sua reserva foi " + status + ".";
        emailService.enviarEmail(emailDestinatario, assunto, corpo);
    }
}
