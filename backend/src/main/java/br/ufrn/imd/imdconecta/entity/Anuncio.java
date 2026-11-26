package br.ufrn.imd.imdconecta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "anuncios")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long anuncioId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "anunciante_id", nullable = false)
    private Usuario anunciante;

    @NotBlank(message = "O título é obrigatório")
    @Column(nullable = false)
    private String titulo;

    @NotNull(message = "A categoria é obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CategoriaAnuncio categoria;

    @Column
    private String disciplina;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @NotNull(message = "O preço é obrigatório (use 0 para gratuito)")
    @PositiveOrZero(message = "O preço não pode ser negativo")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;

    @NotBlank(message = "A disponibilidade é obrigatória")
    @Column(nullable = false)
    private String disponibilidade;

    @NotBlank(message = "O contato é obrigatório")
    @Column(nullable = false)
    private String contato;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusAnuncio status = StatusAnuncio.ATIVO;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

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