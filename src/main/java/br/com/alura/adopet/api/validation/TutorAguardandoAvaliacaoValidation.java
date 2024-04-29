package br.com.alura.adopet.api.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;

@Component
public class TutorAguardandoAvaliacaoValidation implements SolicitarAdocaoValidator {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public void validate(SolicitacaoAdocaoDto dto) {
        List<Adocao> adocoes = adocaoRepository.findAll();
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());
        for (Adocao adocao : adocoes) {
            if (adocao.getTutor() == tutor && adocao.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO)
                throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
        }

    }
}
