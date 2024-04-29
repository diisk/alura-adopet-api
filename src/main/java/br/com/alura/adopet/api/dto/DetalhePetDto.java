package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;

public record DetalhePetDto(
        Long id,

        TipoPet tipo,

        String nome,

        String raca,

        Integer idade,

        String cor,

        Float peso,

        Boolean adotado,

        DetalheAbrigoDto abrigo,

        DetalheAdocaoDto adocao) {
    public DetalhePetDto(Pet pet) {
        this(
                pet.getId(),
                pet.getTipo(),
                pet.getNome(),
                pet.getRaca(),
                pet.getIdade(),
                pet.getCor(),
                pet.getPeso(),
                pet.getAdotado(),
                new DetalheAbrigoDto(pet.getAbrigo()),
                new DetalheAdocaoDto(pet.getAdocao()));
    }
}
