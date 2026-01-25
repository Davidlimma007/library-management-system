package com.davidlima.library.domain.entity.objeto;

import com.davidlima.library.domain.entity.funcao.Categoria;
import com.davidlima.library.domain.entity.funcao.Emprestimo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false, unique = true)
    private String identificadorNumerico; // código numérico único que identifica um livro ou publicação monográfica

    @Column(nullable = false)
    private Integer anoPublicacao;

    @Column(nullable = false)
    private Boolean disponivel = true;

    //book é o dono da relação
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "livro")
    private List<Emprestimo> emprestimos = new ArrayList<>();
}
