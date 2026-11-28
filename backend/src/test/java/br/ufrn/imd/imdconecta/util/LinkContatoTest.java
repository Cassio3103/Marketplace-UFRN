package br.ufrn.imd.imdconecta.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LinkContatoTest {

    @Test
    void deveMontarLinkComNumeroSimples() {
        assertEquals("https://wa.me/5584999990000", LinkContato.whatsapp("84999990000"));
    }

    @Test
    void deveIgnorarFormatacaoDoNumero() {
        assertEquals("https://wa.me/5584999990000", LinkContato.whatsapp("(84) 99999-0000"));
    }

    @Test
    void naoDeveDuplicarCodigoDoPais() {
        assertEquals("https://wa.me/5584999990000", LinkContato.whatsapp("5584999990000"));
    }

    @Test
    void deveAcrescentarCodigoDoPaisQuandoODddEh55() {
        assertEquals("https://wa.me/5555999990000", LinkContato.whatsapp("55999990000"));
    }

    @Test
    void deveDevolverNuloQuandoNaoHaDigitos() {
        assertNull(LinkContato.whatsapp("sem numero"));
    }
}