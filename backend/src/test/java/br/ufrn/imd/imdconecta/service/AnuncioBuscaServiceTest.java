package br.ufrn.imd.imdconecta.service;

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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnuncioBuscaServiceTest {

    @Mock
    private AnuncioRepository anuncioRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AnuncioService service;

    private Anuncio anuncio() {
        Usuario usuario = new Usuario("Aluno Teste", "aluno@ufrn.edu.br", "hash");
        return new Anuncio(usuario, "Monitoria de Cálculo 1", CategoriaAnuncio.MONITORIA, "Cálculo 1",
                "Revisão de limites e derivadas", new BigDecimal("30.00"), "Seg e qua à tarde", "84999990000");
    }

    @Test
    void semFiltroDeveListarTodosOsAnunciosAtivos() {
        List<Anuncio> esperados = List.of(anuncio());
        when(anuncioRepository.findByStatusOrderByCriadoEmDesc(StatusAnuncio.ATIVO)).thenReturn(esperados);

        assertEquals(esperados, service.buscarAnuncio(null, null));
    }

    @Test
    void deveFiltrarPorCategoria() {
        List<Anuncio> esperados = List.of(anuncio());
        when(anuncioRepository.findByStatusAndCategoriaOrderByCriadoEmDesc(
                StatusAnuncio.ATIVO, CategoriaAnuncio.MONITORIA)).thenReturn(esperados);

        assertEquals(esperados, service.buscarAnuncio(CategoriaAnuncio.MONITORIA, null));
    }

    @Test
    void deveFiltrarPorDisciplina() {
        List<Anuncio> esperados = List.of(anuncio());
        when(anuncioRepository.findByStatusAndDisciplinaIgnoreCaseOrderByCriadoEmDesc(
                StatusAnuncio.ATIVO, "Cálculo 1")).thenReturn(esperados);

        assertEquals(esperados, service.buscarAnuncio(null, "Cálculo 1"));
    }

    @Test
    void deveFiltrarPorCategoriaEDisciplina() {
        List<Anuncio> esperados = List.of(anuncio());
        when(anuncioRepository.findByStatusAndCategoriaAndDisciplinaIgnoreCaseOrderByCriadoEmDesc(
                StatusAnuncio.ATIVO, CategoriaAnuncio.MONITORIA, "Cálculo 1")).thenReturn(esperados);

        assertEquals(esperados, service.buscarAnuncio(CategoriaAnuncio.MONITORIA, "Cálculo 1"));
    }

    @Test
    void disciplinaEmBrancoDeveSerIgnorada() {
        List<Anuncio> esperados = List.of(anuncio());
        when(anuncioRepository.findByStatusOrderByCriadoEmDesc(StatusAnuncio.ATIVO)).thenReturn(esperados);

        assertEquals(esperados, service.buscarAnuncio(null, "   "));
    }
}