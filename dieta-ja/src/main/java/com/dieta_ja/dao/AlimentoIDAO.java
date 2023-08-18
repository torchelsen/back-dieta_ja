package com.dieta_ja.dao;

import com.dieta_ja.modelos.Alimento;

import java.util.List;

public interface AlimentoIDAO {

    void salvarAlimento(Alimento alimento);

    Alimento buscarAlimentoPorId(Long id);

    List<Alimento> buscarTodosAlimentos();

    void atualizarAlimento(Alimento alimento);

    void excluirAlimento(Alimento alimento);
}
