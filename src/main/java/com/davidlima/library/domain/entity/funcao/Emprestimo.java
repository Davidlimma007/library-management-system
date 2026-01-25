package com.davidlima.library.domain.entity.funcao;

import com.davidlima.library.domain.entity.objeto.Livro;
import com.davidlima.library.domain.entity.pessoa.Funcionario;
import com.davidlima.library.domain.entity.pessoa.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "emprestimos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // ================= RELATIONSHIPS =================

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    // ================= DATES =================

    @Column(nullable = false)
    private LocalDate dataEmprestimo; //data que o empréstimo foi feito

    @Column(nullable = false)
    private LocalDate dataVencimento; //

    private LocalDate dataRetorno;

    // ================= BUSINESS RULES =================

    @Column(nullable = false)
    private Integer contagemRenovacao = 0;

    @Column(nullable = false)
    private Boolean ativo = true;

}
