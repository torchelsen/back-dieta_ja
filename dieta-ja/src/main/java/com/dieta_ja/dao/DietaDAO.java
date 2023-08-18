package com.dieta_ja.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.dieta_ja.modelos.Dieta;
import com.dieta_ja.modelos.Pessoa;

import java.util.List;

public class DietaDAO implements DietaIDAO {
    private EntityManagerFactory emf;

    public DietaDAO() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public void salvarDieta(Dieta dieta) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(dieta);
        em.getTransaction().commit();
        em.close();
    }

    public Dieta buscarDietaPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Dieta dieta = em.find(Dieta.class, id);
        em.close();
        return dieta;
    }

    public List<Dieta> buscarDietaPorPessoa(Pessoa pessoa) {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT d FROM Dieta d JOIN FETCH d.alimentos WHERE d.pessoa = :pessoa";
        TypedQuery<Dieta> typedQuery = em.createQuery(query, Dieta.class);
        typedQuery.setParameter("pessoa", pessoa);
        return typedQuery.getResultList();
    }

    public void atualizarDieta(Dieta dieta) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(dieta);
        em.getTransaction().commit();
        em.close();
    }

    public void excluirDieta(Dieta dieta) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        dieta = em.merge(dieta);
        em.remove(dieta);
        em.getTransaction().commit();
        em.close();
    }

}
