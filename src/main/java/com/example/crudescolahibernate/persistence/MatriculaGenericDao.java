package com.example.crudescolahibernate.persistence;

import com.example.crudescolahibernate.model.Aluno;
import com.example.crudescolahibernate.model.Matricula;

import java.util.List;

public interface MatriculaGenericDao {
    void insere(Matricula matricula);
    void remove(Matricula matricula);
    List<Aluno> lista();
}
