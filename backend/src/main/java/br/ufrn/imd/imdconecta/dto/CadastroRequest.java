package br.ufrn.imd.imdconecta.dto;

import br.ufrn.imd.imdconecta.validation.EmailInstitucional;
import jakarta.validation.constraints.NotBlank;

public record CadastroRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @EmailInstitucional
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha) {
}