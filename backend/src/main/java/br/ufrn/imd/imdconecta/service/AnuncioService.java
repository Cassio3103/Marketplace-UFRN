package br.ufrn.imd.imdconecta.service;

import br.ufrn.imd.imdconecta.dto.CriarAnuncioRequest;
import br.ufrn.imd.imdconecta.entity.Anuncio;
import br.ufrn.imd.imdconecta.repository.AnuncioRepository;
import br.ufrn.imd.imdconecta.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

    public AnuncioService(AnuncioRepository anuncioRepository, UsuarioRepository usuarioRepository) {
    }

    public Anuncio criar(String emailAnunciante, CriarAnuncioRequest request) {
        return null;
    }
}