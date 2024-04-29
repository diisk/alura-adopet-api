package br.com.alura.adopet.api.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.adopet.api.dto.CadastrarTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;

@Component
public class EmailTutorCadastradoValidation implements CadastrarTutorValidator {

    @Autowired
    private TutorRepository repository;

    @Override
    public void validate(CadastrarTutorDto dto) {
        if (repository.existsByEmail(dto.email()))
            throw new ValidacaoException("Já existe um tutor cadastrado com esse email.");
    }

}
