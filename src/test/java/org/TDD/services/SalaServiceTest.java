package org.TDD.services;

import org.TDD.interfaces.iSala;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SalaServiceTest {

    private SalaService salaService;
    private iSala salaMock;

    @BeforeEach
    void setUp() {
        salaService = new SalaService();
        salaMock = mock(iSala.class);
    }

    @Test
    void cadastrar_DeveLancarExcecaoQuandoBlocoForNulo() {
        when(salaMock.getBloco()).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            salaService.cadastrar(salaMock);
        });
    }

    @Test
    void cadastrar_DeveLancarExcecaoQuandoBlocoForVazio() {
        when(salaMock.getBloco()).thenReturn("");

        assertThrows(IllegalArgumentException.class, () -> {
            salaService.cadastrar(salaMock);
        });
    }

    @Test
    void cadastrar_DeveLancarExcecaoQuandoAndarForNulo() {
        when(salaMock.getAndar()).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            salaService.cadastrar(salaMock);
        });
    }

    @Test
    void cadastrar_DeveLancarExcecaoQuandoAndarForVazio() {
        when(salaMock.getAndar()).thenReturn("");

        assertThrows(IllegalArgumentException.class, () -> {
            salaService.cadastrar(salaMock);
        });
    }

    @Test
    void cadastrar_DeveLancarExcecaoQuandoNumeroForZero() {
        when(salaMock.getNumero()).thenReturn(0);

        assertThrows(IllegalArgumentException.class, () -> {
            salaService.cadastrar(salaMock);
        });
    }

    @Test
    void cadastrar_DeveLancarExcecaoQuandoCapacidadeForZeroOuMenor() {
        when(salaMock.getCapacidade()).thenReturn(0);

        assertThrows(IllegalArgumentException.class, () -> {
            salaService.cadastrar(salaMock);
        });

        when(salaMock.getCapacidade()).thenReturn(-1);

        assertThrows(IllegalArgumentException.class, () -> {
            salaService.cadastrar(salaMock);
        });
    }

    @Test
    void cadastrar_DeveExecutarComSucessoQuandoSalaForValida() {
        when(salaMock.getBloco()).thenReturn("A");
        when(salaMock.getAndar()).thenReturn("1");
        when(salaMock.getNumero()).thenReturn(101);
        when(salaMock.getCapacidade()).thenReturn(30);

        salaService.cadastrar(salaMock);
    }
}
