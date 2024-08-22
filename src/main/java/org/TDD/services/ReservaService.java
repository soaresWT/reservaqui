package org.TDD.services;

import org.TDD.interfaces.iReserva;
import org.TDD.services.iEmailService;
import org.TDD.entity.Reserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReservaService {
    private List<iReserva> reservas = new ArrayList<>();
    private iEmailService emailService;

    // Construtor que recebe iEmailService
    public ReservaService(iEmailService emailService) {
        this.emailService = emailService;
    }

    public void cadastrar(iReserva novaReserva) {
        verificarConflitos(novaReserva);
        reservas.add(novaReserva);
        enviarEmailDeConfirmacao(novaReserva);
    }


    public void cancelar(iReserva reserva) {
        reservas.remove(reserva);
        enviarEmailDeCancelamento(reserva);
    }

    private void enviarEmailDeCancelamento(iReserva reserva) {
        emailService.enviarEmail(
                reserva.getResponsavel().getEmail(),
                "Cancelamento de Reserva",
                "Sua reserva para a sala " + reserva.getSala() + " foi cancelada."
        );
    }

    public void verificarConflitos(iReserva novaReserva) {
        for (iReserva reservaExistente : reservas) {
            // Verifique se a reserva existente é do tipo Reserva
            if (reservaExistente instanceof Reserva) {
                Reserva reservaExistenteConcreta = (Reserva) reservaExistente;

                // Verifique se as salas não são null e se são iguais
                if (reservaExistenteConcreta.getSala() != null && novaReserva.getSala() != null &&
                        reservaExistenteConcreta.getSala().equals(novaReserva.getSala()) &&
                        reservasConflitam(reservaExistenteConcreta, novaReserva)) {
                    throw new IllegalArgumentException("Conflito de horário detectado para a sala " + novaReserva.getSala());
                }
            }
        }
    }
    private void enviarEmailDeConfirmacao(iReserva reserva) {
        emailService.enviarEmail(
                reserva.getResponsavel().getEmail(),
                "Confirmação de Reserva",
                "Sua reserva para a sala " + reserva.getSala() + " foi confirmada."
        );
    }
    private boolean reservasConflitam(iReserva reservaExistente, iReserva novaReserva) {
        // Certifique-se de que as datas não são null
        return reservaExistente.getDataInicio() != null &&
                reservaExistente.getDataFim() != null &&
                novaReserva.getDataInicio() != null &&
                novaReserva.getDataFim() != null &&
                novaReserva.getDataInicio().before(reservaExistente.getDataFim()) &&
                novaReserva.getDataFim().after(reservaExistente.getDataInicio());
    }

    public List<iReserva> buscarReservasPorIntervalo(Date inicio, Date fim) {
        return reservas.stream()
                .filter(reserva -> reserva.getDataInicio().before(fim) && reserva.getDataFim().after(inicio))
                .collect(Collectors.toList());
    }

    public List<iReserva> getReservas() {
        return reservas;
    }
}
