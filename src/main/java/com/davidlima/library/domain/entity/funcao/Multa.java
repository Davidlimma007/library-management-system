package com.davidlima.library.domain.entity.funcao;

import com.davidlima.library.domain.entity.pessoa.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "multas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id", nullable = false)
    private Emprestimo emprestimo;

    @Column(nullable = false)
    private BigDecimal quantidade;

    @Builder.Default
    private Boolean paga = false;

    @Column(nullable = false)
    private LocalDate criadoEm = LocalDate.now();

    @Column(nullable = false)
    private LocalDate pagaEm;

}

