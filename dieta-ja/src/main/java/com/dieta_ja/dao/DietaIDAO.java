package com.dieta_ja.dao;

import com.dieta_ja.modelos.Dieta;
import com.dieta_ja.modelos.Pessoa;

import java.util.List;

public interface DietaIDAO {

    void salvarDieta(Dieta dieta);

    Dieta buscarDietaPorId(Long id);

    List<Dieta> buscarDietaPorPessoa(Pessoa pessoa);

    void atualizarDieta(Dieta dieta);

    void excluirDieta(Dieta dieta);
}
