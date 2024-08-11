package org.TDD;

import org.TDD.entity.Sala;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SalaTest {

    @Test
    public void getBloco_RetornarValorCorreto() {

        Sala sala = new Sala("Bloco 4", "Terreo", 1, true, true, 30);

        Assertions.assertEquals("Bloco 4", sala.getBloco());

    }

    @Test
    public void getProjetor_RetornarValorCorreto() {

        Sala sala = new Sala("Bloco 4", "Terreo", 1, true, true, 30);

        Assertions.assertEquals(true, sala.getProjetor());

    }
}
