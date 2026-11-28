package br.ufrn.imd.imdconecta.service;

import br.ufrn.imd.imdconecta.dto.CriarAnuncioRequest;
import br.ufrn.imd.imdconecta.entity.Anuncio;
import br.ufrn.imd.imdconecta.entity.CategoriaAnuncio;
import br.ufrn.imd.imdconecta.entity.StatusAnuncio;
import br.ufrn.imd.imdconecta.entity.Usuario;
import br.ufrn.imd.imdconecta.repository.AnuncioRepository;
import br.ufrn.imd.imdconecta.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;
    private final UsuarioRepository usuarioRepository;

    public AnuncioService(AnuncioRepository anuncioRepository, UsuarioRepository usuarioRepository) {
        this.anuncioRepository = anuncioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Anuncio criarAnuncio(String emailAnunciante, CriarAnuncioRequest request) {
        Usuario anunciante = usuarioRepository.findByEmail(emailAnunciante)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + emailAnunciante));
        Anuncio anuncio = new Anuncio(anunciante, request.titulo(), request.categoria(), request.disciplina(),
                request.descricao(), request.preco(), request.disponibilidade(), request.contato());
        return anuncioRepository.save(anuncio);
    }

    public List<Anuncio> buscarAnuncio(CategoriaAnuncio categoria, String disciplina) {
        String disciplinaFiltro = (disciplina == null || disciplina.isBlank()) ? null : disciplina.trim();

        if (categoria == null && disciplinaFiltro == null) {
            return anuncioRepository.findByStatusOrderByCriadoEmDesc(StatusAnuncio.ATIVO);
        }
        if (disciplinaFiltro == null) {
            return anuncioRepository.findByStatusAndCategoriaOrderByCriadoEmDesc(StatusAnuncio.ATIVO, categoria);
        }
        if (categoria == null) {
            return anuncioRepository.findByStatusAndDisciplinaIgnoreCaseOrderByCriadoEmDesc(
                    StatusAnuncio.ATIVO, disciplinaFiltro);
        }
        return anuncioRepository.findByStatusAndCategoriaAndDisciplinaIgnoreCaseOrderByCriadoEmDesc(
                StatusAnuncio.ATIVO, categoria, disciplinaFiltro);
    }
}