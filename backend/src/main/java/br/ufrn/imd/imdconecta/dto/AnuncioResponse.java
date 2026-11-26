package br.ufrn.imd.imdconecta.dto;

import br.ufrn.imd.imdconecta.entity.Anuncio;
import br.ufrn.imd.imdconecta.entity.CategoriaAnuncio;

import java.math.BigDecimal;

public record AnuncioResponse(
        Long anuncioId,
        String titulo,
        CategoriaAnuncio categoria,
        String disciplina,
        String descricao,
        BigDecimal preco,
        String disponibilidade,
        String contato,
        String anunciante) {

    public static AnuncioResponse from(Anuncio anuncio) {
        return new AnuncioResponse(
                anuncio.getAnuncioId(),
                anuncio.getTitulo(),
                anuncio.getCategoria(),
                anuncio.getDisciplina(),
                anuncio.getDescricao(),
                anuncio.getPreco(),
                anuncio.getDisponibilidade(),
                anuncio.getContato(),
                anuncio.getAnunciante().getNome());
    }
}