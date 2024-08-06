package org.TDD;

import org.TDD.entity.Usuario;
import org.TDD.interfaces.iUsuario;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * @author Franciel Soumbra
 */

public class UsuarioTest {
    public UsuarioTest() {}

    /**
     * @Description Testa a criação do objeto usuario,
     */

    @Test
    public void criacaoUsuarioTest() {
        String nome = "Yuri Alberto";
        String email = "yuri9alb@gmail.com";
        String senha = "yuri1910";

        iUsuario usuario = new Usuario(nome, email, senha);

        assertEquals(nome, usuario.getNome());
    }
}