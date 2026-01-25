package com.davidlima.library.domain.entity.pessoa;

import com.davidlima.library.domain.entity.funcao.Emprestimo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    //Identifica funcionário internamente
    @Column(nullable = false, unique = true)
    private String codigoRegistro;

    @Column(nullable = false, unique = true)
    private String email;

    // ================= RELATIONSHIPS =================

    @OneToMany(mappedBy = "funcionario")
    private List<Emprestimo> emprestimos = new ArrayList<>();
}
