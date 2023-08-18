package com.dieta_ja.modelos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //private?
    private String nome;
    private double caloriasPor100g;
    private double carboidratosPor100g;
    private double proteinasPor100g;
    private double gordurasPor100g;
    private double valorAlimento;

    // Construtor vazio
    public Alimento() {

    }

    public Alimento(String nome, double caloriasPor100g, double carboidratosPor100g, double proteinasPor100g, double gordurasPor100g, double valorAlimento) {
        this.nome = nome;
        this.caloriasPor100g = caloriasPor100g;
        this.carboidratosPor100g = carboidratosPor100g;
        this.proteinasPor100g = proteinasPor100g;
        this.gordurasPor100g = gordurasPor100g;
        this.valorAlimento = valorAlimento;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getCalorias() {
        return caloriasPor100g;
    }

    public double getCarboidratos() {
        return carboidratosPor100g;
    }

    public double getProteinas() {
        return proteinasPor100g;
    }

    public double getGorduras() {
        return gordurasPor100g;
    }

    public double getValorAlimento() {
        return valorAlimento;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCalorias(double caloriasPor100g) {
        this.caloriasPor100g = caloriasPor100g;
    }

    public void setCarboidratos(double carboidratosPor100g) {
        this.carboidratosPor100g = carboidratosPor100g;
    }

    public void setProteinas(double proteinasPor100g) {
        this.proteinasPor100g = proteinasPor100g;
    }

    public void setGorduras(double gordurasPor100g) {
        this.gordurasPor100g = gordurasPor100g;
    }

    public void setValorAlimento(double valorAlimento) {
        this.valorAlimento = valorAlimento;
    }

    @Override
    public String toString() {
        return "Alimento [id=" + id + ", nome=" + nome + ", calorias=" + caloriasPor100g + ", carboidratos=" + carboidratosPor100g
                + ", proteinas=" + proteinasPor100g + ", gorduras=" + gordurasPor100g + ", valorAlimento=" + valorAlimento + "]";
    }
}
