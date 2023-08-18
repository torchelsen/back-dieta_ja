package com.dieta_ja.modelos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double totalCalorias;
    private double totalCarboidratos;
    private double totalProteinas;
    private double totalGorduras;
    private double totalValor;

    @ManyToMany
    @JoinTable(
        name = "dieta_alimento",
        joinColumns = @JoinColumn(name = "dieta_id"),
        inverseJoinColumns = @JoinColumn(name = "alimento_id")
    )
    private List<Alimento> alimentos;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Dieta() {
    }

    public Dieta(String nome) {
        this.nome = nome;
        this.alimentos = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getTotalCalorias() {
        return totalCalorias;
    }

    public double getTotalCarboidratos() {
        return totalCarboidratos;
    }

    public double getTotalProteinas() {
        return totalProteinas;
    }

    public double getTotalGorduras() {
        return totalGorduras;
    }

    public double getTotalValor() {
        return totalValor;
    }

    public List<Alimento> getAlimentos() {
        return alimentos;
    }

    

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTotalCalorias(double totalCalorias) {
        this.totalCalorias = totalCalorias;
    }

    public void setTotalCarboidratos(double totalCarboidratos) {
        this.totalCarboidratos = totalCarboidratos;
    }

    public void setTotalProteinas(double totalProteinas) {
        this.totalProteinas = totalProteinas;
    }

    public void setTotalGorduras(double totalGorduras) {
        this.totalGorduras = totalGorduras;
    }

    public void setTotalValor(double totalValor) {
        this.totalValor = totalValor;
    }

    public void setAlimentos(List<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    public void adicionarAlimento(Alimento alimento) {
        alimentos.add(alimento);
        atualizarTotais(alimento);
    }

    public void removerAlimento(Alimento alimento) {
        alimentos.remove(alimento);
        atualizarTotais(alimento, -1);
    }

    private void atualizarTotais(Alimento alimento) {
        atualizarTotais(alimento, 1);
    }

    private void atualizarTotais(Alimento alimento, int multiplicador) {
        totalCalorias += alimento.getCalorias() * multiplicador;
        totalCarboidratos += alimento.getCarboidratos() * multiplicador;
        totalProteinas += alimento.getProteinas() * multiplicador;
        totalGorduras += alimento.getGorduras() * multiplicador;
        totalValor += alimento.getValorAlimento() * multiplicador;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        String output = "Dieta ID: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Total de Calorias: " + totalCalorias + "\n" +
                "Total de Carboidratos: " + totalCarboidratos + "\n" +
                "Total de Proteínas: " + totalProteinas + "\n" +
                "Total de Gorduras: " + totalGorduras + "\n" +
                "Total de Valor: " + totalValor + "\n" +
                "Alimentos:\n";

        for (Alimento alimento : alimentos) {
            output += " - Nome: " + alimento.getNome() + "\n" +
                    "   Calorias: " + alimento.getCalorias() + "\n" +
                    "   Carboidratos: " + alimento.getCarboidratos() + "\n" +
                    "   Proteínas: " + alimento.getProteinas() + "\n" +
                    "   Gorduras: " + alimento.getGorduras() + "\n" +
                    "   Valor: " + alimento.getValorAlimento() + "\n";
        }

        return output;
    }

}
