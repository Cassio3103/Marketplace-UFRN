package br.ufrn.imd.imdconecta.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioTest {

    private static Validator validator;

    @BeforeAll
    static void criarValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void naoDeveGerarViolacaoParaUsuarioComEmailInstitucional() {
        Usuario usuario = new Usuario("Aluno Teste", "aluno@ufrn.edu.br", "senha123");

        assertTrue(validator.validate(usuario).isEmpty());
    }

    @Test
    void deveGerarViolacaoComMensagemClaraParaEmailForaDoDominio() {
        Usuario usuario = new Usuario("Aluno Teste", "aluno@gmail.com", "senha123");

        Set<ConstraintViolation<Usuario>> violacoes = validator.validate(usuario);

        assertEquals(1, violacoes.size());
        String mensagem = violacoes.iterator().next().getMessage();
        assertTrue(mensagem.contains("@ufrn.edu.br"));
    }
}