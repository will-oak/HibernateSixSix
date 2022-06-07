package com.example.crudescolahibernate.persistence;

import com.example.crudescolahibernate.model.Disciplina;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDao implements IObjDao<Disciplina>{
    private SessionFactory sf;

    public DisciplinaDao(org.hibernate.SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void insere(Disciplina disciplina) {
        EntityManager entityManager = sf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(disciplina);
        transaction.commit();
    }

    @Override
    public void modifica(Disciplina disciplina) {
        EntityManager entityManager = sf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(disciplina);
        transaction.commit();
    }

    @Override
    public void remove(Disciplina disciplina) {
        EntityManager entityManager = sf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(disciplina);
        transaction.commit();
    }

    @Override
    public Disciplina busca(Disciplina disciplina) {
        EntityManager entityManager = sf.createEntityManager();
        disciplina = entityManager.find(Disciplina.class, disciplina.getNome());
        return disciplina;
    }

    @Override
    public List<Disciplina> lista() {
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM disciplina");
        EntityManager entityManager = sf.createEntityManager();
        Query query = entityManager.createNativeQuery(buffer.toString());
        List<Object[]> lista = query.getResultList();
        for (Object[] obj : lista) {
            Disciplina disciplina = new Disciplina();
            disciplina.setNome(obj[0].toString());
            disciplina.setCargaHoraria((Integer) obj[1]);

            disciplinas.add(disciplina);
        }
        return disciplinas;
    }
}
