package com.dieta_ja.modelos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


// Classe Pessoa
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nome;
    protected int idade;
    protected String sexo;
    protected double peso;
    protected int altura;
    protected String email;
    protected String senha;
    private double taxaMetabolismoBasal;
    
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<Dieta> dietas;

    //Construtor vazio
    public Pessoa(){
    }
    
    //Construtor UserComum
    public Pessoa(String nome, int idade, String sexo, double peso, int altura, String email, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.email = email;
        this.senha = senha;
        calcularTaxaMetabolismoBasal();
    }

    //Construtor Admin
    public Pessoa(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void addDieta(Dieta dieta) {
        if (dietas == null) {
            dietas = new ArrayList<>();
        }
        dietas.add(dieta);
        dieta.setPessoa(this); // setta uma referencia na entidade dieta
    }

    //getters
    public List<Dieta> getDietas() {
        return dietas;
    }
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public int getIdade() {
        return idade;
    }
    public String getSexo() {
        return sexo;
    }
    public double getPeso() {
        return peso;
    }
    public int getAltura() {
        return altura;
    }
    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    public Double getTaxaMetabolismoBasal(){
        return taxaMetabolismoBasal;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Pessoa [id=" + id + ", nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + ", peso=" + peso + ", altura=" + altura + ", email=" + email + ", senha=" + senha + "]";
    }

    public double calcularTaxaMetabolismoBasal() {
        if (sexo.equalsIgnoreCase("masculino")) {
            taxaMetabolismoBasal = 66 + (13.8 * peso) + (5 * altura) - (6.8 * idade);
        } else {
            taxaMetabolismoBasal = 65 + (9.6 * peso) + (1.5 * altura) - (4.7 * idade);
        }
        System.out.println("Taxa metabolica basal: "+taxaMetabolismoBasal);
        return taxaMetabolismoBasal;
    }

}




