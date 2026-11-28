package br.ufrn.imd.imdconecta.controller;

import br.ufrn.imd.imdconecta.entity.Anuncio;
import br.ufrn.imd.imdconecta.entity.CategoriaAnuncio;
import br.ufrn.imd.imdconecta.entity.Usuario;
import br.ufrn.imd.imdconecta.exception.AnuncioNaoEncontradoException;
import br.ufrn.imd.imdconecta.exception.GlobalExceptionHandler;
import br.ufrn.imd.imdconecta.service.AnuncioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AnuncioDetalheControllerTest {

    @Mock
    private AnuncioService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new AnuncioController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void deveDetalharAnuncioComLinkDeContato() throws Exception {
        Usuario usuario = new Usuario("Aluno Teste", "aluno@ufrn.edu.br", "hash");
        Anuncio anuncio = new Anuncio(usuario, "Monitoria de Cálculo 1", CategoriaAnuncio.MONITORIA, "Cálculo 1",
                "Revisão de limites e derivadas", new BigDecimal("30.00"), "Seg e qua à tarde", "84999990000");
        when(service.buscarPorId(1L)).thenReturn(anuncio);

        mockMvc.perform(get("/api/anuncios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Monitoria de Cálculo 1"))
                .andExpect(jsonPath("$.linkContato").value("https://wa.me/5584999990000"));
    }

    @Test
    void deveRetornar404ParaAnuncioInexistente() throws Exception {
        when(service.buscarPorId(99L)).thenThrow(new AnuncioNaoEncontradoException("Anúncio 99 não encontrado"));

        mockMvc.perform(get("/api/anuncios/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(containsString("não encontrado")));
    }
}