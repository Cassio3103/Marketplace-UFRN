package br.ufrn.imd.imdconecta.entity;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnuncioTest {

    private static Validator validator;

    @BeforeAll
    static void criarValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private Usuario anunciante() {
        return new Usuario("Aluno Teste", "aluno@ufrn.edu.br", "hash");
    }

    private Anuncio anuncio(String titulo, CategoriaAnuncio categoria, BigDecimal preco) {
        return new Anuncio(anunciante(), titulo, categoria, "Cálculo 1",
                "Revisão de limites e derivadas", preco, "Seg e qua à tarde", "84999990000");
    }

    @Test
    void naoDeveGerarViolacaoParaAnuncioCompleto() {
        Anuncio anuncio = anuncio("Monitoria de Cálculo 1", CategoriaAnuncio.MONITORIA, new BigDecimal("30.00"));

        assertTrue(validator.validate(anuncio).isEmpty());
    }

    @Test
    void deveGerarViolacaoParaTituloEmBranco() {
        Anuncio anuncio = anuncio("", CategoriaAnuncio.MONITORIA, new BigDecimal("30.00"));

        assertEquals(1, validator.validate(anuncio).size());
    }

    @Test
    void deveGerarViolacaoParaPrecoNegativo() {
        Anuncio anuncio = anuncio("Monitoria de Cálculo 1", CategoriaAnuncio.MONITORIA, new BigDecimal("-1"));

        assertEquals(1, validator.validate(anuncio).size());
    }

    @Test
    void deveAceitarPrecoZeroParaAnuncioGratuito() {
        Anuncio anuncio = anuncio("Monitoria de Cálculo 1", CategoriaAnuncio.MONITORIA, BigDecimal.ZERO);

        assertTrue(validator.validate(anuncio).isEmpty());
    }

    @Test
    void deveGerarViolacaoQuandoCategoriaNula() {
        Anuncio anuncio = anuncio("Monitoria de Cálculo 1", null, new BigDecimal("30.00"));

        assertEquals(1, validator.validate(anuncio).size());
    }
}