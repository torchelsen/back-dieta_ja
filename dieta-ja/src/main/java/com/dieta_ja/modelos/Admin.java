package com.dieta_ja.modelos;

import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "admin")
public class Admin extends Pessoa {
    
    public Admin() {
    }
    
    public Admin(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    @Override
    public String toString() {
        return "\nAdmin [id=" + id + ", nome=" + nome + ", email=" + email +"]";
    }
}
