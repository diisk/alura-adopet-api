package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AtualizarTutorDto;
import br.com.alura.adopet.api.dto.CadastrarTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastrarTutorDto dto) {
        try {
            tutorService.cadastrar(dto);
            return ResponseEntity.ok("Tutor cadastrado com sucesso.");
        } catch (ValidacaoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizarTutorDto dto) {
        try {
            tutorService.atualizar(dto);
            return ResponseEntity.ok("Tutor cadastrado com sucesso.");
        } catch (ValidacaoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
