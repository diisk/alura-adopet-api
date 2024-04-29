package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Adocao;

public record DetalheAdocaoDto() {
    public DetalheAdocaoDto(Adocao adocao) {
        this();
    }
}
