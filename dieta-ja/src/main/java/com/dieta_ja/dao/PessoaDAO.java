package com.dieta_ja.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.dieta_ja.modelos.Pessoa;

import java.util.List;

public class PessoaDAO implements PessoaIDAO {
    private EntityManagerFactory emf;

    public PessoaDAO() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public void salvarPessoa(Pessoa pessoa) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pessoa);
        em.getTransaction().commit();
        em.close();
    }

    public Pessoa buscarPessoaPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa = em.find(Pessoa.class, id);
        em.close();
        return pessoa;
    }

    public Pessoa buscarPessoaPorEmail(String email) {
    EntityManager em = emf.createEntityManager();
    TypedQuery<Pessoa> query = em.createQuery("SELECT p FROM Pessoa p WHERE p.email = :email", Pessoa.class);
    query.setParameter("email", email);
    Pessoa pessoa = query.getSingleResult();
    em.close();
    return pessoa;
}

    public List<Pessoa> buscarTodasPessoas() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Pessoa p");
        List<Pessoa> pessoas = query.getResultList();
        em.close();
        return pessoas;
    }

    public void atualizarPessoa(Pessoa pessoa) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(pessoa);
        em.getTransaction().commit();
        em.close();
    }

    public void excluirPessoa(Pessoa pessoa) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        pessoa = em.merge(pessoa);
        em.remove(pessoa);
        em.getTransaction().commit();
        em.close();
    }

    public boolean existePessoaComEmail(String email) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT COUNT(p) FROM Pessoa p WHERE p.email = :email");
        query.setParameter("email", email);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}
