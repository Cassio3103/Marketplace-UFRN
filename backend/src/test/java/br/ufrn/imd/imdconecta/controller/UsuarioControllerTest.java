package br.ufrn.imd.imdconecta.controller;

import br.ufrn.imd.imdconecta.entity.Usuario;
import br.ufrn.imd.imdconecta.exception.EmailJaCadastradoException;
import br.ufrn.imd.imdconecta.exception.GlobalExceptionHandler;
import br.ufrn.imd.imdconecta.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new UsuarioController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void deveCadastrarUsuarioComEmailInstitucional() throws Exception {
        Usuario salvo = new Usuario("Aluno Teste", "aluno@ufrn.edu.br", "hash");
        when(service.cadastrar("Aluno Teste", "aluno@ufrn.edu.br", "senha123")).thenReturn(salvo);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"Aluno Teste","email":"aluno@ufrn.edu.br","senha":"senha123"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("aluno@ufrn.edu.br"))
                .andExpect(jsonPath("$.senha").doesNotExist());
    }

    @Test
    void deveRejeitarEmailForaDoDominioComMensagemClara() throws Exception {
        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"Aluno Teste","email":"aluno@gmail.com","senha":"senha123"}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(containsString("@ufrn.edu.br")));

        verify(service, never()).cadastrar(any(), any(), any());
    }

    @Test
    void deveRetornarConflitoParaEmailJaCadastrado() throws Exception {
        when(service.cadastrar("Aluno Teste", "aluno@ufrn.edu.br", "senha123"))
                .thenThrow(new EmailJaCadastradoException("aluno@ufrn.edu.br"));

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"Aluno Teste","email":"aluno@ufrn.edu.br","senha":"senha123"}
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").exists());
    }
}