package br.com.alura.adopet.api.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;

@Component
public class EmailAbrigoCadastradoValidation implements CadastrarAbrigoValidator {

    @Autowired
    private AbrigoRepository repository;

    @Override
    public void validate(CadastrarAbrigoDto dto) {
        if (repository.existsByEmail(dto.email()))
            throw new ValidacaoException("Já existe um abrigo cadastrado com esse email.");
    }

}
