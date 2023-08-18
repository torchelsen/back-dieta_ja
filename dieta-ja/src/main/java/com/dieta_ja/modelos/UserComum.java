package com.dieta_ja.modelos;

import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "userComum")
public class UserComum extends Pessoa {

    public UserComum() {
    }
    
    public UserComum(String nome, int idade, String sexo, double peso, int altura, String email, String senha) {
        super(nome, idade, sexo, peso, altura, email, senha);
    }

    @Override
    public String toString() {
        return "\nUserComum [id=" + id + ", nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + ", peso=" + peso + ", altura=" + altura + ", email=" + email + ", senha=" + senha + "]";
    }
}
