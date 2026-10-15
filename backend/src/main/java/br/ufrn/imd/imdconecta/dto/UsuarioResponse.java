package br.ufrn.imd.imdconecta.dto;

import br.ufrn.imd.imdconecta.entity.Usuario;

public record UsuarioResponse(Long id, String nome, String email) {

    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}