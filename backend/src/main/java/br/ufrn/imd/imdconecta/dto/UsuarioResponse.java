package br.ufrn.imd.imdconecta.dto;

import br.ufrn.imd.imdconecta.entity.Usuario;

public record UsuarioResponse(Long Usuarioid, String nome, String email) {

    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(usuario.getUsuarioId(), usuario.getNome(), usuario.getEmail());
    }
}