package org.TDD;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * @author Franciel Soumbra
 */

public class AlunoTest {
    public AlunoTest(){}

    /**
     * @Description Testa a criação do objeto aluno
     * Este só deve ser criado caso:
     *      seu email seja @alu.ufc.br
     *      sua senha seja >= 8 caracteres e seguir o padrao definido nas regras
     */

    @Test
    public void verificaEmailAlunoisValidoTest() {
        String email = "roberto@alu.ufc.br";
        String[] emailSplit = email.split("@");
        String expected = emailSplit[0] + "@alu.ufc.br";

        assertEquals(expected, email);
    }

    /*@Test
    public void criarAlunoTeste() {
        String nome = "Roberto Carvalho";
        String email = "roberto@dj.com";
        String senha = "robertogol!";

        if(email.contains("@alu.ufc.br")) {

        }

        assertEquals();

    }*/
}
