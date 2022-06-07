package com.example.crudescolahibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @Column(name = "id_disciplina", length = 100)
    private List<Disciplina> disciplinas = new ArrayList<>();
    @ManyToMany
    @Column(name = "id_aluno", length = 100)
    private List<Aluno> alunos = new ArrayList<>();
}
