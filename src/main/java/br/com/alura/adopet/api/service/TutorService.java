package br.com.alura.adopet.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.adopet.api.dto.AtualizarTutorDto;
import br.com.alura.adopet.api.dto.CadastrarTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validation.CadastrarTutorValidator;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Autowired
    private List<CadastrarTutorValidator> cadastrarTutorValidations;

    public void cadastrar(CadastrarTutorDto dto) {

        cadastrarTutorValidations.forEach(validation -> validation.validate(dto));
        repository.save(new Tutor(dto));
    }

    public void atualizar(AtualizarTutorDto dto) {
        Tutor tutor = repository.getReferenceById(dto.id());
        tutor.atualizar(dto);
        repository.save(tutor);
    }

}
