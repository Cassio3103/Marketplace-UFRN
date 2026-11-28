package br.ufrn.imd.imdconecta.dto;

import br.ufrn.imd.imdconecta.entity.Anuncio;
import br.ufrn.imd.imdconecta.entity.CategoriaAnuncio;
import br.ufrn.imd.imdconecta.util.LinkContato;

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
        String linkContato,
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
                LinkContato.whatsapp(anuncio.getContato()),
                anuncio.getAnunciante().getNome());
    }
}