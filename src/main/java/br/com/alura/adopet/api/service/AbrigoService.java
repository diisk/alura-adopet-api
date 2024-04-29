package br.com.alura.adopet.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.dto.CadastrarPetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validation.CadastrarAbrigoValidator;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private List<CadastrarAbrigoValidator> cadastrarAbrigoValidators;

    public List<Abrigo> listar() {
        return repository.findAll();
    }

    public void cadastrar(CadastrarAbrigoDto dto) {
        cadastrarAbrigoValidators.forEach(validation -> validation.validate(dto));
        repository.save(new Abrigo(dto));
    }

    public List<Pet> listarPets(String idOuNomeAbrigo) {
        Optional<Abrigo> abrigoEncontrado = Optional
                .of(repository.findByIdOrName(idOuNomeAbrigo));

        if (abrigoEncontrado.isEmpty())
            throw new ValidacaoException("Abrigo não encontrado.");

        return abrigoEncontrado.get().getPets();

    }

    public void cadastrarPet(String idOuNomeAbrigo, CadastrarPetDto dto) {
        Optional<Abrigo> abrigoEncontrado = Optional
                .of(repository.findByIdOrName(idOuNomeAbrigo));

        if (abrigoEncontrado.isEmpty())
            throw new ValidacaoException("Abrigo não encontrado.");

        Abrigo abrigo = abrigoEncontrado.get();
        Pet pet = new Pet(dto);
        abrigo.adicionarPet(pet);
        repository.save(abrigo);
    }

}
