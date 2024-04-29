package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "abrigos")
public class Abrigo {

    public Abrigo() {
    }

    public Abrigo(CadastrarAbrigoDto dto) {
        this.email = dto.email();
        this.nome = dto.nome();
        this.telefone = dto.telefone();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String email;

    @OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL)
    private List<Pet> pets;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Abrigo abrigo = (Abrigo) o;
        return Objects.equals(id, abrigo.id);
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

    public void adicionarPet(Pet pet) {
        pet.setAbrigo(this);
        this.pets.add(pet);
    }

    public List<Pet> getPets() {
        return pets;
    }
}
