package br.ufrn.imd.imdconecta.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailInstitucionalValidatorTest {

    private EmailInstitucionalValidator validator;

    @BeforeEach
    void setUp() {
        validator = new EmailInstitucionalValidator();
    }

    @Test
    void deveAceitarEmailDoDominioInstitucional() {
        assertTrue(validator.isValid("loginSigaa@ufrn.edu.br", null));
    }

    @Test
    void deveRejeitarEmailDeOutroDominio() {
        assertFalse(validator.isValid("aluno@gmail.com", null));
    }

    @Test
    void deveRejeitarEmailNulo() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void deveRejeitarDominioParecidoComOInstitucional() {
        assertFalse(validator.isValid("aluno@ufrn.edu.br.com", null));
    }
}