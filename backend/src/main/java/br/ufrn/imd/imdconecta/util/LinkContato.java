package br.ufrn.imd.imdconecta.util;

public final class LinkContato {

    private static final int TAMANHO_MAXIMO_SEM_CODIGO_DO_PAIS = 11;

    private LinkContato() {
    }

    public static String whatsapp(String contato) {
        if (contato == null) {
            return null;
        }
        String digitos = contato.replaceAll("\\D", "");
        if (digitos.isEmpty()) {
            return null;
        }
        if (digitos.length() <= TAMANHO_MAXIMO_SEM_CODIGO_DO_PAIS) {
            digitos = "55" + digitos;
        }
        return "https://wa.me/" + digitos;
    }
}