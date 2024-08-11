package org.TDD;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class UsuarioTest {
    UsuarioTest() {};

    /**
     * @Description Testa a senha do usuario!
        Devem ter entre 8 e 20 caracteres.
        Devem conter pelo menos um caractere especial definido.
        Devem conter pelo menos um dígito.
        Devem conter pelo menos uma letra.
        Não podem conter outros caracteres além dos definidos.
     */

    @Test
    public void verificaSenhaUsuarioisValidoTest() {
        String senha = "rober3tosom";
        String regex = "^(?=.*[@!#$%^&*()/\\]])(?=.*[0-9])(?=.*[a-zA-Z])[@!#$%^&*()/\\]a-zA-Z0-9]{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(senha);

        assertTrue(senha.length() >= 8 && matcher.find());
    }
}
