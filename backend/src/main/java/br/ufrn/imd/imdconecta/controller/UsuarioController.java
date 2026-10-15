package br.ufrn.imd.imdconecta.controller;

import br.ufrn.imd.imdconecta.dto.CadastroRequest;
import br.ufrn.imd.imdconecta.dto.UsuarioResponse;
import br.ufrn.imd.imdconecta.service.UsuarioService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@RestController
@RequestMapping("api/usuarios/")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody CadastroRequest request) {
        return null;
    }

}
