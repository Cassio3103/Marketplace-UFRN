package br.ufrn.imd.imdconecta.controller;

import br.ufrn.imd.imdconecta.entity.Anuncio;
import br.ufrn.imd.imdconecta.entity.CategoriaAnuncio;
import br.ufrn.imd.imdconecta.entity.Usuario;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AnuncioBuscaControllerTest {

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

    private Anuncio anuncio() {
        Usuario usuario = new Usuario("Aluno Teste", "aluno@ufrn.edu.br", "hash");
        return new Anuncio(usuario, "Monitoria de Cálculo 1", CategoriaAnuncio.MONITORIA, "Cálculo 1",
                "Revisão de limites e derivadas", new BigDecimal("30.00"), "Seg e qua à tarde", "84999990000");
    }

    @Test
    void deveListarAnunciosSemFiltro() throws Exception {
        when(service.buscarAnuncio(null, null)).thenReturn(List.of(anuncio()));

        mockMvc.perform(get("/api/anuncios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Monitoria de Cálculo 1"))
                .andExpect(jsonPath("$[0].contato").value("84999990000"));
    }

    @Test
    void devePassarOsFiltrosParaOServico() throws Exception {
        when(service.buscarAnuncio(CategoriaAnuncio.MONITORIA, "Cálculo 1")).thenReturn(List.of(anuncio()));

        mockMvc.perform(get("/api/anuncios")
                        .param("categoria", "MONITORIA")
                        .param("disciplina", "Cálculo 1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoria").value("MONITORIA"));
    }

    @Test
    void deveRejeitarCategoriaInexistente() throws Exception {
        mockMvc.perform(get("/api/anuncios").param("categoria", "INEXISTENTE"))
                .andExpect(status().isBadRequest());

        verify(service, never()).buscarAnuncio(any(), any());
    }
}