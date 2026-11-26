package br.ufrn.imd.imdconecta.service;

import br.ufrn.imd.imdconecta.dto.CriarAnuncioRequest;
import br.ufrn.imd.imdconecta.entity.Anuncio;
import br.ufrn.imd.imdconecta.entity.CategoriaAnuncio;
import br.ufrn.imd.imdconecta.entity.StatusAnuncio;
import br.ufrn.imd.imdconecta.entity.Usuario;
import br.ufrn.imd.imdconecta.repository.AnuncioRepository;
import br.ufrn.imd.imdconecta.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnuncioServiceTest {

    @Mock
    private AnuncioRepository anuncioRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AnuncioService service;

    private CriarAnuncioRequest requisicao() {
        return new CriarAnuncioRequest("Monitoria de Cálculo 1", CategoriaAnuncio.MONITORIA, "Cálculo 1",
                "Revisão de limites e derivadas", new BigDecimal("30.00"), "Seg e qua à tarde", "84999990000");
    }

    @Test
    void deveCriarAnuncioAtivoVinculadoAoAnunciante() {
        Usuario usuario = new Usuario("Aluno Teste", "aluno@ufrn.edu.br", "hash");
        when(usuarioRepository.findByEmail("aluno@ufrn.edu.br")).thenReturn(Optional.of(usuario));
        when(anuncioRepository.save(any(Anuncio.class))).thenAnswer(inv -> inv.getArgument(0));

        Anuncio criado = service.criar("aluno@ufrn.edu.br", requisicao());

        assertEquals(usuario, criado.getAnunciante());
        assertEquals("Monitoria de Cálculo 1", criado.getTitulo());
        assertEquals(StatusAnuncio.ATIVO, criado.getStatus());
        verify(anuncioRepository).save(any(Anuncio.class));
    }

    @Test
    void deveRejeitarQuandoAnuncianteNaoExiste() {
        when(usuarioRepository.findByEmail("naoexiste@ufrn.edu.br")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> service.criar("naoexiste@ufrn.edu.br", requisicao()));

        verify(anuncioRepository, never()).save(any());
    }
}