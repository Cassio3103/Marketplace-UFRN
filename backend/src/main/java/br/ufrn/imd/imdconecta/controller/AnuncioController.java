package br.ufrn.imd.imdconecta.controller;

import br.ufrn.imd.imdconecta.dto.AnuncioResponse;
import br.ufrn.imd.imdconecta.dto.CriarAnuncioRequest;
import br.ufrn.imd.imdconecta.entity.Anuncio;
import br.ufrn.imd.imdconecta.service.AnuncioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/anuncios")
public class AnuncioController {

    private final AnuncioService service;

    public AnuncioController(AnuncioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AnuncioResponse> criar(@Valid @RequestBody CriarAnuncioRequest request,
                                                 Principal principal) {
        Anuncio anuncio = service.criar(principal.getName(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(AnuncioResponse.from(anuncio));
    }
}