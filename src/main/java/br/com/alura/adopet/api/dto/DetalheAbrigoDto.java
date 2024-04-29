package br.com.alura.adopet.api.dto;

import java.util.List;

import br.com.alura.adopet.api.model.Abrigo;

public record DetalheAbrigoDto(String nome, String telefone, String email, List<DetalhePetDto> pets) {
    public DetalheAbrigoDto(Abrigo abrigo) {
        this(abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail(),
                abrigo.getPets().stream().map(pet -> new DetalhePetDto(pet)).toList());
    }
}
