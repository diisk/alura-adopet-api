package br.com.alura.adopet.api.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;

@Component
public class PetAguardandoAvaliacaoValidation implements SolicitarAdocaoValidator {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Override
    public void validate(SolicitacaoAdocaoDto dto) {
        boolean petPossuiAdocaoEmAndamento = adocaoRepository.existsByPetIdAndStatus(
                dto.idPet(),
                StatusAdocao.AGUARDANDO_AVALIACAO);
        if (petPossuiAdocaoEmAndamento)
            throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
    }
}
