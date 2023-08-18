package com.dieta_ja.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.dieta_ja.modelos.Alimento;

import java.util.List;

public class AlimentoDAO implements AlimentoIDAO {
    private EntityManagerFactory emf;

    public AlimentoDAO() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public void salvarAlimento(Alimento alimento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(alimento);
        em.getTransaction().commit();
        em.close();
    }

    public Alimento buscarAlimentoPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Alimento alimento = em.find(Alimento.class, id);
        em.close();
        return alimento;
    }

    public List<Alimento> buscarTodosAlimentos() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT a FROM Alimento a");
        List<Alimento> alimentos = query.getResultList();
        em.close();
        return alimentos;
    }

    public void atualizarAlimento(Alimento alimento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(alimento);
        em.getTransaction().commit();
        em.close();
    }

    public void excluirAlimento(Alimento alimento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        alimento = em.merge(alimento);
        em.remove(alimento);
        em.getTransaction().commit();
        em.close();
    }

}
