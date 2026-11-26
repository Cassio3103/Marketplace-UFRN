package br.ufrn.imd.imdconecta.entity;

import java.math.BigDecimal;

public class Anuncio {

    private Usuario anunciante;
    private String titulo;
    private CategoriaAnuncio categoria;
    private String disciplina;
    private String descricao;
    private BigDecimal preco;
    private String disponibilidade;
    private String contato;

    public Anuncio(Usuario anunciante, String titulo, CategoriaAnuncio categoria, String disciplina,
                   String descricao, BigDecimal preco, String disponibilidade, String contato) {
        this.anunciante = anunciante;
        this.titulo = titulo;
        this.categoria = categoria;
        this.disciplina = disciplina;
        this.descricao = descricao;
        this.preco = preco;
        this.disponibilidade = disponibilidade;
        this.contato = contato;
    }
}