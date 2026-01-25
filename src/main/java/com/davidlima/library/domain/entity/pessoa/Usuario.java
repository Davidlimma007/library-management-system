package com.davidlima.library.domain.entity.pessoa;

import com.davidlima.library.domain.entity.funcao.Emprestimo;
import com.davidlima.library.domain.enums.Papel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING) // Evita bugs, Mudança de ordem do Enum, Dado quebrado no banco de dados
    @Column(nullable = false)
    private Papel papel;

    // ================= RELATIONSHIPS =================

    @OneToMany(mappedBy = "usuario") //Dono da relação será Loan, evita tabela intermediária desnecessária
    private List<Emprestimo> emprestimos = new ArrayList<>();
}