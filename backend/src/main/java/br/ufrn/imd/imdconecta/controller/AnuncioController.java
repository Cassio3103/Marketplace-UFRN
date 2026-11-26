package br.ufrn.imd.imdconecta.controller;

import br.ufrn.imd.imdconecta.dto.AnuncioResponse;
import br.ufrn.imd.imdconecta.dto.CriarAnuncioRequest;
import br.ufrn.imd.imdconecta.service.AnuncioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/anuncios")
public class AnuncioController {

    public AnuncioController(AnuncioService service) {
    }

    @PostMapping
    public ResponseEntity<AnuncioResponse> criar(@RequestBody CriarAnuncioRequest request, Principal principal) {
        return null;
    }
}