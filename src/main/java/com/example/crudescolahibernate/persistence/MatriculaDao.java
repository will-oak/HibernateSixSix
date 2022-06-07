package com.example.crudescolahibernate.persistence;

import com.example.crudescolahibernate.model.Aluno;
import com.example.crudescolahibernate.model.Matricula;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDao implements MatriculaGenericDao {

    private SessionFactory sf;

    public MatriculaDao(SessionFactory sf) {
        this.sf = sf;
    }
    @Override
    public void insere(Matricula matricula) {
        EntityManager entityManager = sf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(matricula);
        transaction.commit();
    }

    @Override
    public void remove(Matricula matricula) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("DELETE FROM matricula WHERE code_url = ?");
        EntityManager entityManager = sf.createEntityManager();
        entityManager.createNativeQuery(buffer.toString());
    }

    @Override
    public List<Aluno> lista() {
        List<Aluno> alunos = new ArrayList<Aluno>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT a.* FROM matricula m ");
        buffer.append("JOIN a.id = m.id_disciplina");
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
