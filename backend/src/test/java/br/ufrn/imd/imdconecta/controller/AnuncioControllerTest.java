package br.ufrn.imd.imdconecta.controller;

import br.ufrn.imd.imdconecta.dto.CriarAnuncioRequest;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AnuncioControllerTest {

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
    void deveCriarAnuncioParaUsuarioAutenticado() throws Exception {
        Usuario usuario = new Usuario("Aluno Teste", "aluno@ufrn.edu.br", "hash");
        Anuncio criado = new Anuncio(usuario, "Monitoria de Cálculo 1", CategoriaAnuncio.MONITORIA, "Cálculo 1",
                "Revisão de limites e derivadas", new BigDecimal("30.00"), "Seg e qua à tarde", "84999990000");
        when(service.criar(eq("aluno@ufrn.edu.br"), any(CriarAnuncioRequest.class))).thenReturn(criado);

        mockMvc.perform(post("/api/anuncios")
                        .principal(() -> "aluno@ufrn.edu.br")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"titulo":"Monitoria de Cálculo 1","categoria":"MONITORIA","disciplina":"Cálculo 1",
                                 "descricao":"Revisão de limites e derivadas","preco":30.00,
                                 "disponibilidade":"Seg e qua à tarde","contato":"84999990000"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Monitoria de Cálculo 1"))
                .andExpect(jsonPath("$.categoria").value("MONITORIA"))
                .andExpect(jsonPath("$.anunciante").value("Aluno Teste"));
    }

    @Test
    void deveRejeitarAnuncioSemTituloComMensagemClara() throws Exception {
        mockMvc.perform(post("/api/anuncios")
                        .principal(() -> "aluno@ufrn.edu.br")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"titulo":"","categoria":"MONITORIA","disciplina":"Cálculo 1",
                                 "descricao":"Revisão de limites e derivadas","preco":30.00,
                                 "disponibilidade":"Seg e qua à tarde","contato":"84999990000"}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(containsString("título")));

        verify(service, never()).criar(any(), any());
    }
}