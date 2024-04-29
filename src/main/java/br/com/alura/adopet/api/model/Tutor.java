package br.com.alura.adopet.api.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

import br.com.alura.adopet.api.dto.AtualizarTutorDto;
import br.com.alura.adopet.api.dto.CadastrarTutorDto;

@Entity
@Table(name = "tutores")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String email;

    @OneToMany(mappedBy = "tutor")
    private List<Adocao> adocoes;

    public Tutor() {
    }

    public Tutor(CadastrarTutorDto dto) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tutor tutor = (Tutor) o;
        return Objects.equals(id, tutor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public List<Adocao> getAdocoes() {
        return adocoes;
    }

    public void atualizar(AtualizarTutorDto dto) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
    }
}
