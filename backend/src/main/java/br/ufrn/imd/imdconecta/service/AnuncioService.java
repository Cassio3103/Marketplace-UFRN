package br.ufrn.imd.imdconecta.service;

import br.ufrn.imd.imdconecta.dto.CriarAnuncioRequest;
import br.ufrn.imd.imdconecta.entity.Anuncio;
import br.ufrn.imd.imdconecta.entity.Usuario;
import br.ufrn.imd.imdconecta.repository.AnuncioRepository;
import br.ufrn.imd.imdconecta.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;
    private final UsuarioRepository usuarioRepository;

    public AnuncioService(AnuncioRepository anuncioRepository, UsuarioRepository usuarioRepository) {
        this.anuncioRepository = anuncioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Anuncio criar(String emailAnunciante, CriarAnuncioRequest request) {
        Usuario anunciante = usuarioRepository.findByEmail(emailAnunciante)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + emailAnunciante));
        Anuncio anuncio = new Anuncio(anunciante, request.titulo(), request.categoria(), request.disciplina(),
                request.descricao(), request.preco(), request.disponibilidade(), request.contato());
        return anuncioRepository.save(anuncio);
    }
}