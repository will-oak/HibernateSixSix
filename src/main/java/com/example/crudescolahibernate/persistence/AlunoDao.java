package com.example.crudescolahibernate.persistence;

import com.example.crudescolahibernate.model.Aluno;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class AlunoDao implements IObjDao<Aluno> {
    private SessionFactory sf;

    public AlunoDao(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void insere(Aluno aluno) {
        EntityManager entityManager = sf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(aluno);
        transaction.commit();
    }

    @Override
    public void modifica(Aluno aluno) {
        EntityManager entityManager = sf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(aluno);
        transaction.commit();
    }

    @Override
    public void remove(Aluno aluno) {
        EntityManager entityManager = sf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(aluno);
        transaction.commit();
    }

    @Override
    public Aluno busca(Aluno aluno) {
        EntityManager entityManager = sf.createEntityManager();
        aluno = entityManager.find(Aluno.class, aluno.getEmail());
        return aluno;
    }

    @Override
    public List<Aluno> lista() {
        List<Aluno> alunos = new ArrayList<Aluno>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM aluno");
        EntityManager entityManager = sf.createEntityManager();
        Query query = entityManager.createNativeQuery(buffer.toString());
        List<Object[]> lista = query.getResultList();
        for (Object[] obj : lista) {
            Aluno aluno = new Aluno();
            aluno.setNome(obj[0].toString());
            aluno.setEmail(obj[1].toString());
            aluno.setPosicaoVestibular((Integer) obj[2]);

            alunos.add(aluno);
        }
        return alunos;
    }
}
