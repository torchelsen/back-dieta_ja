package com.dieta_ja.dao;

import com.dieta_ja.modelos.Pessoa;

import java.util.List;

public interface PessoaIDAO {

    void salvarPessoa(Pessoa pessoa);

    Pessoa buscarPessoaPorId(Long id);

    Pessoa buscarPessoaPorEmail(String email);

    List<Pessoa> buscarTodasPessoas();

    void atualizarPessoa(Pessoa pessoa);

    void excluirPessoa(Pessoa pessoa);

    boolean existePessoaComEmail(String email);
}