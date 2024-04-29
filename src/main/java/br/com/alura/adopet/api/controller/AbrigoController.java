package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.dto.CadastrarPetDto;
import br.com.alura.adopet.api.dto.DetalheAbrigoDto;
import br.com.alura.adopet.api.dto.DetalhePetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<DetalheAbrigoDto>> listar() {
        return ResponseEntity.ok(
                abrigoService.listar()
                        .stream().map(DetalheAbrigoDto::new)
                        .toList());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastrarAbrigoDto abrigo) {
        try {
            abrigoService.cadastrar(abrigo);
            return ResponseEntity.ok("Abrigo cadastrado com sucesso.");
        } catch (ValidacaoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{idOuNomeAbrigo}/pets")
    public ResponseEntity<List<DetalhePetDto>> listarPets(@PathVariable @NotBlank String idOuNomeAbrigo) {
        try {
            return ResponseEntity.ok(
                    abrigoService.listarPets(idOuNomeAbrigo)
                            .stream().map(DetalhePetDto::new)
                            .toList());
        } catch (ValidacaoException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idOuNomeAbrigo}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNomeAbrigo,
            @RequestBody @Valid CadastrarPetDto dto) {
        try {
            abrigoService.cadastrarPet(idOuNomeAbrigo, dto);
            return ResponseEntity.ok("Pet cadastrado com sucesso.");
        } catch (ValidacaoException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
