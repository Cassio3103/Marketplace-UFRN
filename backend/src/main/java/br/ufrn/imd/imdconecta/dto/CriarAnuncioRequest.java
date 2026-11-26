package br.ufrn.imd.imdconecta.dto;

import br.ufrn.imd.imdconecta.entity.CategoriaAnuncio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CriarAnuncioRequest(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotNull(message = "A categoria é obrigatória")
        CategoriaAnuncio categoria,

        String disciplina,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotNull(message = "O preço é obrigatório (use 0 para gratuito)")
        @PositiveOrZero(message = "O preço não pode ser negativo")
        BigDecimal preco,

        @NotBlank(message = "A disponibilidade é obrigatória")
        String disponibilidade,

        @NotBlank(message = "O contato é obrigatório")
        String contato) {
}