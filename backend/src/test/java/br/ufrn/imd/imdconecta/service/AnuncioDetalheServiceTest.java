package br.ufrn.imd.imdconecta.service;

import br.ufrn.imd.imdconecta.entity.Anuncio;
import br.ufrn.imd.imdconecta.entity.CategoriaAnuncio;
import br.ufrn.imd.imdconecta.entity.Usuario;
import br.ufrn.imd.imdconecta.exception.AnuncioNaoEncontradoException;
import br.ufrn.imd.imdconecta.repository.AnuncioRepository;
import br.ufrn.imd.imdconecta.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnuncioDetalheServiceTest {

    @Mock
    private AnuncioRepository anuncioRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AnuncioService service;

    @Test
    void deveBuscarAnuncioPorId() {
        Usuario usuario = new Usuario("Aluno Teste", "aluno@ufrn.edu.br", "hash");
        Anuncio anuncio = new Anuncio(usuario, "Monitoria de Cálculo 1", CategoriaAnuncio.MONITORIA, "Cálculo 1",
                "Revisão de limites e derivadas", new BigDecimal("30.00"), "Seg e qua à tarde", "84999990000");
        when(anuncioRepository.findById(1L)).thenReturn(Optional.of(anuncio));

        assertEquals(anuncio, service.buscarPorId(1L));
    }

    @Test
    void deveLancarExcecaoQuandoAnuncioNaoExiste() {
        when(anuncioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(AnuncioNaoEncontradoException.class, () -> service.buscarPorId(99L));
    }
}